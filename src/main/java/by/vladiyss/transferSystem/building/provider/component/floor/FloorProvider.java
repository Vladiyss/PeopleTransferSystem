package by.vladiyss.transferSystem.building.provider.component.floor;

import by.vladiyss.transferSystem.building.provider.ComponentProvider;
import by.vladiyss.transferSystem.domain.Floor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FloorProvider implements ComponentProvider<Floor> {

    private final int floorsNumber;

    public FloorProvider(int floorsNumber) {
        this.floorsNumber = floorsNumber;
    }

    @Override
    public List<Floor> provide() {
        List<Floor> floors;

        floors = IntStream.range(0, floorsNumber)
                .mapToObj(Floor::new)
                .collect(Collectors.toList());

        return floors;
    }
}
