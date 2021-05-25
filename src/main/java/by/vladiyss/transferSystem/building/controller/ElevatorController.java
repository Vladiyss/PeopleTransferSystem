package by.vladiyss.transferSystem.building.controller;

import by.vladiyss.transferSystem.domain.Floor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ElevatorController extends Thread {

    private final int id;
    private final Floor underControlFloor;
    private final Object queuesMonitor;

    private boolean isWorking;
    private final ElevatorControllerManager elevatorControllerManager;

    public ElevatorController(int id, Floor underControlFloor, Object queuesMonitor,
                              ElevatorControllerManager elevatorControllerManager) {
        this.id = id;
        this.underControlFloor = underControlFloor;
        this.queuesMonitor = queuesMonitor;
        this.elevatorControllerManager = elevatorControllerManager;
        log.debug("ELEVATOR_CONTROLLER --- Generated --- {}", this);
    }

    public void pauseElevatorController() {
        isWorking = false;
    }

    public void resumeElevatorController() {
        isWorking = true;
    }

    @SneakyThrows
    @Override
    public void run() {
        isWorking = true;

        log.debug("ELEVATOR_CONTROLLER --- Is starting to work --- {}", this);
        TimeUnit.SECONDS.sleep(2);
        while (isWorking) {

            while (underControlFloor.getUpPeopleQueue().isEmpty() && underControlFloor.getDownPeopleQueue().isEmpty()) {
                queuesMonitor.wait();

            }

            elevatorControllerManager.processPeopleTransferRequest(underControlFloor.getUpPeopleQueue(),
                    true, underControlFloor);
            elevatorControllerManager.processPeopleTransferRequest(underControlFloor.getDownPeopleQueue(),
                    false, underControlFloor);
        }
        log.debug("ELEVATOR_CONTROLLER --- Finished working --- {}", this);
    }

    @Override
    public String toString() {
        return "ElevatorController : " +
                "id=" + id +
                ", underControlFloor=" + underControlFloor +
                ", queuesMonitor=" + queuesMonitor + '.';
    }
}
