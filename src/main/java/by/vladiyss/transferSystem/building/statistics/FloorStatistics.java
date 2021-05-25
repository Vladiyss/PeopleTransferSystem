package by.vladiyss.transferSystem.building.statistics;

public class FloorStatistics {

    private final int id;
    private int upTransferredPeopleNumber;
    private int downTransferredPeopleNumber;
    private int upTransfersNumber;
    private int downTransfersNumber;

    public FloorStatistics(int id) {
        this.id = id;
        upTransferredPeopleNumber = 0;
        downTransferredPeopleNumber = 0;
        upTransfersNumber = 0;
        downTransfersNumber = 0;
    }

    public int getId() {
        return id;
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

    public synchronized void incrementUpTransfersNumber() {
        this.upTransfersNumber++;
    }

    public synchronized void incrementDownTransfersNumber() {
        this.downTransfersNumber++;
    }
}
