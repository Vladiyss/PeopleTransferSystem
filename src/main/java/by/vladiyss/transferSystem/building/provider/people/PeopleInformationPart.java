package by.vladiyss.transferSystem.building.provider.people;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class PeopleInformationPart {

    private static final int MINIMUM_WEIGHT = 20;
    private static final int MINIMUM_DRIVING_BETWEEN_FLOORS_TIME = 10;
    private static final int MINIMUM_OPEN_CLOSE_DOORS_TIME = 15;

    private static final int RAW_MAXIMUM_WEIGHT = 120;
    private static final int RAW_MAXIMUM_DRIVING_BETWEEN_FLOORS_TIME = 40;
    private static final int RAW_MAXIMUM_OPEN_CLOSE_DOORS_TIME = 50;

    private final int originalFloor;
    private final Random random;
    private final int floorsNumber;

    public UUID getPersonRandomUUID() {
        return UUID.randomUUID();
    }

    public double getPersonRandomWeight() {
        return random.nextInt(RAW_MAXIMUM_WEIGHT) + MINIMUM_WEIGHT;
    }

    public int getPersonRandomRequiredFloor() {
        OptionalInt randomRequiredFloor = IntStream.generate(random::nextInt)
                .filter(i -> i > -1)
                .filter(i -> i < floorsNumber)
                .filter(i -> i != originalFloor)
                .findAny();
        return randomRequiredFloor.getAsInt();
    }

    public PeopleInformationPart(int originalFloor, int floorsNumber) {
        this.originalFloor = originalFloor;
        this.floorsNumber = floorsNumber;
        this.random = new Random();
    }
}
