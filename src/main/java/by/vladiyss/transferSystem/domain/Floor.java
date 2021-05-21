package by.vladiyss.transferSystem.domain;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Floor {
    private final int id;
    private Queue<Person> upPeopleQueue = new LinkedList<>();
    private Queue<Person> downPeopleQueue = new LinkedList<>();


    public Floor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Queue<Person> getUpPeopleQueue() {
        return upPeopleQueue;
    }

    public Queue<Person> getDownPeopleQueue() {
        return downPeopleQueue;
    }

    public void addPeopleToUpPeopleQueue(Queue<Person> newUpPeopleQueue) {
        this.upPeopleQueue.addAll(newUpPeopleQueue);
    }

    public void addPeopleToDownPeopleQueue(Queue<Person> newDownPeopleQueue) {
        this.downPeopleQueue.addAll(newDownPeopleQueue);
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
