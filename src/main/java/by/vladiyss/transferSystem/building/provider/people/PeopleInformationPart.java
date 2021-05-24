package by.vladiyss.transferSystem.building.provider.people;

import java.util.OptionalInt;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class PeopleInformationPart {

    private static final int MINIMUM_WEIGHT = 20;

    private static final int RAW_MAXIMUM_WEIGHT = 120;

    private final int originalFloor;
    private final int floorsNumber;

    public PeopleInformationPart(int originalFloor, int floorsNumber) {
        this.originalFloor = originalFloor;
        this.floorsNumber = floorsNumber;
    }

    public UUID getPersonRandomUUID() {
        return UUID.randomUUID();
    }

    public double getPersonRandomWeight() {
        return ThreadLocalRandom.current().nextInt(RAW_MAXIMUM_WEIGHT) + MINIMUM_WEIGHT;
    }

    public int getPersonRandomRequiredFloor() {
        OptionalInt randomRequiredFloor = IntStream.generate(ThreadLocalRandom.current()::nextInt)
                .filter(i -> i > -1)
                .filter(i -> i < floorsNumber)
                .filter(i -> i != originalFloor)
                .findAny();
        return randomRequiredFloor.getAsInt();
    }


}
