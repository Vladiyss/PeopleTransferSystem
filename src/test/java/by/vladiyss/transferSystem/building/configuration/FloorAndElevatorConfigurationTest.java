package by.vladiyss.transferSystem.building.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class FloorAndElevatorConfigurationTest {

    private FloorAndElevatorConfiguration floorAndElevatorConfigurationObject;

    @BeforeEach
    public void before() {
        floorAndElevatorConfigurationObject = FloorAndElevatorConfigurationObject.getFloorAndElevatorConfigurationObject();
    }

    @Test
    void getFloorsNumber() {
        assertThat(floorAndElevatorConfigurationObject.getFloorsNumber(), not(0));
        assertThat(floorAndElevatorConfigurationObject.getFloorsNumber(), greaterThan(0));
    }

    @Test
    void getElevatorsNumber() {
        assertThat(floorAndElevatorConfigurationObject.getElevatorsNumber(), not(0));
        assertThat(floorAndElevatorConfigurationObject.getElevatorsNumber(), greaterThan(0));
    }
}