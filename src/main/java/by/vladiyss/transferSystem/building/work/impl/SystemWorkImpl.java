package by.vladiyss.transferSystem.building.work.impl;

import by.vladiyss.transferSystem.building.work.SystemWork;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class SystemWorkImpl implements SystemWork {

    private boolean isWorking;

    public static void main(String[] args) {

        SystemWorkImpl systemWork = new SystemWorkImpl();
        systemWork.work();
    }

    @SneakyThrows
    public void work() {

        while (isWorking) {

            //initialize system

            TimeUnit.SECONDS.sleep(60);

            //pause all threads
            //join all Threads

            isWorking = false;
        }
    }
}
