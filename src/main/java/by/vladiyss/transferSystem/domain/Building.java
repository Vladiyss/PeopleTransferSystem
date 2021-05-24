package by.vladiyss.transferSystem.domain;

import by.vladiyss.transferSystem.building.configuration.FloorAndElevatorConfiguration;
import by.vladiyss.transferSystem.building.provider.component.elevator.ElevatorInformationPart;
import by.vladiyss.transferSystem.building.provider.component.elevator.ElevatorProvider;
import by.vladiyss.transferSystem.building.provider.component.floor.FloorProvider;
import by.vladiyss.transferSystem.domain.elevator.Elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Building {

    private final List<Floor> floors;
    private final List<Elevator> elevators;
    private final FloorAndElevatorConfiguration floorAndElevatorConfiguration;

    public List<Floor> getFloors() {
        return List.copyOf(floors);
    }

    public List<Elevator> getElevators() {
        return List.copyOf(elevators);
    }

    public Building(List<Floor> floors, List<Elevator> elevators) {
        floorAndElevatorConfiguration = new FloorAndElevatorConfiguration();
        this.floors = new ArrayList<>(generateFloorsForBuilding(floorAndElevatorConfiguration.getFloorsNumber()));
        this.elevators = new ArrayList<>(generateElevatorsForBuilding(floorAndElevatorConfiguration.getElevatorsNumber()));
    }

    private List<Floor> generateFloorsForBuilding(int floorsNumber) {
        FloorProvider floorProvider = new FloorProvider(floorsNumber);
        return floorProvider.provide();
    }

    private List<Elevator> generateElevatorsForBuilding(int elevatorsNumber) {
        ElevatorInformationPart elevatorInformationPart = new ElevatorInformationPart();
        ElevatorProvider elevatorProvider = new ElevatorProvider(elevatorsNumber, elevatorInformationPart);
        return elevatorProvider.provide();
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