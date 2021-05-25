package by.vladiyss.transferSystem.building.statistics;

import by.vladiyss.transferSystem.building.configuration.FloorAndElevatorConfiguration;
import by.vladiyss.transferSystem.building.configuration.FloorAndElevatorConfigurationObject;

public class ObjectsForStatistics {

    public static GeneralBuildingStatistics getGeneralBuildingStatisticsObject() {
        return new GeneralBuildingStatistics(
                FloorAndElevatorConfigurationObject.getFloorAndElevatorConfigurationObject().getElevatorsNumber(),
                FloorAndElevatorConfigurationObject.getFloorAndElevatorConfigurationObject().getFloorsNumber());
    }

    public static StatisticsManager getStatisticsManagerObject() {
        return new StatisticsManager(getGeneralBuildingStatisticsObject());
    }

    public static FloorStatistics getFloorStatisticsObject(int id) {
        return new FloorStatistics(id);
    }

    public static ElevatorStatistics getElevatorStatisticsObject(int id) {
        return new ElevatorStatistics(id);
    }

    public static BetweenFloorsTransfersStatistics getBetweenFloorsTransfersStatisticsObject(
            int id, int startFloor, int endFloor) {
        return new BetweenFloorsTransfersStatistics(id, startFloor, endFloor);
    }
}
