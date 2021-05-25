package by.vladiyss.transferSystem.building.provider.people;

import by.vladiyss.transferSystem.domain.Floor;
import by.vladiyss.transferSystem.domain.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
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
        log.debug("PEOPLE_GENERATION_MANAGER --- Generated people --- {}", generatedPeople);
        return generatedPeople;
    }

    public synchronized void putPeopleToQueue(Floor managedFloorForPeopleGeneration,
                                               PeopleInformationPart peopleInformationPart,
                                               BlockingQueue<Person> blockingQueue) {
        log.debug("PEOPLE_GENERATION_MANAGER --- Is starting to generate people --- {}", this);
        managedFloorForPeopleGeneration.addPeopleToPeopleQueue(blockingQueue, prepareGeneratedPeopleForAddingToQueue(
                managedFloorForPeopleGeneration, peopleInformationPart,
                peopleGenerationInformation.getRandomNumberOfPeopleToGenerate()));
    }

}
