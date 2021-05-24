package by.vladiyss.transferSystem.building.provider.people;

import by.vladiyss.transferSystem.domain.Floor;
import by.vladiyss.transferSystem.domain.Person;
import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PeopleGenerator extends Thread {

    private final int id;
    private final Floor managedFloorForPeopleGeneration;
    private boolean isWorking;

    private final int floorsNumber;
    private final Object queuesMonitor;

    private PeopleInformationPart peopleInformationPart;
    private final PeopleGenerationManager peopleGenerationManager;

    public PeopleGenerator(int id, Floor managedFloorForPeopleGeneration,
                           int floorsNumber, Object queuesMonitor,
                           PeopleGenerationManager peopleGenerationManager) {
        this.id = id;
        this.managedFloorForPeopleGeneration = managedFloorForPeopleGeneration;
        this.floorsNumber = floorsNumber;
        this.queuesMonitor = queuesMonitor;
        this.peopleGenerationManager = peopleGenerationManager;
    }

    public Floor getManagedFloorForPeopleGeneration() {
        return managedFloorForPeopleGeneration;
    }

    public int getFloorsNumber() {
        return floorsNumber;
    }

    public PeopleInformationPart getPeopleInformationPart() {
        return peopleInformationPart;
    }

    private PeopleInformationPart createPeopleInformationConfiguration()  {
        return new PeopleInformationPart(managedFloorForPeopleGeneration.getId(), floorsNumber);
    }

    public void pausePeopleGenerator() {
        isWorking = false;
    }

    public void resumePeopleGenerator() {
        isWorking = true;
    }

    @SneakyThrows
    @Override
    public void run() {
        isWorking = true;
        peopleInformationPart = createPeopleInformationConfiguration();

        while (isWorking) {
            if (peopleGenerationManager.getPeopleGenerationInformation().getComingDownPeopleOption()) {
                peopleGenerationManager.putPeopleToQueue(managedFloorForPeopleGeneration,
                        peopleInformationPart,
                        managedFloorForPeopleGeneration.getDownPeopleQueue());
            }
            else {
                peopleGenerationManager.putPeopleToQueue(managedFloorForPeopleGeneration,
                        peopleInformationPart,
                        managedFloorForPeopleGeneration.getUpPeopleQueue());
            }

            queuesMonitor.notify();
            TimeUnit.MILLISECONDS.sleep(peopleGenerationManager
                    .getPeopleGenerationInformation()
                    .getRandomFrequencyOfPeopleGeneration());
        }
    }
}
