package by.vladiyss.transferSystem.building.provider;

import java.util.List;

public interface ComponentProvider<T> {
    List<T> provide();
}
