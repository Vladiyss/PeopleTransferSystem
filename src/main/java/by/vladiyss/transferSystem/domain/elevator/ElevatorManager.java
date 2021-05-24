package by.vladiyss.transferSystem.domain.elevator;

import by.vladiyss.transferSystem.building.controller.ElevatorTransferTask;
import by.vladiyss.transferSystem.domain.Person;
import lombok.SneakyThrows;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ElevatorManager {

    @SneakyThrows
    private synchronized void openOrCloseDoors(Elevator elevator) {
        TimeUnit.MILLISECONDS.sleep(elevator.getOpenCloseDoorsTime());
    }

    private synchronized void doMotion(Elevator elevator, int endFloor, boolean isUpTrip) {
        int floorsNumberDifference = Math.abs(elevator.getCurrentFloor() - endFloor);
        elevator.setUpOption(isUpTrip);

        IntStream.range(0, floorsNumberDifference)
                .forEach((i) -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(elevator.getDrivingBetweenFloorsTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (isUpTrip) {
                        elevator.incrementCurrentFloor();
                    }
                    else {
                        elevator.decrementCurrentFloor();
                    }
                });
    }

    @SneakyThrows
    private synchronized void putPeopleFromQueueToTransfer(Elevator elevator, ElevatorTransferTask transferTask) {

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

        doMotion(elevator, transferTask.getPeopleToTransfer().get(0).getOriginalFloor(),
                transferTask.getPeopleToTransfer().get(0).getOriginalFloor() - elevator.getCurrentFloor() > 0);

        putPeopleFromQueueToTransfer(elevator, transferTask);

        List<Integer> requiredFloorsOfPeople = getRequiredFloorsToStop(transferTask);

        sortRequiredFloorsToStopListAccordingToOption(requiredFloorsOfPeople, elevator.isUp());

        deliverPeople(requiredFloorsOfPeople, elevator);

        return true;
    }
}
