package by.vladiyss.transferSystem.building.provider.people;

import by.vladiyss.transferSystem.domain.Floor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
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
        log.debug("PEOPLE_GENERATOR --- Created --- {}", this);
    }

    public boolean isWorking() {
        return isWorking;
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

        log.debug("PEOPLE_GENERATOR --- Is strarting to work --- {}", this);
        TimeUnit.SECONDS.sleep(2);
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

            queuesMonitor.notify();   //comment if run test
            TimeUnit.MILLISECONDS.sleep(peopleGenerationManager
                    .getPeopleGenerationInformation()
                    .getRandomFrequencyOfPeopleGeneration());
        }
    }

    @Override
    public String toString() {
        return "PeopleGenerator :" +
                "id=" + id +
                ", managedFloorForPeopleGeneration=" + managedFloorForPeopleGeneration +
                ", floorsNumber=" + floorsNumber +
                ", queuesMonitor=" + queuesMonitor + '.';
    }
}
