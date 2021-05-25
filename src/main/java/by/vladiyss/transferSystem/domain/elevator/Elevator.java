package by.vladiyss.transferSystem.domain.elevator;

import by.vladiyss.transferSystem.building.controller.ElevatorTransferTask;
import by.vladiyss.transferSystem.domain.Person;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Elevator extends Thread{
    private final int id;
    private final int capacity;
    private final int drivingBetweenFloorsTime;   //Millis
    private final int openCloseDoorsTime;         //Millis

    private int currentFloor;
    private boolean isBusy;
    private boolean isUp;

    private boolean isWorking;

    private final ElevatorManager elevatorManager;
    private final BlockingQueue<ElevatorTransferTask> transferTasks;

    public Elevator(int id, int capacity, int drivingBetweenFloorsTime, int openCloseDoorsTime, ElevatorManager elevatorManager) {
        this.id = id;
        this.capacity = capacity;
        this.drivingBetweenFloorsTime = drivingBetweenFloorsTime;
        this.openCloseDoorsTime = openCloseDoorsTime;

        currentFloor = 0;
        isBusy = false;
        isUp = false;
        this.elevatorManager = elevatorManager;
        transferTasks = new LinkedBlockingQueue<>();
        log.debug("ELEVATOR --- Generated --- {}", this);
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
    @Override
    public void run() {
        isWorking = true;
        TimeUnit.SECONDS.sleep(2);
        log.debug("ELEVATOR --- Starts to move --- {}", this);

        while (isWorking) {

            while (transferTasks.isEmpty()) {
                transferTasks.wait();
            }

            isBusy = true;
            boolean transferTaskResult = elevatorManager.processTransferTask(this, transferTasks.take());
            isBusy = false;
        }
        log.debug("ELEVATOR --- Stops working --- {}", this);

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
