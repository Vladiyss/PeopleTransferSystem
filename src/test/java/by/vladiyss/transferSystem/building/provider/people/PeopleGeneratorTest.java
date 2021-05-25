package by.vladiyss.transferSystem.building.provider.people;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class PeopleGeneratorTest {

    private List<PeopleGenerator> peopleGeneratorList;

    @BeforeEach
    public void before() {
        peopleGeneratorList = ObjectsForPeopleGeneration.getPeopleGeneratorObjectsList();
    }

    @SneakyThrows
    @Test
    void run_checkWorkingBeforePause() {
        peopleGeneratorList.forEach(Thread::start);

        peopleGeneratorList.stream()
                .forEach((i) -> assertThat(i.isWorking(), is(true)));

        peopleGeneratorList.stream()
                .forEach(PeopleGenerator::pausePeopleGenerator);

        for (PeopleGenerator peopleGenerator : peopleGeneratorList) {
            peopleGenerator.join();
        }
    }

    @SneakyThrows
    @Test
    void run_checkWorkingAfterPause() {
        peopleGeneratorList.forEach(Thread::start);

        peopleGeneratorList.stream()
                .forEach(PeopleGenerator::pausePeopleGenerator);

        TimeUnit.SECONDS.sleep(4);

        peopleGeneratorList.stream()
                .forEach((i) -> assertThat(i.isWorking(), is(false)));

        TimeUnit.SECONDS.sleep(3);

        for (PeopleGenerator peopleGenerator : peopleGeneratorList) {
            peopleGenerator.join();
        }


    }
}