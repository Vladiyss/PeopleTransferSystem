package by.vladiyss.transferSystem.building.provider.component.floor;

import by.vladiyss.transferSystem.building.provider.component.ComponentProvider;
import by.vladiyss.transferSystem.domain.Floor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class FloorProvider implements ComponentProvider<Floor> {

    private final int floorsNumber;

    public FloorProvider(int floorsNumber) {
        this.floorsNumber = floorsNumber;
        log.debug("FLOOR_PROVIDER --- Created");
    }

    public int getFloorsNumber() {
        return floorsNumber;
    }

    @Override
    public List<Floor> provide() {
        List<Floor> floors;

        floors = IntStream.range(0, floorsNumber)
                .mapToObj(Floor::new)
                .collect(Collectors.toList());

        log.debug("FLOOR_PROVIDER --- Generated floors --- {}", floors);
        return floors;
    }
}
