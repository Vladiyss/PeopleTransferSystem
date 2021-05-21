package by.vladiyss.transferSystem.domain;

import java.util.Objects;
import java.util.UUID;

public class Person {
    private final UUID id;
    private final double weight;
    private final int requiredFloor;
    private final int originalFloor;

    public Person(UUID id, double weight, int requiredFloor, int originalFloor) {
        this.id = id;
        this.weight = weight;
        this.requiredFloor = requiredFloor;
        this.originalFloor = originalFloor;
    }

    public UUID getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public int getRequiredFloor() {
        return requiredFloor;
    }

    public int getOriginalFloor() {
        return originalFloor;
    }

    @Override
    public String toString() {
        return "Person : " + "id=" + id +
                ", weight=" + weight +
                ", requiredFloor=" + requiredFloor +
                ", originalFloor=" + originalFloor + '.';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.weight, weight) == 0
                && requiredFloor == person.requiredFloor
                && originalFloor == person.originalFloor
                && id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, weight, requiredFloor, originalFloor);
    }
}
