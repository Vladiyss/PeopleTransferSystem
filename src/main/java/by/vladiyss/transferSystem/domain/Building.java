package by.vladiyss.transferSystem.domain;

import by.vladiyss.transferSystem.building.configuration.FloorAndElevatorConfiguration;
import by.vladiyss.transferSystem.building.provider.component.elevator.ElevatorProvider;
import by.vladiyss.transferSystem.building.provider.component.floor.FloorProvider;
import by.vladiyss.transferSystem.building.statistics.GeneralBuildingStatistics;
import by.vladiyss.transferSystem.domain.elevator.Elevator;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class Building {

    private final List<Floor> floors;
    private final List<Elevator> elevators;

    private final FloorAndElevatorConfiguration floorAndElevatorConfiguration;
    private GeneralBuildingStatistics generalBuildingStatistics;

    public List<Floor> getFloors() {
        return List.copyOf(floors);
    }

    public List<Elevator> getElevators() {
        return List.copyOf(elevators);
    }

    public FloorAndElevatorConfiguration getFloorAndElevatorConfiguration() {
        return floorAndElevatorConfiguration;
    }

    public GeneralBuildingStatistics getGeneralBuildingStatistics() {
        return generalBuildingStatistics;
    }

    public Building() {
        log.debug("BUILDING --- Creating");
        floorAndElevatorConfiguration = new FloorAndElevatorConfiguration();
        int floorsNumber = floorAndElevatorConfiguration.getFloorsNumber();
        this.floors = new ArrayList<>(generateFloorsForBuilding(floorsNumber));
        log.debug("BUILDING --- Generated {} floors --- {}", this.floors.size(), this.floors);

        this.elevators = new ArrayList<>(generateElevatorsForBuilding(floorAndElevatorConfiguration.getElevatorsNumber(),
                floorsNumber));
        log.debug("BUILDING --- Generated {} elevators --- {}", this.elevators.size(), this.elevators);
        log.debug("BUILDING --- Created");
    }

    private List<Floor> generateFloorsForBuilding(int floorsNumber) {
        FloorProvider floorProvider = new FloorProvider(floorsNumber);
        return floorProvider.provide();
    }

    private List<Elevator> generateElevatorsForBuilding(int elevatorsNumber, int floorsNumber) {
        generalBuildingStatistics = new GeneralBuildingStatistics(elevatorsNumber, floorsNumber);
        ElevatorProvider elevatorProvider = new ElevatorProvider(elevatorsNumber, generalBuildingStatistics);
        return elevatorProvider.provide();
    }

    @Override
    public String toString() {
        return "Building : " +
                "floors=" + floors +
                ", elevators=" + elevators + '.';
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