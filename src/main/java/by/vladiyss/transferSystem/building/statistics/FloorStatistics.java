package by.vladiyss.transferSystem.building.statistics;

public class FloorStatistics {

    private int upTransferredPeopleNumber;
    private int downTransferredPeopleNumber;
    private int upTransfersNumber;
    private int downTransfersNumber;

    public FloorStatistics() {
        upTransferredPeopleNumber = 0;
        downTransferredPeopleNumber = 0;
        upTransfersNumber = 0;
        downTransfersNumber = 0;
    }

    public synchronized int getUpTransferredPeopleNumber() {
        return upTransferredPeopleNumber;
    }

    public synchronized int getDownTransferredPeopleNumber() {
        return downTransferredPeopleNumber;
    }

    public synchronized int getUpTransfersNumber() {
        return upTransfersNumber;
    }

    public synchronized int getDownTransfersNumber() {
        return downTransfersNumber;
    }

    public synchronized void incrementUpTransferredPeopleNumber(int upTransferredPeopleNum) {
        this.upTransferredPeopleNumber += upTransferredPeopleNum;
    }

    public synchronized void incrementDownTransferredPeopleNumber(int downTransferredPeopleNum) {
        this.downTransferredPeopleNumber += downTransferredPeopleNum;
    }

    public synchronized void incrementUpTransfersNumber(int upTransfersNum) {
        this.upTransfersNumber += upTransfersNum;
    }

    public synchronized void incrementDownTransfersNumber(int downTransfersNum) {
        this.downTransfersNumber += downTransfersNum;
    }
}
