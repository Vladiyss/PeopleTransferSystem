package by.vladiyss.transferSystem.building.provider.component.elevator;

import java.util.Random;

public class ElevatorInformationPart {

    private static final int MINIMUM_CAPACITY = 200;
    private static final int MINIMUM_DRIVING_BETWEEN_FLOORS_TIME = 10;
    private static final int MINIMUM_OPEN_CLOSE_DOORS_TIME = 15;

    private static final int RAW_MAXIMUM_CAPACITY = 1200;
    private static final int RAW_MAXIMUM_DRIVING_BETWEEN_FLOORS_TIME = 40;
    private static final int RAW_MAXIMUM_OPEN_CLOSE_DOORS_TIME = 50;

    private final Random random;

    public int getRandomCapacity() {
        return random.nextInt(RAW_MAXIMUM_CAPACITY) + MINIMUM_CAPACITY;
    }

    public int getRandomDrivingBetweenFloorsTime() {
        return random.nextInt(RAW_MAXIMUM_DRIVING_BETWEEN_FLOORS_TIME) + MINIMUM_DRIVING_BETWEEN_FLOORS_TIME;
    }

    public int getRandomOpenCloseDoorsTime() {
        return random.nextInt(RAW_MAXIMUM_OPEN_CLOSE_DOORS_TIME) + MINIMUM_OPEN_CLOSE_DOORS_TIME;
    }

    public ElevatorInformationPart() {
        this.random = new Random();
    }

}
