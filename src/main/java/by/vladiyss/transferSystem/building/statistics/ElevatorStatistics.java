package by.vladiyss.transferSystem.building.statistics;

public class ElevatorStatistics {

    protected int generalTransferredPeopleNumber;
    protected double totalTransferredPeopleWeight;
    protected int totalTransfersNumber;

    public ElevatorStatistics() {
        generalTransferredPeopleNumber = 0;
        totalTransferredPeopleWeight = 0.0;
        totalTransfersNumber = 0;
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

    public synchronized void incrementGeneralTransferredPeopleNumber(int transferedPeopleNumber) {
        this.generalTransferredPeopleNumber += transferedPeopleNumber;
    }

    public synchronized void incrementTotalTransferredPeopleWeight(double transferedPeopleWeight) {
        this.totalTransferredPeopleWeight += transferedPeopleWeight;
    }

    public synchronized void incrementTotalTransfersNumber(int transfersNumber) {
        this.totalTransfersNumber += transfersNumber;
    }
}

