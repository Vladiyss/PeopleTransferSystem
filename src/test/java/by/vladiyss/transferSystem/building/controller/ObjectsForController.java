package by.vladiyss.transferSystem.building.controller;

import by.vladiyss.transferSystem.building.configuration.FloorAndElevatorConfigurationObject;
import by.vladiyss.transferSystem.building.provider.component.ObjectsForProvider;
import by.vladiyss.transferSystem.building.provider.component.elevator.ElevatorProvider;
import by.vladiyss.transferSystem.domain.QueueMonitorObjects;
import by.vladiyss.transferSystem.domain.elevator.Elevator;
import by.vladiyss.transferSystem.domain.floor.FloorObjects;

import java.util.List;

public class ObjectsForController {

    public static ElevatorControllerManager getElevatorControllerManagerObject() {
        ElevatorProvider elevatorProvider = ObjectsForProvider.getElevatorProviderObject();
        List<Elevator> elevators = elevatorProvider.provide();
        return new ElevatorControllerManager(elevators);
    }

    public static ElevatorController getElevatorControllerObject(int id) {
        int elevatorsNumber = getElevatorControllerManagerObject().getElevators().size();
        return new ElevatorController(id, FloorObjects.getFloorObject(id),
                QueueMonitorObjects.getQueueMonitorsList(elevatorsNumber).get(id),
                getElevatorControllerManagerObject());
    }
}
