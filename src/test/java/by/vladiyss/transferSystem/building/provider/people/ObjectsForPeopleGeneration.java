package by.vladiyss.transferSystem.building.provider.people;

import by.vladiyss.transferSystem.building.configuration.FloorAndElevatorConfigurationObject;
import by.vladiyss.transferSystem.domain.QueueMonitorObjects;
import by.vladiyss.transferSystem.domain.floor.FloorObjects;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ObjectsForPeopleGeneration {

    public static PeopleGenerationInformation getPeopleGenerationInformationObject() {
        return new PeopleGenerationInformation();
    }

    public static PeopleInformationPart getPeopleInformationPartObject(int id) {
        return new PeopleInformationPart(id, FloorAndElevatorConfigurationObject
                .getFloorAndElevatorConfigurationObject()
                .getFloorsNumber());
    }

    public static List<PeopleInformationPart> getPeopleInformationPartObjectsList() {
        int floorsNumber = FloorAndElevatorConfigurationObject
                .getFloorAndElevatorConfigurationObject()
                .getFloorsNumber();
        return IntStream.range(0, floorsNumber)
                .mapToObj(i -> new PeopleInformationPart(i, floorsNumber))
                .collect(Collectors.toList());
    }

    public static PeopleGenerationManager getPeopleGenerationManagerObject() {
        return new PeopleGenerationManager();
    }

    public static PeopleGenerator getPeopleGeneratorObject(int id) {
        int floorsNumber = FloorAndElevatorConfigurationObject
                .getFloorAndElevatorConfigurationObject()
                .getFloorsNumber();
        return new PeopleGenerator(id, FloorObjects.getFloorObject(id), floorsNumber,
                QueueMonitorObjects.getQueueMonitorsList(floorsNumber).get(id), getPeopleGenerationManagerObject());
    }

    public static List<PeopleGenerator> getPeopleGeneratorObjectsList() {
        int floorsNumber = FloorAndElevatorConfigurationObject
                .getFloorAndElevatorConfigurationObject()
                .getFloorsNumber();
        List<Object> queueMonitors = QueueMonitorObjects.getQueueMonitorsList(floorsNumber);
        PeopleGenerationManager peopleGenerationManager = getPeopleGenerationManagerObject();

        return IntStream.range(0, floorsNumber)
                .mapToObj(i -> new PeopleGenerator(i, FloorObjects.getFloorObject(i), floorsNumber,
                        queueMonitors.get(i), peopleGenerationManager))
                .collect(Collectors.toList());
    }
}
