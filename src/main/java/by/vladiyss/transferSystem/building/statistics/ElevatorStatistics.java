package by.vladiyss.transferSystem.building.statistics;

public class ElevatorStatistics {

    protected final int id;
    protected int generalTransferredPeopleNumber;
    protected double totalTransferredPeopleWeight;
    protected int totalTransfersNumber;

    public ElevatorStatistics(int id) {
        this.id = id;
        generalTransferredPeopleNumber = 0;
        totalTransferredPeopleWeight = 0.0;
        totalTransfersNumber = 0;
    }

    public int getId() {
        return id;
    }

    public synchronized int getGeneralTransferredPeopleNumber() {
        return generalTransferredPeopleNumber;
    }

    public synchronized double getTotalTransferredPeopleWeight() {
        return totalTransferredPeopleWeight;
    }

    public synchronized int getTotalTransfersNumber() {
        return totalTransfersNumber;
    }

    public synchronized void incrementGeneralTransferredPeopleNumber(int transferredPeopleNumber) {
        this.generalTransferredPeopleNumber += transferredPeopleNumber;
    }

    public synchronized void incrementTotalTransferredPeopleWeight(double transferredPeopleWeight) {
        this.totalTransferredPeopleWeight += transferredPeopleWeight;
    }

    public synchronized void incrementTotalTransfersNumber(int transfersNumber) {
        this.totalTransfersNumber += transfersNumber;
    }
}

