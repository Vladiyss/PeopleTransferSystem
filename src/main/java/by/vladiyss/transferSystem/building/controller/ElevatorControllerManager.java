package by.vladiyss.transferSystem.building.controller;

import by.vladiyss.transferSystem.domain.elevator.Elevator;
import by.vladiyss.transferSystem.domain.Floor;
import by.vladiyss.transferSystem.domain.Person;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.BlockingQueue;

import static java.util.function.Predicate.not;

@Slf4j
public class ElevatorControllerManager {

    private final List<Elevator> elevators;

    public ElevatorControllerManager(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    public List<Elevator> getElevators() {
        return List.copyOf(elevators);
    }

    private synchronized Elevator chooseTheMostSuitableElevatorToDoTransfer(Floor underControlFloor) {

        Optional<Elevator> mostSuitableElevator;
        do {
            mostSuitableElevator = elevators.stream()
                    .filter(not(Elevator::isBusy))
                    .min(Comparator.comparingInt(elevator ->
                            Math.abs(elevator.getCurrentFloor() - underControlFloor.getId())));
                    /*.min((elevator1, elevator2) -> Integer.compare(
                            Math.abs(elevator1.getCurrentFloor() - underControlFloor.getId()),
                            Math.abs(elevator2.getCurrentFloor() - underControlFloor.getId()) ));*/
        }
        while (mostSuitableElevator.isEmpty());

        return mostSuitableElevator.get();

    }

    private synchronized Double defineCommonWeightOfAllPeopleInQueue(List<Person> peopleQueue) {
        return peopleQueue.stream()
                .map(Person::getWeight)
                .reduce(0.0, Double::sum);
    }

    private synchronized int defineNumberOfPeopleToTransfer(List<Person> peopleQueue, Elevator chosenElevator) {

        int peopleListPosition = 0;
        double currentWeight = 0.0;
        while (currentWeight <= chosenElevator.getCapacity()) {
            currentWeight += peopleQueue.get(peopleListPosition).getWeight();
            peopleListPosition++;
        }

        return peopleListPosition;
    }

    private synchronized int definePossibleNumberOfPeopleToTransfer(List<Person> peopleQueue, Elevator chosenElevator) {

        final Double commonWeightOfAllPeopleInQueue = defineCommonWeightOfAllPeopleInQueue(peopleQueue);

        if (commonWeightOfAllPeopleInQueue <= chosenElevator.getCapacity()) {
            return peopleQueue.size();
        }

        return defineNumberOfPeopleToTransfer(peopleQueue, chosenElevator);
    }

    @SneakyThrows
    private synchronized void prepareTransferTask(BlockingQueue<Person> peopleQueue,
                                                  boolean isUpTransfer, Floor underControlFloor) {

        Elevator chosenElevatorToDoTransfer = chooseTheMostSuitableElevatorToDoTransfer(underControlFloor);
        log.debug("ELEVATOR_CONTROLLER_MANAGER --- Chosen elevator for task --- {}", chosenElevatorToDoTransfer);

        List<Person> peopleInQueue = new ArrayList<>(peopleQueue);
        int numberOfPeopleToTransfer = definePossibleNumberOfPeopleToTransfer(peopleInQueue, chosenElevatorToDoTransfer);
        log.debug("ELEVATOR_CONTROLLER_MANAGER --- Defined number of people({}) to transfer from list --- {}",
                numberOfPeopleToTransfer, peopleInQueue);

        List<Person> peopleToTransfer = peopleInQueue.subList(0, numberOfPeopleToTransfer);
        ElevatorTransferTask transferTask = new ElevatorTransferTask(UUID.randomUUID(),
                isUpTransfer, peopleToTransfer, peopleQueue, chosenElevatorToDoTransfer);
        chosenElevatorToDoTransfer.getTransferTasks().put(transferTask);
        chosenElevatorToDoTransfer.getTransferTasks().notify();
    }

    public synchronized void processPeopleTransferRequest(BlockingQueue<Person> peopleQueue, boolean isUpTransfer,
                                                          Floor underControlFloor) {
        if (peopleQueue.isEmpty()) {
            return;
        }

        log.debug("ELEVATOR_CONTROLLER_MANAGER --- Starts to process new people receipt");
        prepareTransferTask(peopleQueue, isUpTransfer, underControlFloor);
    }
}
