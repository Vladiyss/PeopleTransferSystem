package by.vladiyss.transferSystem.building.statistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class ElevatorStatisticsTest {

    private static final int SOME_RANDOM_NUMBER = 5;
    private static final double ANOTHER_RANDOM_NUMBER = 70.0;

    private ElevatorStatistics elevatorStatistics;

    @BeforeEach
    public void before() {
        elevatorStatistics = ObjectsForStatistics.getElevatorStatisticsObject(2);
    }

    @Test
    void incrementGeneralTransferredPeopleNumber() {
        int before_GeneralTransferredPeopleNumber = elevatorStatistics.getGeneralTransferredPeopleNumber();
        elevatorStatistics.incrementGeneralTransferredPeopleNumber(SOME_RANDOM_NUMBER);
        int after_GeneralTransferredPeopleNumber = elevatorStatistics.getGeneralTransferredPeopleNumber();

        assertThat(after_GeneralTransferredPeopleNumber - before_GeneralTransferredPeopleNumber, equalTo(SOME_RANDOM_NUMBER));
    }

    @Test
    void incrementTotalTransferredPeopleWeight() {
        double before_TotalTransferredPeopleWeight = elevatorStatistics.getTotalTransferredPeopleWeight();
        elevatorStatistics.incrementTotalTransferredPeopleWeight(ANOTHER_RANDOM_NUMBER);
        double after_TotalTransferredPeopleWeight = elevatorStatistics.getTotalTransferredPeopleWeight();

        assertThat(after_TotalTransferredPeopleWeight - before_TotalTransferredPeopleWeight, equalTo(ANOTHER_RANDOM_NUMBER));
    }

    @Test
    void incrementTotalTransfersNumber() {
        int before_TotalTransfersNumber = elevatorStatistics.getTotalTransfersNumber();
        elevatorStatistics.incrementTotalTransfersNumber();
        int after_TotalTransfersNumber = elevatorStatistics.getTotalTransfersNumber();

        assertThat(after_TotalTransfersNumber - before_TotalTransfersNumber, equalTo(1));
    }
}