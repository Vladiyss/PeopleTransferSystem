package by.vladiyss.transferSystem.building.statistics;

public class BetweenFloorsTransfersStatistics extends ElevatorStatistics{

    private final int startFloor;
    private final int endFloor;
    /*private int generalTransferredPeopleNumber;
    private double totalTransferredPeopleWeight;
    private int totalTransfersNumber;*/

    public BetweenFloorsTransfersStatistics(int startFloor, int endFloor) {
        super();
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        /*generalTransferredPeopleNumber = 0;
        totalTransferredPeopleWeight = 0.0;
        totalTransfersNumber = 0;*/
    }

    public int getStartFloor() {
        return startFloor;
    }

    public int getEndFloor() {
        return endFloor;
    }

    /*public int getGeneralTransferredPeopleNumber() {
        return generalTransferredPeopleNumber;
    }

    public double getTotalTransferredPeopleWeight() {
        return totalTransferredPeopleWeight;
    }

    public int getTotalTransfersNumber() {
        return totalTransfersNumber;
    }

    public void incrementGeneralTransferredPeopleNumber(int transferredPeopleNumber) {
        this.generalTransferredPeopleNumber += transferredPeopleNumber;
    }

    public void incrementTotalTransferredPeopleWeight(double transferredPeopleWeight) {
        this.totalTransferredPeopleWeight += transferredPeopleWeight;
    }

    public void incrementTotalTransfersNumber(int transfersNumber) {
        this.totalTransfersNumber += transfersNumber;
    }*/
}
