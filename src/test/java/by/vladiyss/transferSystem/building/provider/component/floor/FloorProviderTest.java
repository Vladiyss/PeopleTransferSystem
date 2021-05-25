package by.vladiyss.transferSystem.building.provider.component.floor;

import by.vladiyss.transferSystem.building.configuration.FloorAndElevatorConfiguration;
import by.vladiyss.transferSystem.building.configuration.FloorAndElevatorConfigurationObject;
import by.vladiyss.transferSystem.building.provider.component.ObjectsForProvider;
import by.vladiyss.transferSystem.building.provider.component.elevator.ElevatorProvider;
import by.vladiyss.transferSystem.domain.Floor;
import by.vladiyss.transferSystem.domain.elevator.Elevator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class FloorProviderTest {

    private FloorProvider floorProvider;

    @BeforeEach
    public void before() {
        FloorAndElevatorConfiguration floorAndElevatorConfiguration = FloorAndElevatorConfigurationObject
                .getFloorAndElevatorConfigurationObject();

        floorProvider = ObjectsForProvider.getFloorProviderObject(floorAndElevatorConfiguration.getFloorsNumber());
    }

    @Test
    void provide() {
        List<Floor> floors = floorProvider.provide();

        assertThat(floors, notNullValue());
        assertThat(floors.size(), equalTo(floorProvider.getFloorsNumber()));
        floors.stream()
                .forEach((i) -> assertThat(i, notNullValue()));
    }
}