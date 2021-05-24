package by.vladiyss.transferSystem.building.provider.people;

import java.util.concurrent.ThreadLocalRandom;

public class PeopleGenerationInformation {
    private static final int MINIMUM_NUMBER_OF_PEOPLE_TO_GENERATE = 1;
    private static final int MINIMUM_FREQUENCY_IN_MILLIS = 350;

    private static final int RAW_MAXIMUM_NUMBER_OF_PEOPLE_TO_GENERATE = 7;
    private static final int RAW_MAXIMUM_FREQUENCY_IN_MILLIS = 1000;

    public int getRandomNumberOfPeopleToGenerate() {
        return ThreadLocalRandom.current().nextInt(
                RAW_MAXIMUM_NUMBER_OF_PEOPLE_TO_GENERATE) + MINIMUM_NUMBER_OF_PEOPLE_TO_GENERATE;
    }

    public int getRandomFrequencyOfPeopleGeneration() {
        return ThreadLocalRandom.current().nextInt(
                RAW_MAXIMUM_FREQUENCY_IN_MILLIS) + MINIMUM_FREQUENCY_IN_MILLIS;
    }

    public boolean getComingDownPeopleOption() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
