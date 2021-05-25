package by.vladiyss.transferSystem.building.provider.people;

import by.vladiyss.transferSystem.building.configuration.FloorAndElevatorConfigurationObject;
import com.sun.jdi.BooleanValue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class PeopleGenerationInformationTest {

    private PeopleGenerationInformation peopleGenerationInformation;

    @BeforeEach
    public void before() {
        peopleGenerationInformation = ObjectsForPeopleGeneration.getPeopleGenerationInformationObject();
    }

    @Test
    void getRandomNumberOfPeopleToGenerate() {
        assertThat(peopleGenerationInformation.getRandomNumberOfPeopleToGenerate(), not(0));
        assertThat(peopleGenerationInformation.getRandomNumberOfPeopleToGenerate(), greaterThan(0));
    }

    @Test
    void getRandomFrequencyOfPeopleGeneration() {
        assertThat(peopleGenerationInformation.getRandomFrequencyOfPeopleGeneration(), not(0));
        assertThat(peopleGenerationInformation.getRandomFrequencyOfPeopleGeneration(), greaterThan(0));
    }

    @Test
    void getComingDownPeopleOption() {
        assertThat(peopleGenerationInformation.getComingDownPeopleOption(), instanceOf(boolean.class));
    }
}