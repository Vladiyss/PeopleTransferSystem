package by.vladiyss.transferSystem.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Building {
    private final List<Floor> floors;
    private final List<Elevator> elevators;

    public List<Floor> getFloors() {
        return List.copyOf(floors);
    }

    public List<Elevator> getElevators() {
        return List.copyOf(elevators);
    }

    public Building(List<Floor> floors, List<Elevator> elevators) {
        this.floors = new ArrayList<>(floors);
        this.elevators = new ArrayList<>(elevators);
    }

    @Override
    public String toString() {
        return "Building{" +
                "floors=" + floors +
                ", elevators=" + elevators +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return floors.equals(building.floors) && elevators.equals(building.elevators);
    }

    @Override
    public int hashCode() {
        return Objects.hash(floors, elevators);
    }
}