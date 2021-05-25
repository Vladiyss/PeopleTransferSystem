package by.vladiyss.transferSystem.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QueueMonitorObjects {

    public static List<Object> getQueueMonitorsList(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new Object())
                .collect(Collectors.toList());
    }
}
