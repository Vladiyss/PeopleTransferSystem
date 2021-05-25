package by.vladiyss.transferSystem.domain.elevator;

import by.vladiyss.transferSystem.building.controller.ElevatorTransferTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ElevatorManagerTest {

    @Mock
    private ElevatorManager elevatorManager;

    @Mock
    private ElevatorTransferTask elevatorTransferTask;

    @Mock
    private Elevator elevator;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void processTransferTask() {
        when(elevatorManager.processTransferTask(elevator, elevatorTransferTask)).thenReturn(true);
        boolean actual = elevatorManager.processTransferTask(elevator, elevatorTransferTask);
        assertTrue(actual);
    }
}