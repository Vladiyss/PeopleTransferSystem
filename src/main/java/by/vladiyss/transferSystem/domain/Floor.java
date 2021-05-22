package by.vladiyss.transferSystem.domain;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Floor {
    private final int id;
    private BlockingQueue<Person> upPeopleQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<Person> downPeopleQueue = new LinkedBlockingQueue<>();


    public Floor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public BlockingQueue<Person> getUpPeopleQueue() {
        return upPeopleQueue;
    }

    public BlockingQueue<Person> getDownPeopleQueue() {
        return downPeopleQueue;
    }

    /*
    public void addPeopleToUpPeopleQueue(Collection<Person> newUpPeopleQueue) {
        this.upPeopleQueue.addAll(newUpPeopleQueue);
    }

    public void addPeopleToDownPeopleQueue(Collection<Person> newDownPeopleQueue) {
        this.downPeopleQueue.addAll(newDownPeopleQueue);
    }
     */

    public void addPeopleToPeopleQueue(BlockingQueue<Person> blockingQueue, Collection<Person> newDownPeopleQueue) {
        blockingQueue.addAll(newDownPeopleQueue);
    }

    @Override
    public String toString() {
        return "Floor : " +
                "id=" + id +
                ", upPeopleQueue=" + upPeopleQueue +
                ", downPeopleQueue=" + downPeopleQueue +
                '.';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return id == floor.id
                && Objects.equals(upPeopleQueue, floor.upPeopleQueue)
                && Objects.equals(downPeopleQueue, floor.downPeopleQueue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, upPeopleQueue, downPeopleQueue);
    }
}
