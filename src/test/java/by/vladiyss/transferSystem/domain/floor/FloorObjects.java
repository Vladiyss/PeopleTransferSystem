package by.vladiyss.transferSystem.domain.floor;

import by.vladiyss.transferSystem.building.configuration.FloorAndElevatorConfigurationObject;
import by.vladiyss.transferSystem.domain.Floor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FloorObjects {

    public static Floor getFloorObject(int id) {
        return new Floor(id);
    }

    public static List<Floor> getFloorObjectsList() {
        int floorsNumber = FloorAndElevatorConfigurationObject.getFloorAndElevatorConfigurationObject().getFloorsNumber();
        return IntStream.range(0, floorsNumber)
                .mapToObj(Floor::new)
                .collect(Collectors.toList());
    }
}
