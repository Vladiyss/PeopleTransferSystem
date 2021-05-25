package by.vladiyss.transferSystem.building.provider.component.elevator;

import by.vladiyss.transferSystem.building.provider.component.ObjectsForProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ElevatorInformationPartTest {

    private ElevatorInformationPart elevatorInformationPart;

    @BeforeEach
    public void before() {
        elevatorInformationPart = ObjectsForProvider.getElevatorInformationPartObject();
    }

    @Test
    void getRandomCapacity() {
        assertThat(elevatorInformationPart.getRandomCapacity(), lessThan(2000));
        assertThat(elevatorInformationPart.getRandomCapacity(), greaterThan(0));
    }

    @Test
    void getRandomDrivingBetweenFloorsTime() {
        assertThat(elevatorInformationPart.getRandomDrivingBetweenFloorsTime(), lessThan(500));
        assertThat(elevatorInformationPart.getRandomDrivingBetweenFloorsTime(), greaterThan(5));
    }

    @Test
    void getRandomOpenCloseDoorsTime() {
        assertThat(elevatorInformationPart.getRandomOpenCloseDoorsTime(), lessThan(400));
        assertThat(elevatorInformationPart.getRandomOpenCloseDoorsTime(), greaterThan(10));
    }
}