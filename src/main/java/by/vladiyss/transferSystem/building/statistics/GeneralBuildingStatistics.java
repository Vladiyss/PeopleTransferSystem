package by.vladiyss.transferSystem.building.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralBuildingStatistics {

    private final Map<Integer, ElevatorStatistics> elevatorsStatistics;
    private final Map<Integer, FloorStatistics> floorsStatistics;
    private final List<BetweenFloorsTransfersStatistics> betweenFloorsTransfersStatisticsList;

    public GeneralBuildingStatistics() {
        elevatorsStatistics = new HashMap<>();
        floorsStatistics = new HashMap<>();
        betweenFloorsTransfersStatisticsList = new ArrayList<>();
    }

    public Map<Integer, ElevatorStatistics> getElevatorsStatistics() {
        return elevatorsStatistics;
    }

    public Map<Integer, FloorStatistics> getFloorsStatistics() {
        return floorsStatistics;
    }

    public synchronized List<BetweenFloorsTransfersStatistics> getBetweenFloorsTransfersStatisticsList() {
        return List.copyOf(betweenFloorsTransfersStatisticsList);
    }

    public synchronized void addElementToBetweenFloorsTransfersStatisticsList(BetweenFloorsTransfersStatistics element) {
        betweenFloorsTransfersStatisticsList.add(element);
    }

    public void setElevatorsStatisticsElements(int elevatorsNumber) {

    }

    public void setFloorsStatisticsElements(int floorsNumber) {

    }
}
