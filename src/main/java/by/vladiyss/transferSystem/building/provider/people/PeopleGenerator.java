package by.vladiyss.transferSystem.building.provider.people;

import by.vladiyss.transferSystem.domain.Floor;
import by.vladiyss.transferSystem.domain.Person;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PeopleGenerator extends Thread {

    /*
    private final IntConsumer putPersonToUpQueue = i -> {
        getOriginalFloor().addPeopleToUpPeopleQueue(prepareGeneratedPeopleForAddingToQueue(
                getPeopleGenerationInformation().getRandomNumberOfPeopleToGenerate()));
    };

    private final IntConsumer putPersonToDownQueue = i -> {
        getOriginalFloor().addPeopleToDownPeopleQueue(prepareGeneratedPeopleForAddingToQueue(
                getPeopleGenerationInformation().getRandomNumberOfPeopleToGenerate()));
    };
    */

    private final Object queuesMonitor;

    private final int id;
    private final Floor originalFloor;
    private final int floorsNumber;
    private PeopleInformationPart peopleInformationPart;
    private PeopleGenerationInformation peopleGenerationInformation;

    private boolean isWorking;

    public PeopleGenerator(Object queuesMonitor, int id, Floor originalFloor, int floorsNumber) {
        this.queuesMonitor = queuesMonitor;
        this.id = id;
        this.originalFloor = originalFloor;
        this.floorsNumber = floorsNumber;
    }

    public Floor getOriginalFloor() {
        return originalFloor;
    }

    public int getFloorsNumber() {
        return floorsNumber;
    }

    public PeopleInformationPart getPeopleInformationPart() {
        return peopleInformationPart;
    }

    public PeopleGenerationInformation getPeopleGenerationInformation() {
        return peopleGenerationInformation;
    }

    private PeopleInformationPart createPeopleInformationConfiguration()  {
        return new PeopleInformationPart(originalFloor.getId(), floorsNumber);
    }

    private PeopleGenerationInformation createPeopleGenerationInformation()  {
        return new PeopleGenerationInformation();
    }

    public void pausePeopleGenerator() {
        isWorking = false;
    }

    public void resumePeopleGenerator() {
        isWorking = true;
    }

    public List<Person> prepareGeneratedPeopleForAddingToQueue(int peopleNumber) {
        final List<Person> generatedPeople = IntStream.range(0, peopleNumber)
                .mapToObj(i -> new Person(peopleInformationPart.getPersonRandomUUID(),
                        peopleInformationPart.getPersonRandomWeight(),
                        peopleInformationPart.getPersonRandomRequiredFloor(),
                        originalFloor.getId()))
                .collect(Collectors.toList());
        return generatedPeople;
    }

    /*
    private void putPersonToUpQueue() {
        originalFloor.addPeopleToDownPeopleQueue(prepareGeneratedPeopleForAddingToQueue(
                peopleGenerationInformation.getRandomNumberOfPeopleToGenerate()));
    }

    private void putPersonToDownQueue() {
        originalFloor.addPeopleToDownPeopleQueue(prepareGeneratedPeopleForAddingToQueue(
                peopleGenerationInformation.getRandomNumberOfPeopleToGenerate()));
    }
    */

    private void putPersonToQueue(BlockingQueue<Person> blockingQueue) {
        originalFloor.addPeopleToPeopleQueue(blockingQueue, prepareGeneratedPeopleForAddingToQueue(
                peopleGenerationInformation.getRandomNumberOfPeopleToGenerate()));
    }

    @SneakyThrows
    @Override
    public void run() {
        isWorking = true;

        peopleInformationPart = createPeopleInformationConfiguration();
        peopleGenerationInformation = createPeopleGenerationInformation();

        while (isWorking) {
            if (peopleGenerationInformation.getComingDownPeopleOption()) {
                //putPersonToDownQueue();
                putPersonToQueue(originalFloor.getDownPeopleQueue());
            }
            else {
                //putPersonToUpQueue();
                putPersonToQueue(originalFloor.getUpPeopleQueue());
            }

            /*
            Optional<Boolean> comingDownPeopleOption
                    = Optional.ofNullable(peopleGenerationInformation.getComingDownPeopleOption());
            comingDownPeopleOption.filter(i -> i);
            comingDownPeopleOption.ifPresentOrElse((i) -> putPersonToQueue(originalFloor.getDownPeopleQueue()),
                    () -> putPersonToQueue(originalFloor.getUpPeopleQueue()));
             */

            queuesMonitor.notify();

            TimeUnit.MILLISECONDS.sleep(peopleGenerationInformation.getRandomFrequencyOfPeopleGeneration());
        }
    }
}
