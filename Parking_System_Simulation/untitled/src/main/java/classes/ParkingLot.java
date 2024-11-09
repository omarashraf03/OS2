package classes;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ParkingLot {
    private final Semaphore spots;
    private int TotalParked = 0; //counter

    public ParkingLot(int totalSpots) {
        this.spots = new Semaphore(totalSpots, true);
    }

    public synchronized void parkCar(Car car) throws InterruptedException {
        if (spots.tryAcquire(car.arrivalTime(), TimeUnit.SECONDS)) {
            System.out.println("Car " + car.getId() + " from Gate " + car.GateId() + " parked.");
            TotalParked++;
        } else {
            System.out.println("Car " + car.getId() + " from Gate " + car.GateId() + " is waiting.");
        }    
    }

    public synchronized void leaveCar(Car car ) {
        spots.release(); // Release a permit, allowing another car to park
        TotalParked--; // Decrement the local counter
        System.out.println("Car " + car.getId() + " from Gate " + car.GateId() + " left after " + car.getParkDuration() + " units.");
    }
    public int getTotalCarsServed() {
        return TotalParked;
    }
}