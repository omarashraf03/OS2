package classes;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class ParkingLot {
    private final Semaphore spots;
    private int totalParked = 0; 

    public ParkingLot(int totalSpots) {
        this.spots = new Semaphore(totalSpots, true);
    }

    public synchronized void enter(Car car) throws InterruptedException {
        long waitStartTime = System.currentTimeMillis(); 
        System.out.println("Car " + car.getId() + " from Gate " + car.GateId() + "arrived at time " + car.arrivalTime());
        if (!spots.tryAcquire()) {
            System.out.println("Car " + car.getId() + " from Gate " + car.GateId() + " waiting for a spot.");
            spots.acquire(); // Wait until a spot is available
            long waitedTime = (System.currentTimeMillis() - waitStartTime) / 1000; // Calculate wait time in seconds
            System.out.println("Car " + car.getId() + " from Gate " + car.GateId() + " parked after waiting for " + waitedTime + " units of time. parked. (Parking Status: " + (spots.availablePermits()) + " spots occupied)");

        }
        else{
            System.out.println("Car " + car.getId() + " from Gate " + car.GateId() + " parked. (Parking Status: " + (spots.availablePermits()) + " spots occupied)");
        }
        totalParked++;
        Thread.sleep(car.getParkDuration() * 1000); 
        leaveCar(car);
    }

    

    public synchronized void leaveCar(Car car) {
        spots.release();
        totalParked--;
        System.out.println("Car " + car.getId() + " from Gate " + car.GateId() + " left after " + car.getParkDuration() + " units of time. (Parking Status: " + (spots.availablePermits()) + " spots occupied)");
    }

    public int getTotalCarsServed() {
        return totalParked;
    }
}