package by.vladiyss.transferSystem.building.provider.component.elevator;

import by.vladiyss.transferSystem.building.provider.component.ComponentProvider;
import by.vladiyss.transferSystem.building.statistics.GeneralBuildingStatistics;
import by.vladiyss.transferSystem.domain.elevator.Elevator;
import by.vladiyss.transferSystem.domain.elevator.ElevatorManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class ElevatorProvider implements ComponentProvider<Elevator> {

    private final int elevatorsNumber;
    private final ElevatorInformationPart elevatorInformationPart;
    private final ElevatorManager elevatorManager;
    private final GeneralBuildingStatistics generalBuildingStatistics;

    public ElevatorProvider(int elevatorsNumber, GeneralBuildingStatistics generalBuildingStatistics) {
        this.elevatorsNumber = elevatorsNumber;
        this.generalBuildingStatistics = generalBuildingStatistics;
        elevatorInformationPart = new ElevatorInformationPart();
        elevatorManager = new ElevatorManager(this.generalBuildingStatistics);
        log.debug("ELEVATOR_PROVIDER --- Created");
    }

    public int getElevatorsNumber() {
        return elevatorsNumber;
    }

    @Override
    public List<Elevator> provide() {
        List<Elevator> elevators;

        elevators = IntStream.range(0, elevatorsNumber)
                .mapToObj(i -> new Elevator(i,
                        elevatorInformationPart.getRandomCapacity(),
                        elevatorInformationPart.getRandomDrivingBetweenFloorsTime(),
                        elevatorInformationPart.getRandomOpenCloseDoorsTime(),
                        elevatorManager))
                .collect(Collectors.toList());

        log.debug("ELEVATOR_PROVIDER --- Generated elevators --- {}", elevators);
        return elevators;
    }


}
