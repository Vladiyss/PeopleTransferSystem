package by.vladiyss.transferSystem.building.statistics;

import by.vladiyss.transferSystem.building.controller.ElevatorTransferTask;
import by.vladiyss.transferSystem.domain.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Slf4j
public class StatisticsManager {

    private final GeneralBuildingStatistics generalBuildingStatistics;

    public StatisticsManager(GeneralBuildingStatistics generalBuildingStatistics) {
        this.generalBuildingStatistics = generalBuildingStatistics;
    }

    private synchronized boolean defineUpTransferOption(ElevatorTransferTask elevatorTransferTask) {

        return elevatorTransferTask.getPeopleToTransfer().get(0).getOriginalFloor() <
                elevatorTransferTask.getPeopleToTransfer().get(0).getRequiredFloor();
    }

    private synchronized void writeFloorsStatistics(ElevatorTransferTask elevatorTransferTask) {

        FloorStatistics neededFloorStatistics = generalBuildingStatistics
                .getFloorsStatistics()
                .get(elevatorTransferTask.getPeopleToTransfer().get(0).getOriginalFloor());

        if (defineUpTransferOption(elevatorTransferTask)) {
            neededFloorStatistics.incrementUpTransferredPeopleNumber(elevatorTransferTask.getPeopleToTransfer().size());
            neededFloorStatistics.incrementUpTransfersNumber();
        }
        else {
            neededFloorStatistics.incrementDownTransferredPeopleNumber(elevatorTransferTask.getPeopleToTransfer().size());
            neededFloorStatistics.incrementDownTransfersNumber();
        }
    }

    private synchronized double defineTotalPeopleWeightInTransfer(ElevatorTransferTask elevatorTransferTask) {

        return elevatorTransferTask.getPeopleToTransfer().stream()
                .mapToDouble(Person::getWeight)
                .reduce(0, Double::sum);
    }

    private synchronized void writeElevatorsStatistics(ElevatorTransferTask elevatorTransferTask) {

        ElevatorStatistics neededElevatorStatistics = generalBuildingStatistics
                .getElevatorsStatistics()
                .get(elevatorTransferTask.getChosenElevator().getElevatorId());

        neededElevatorStatistics.incrementTotalTransfersNumber();
        neededElevatorStatistics.incrementGeneralTransferredPeopleNumber(elevatorTransferTask.getPeopleToTransfer().size());
        neededElevatorStatistics.incrementTotalTransferredPeopleWeight(defineTotalPeopleWeightInTransfer(elevatorTransferTask));
    }

    private synchronized List<Integer> getRequiredPeopleFloors(List<Person> peopleToTransfer) {

        return peopleToTransfer.stream()
                .map(Person::getRequiredFloor)
                .distinct()
                .collect(Collectors.toList());
    }

    private synchronized Optional<BetweenFloorsTransfersStatistics> definePresenceOfSuchAFloorCombination(
            List<BetweenFloorsTransfersStatistics> betweenFloorsTransfersStatisticsList,
            int startFloor, int endFloor) {
        return betweenFloorsTransfersStatisticsList.stream()
                .filter(i -> i.getStartFloor() == startFloor)
                .filter(i -> i.getEndFloor() == endFloor)
                .findAny();
    }

    private synchronized List<Integer> addMissingBetweenFloorsTransfersListElements(ElevatorTransferTask elevatorTransferTask,
                                                                       List<BetweenFloorsTransfersStatistics>
                                                                               betweenFloorsTransfersStatisticsList,
                                                                       int originalFloor) {

        List<Integer> requiredPeopleFloors = getRequiredPeopleFloors(elevatorTransferTask.getPeopleToTransfer());

        requiredPeopleFloors.stream()
                .forEachOrdered((i) -> {
                    Optional<BetweenFloorsTransfersStatistics> possiblePresentedElement
                            = definePresenceOfSuchAFloorCombination(betweenFloorsTransfersStatisticsList,
                            originalFloor, i);
                    if (possiblePresentedElement.isEmpty()) {
                        generalBuildingStatistics.addElementToBetweenFloorsTransfersStatisticsList(
                                new BetweenFloorsTransfersStatistics(
                                        ThreadLocalRandom.current().nextInt(),
                                        originalFloor, i));
                    }
                });
        return requiredPeopleFloors;
    }

    private synchronized void fillInBetweenFloorsTransfersStatisticsElement(
            ElevatorStatistics betweenFloorsTransfersStatisticsElement,
            ElevatorTransferTask elevatorTransferTask) {
        betweenFloorsTransfersStatisticsElement.incrementTotalTransfersNumber();
        betweenFloorsTransfersStatisticsElement.incrementGeneralTransferredPeopleNumber(
                elevatorTransferTask.getPeopleToTransfer().size());
        betweenFloorsTransfersStatisticsElement.incrementTotalTransferredPeopleWeight(
                defineTotalPeopleWeightInTransfer(elevatorTransferTask));
    }

    private synchronized void writeBetweenFloorsTransfersStatistics(ElevatorTransferTask elevatorTransferTask) {

        int originalFloor = elevatorTransferTask.getPeopleToTransfer().get(0).getOriginalFloor();
        List<BetweenFloorsTransfersStatistics> betweenFloorsTransfersStatisticsList = generalBuildingStatistics
                .getBetweenFloorsTransfersStatisticsList();

        List<Integer> requiredPeopleFloors = addMissingBetweenFloorsTransfersListElements(elevatorTransferTask,
                betweenFloorsTransfersStatisticsList, originalFloor);
        requiredPeopleFloors.stream()
                .forEachOrdered((i) -> {
                    Optional<BetweenFloorsTransfersStatistics> possiblePresentedElement
                            = definePresenceOfSuchAFloorCombination(betweenFloorsTransfersStatisticsList,
                            originalFloor, i);
                    fillInBetweenFloorsTransfersStatisticsElement(possiblePresentedElement.get(), elevatorTransferTask);
                });
    }

    public synchronized void processWritingStatisticsRequest(ElevatorTransferTask elevatorTransferTask) {

        log.debug("STATISTICS_MANAGER --- Is updating statistics --- {}", elevatorTransferTask.toString());
        writeFloorsStatistics(elevatorTransferTask);
        writeElevatorsStatistics(elevatorTransferTask);
        writeBetweenFloorsTransfersStatistics(elevatorTransferTask);
    }
}
