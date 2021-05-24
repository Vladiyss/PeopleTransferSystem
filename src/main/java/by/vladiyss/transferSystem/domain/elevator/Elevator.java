package by.vladiyss.transferSystem.domain.elevator;

import by.vladiyss.transferSystem.building.controller.ElevatorTransferTask;
import by.vladiyss.transferSystem.domain.Person;
import lombok.SneakyThrows;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Elevator extends Thread{
    private final int id;
    private final int capacity;
    private final int drivingBetweenFloorsTime;   //Millis
    private final int openCloseDoorsTime;         //Millis

    private int currentFloor;
    private boolean isBusy;
    private boolean isUp;

    private boolean isWorking;

    private BlockingQueue<ElevatorTransferTask> transferTasks;

    public Elevator(int id, int capacity, int drivingBetweenFloorsTime, int openCloseDoorsTime) {
        this.id = id;
        this.capacity = capacity;
        this.drivingBetweenFloorsTime = drivingBetweenFloorsTime;
        this.openCloseDoorsTime = openCloseDoorsTime;

        currentFloor = 0;
        isBusy = false;
        isUp = false;
        transferTasks = new LinkedBlockingQueue<>();
    }

    public int getElevatorId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDrivingBetweenFloorsTime() {
        return drivingBetweenFloorsTime;
    }

    public int getOpenCloseDoorsTime() {
        return openCloseDoorsTime;
    }

    public synchronized int getCurrentFloor() {
        return currentFloor;
    }

    public synchronized boolean isBusy() {
        return isBusy;
    }

    public synchronized boolean isUp() {
        return isUp;
    }

    public BlockingQueue<ElevatorTransferTask> getTransferTasks() {
        return transferTasks;
    }

    public void pauseElevator() {
        isWorking = false;
    }

    public void resumeElevator() {
        isWorking = true;
    }

    public void incrementCurrentFloor() {
        this.currentFloor++;
    }

    public void decrementCurrentFloor() {
        this.currentFloor--;
    }

    public void setUpOption(boolean option) {
        isUp = option;
    }

    @SneakyThrows
    private void openCloseDoors() {
        TimeUnit.MILLISECONDS.sleep(openCloseDoorsTime + openCloseDoorsTime);
    }

    private void achieveRequiredFloorToTakePeople(int requiredFloor) {
        int floorsNumberDifference = Math.abs(currentFloor - requiredFloor);
        boolean isDestinationFloorUpper = requiredFloor - currentFloor > 0;
        isUp = isDestinationFloorUpper;

        IntStream.range(0, floorsNumberDifference)
                .forEach((i) -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(drivingBetweenFloorsTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (isDestinationFloorUpper) {
                        currentFloor++;
                    }
                    else {
                        currentFloor--;
                    }
                });
    }

    private void doMotion(int endFloor, boolean isUpTrip) {
        int floorsNumberDifference = Math.abs(currentFloor - endFloor);

        IntStream.range(0, floorsNumberDifference)
                .forEach((i) -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(drivingBetweenFloorsTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (isUpTrip) {
                        currentFloor++;
                    }
                    else {
                        currentFloor--;
                    }
                });
    }

    @SneakyThrows
    private boolean processTransferTask(ElevatorTransferTask transferTask) {

        achieveRequiredFloorToTakePeople(transferTask.getPeopleToTransfer().get(0).getOriginalFloor());

        isUp = transferTask.isUpTransfer();
        TimeUnit.MILLISECONDS.sleep(openCloseDoorsTime);
        IntStream.range(0, transferTask.getPeopleToTransfer().size())
                .forEachOrdered((i) -> {
                    try {
                        transferTask.getQueueToTakePeople().take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        TimeUnit.MILLISECONDS.sleep(openCloseDoorsTime);

        final List<Integer> requiredFloorsOfPeople = transferTask.getPeopleToTransfer().stream()
                //.sorted(Comparator.comparing(Person::getRequiredFloor))
                .mapToInt(Person::getRequiredFloor)
                .distinct()
                .boxed()
                .collect(Collectors.toList());


        if (!isUp) {
            requiredFloorsOfPeople.sort(Comparator.comparing(Integer::intValue).reversed());
        }
        else {
            requiredFloorsOfPeople.sort(Comparator.comparing(Integer::intValue));
        }

        requiredFloorsOfPeople.stream()
        //IntStream.range(0, requiredFloorsOfPeople.size())
                .forEachOrdered((i) -> {
                    doMotion(i, isUp);
                    openCloseDoors();
                });
        
        
        return true;
    }

    @SneakyThrows
    @Override
    public void run() {
        isWorking = true;

        while (isWorking) {

            while (transferTasks.isEmpty()) {
                transferTasks.wait();
            }

            isBusy = true;
            boolean transferTaskResult = processTransferTask(transferTasks.take());
            isBusy = false;
        }

    }

    @Override
    public String toString() {
        return "Elevator : " +
                "id=" + id +
                ", capacity=" + capacity +
                ", drivingBetweenFloorsTime=" + drivingBetweenFloorsTime +
                ", openCloseDoorsTime=" + openCloseDoorsTime + '.';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elevator elevator = (Elevator) o;
        return id == elevator.id && capacity == elevator.capacity && drivingBetweenFloorsTime == elevator.drivingBetweenFloorsTime && openCloseDoorsTime == elevator.openCloseDoorsTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, capacity, drivingBetweenFloorsTime, openCloseDoorsTime);
    }
}
