package by.vladiyss.transferSystem.building.controller;

import by.vladiyss.transferSystem.domain.Person;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class ElevatorTransferTask {

    private final UUID id;
    private final boolean isUpTransfer;
    private final List<Person> peopleToTransfer;
    private final BlockingQueue<Person> queueToTakePeople;


    public ElevatorTransferTask(UUID id, boolean isUpTransfer,
                                List<Person> peopleToTransfer,
                                BlockingQueue<Person> queueToTakePeople) {
        this.id = id;
        this.isUpTransfer = isUpTransfer;
        this.peopleToTransfer = peopleToTransfer;
        this.queueToTakePeople = queueToTakePeople;
    }

    public UUID getId() {
        return id;
    }

    public boolean isUpTransfer() {
        return isUpTransfer;
    }

    public List<Person> getPeopleToTransfer() {
        return List.copyOf(peopleToTransfer);
    }

    public BlockingQueue<Person> getQueueToTakePeople() {
        return queueToTakePeople;
    }
}
