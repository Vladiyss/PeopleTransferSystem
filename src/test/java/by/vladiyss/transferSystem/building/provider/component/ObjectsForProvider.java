package by.vladiyss.transferSystem.building.provider.component;

import by.vladiyss.transferSystem.building.provider.component.elevator.ElevatorInformationPart;
import by.vladiyss.transferSystem.building.provider.component.elevator.ElevatorProvider;
import by.vladiyss.transferSystem.building.provider.component.floor.FloorProvider;
import by.vladiyss.transferSystem.building.statistics.GeneralBuildingStatistics;
import by.vladiyss.transferSystem.building.statistics.ObjectsForStatistics;

public class ObjectsForProvider {

    public static FloorProvider getFloorProviderObject(int floorsNumber) {
        return new FloorProvider(floorsNumber);
    }

    public static ElevatorProvider getElevatorProviderObject() {
        GeneralBuildingStatistics generalBuildingStatistics = ObjectsForStatistics.getGeneralBuildingStatisticsObject();
        return new ElevatorProvider(generalBuildingStatistics.getElevatorsStatistics().size(), generalBuildingStatistics);
    }

    public static ElevatorInformationPart getElevatorInformationPartObject() {
        return new ElevatorInformationPart();
    }
}
