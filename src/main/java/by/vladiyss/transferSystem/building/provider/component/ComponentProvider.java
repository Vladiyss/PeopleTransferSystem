package by.vladiyss.transferSystem.building.provider.component;

import java.util.List;

public interface ComponentProvider<T> {
    List<T> provide();
}
