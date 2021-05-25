package by.vladiyss.transferSystem.building.statistics;

import by.vladiyss.transferSystem.building.configuration.FloorAndElevatorConfiguration;
import by.vladiyss.transferSystem.building.configuration.FloorAndElevatorConfigurationObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class FloorStatisticsTest {

    private static final int SOME_RANDOM_NUMBER = 10;

    private FloorStatistics floorStatistics;

    @BeforeEach
    public void before() {
        floorStatistics = ObjectsForStatistics.getFloorStatisticsObject(2);
    }

    @Test
    void incrementUpTransferredPeopleNumber() {
        int before_upTransferredPeopleNumber = floorStatistics.getUpTransferredPeopleNumber();
        floorStatistics.incrementUpTransferredPeopleNumber(SOME_RANDOM_NUMBER);
        int after_upTransferredPeopleNumber = floorStatistics.getUpTransferredPeopleNumber();

        assertThat(after_upTransferredPeopleNumber - before_upTransferredPeopleNumber, equalTo(SOME_RANDOM_NUMBER));
    }

    @Test
    void incrementDownTransferredPeopleNumber() {
        int before_downTransferredPeopleNumber = floorStatistics.getDownTransferredPeopleNumber();
        floorStatistics.incrementDownTransferredPeopleNumber(SOME_RANDOM_NUMBER);
        int after_downTransferredPeopleNumber = floorStatistics.getDownTransferredPeopleNumber();

        assertThat(after_downTransferredPeopleNumber - before_downTransferredPeopleNumber, equalTo(SOME_RANDOM_NUMBER));
    }

    @Test
    void incrementUpTransfersNumber() {
        int before_upTransfersNumber = floorStatistics.getUpTransfersNumber();
        floorStatistics.incrementUpTransfersNumber();
        int after_upTransfersNumber = floorStatistics.getUpTransfersNumber();

        assertThat(after_upTransfersNumber - before_upTransfersNumber, equalTo(1));
    }

    @Test
    void incrementDownTransfersNumber() {
        int before_downTransfersNumber = floorStatistics.getDownTransfersNumber();
        floorStatistics.incrementDownTransfersNumber();
        int after_downTransfersNumber = floorStatistics.getDownTransfersNumber();

        assertThat(after_downTransfersNumber - before_downTransfersNumber, equalTo(1));
    }
}