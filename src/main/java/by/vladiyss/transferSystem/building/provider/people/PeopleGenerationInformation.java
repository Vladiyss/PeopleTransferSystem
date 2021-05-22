package by.vladiyss.transferSystem.building.provider.people;

import java.util.Random;

public class PeopleGenerationInformation {
    private static final int MINIMUM_NUMBER_OF_PEOPLE_TO_GENERATE = 1;
    private static final int MINIMUM_FREQUENCY_IN_MILLIS = 150;

    private static final int RAW_MAXIMUM_NUMBER_OF_PEOPLE_TO_GENERATE = 7;
    private static final int RAW_MAXIMUM_FREQUENCY_IN_MILLIS = 600;

    private final Random random;

    public PeopleGenerationInformation() {
        random = new Random();
    }

    public int getRandomNumberOfPeopleToGenerate() {
        return random.nextInt(RAW_MAXIMUM_NUMBER_OF_PEOPLE_TO_GENERATE) + MINIMUM_NUMBER_OF_PEOPLE_TO_GENERATE;
    }

    public int getRandomFrequencyOfPeopleGeneration() {
        return random.nextInt(RAW_MAXIMUM_FREQUENCY_IN_MILLIS) + MINIMUM_FREQUENCY_IN_MILLIS;
    }

    public boolean getComingDownPeopleOption() {
        return random.nextBoolean();
    }
}
