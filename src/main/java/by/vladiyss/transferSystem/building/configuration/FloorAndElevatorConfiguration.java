package by.vladiyss.transferSystem.building.configuration;

import java.util.Random;

public class FloorAndElevatorConfiguration {

    private static final int FLOORS_MINIMUM_NUMBER = 4;
    private static final int ELEVATORS_MINIMUM_NUMBER = 4;

    private static final int RAW_FLOORS_MAXIMUM_NUMBER = 30;
    private static final int RAW_ELEVATORS_MAXIMUM_NUMBER = 8;

    private final Random random = new Random();

    private final int floorsNumber;
    private final int elevatorsNumber;

    public FloorAndElevatorConfiguration() {
        this.floorsNumber = random.nextInt(RAW_FLOORS_MAXIMUM_NUMBER) + FLOORS_MINIMUM_NUMBER;
        this.elevatorsNumber = random.nextInt(RAW_ELEVATORS_MAXIMUM_NUMBER) + ELEVATORS_MINIMUM_NUMBER;
    }

    public int getFloorsNumber() {
        return floorsNumber;
    }

    public int getElevatorsNumber() {
        return elevatorsNumber;
    }

    @Override
    public String toString() {
        return "FloorAndElevatorConfiguration : " +
                "floorsNumber=" + floorsNumber +
                ", elevatorsNumber=" + elevatorsNumber + '}';
    }
}
