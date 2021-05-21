package by.vladiyss.transferSystem.domain;

import java.util.Objects;

public class Elevator {
    private final int id;
    private final int capacity;
    private final int drivingBetweenFloorsTime;   //Millis
    private final int openCloseDoorsTime;         //Millis


    public Elevator(int id, int capacity, int drivingBetweenFloorsTime, int openCloseDoorsTime) {
        this.id = id;
        this.capacity = capacity;
        this.drivingBetweenFloorsTime = drivingBetweenFloorsTime;
        this.openCloseDoorsTime = openCloseDoorsTime;
    }

    public int getId() {
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
