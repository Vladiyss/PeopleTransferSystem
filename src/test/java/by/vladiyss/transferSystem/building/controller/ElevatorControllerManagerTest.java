package by.vladiyss.transferSystem.building.controller;

import by.vladiyss.transferSystem.building.statistics.StatisticsManager;
import by.vladiyss.transferSystem.domain.Floor;
import by.vladiyss.transferSystem.domain.person.PersonObjects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ElevatorControllerManagerTest {

    @Mock
    private ElevatorControllerManager elevatorControllerManager;

    @Mock
    private ElevatorTransferTask elevatorTransferTask;

    @Mock
    private Floor floor;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void processPeopleTransferRequest_verifyValidQueue() {
        floor.addPeopleToPeopleQueue(floor.getUpPeopleQueue(), PersonObjects.getPeopleList());
        elevatorControllerManager.processPeopleTransferRequest(floor.getUpPeopleQueue(), true, floor);

        verify(elevatorControllerManager, times(1)).
                processPeopleTransferRequest(floor.getUpPeopleQueue(), true, floor);
    }

}