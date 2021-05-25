package by.vladiyss.transferSystem.building.work.impl;

import by.vladiyss.transferSystem.building.work.SystemWork;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class SystemWorkImpl implements SystemWork {

    private boolean isWorking;

    public static void main(String[] args) {

        SystemWorkImpl systemWork = new SystemWorkImpl();
        systemWork.work();
    }

    @SneakyThrows
    public void work() {

        isWorking = true;

        while (isWorking) {

            log.debug("TRANSFER SYSTEM --- Starts working");
            //initialize system

            TimeUnit.SECONDS.sleep(60);

            //pause all threads
            //join all Threads

            isWorking = false;
        }

        log.debug("TRANSFER SYSTEM --- Finishes working");
    }
}
