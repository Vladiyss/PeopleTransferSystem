package by.vladiyss.transferSystem.building.provider.people;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class PeopleInformationPartTest {

    private PeopleInformationPart peopleInformationPart;

    @BeforeEach
    public void before() {
        peopleInformationPart = ObjectsForPeopleGeneration.getPeopleInformationPartObject(5);
    }

    @Test
    void getPersonRandomUUID() {
        assertThat(peopleInformationPart.getPersonRandomUUID(), notNullValue());
    }

    @Test
    void getPersonRandomWeight() {
        assertThat(peopleInformationPart.getPersonRandomWeight(), greaterThan(19.0));
        assertThat(peopleInformationPart.getPersonRandomWeight(), lessThan(150.0));
    }

    @Test
    void getPersonRandomRequiredFloor() {
        IntStream.range(0, 1000)
                .forEach((i) -> {
                    assertThat(peopleInformationPart.getPersonRandomRequiredFloor(),
                            not(equalTo(peopleInformationPart.getOriginalFloor())));
                    assertThat(peopleInformationPart.getPersonRandomRequiredFloor(),
                            greaterThan(-1));
                    assertThat(peopleInformationPart.getPersonRandomRequiredFloor(),
                            lessThan(peopleInformationPart.getFloorsNumber()));
                });
    }
}