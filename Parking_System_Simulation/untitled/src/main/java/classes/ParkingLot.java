package classes;
import java.util.concurrent.Semaphore;

public class ParkingLot {
    private final Semaphore spots;
    private int currentlyParked = 0; // Simple integer counter

    public ParkingLot(int totalSpots) {
        this.spots = new Semaphore(totalSpots, true);
    }

    public synchronized void parkCar(int carId) throws InterruptedException {
        spots.acquire(); // Acquire a permit, blocking if no permits are available
        currentlyParked++; // Increment the local counter
        System.out.println("Car " + carId + " parked. (Parking Status: " + currentlyParked + " spots occupied)");
    }

    public synchronized void leaveCar(int carId) {
        spots.release(); // Release a permit, allowing another car to park
        currentlyParked--; // Decrement the local counter
        System.out.println("Car " + carId + " left. (Parking Status: " + currentlyParked + " spots occupied)");
    }
}
