package classes;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingLot {
    private final Semaphore availableSpots;
    private int currentlyParked = 0;
    private int totalServed = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public ParkingLot(int totalSpots) {
        this.availableSpots = new Semaphore(totalSpots);
    }
}
