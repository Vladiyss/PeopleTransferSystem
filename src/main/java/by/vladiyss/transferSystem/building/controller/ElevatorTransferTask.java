package by.vladiyss.transferSystem.building.controller;

import by.vladiyss.transferSystem.domain.Person;
import by.vladiyss.transferSystem.domain.elevator.Elevator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class ElevatorTransferTask {

    private final UUID id;
    private final boolean isUpTransfer;
    private final List<Person> peopleToTransfer;
    private final BlockingQueue<Person> queueToTakePeople;
    private final Elevator chosenElevator;

    public ElevatorTransferTask(UUID id, boolean isUpTransfer,
                                List<Person> peopleToTransfer,
                                BlockingQueue<Person> queueToTakePeople,
                                Elevator chosenElevator) {
        this.id = id;
        this.isUpTransfer = isUpTransfer;
        this.peopleToTransfer = peopleToTransfer;
        this.queueToTakePeople = queueToTakePeople;
        this.chosenElevator = chosenElevator;
        log.debug("ELEVATOR_TRANSFER_TASK --- Generated --- {}", this);
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

    public Elevator getChosenElevator() {
        return chosenElevator;
    }

    @Override
    public String toString() {
        return "ElevatorTransferTask : " +
                "id=" + id +
                ", isUpTransfer=" + isUpTransfer +
                ", peopleToTransfer=" + peopleToTransfer +
                ", queueToTakePeople=" + queueToTakePeople +
                ", chosenElevator=" + chosenElevator +
                '.';
    }
}
