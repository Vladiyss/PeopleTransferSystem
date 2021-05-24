package by.vladiyss.transferSystem.building.statistics;

public class BetweenFloorsTransfersStatistics extends ElevatorStatistics {

    private final int startFloor;
    private final int endFloor;

    public BetweenFloorsTransfersStatistics(int id, int startFloor, int endFloor) {
        super(id);
        this.startFloor = startFloor;
        this.endFloor = endFloor;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public int getEndFloor() {
        return endFloor;
    }
}
