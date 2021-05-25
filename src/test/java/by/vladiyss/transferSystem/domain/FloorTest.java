package by.vladiyss.transferSystem.domain;

import by.vladiyss.transferSystem.domain.floor.FloorObjects;
import by.vladiyss.transferSystem.domain.person.PersonObjects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    private Floor floor;

    @BeforeEach
    public void before() {
        floor = FloorObjects.getFloorObject(2);
    }

    @Test
    void addPeopleToPeopleQueue() {
        int beforeSize_peopleUpQueue = floor.getUpPeopleQueue().size();
        floor.addPeopleToPeopleQueue(floor.getUpPeopleQueue(), PersonObjects.getPeopleList());
        int afterSize_peopleUpQueue = floor.getUpPeopleQueue().size();

        assertThat(afterSize_peopleUpQueue - beforeSize_peopleUpQueue, greaterThan(0));
    }
}