package by.vladiyss.transferSystem.building.provider.people;

import by.vladiyss.transferSystem.domain.Floor;
import by.vladiyss.transferSystem.domain.floor.FloorObjects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class PeopleGenerationManagerTest {

    private PeopleGenerationManager peopleGenerationManager;

    @BeforeEach
    public void before() {
        peopleGenerationManager = ObjectsForPeopleGeneration.getPeopleGenerationManagerObject();
    }

    @Test
    void getPeopleGenerationInformation() {
        assertThat(peopleGenerationManager.getPeopleGenerationInformation(), notNullValue());
    }

    @Test
    void putPeopleToQueue() {
        Floor currentFloor = FloorObjects.getFloorObject(5);

        PeopleInformationPart currentPeopleInformationPart = ObjectsForPeopleGeneration
                .getPeopleInformationPartObject(5);
        int queueSizeBeforeAddingPeople = currentFloor.getUpPeopleQueue().size();

        peopleGenerationManager.putPeopleToQueue(currentFloor,
                currentPeopleInformationPart, currentFloor.getUpPeopleQueue());

        assertThat(currentFloor.getUpPeopleQueue().size(), is(not(queueSizeBeforeAddingPeople)));
        assertThat(currentFloor.getUpPeopleQueue().size(), greaterThan(queueSizeBeforeAddingPeople));
    }
}