package by.vladiyss.transferSystem.building.provider.component.elevator;

import by.vladiyss.transferSystem.building.provider.component.ObjectsForProvider;
import by.vladiyss.transferSystem.building.statistics.FloorStatistics;
import by.vladiyss.transferSystem.building.statistics.ObjectsForStatistics;
import by.vladiyss.transferSystem.domain.elevator.Elevator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class ElevatorProviderTest {

    private ElevatorProvider elevatorProvider;

    @BeforeEach
    public void before() {
        elevatorProvider = ObjectsForProvider.getElevatorProviderObject();
    }

    @Test
    void provide() {
        List<Elevator> elevators = elevatorProvider.provide();

        assertThat(elevators, notNullValue());
        assertThat(elevators.size(), equalTo(elevatorProvider.getElevatorsNumber()));
        elevators.stream()
                .forEach((i) -> assertThat(i, notNullValue()));

    }
}