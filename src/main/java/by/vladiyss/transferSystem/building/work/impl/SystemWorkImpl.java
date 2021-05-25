package by.vladiyss.transferSystem.building.work.impl;

import by.vladiyss.transferSystem.building.controller.ElevatorController;
import by.vladiyss.transferSystem.building.controller.ElevatorControllerManager;
import by.vladiyss.transferSystem.building.provider.people.PeopleGenerationManager;
import by.vladiyss.transferSystem.building.provider.people.PeopleGenerator;
import by.vladiyss.transferSystem.building.statistics.GeneralBuildingStatistics;
import by.vladiyss.transferSystem.building.work.SystemWork;
import by.vladiyss.transferSystem.domain.Building;
import by.vladiyss.transferSystem.domain.Floor;
import by.vladiyss.transferSystem.domain.elevator.Elevator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class SystemWorkImpl implements SystemWork {

    private Building building;
    private GeneralBuildingStatistics generalBuildingStatistics;
    private ElevatorControllerManager elevatorControllerManager;
    private PeopleGenerationManager peopleGenerationManager;

    private List<Object> floorQueuesMonitors;
    private List<ElevatorController> elevatorControllers;
    private List<PeopleGenerator> peopleGenerators;

    private boolean isWorking;

    public static void main(String[] args) {

        SystemWorkImpl systemWork = new SystemWorkImpl();
        systemWork.work();
    }

    private List<ElevatorController> generateElevatorControllers(List<Floor> floors,
                                                                 List<Object> queuesMonitors,
                                                                 ElevatorControllerManager elevatorControllerManager) {
        return IntStream.range(0, floors.size())
                .mapToObj(i -> new ElevatorController(i, floors.get(i), queuesMonitors.get(i),
                        elevatorControllerManager))
                .collect(Collectors.toList());
    }

    private List<PeopleGenerator> generatePeopleGenerators(List<Floor> floors, List<Object> queuesMonitors,
                                                           PeopleGenerationManager peopleGenerationManager) {
        int floorsNumber = floors.size();
        return IntStream.range(0, floorsNumber)
                .mapToObj(i -> new PeopleGenerator(i, floors.get(i), floorsNumber, queuesMonitors.get(i),
                        peopleGenerationManager))
                .collect(Collectors.toList());
    }

    private List<Object> generateQueuesMonitors(int floorsNumber) {
        return IntStream.range(0, floorsNumber)
                .mapToObj(i -> new Object())
                .collect(Collectors.toList());
    }

    private void initializeSystem() {
        building = new Building();
        generalBuildingStatistics = new GeneralBuildingStatistics(building.getElevators().size(), building.getFloors().size());

        floorQueuesMonitors = generateQueuesMonitors(building.getFloors().size());

        elevatorControllerManager = new ElevatorControllerManager(building.getElevators());
        elevatorControllers = generateElevatorControllers(building.getFloors(), floorQueuesMonitors, elevatorControllerManager);

        peopleGenerationManager = new PeopleGenerationManager();
        peopleGenerators = generatePeopleGenerators(building.getFloors(), floorQueuesMonitors, peopleGenerationManager);
    }

    private void startAllThreads() {
        log.debug("TRANSFER SYSTEM --- Start of all threads");
        elevatorControllers.forEach(Thread::start);
        building.getElevators().forEach(Thread::start);
        peopleGenerators.forEach(Thread::start);
    }

    private void pauseAllThreads() {
        elevatorControllers.forEach(ElevatorController::pauseElevatorController);
        building.getElevators().forEach(Elevator::pauseElevator);
        peopleGenerators.forEach(PeopleGenerator::pausePeopleGenerator);
        log.debug("TRANSFER SYSTEM --- All threads are paused");
    }

    @SneakyThrows
    private void joinAllThreads() {
        for (ElevatorController elevatorController : elevatorControllers) {
            elevatorController.join();
        }
        for (PeopleGenerator peopleGenerator : peopleGenerators) {
            peopleGenerator.join();
        }
        for (Elevator elevator : building.getElevators()) {
            elevator.join();
        }
        log.debug("TRANSFER SYSTEM --- Joined all threads");
    }

    @SneakyThrows
    public void work() {

        isWorking = true;

        while (isWorking) {

            log.debug("TRANSFER SYSTEM --- Initialization");

            initializeSystem();
            startAllThreads();

            TimeUnit.SECONDS.sleep(60);
            log.debug("TRANSFER SYSTEM --- Statistics --- {}", building.getGeneralBuildingStatistics().toString());

            pauseAllThreads();
            TimeUnit.SECONDS.sleep(10);
            joinAllThreads();

            isWorking = false;
        }

        log.debug("TRANSFER SYSTEM --- Finishes working");
    }
}
