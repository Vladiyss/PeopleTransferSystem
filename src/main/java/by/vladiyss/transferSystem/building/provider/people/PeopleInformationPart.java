package by.vladiyss.transferSystem.building.provider.people;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class PeopleInformationPart {

    private static final int MINIMUM_WEIGHT = 20;

    private static final int RAW_MAXIMUM_WEIGHT = 120;

    private final int originalFloor;
    private final int floorsNumber;

    public PeopleInformationPart(int originalFloor, int floorsNumber) {
        this.originalFloor = originalFloor;
        this.floorsNumber = floorsNumber;
    }

    public int getOriginalFloor() {
        return originalFloor;
    }

    public int getFloorsNumber() {
        return floorsNumber;
    }

    public UUID getPersonRandomUUID() {
        return UUID.randomUUID();
    }

    public double getPersonRandomWeight() {
        return ThreadLocalRandom.current().nextInt(RAW_MAXIMUM_WEIGHT) + MINIMUM_WEIGHT;
    }

    public int getPersonRandomRequiredFloor() {

        int randomRequiredFloor = ThreadLocalRandom.current().nextInt(0, floorsNumber);
        while (randomRequiredFloor == originalFloor) {
            randomRequiredFloor = ThreadLocalRandom.current().nextInt(0, floorsNumber);
        }
        return randomRequiredFloor;
    }


}
