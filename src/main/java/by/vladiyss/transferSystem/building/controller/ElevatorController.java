package by.vladiyss.transferSystem.building.controller;

import by.vladiyss.transferSystem.domain.Elevator;
import by.vladiyss.transferSystem.domain.Floor;
import by.vladiyss.transferSystem.domain.Person;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class ElevatorController extends Thread {

    private final int id;
    private final Floor underControlFloor;
    private final List<Elevator> elevators;
    private final Object queuesMonitor;

    private boolean isWorking;

    public ElevatorController(int id, Floor underControlFloor, List<Elevator> elevators, Object queuesMonitor) {
        this.id = id;
        this.underControlFloor = underControlFloor;
        this.elevators = elevators;
        this.queuesMonitor = queuesMonitor;
    }

    public void pauseElevatorController() {
        isWorking = false;
    }

    public void resumeElevatorController() {
        isWorking = true;
    }

    private Elevator chooseTheMostSuitableElevatorToDoTransfer(boolean isUpTransfer) {

        /*
        List<Elevator> availableElevators = elevators.stream()
                .filter(not(Elevator::isBusy))
                .min(elevator -> elevator.getCurrentFloor() - underControlFloor.getId())
                .collect(Collectors.toList());
         */

        Optional<Elevator> mostSuitableElevator = elevators.stream()
                .filter(not(Elevator::isBusy))
                .min((elevator1, elevator2) -> Integer.compare(
                        Math.abs(elevator1.getCurrentFloor() - underControlFloor.getId()),
                        Math.abs(elevator2.getCurrentFloor() - underControlFloor.getId()) ));
        return mostSuitableElevator.get();

    }

    private int definePossibleNumberOfPeopleToTransfer(List<Person> peopleQueue, Elevator chosenElevator) {
        final Double commonWeightOfAllPeopleInQueue = peopleQueue.stream()
                .map(Person::getWeight)
                .reduce(0.0, Double::sum);

        if (commonWeightOfAllPeopleInQueue <= chosenElevator.getCapacity()) {
            return peopleQueue.size();
        }

        int peopleListPosition = 0;
        double currentWeight = 0.0;
        while (currentWeight <= chosenElevator.getCapacity()) {
            currentWeight += peopleQueue.get(peopleListPosition).getWeight();
            peopleListPosition++;
        }

        //put someone else from the queue so as to get elevator more full

        return peopleListPosition;
    }

    @SneakyThrows
    private boolean processPeopleTransferRequest(BlockingQueue<Person> peopleQueue, boolean isUpTransfer) {
        if (peopleQueue.isEmpty()) {
            return false;
        }

        List<Person> peopleInQueue = new ArrayList<>(peopleQueue);
        Elevator chosenElevatorToDoTransfer = chooseTheMostSuitableElevatorToDoTransfer(isUpTransfer);
        int numberOfPeopleToTransfer = definePossibleNumberOfPeopleToTransfer(peopleInQueue, chosenElevatorToDoTransfer);

        List<Person> peopleToTransfer = peopleInQueue.subList(0, numberOfPeopleToTransfer);
        ElevatorTransferTask transferTask = new ElevatorTransferTask(UUID.randomUUID(),
                isUpTransfer, peopleToTransfer, peopleQueue);
        chosenElevatorToDoTransfer.getTransferTasks().put(transferTask);
        chosenElevatorToDoTransfer.getTransferTasks().notify();

        return true;
    }


    @SneakyThrows
    @Override
    public void run() {
        isWorking = true;

        while (isWorking) {

            while (underControlFloor.getUpPeopleQueue().isEmpty() && underControlFloor.getDownPeopleQueue().isEmpty()) {
                queuesMonitor.wait();

            }

            processPeopleTransferRequest(underControlFloor.getUpPeopleQueue(), true);
            processPeopleTransferRequest(underControlFloor.getDownPeopleQueue(), false);
        }
    }
}
