package by.vladiyss.transferSystem.domain.person;

import by.vladiyss.transferSystem.domain.Person;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PersonObjects {

    private static final int PEOPLE_NUMBER = 5;

    public static List<Person> getPeopleList() {
        return IntStream.range(0, PEOPLE_NUMBER)
                .mapToObj(i -> new Person(UUID.randomUUID(), 50, 2,4))
                .collect(Collectors.toList());
    }
}
