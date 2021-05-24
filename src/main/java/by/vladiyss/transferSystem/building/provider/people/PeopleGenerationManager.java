package by.vladiyss.transferSystem.building.provider.people;

import by.vladiyss.transferSystem.domain.Floor;
import by.vladiyss.transferSystem.domain.Person;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PeopleGenerationManager {

    private final PeopleGenerationInformation peopleGenerationInformation;

    public PeopleGenerationManager() {
        peopleGenerationInformation = new PeopleGenerationInformation();
    }

    public synchronized PeopleGenerationInformation getPeopleGenerationInformation() {
        return peopleGenerationInformation;
    }

    private synchronized List<Person> prepareGeneratedPeopleForAddingToQueue(Floor managedFloorForPeopleGeneration,
                                                               PeopleInformationPart peopleInformationPart,
                                                               int peopleNumber) {
        final List<Person> generatedPeople = IntStream.range(0, peopleNumber)
                .mapToObj(i -> new Person(peopleInformationPart.getPersonRandomUUID(),
                        peopleInformationPart.getPersonRandomWeight(),
                        peopleInformationPart.getPersonRandomRequiredFloor(),
                        managedFloorForPeopleGeneration.getId()))
                .collect(Collectors.toList());
        return generatedPeople;
    }

    public synchronized void putPeopleToQueue(Floor managedFloorForPeopleGeneration,
                                               PeopleInformationPart peopleInformationPart,
                                               BlockingQueue<Person> blockingQueue) {
        managedFloorForPeopleGeneration.addPeopleToPeopleQueue(blockingQueue, prepareGeneratedPeopleForAddingToQueue(
                managedFloorForPeopleGeneration, peopleInformationPart,
                peopleGenerationInformation.getRandomNumberOfPeopleToGenerate()));
    }

}
