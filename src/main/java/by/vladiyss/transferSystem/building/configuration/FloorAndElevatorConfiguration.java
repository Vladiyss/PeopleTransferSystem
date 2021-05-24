package by.vladiyss.transferSystem.building.configuration;

import java.util.Random;

public class FloorAndElevatorConfiguration {

    private static final int FLOORS_MINIMUM_NUMBER = 4;
    private static final int ELEVATORS_MINIMUM_NUMBER = 4;

    private static final int RAW_FLOORS_MAXIMUM_NUMBER = 20;
    private static final int RAW_ELEVATORS_MAXIMUM_NUMBER = 8;

    private final Random random = new Random();

    private int floorsNumber;
    private int elevatorsNumber;

    public FloorAndElevatorConfiguration() {
        generateRandomValuesForFloorsAndelevatorsNumber();
    }

    public int getFloorsNumber() {
        return floorsNumber;
    }

    public int getElevatorsNumber() {
        return elevatorsNumber;
    }

    private void generateRandomValuesForFloorsAndelevatorsNumber() {
        floorsNumber = random.nextInt(RAW_FLOORS_MAXIMUM_NUMBER) + FLOORS_MINIMUM_NUMBER;
        elevatorsNumber = random.nextInt(RAW_ELEVATORS_MAXIMUM_NUMBER) + ELEVATORS_MINIMUM_NUMBER;
    }

    @Override
    public String toString() {
        return "FloorAndElevatorConfiguration : " +
                "floorsNumber=" + floorsNumber +
                ", elevatorsNumber=" + elevatorsNumber + '}';
    }
}
