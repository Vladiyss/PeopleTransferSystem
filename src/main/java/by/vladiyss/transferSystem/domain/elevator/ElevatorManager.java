package by.vladiyss.transferSystem.domain.elevator;

import by.vladiyss.transferSystem.building.controller.ElevatorTransferTask;
import by.vladiyss.transferSystem.building.statistics.GeneralBuildingStatistics;
import by.vladiyss.transferSystem.building.statistics.StatisticsManager;
import by.vladiyss.transferSystem.domain.Person;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class ElevatorManager {

    private final StatisticsManager statisticsManager;

    public ElevatorManager(GeneralBuildingStatistics generalBuildingStatistics) {
        statisticsManager = new StatisticsManager(generalBuildingStatistics);
    }

    @SneakyThrows
    private synchronized void openOrCloseDoors(Elevator elevator) {
        TimeUnit.MILLISECONDS.sleep(elevator.getOpenCloseDoorsTime());
    }

    @SneakyThrows
    private synchronized void moveBetweenFloors(Elevator elevator) {
        TimeUnit.MILLISECONDS.sleep(elevator.getDrivingBetweenFloorsTime());
    }

    private synchronized void doMotion(Elevator elevator, int endFloor, boolean isUpTrip) {
        log.debug("ELEVATOR_MANAGER --- Elevator is moving from {} floor to {}, elevator --- {}",
                elevator.getCurrentFloor(), endFloor, elevator);
        int floorsNumberDifference = Math.abs(elevator.getCurrentFloor() - endFloor);
        elevator.setUpOption(isUpTrip);

        IntStream.range(0, floorsNumberDifference)
                .forEach((i) -> {
                    moveBetweenFloors(elevator);

                    if (isUpTrip) {
                        elevator.incrementCurrentFloor();
                    }
                    else {
                        elevator.decrementCurrentFloor();
                    }
                });

        log.debug("ELEVATOR_MANAGER --- Elevator moved to {} floor --- {}", endFloor, elevator);
    }

    @SneakyThrows
    private synchronized void putPeopleFromQueueToTransfer(Elevator elevator, ElevatorTransferTask transferTask) {

        log.debug("ELEVATOR_MANAGER --- Elevator is taking people from queue {} --- {}",
                transferTask.getQueueToTakePeople(), elevator);
        elevator.setUpOption(transferTask.isUpTransfer());
        openOrCloseDoors(elevator);
        IntStream.range(0, transferTask.getPeopleToTransfer().size())
                .forEachOrdered((i) -> {
                    try {
                        transferTask.getQueueToTakePeople().take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        openOrCloseDoors(elevator);
    }

    private synchronized List<Integer> getRequiredFloorsToStop(ElevatorTransferTask transferTask) {

        return transferTask.getPeopleToTransfer().stream()
                .mapToInt(Person::getRequiredFloor)
                .distinct()
                .boxed()
                .collect(Collectors.toList());
    }

    private synchronized void sortRequiredFloorsToStopListAccordingToOption(List<Integer> requiredFloorsOfPeople, boolean isUp) {
        if (!isUp) {
            requiredFloorsOfPeople.sort(Comparator.comparing(Integer::intValue).reversed());
        }
        else {
            requiredFloorsOfPeople.sort(Comparator.comparing(Integer::intValue));
        }
    }

    private synchronized void deliverPeople(List<Integer> requiredFloorsOfPeople, Elevator elevator) {
        requiredFloorsOfPeople.stream()
                .forEachOrdered((i) -> {
                    doMotion(elevator, i, elevator.isUp());
                    openOrCloseDoors(elevator);
                    openOrCloseDoors(elevator);
                });
    }

    @SneakyThrows
    public synchronized boolean processTransferTask(Elevator elevator, ElevatorTransferTask transferTask) {

        log.debug("ELEVATOR_MANAGER --- Executes transfer task {} of elevator --- {}", transferTask, elevator);
        doMotion(elevator, transferTask.getPeopleToTransfer().get(0).getOriginalFloor(),
                transferTask.getPeopleToTransfer().get(0).getOriginalFloor() - elevator.getCurrentFloor() > 0);

        putPeopleFromQueueToTransfer(elevator, transferTask);

        log.debug("ELEVATOR_MANAGER --- Is Getting required floors to move for elevator --- {}", elevator);
        List<Integer> requiredFloorsOfPeople = getRequiredFloorsToStop(transferTask);
        log.debug("ELEVATOR_MANAGER --- Got required floors {} to move for elevator --- {}", requiredFloorsOfPeople, elevator);

        sortRequiredFloorsToStopListAccordingToOption(requiredFloorsOfPeople, elevator.isUp());

        deliverPeople(requiredFloorsOfPeople, elevator);

        log.debug("ELEVATOR_MANAGER --- Is starting to write statistics by elevator --- {}", elevator);
        statisticsManager.processWritingStatisticsRequest(transferTask);

        log.debug("ELEVATOR_MANAGER --- Task {} is done by elevator --- {}", transferTask, elevator);
        return true;
    }
}
