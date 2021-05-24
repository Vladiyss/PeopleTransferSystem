package by.vladiyss.transferSystem.building.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneralBuildingStatistics {

    private Map<Integer, ElevatorStatistics> elevatorsStatistics;
    private Map<Integer, FloorStatistics> floorsStatistics;
    private final List<BetweenFloorsTransfersStatistics> betweenFloorsTransfersStatisticsList;

    public GeneralBuildingStatistics(int elevatorsNumber, int floorsNumber) {
        elevatorsStatistics = new HashMap<>();
        floorsStatistics = new HashMap<>();
        betweenFloorsTransfersStatisticsList = new ArrayList<>();
        setElevatorsStatisticsElements(elevatorsNumber);
        setFloorsStatisticsElements(floorsNumber);
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

    private void setElevatorsStatisticsElements(int elevatorsNumber) {
        List<ElevatorStatistics> elevatorStatisticsList = IntStream.range(0, elevatorsNumber)
                .mapToObj(ElevatorStatistics::new)
                .collect(Collectors.toList());

        elevatorsStatistics = elevatorStatisticsList.stream()
                .collect(Collectors.toMap(ElevatorStatistics::getId, Function.identity()));
    }

    private void setFloorsStatisticsElements(int floorsNumber) {
        List<FloorStatistics> floorStatisticsList = IntStream.range(0, floorsNumber)
                .mapToObj(FloorStatistics::new)
                .collect(Collectors.toList());

        floorsStatistics = floorStatisticsList.stream()
                .collect(Collectors.toMap(FloorStatistics::getId, Function.identity()));
    }
}
