package by.vladiyss.transferSystem.building.statistics;

import by.vladiyss.transferSystem.building.controller.ElevatorTransferTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatisticsManagerTest {

    @Mock
    private StatisticsManager statisticsManager;

    @Mock
    private ElevatorTransferTask elevatorTransferTask;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void processWritingStatisticsRequest_verify() {
        statisticsManager.processWritingStatisticsRequest(elevatorTransferTask);

        verify(statisticsManager, times(1)).processWritingStatisticsRequest(elevatorTransferTask);
    }
}