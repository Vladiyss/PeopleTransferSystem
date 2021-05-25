package by.vladiyss.transferSystem.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class Floor {
    private final int id;
    private final BlockingQueue<Person> upPeopleQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<Person> downPeopleQueue = new LinkedBlockingQueue<>();

    public Floor(int id) {
        this.id = id;
        log.debug("FLOOR --- Generated --- {}", this);
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

    public void addPeopleToPeopleQueue(BlockingQueue<Person> blockingQueue, Collection<Person> newDownPeopleQueue) {
        log.debug("FLOOR --- Before adding new people --- {}", this);
        log.debug("FLOOR --- New people is to be in {} queue --- {}", blockingQueue, this);
        blockingQueue.addAll(newDownPeopleQueue);
        log.debug("FLOOR --- After adding new people --- {}", this);
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
