package by.vladiyss.transferSystem.domain.building;

import by.vladiyss.transferSystem.building.configuration.FloorAndElevatorConfiguration;
import by.vladiyss.transferSystem.building.configuration.FloorAndElevatorConfigurationObject;
import by.vladiyss.transferSystem.building.provider.component.ObjectsForProvider;
import by.vladiyss.transferSystem.building.provider.component.floor.FloorProvider;
import by.vladiyss.transferSystem.domain.Building;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    private Building building;

    @BeforeEach
    public void before() {
        building = new Building();
    }

    @Test
    void getFloors() {
        assertThat(building.getFloors().size(), greaterThan(0));
        building.getFloors().stream()
                .forEach((i) -> assertThat(i, notNullValue()));
    }

    @Test
    void getElevators() {
        assertThat(building.getElevators().size(), greaterThan(0));
        building.getElevators().stream()
                .forEach((i) -> assertThat(i, notNullValue()));
    }
}