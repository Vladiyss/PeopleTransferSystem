package by.vladiyss.transferSystem.building.provider.component.elevator;

import by.vladiyss.transferSystem.building.provider.component.ComponentProvider;
import by.vladiyss.transferSystem.domain.elevator.Elevator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ElevatorProvider implements ComponentProvider<Elevator> {

    private final int elevatorsNumber;
    private final ElevatorInformationPart elevatorInformationPart;

    public ElevatorProvider(int elevatorsNumber, ElevatorInformationPart elevatorInformationPart) {
        this.elevatorsNumber = elevatorsNumber;
        this.elevatorInformationPart = elevatorInformationPart;
    }

    @Override
    public List<Elevator> provide() {
        List<Elevator> elevators;

        elevators = IntStream.range(0, elevatorsNumber)
                .mapToObj(i -> new Elevator(i,
                        elevatorInformationPart.getRandomCapacity(),
                        elevatorInformationPart.getRandomDrivingBetweenFloorsTime(),
                        elevatorInformationPart.getRandomOpenCloseDoorsTime()) )
                .collect(Collectors.toList());

        return elevators;
    }
}
