package classes;
import java.util.concurrent.Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class ParkingLot {
    private final Semaphore spots;
    private final int totalSpots;
    private final AtomicInteger totalParked = new AtomicInteger(0);
    private final AtomicInteger totalServed = new AtomicInteger(0);

    public ParkingLot(int totalSpots) {
        this.spots = new Semaphore(totalSpots, true);
        this.totalSpots = totalSpots;
    }

    public void enter(Car car) throws InterruptedException {
        long waitStartTime = System.currentTimeMillis();
        System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " arrived at time " + car.getArriveTime());

        if (spots.tryAcquire()) {
            System.out.print("Car " + car.getCarID() + " from Gate " + car.getGateID() + " parked immediately.");
        } else {
            System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " waiting for a spot.");
            spots.acquire();  // Wait until a spot is available
            long waitedTime = (System.currentTimeMillis() - waitStartTime) / 1000;
            System.out.print("Car " + car.getCarID() + " from Gate " + car.getGateID() + " parked after waiting " + waitedTime + " seconds.");
        }

        totalParked.incrementAndGet();
        totalServed.incrementAndGet();
        System.out.println("  Parking Status: " + totalParked.get() + " spots occupied.");
    }

    public void leaveCar(Car car) {
        spots.release();
        totalParked.decrementAndGet();
        System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " left after " + car.getParkDuration() + " seconds. (Parking Status: " + totalParked.get() + " spots occupied)");
    }

    public int getTotalCarsParked() {
        return totalParked.get();
    }

    public int getTotalCarsServed() {
        return totalServed.get();
    }

    public void details() {
        System.out.println("...\nTotal Cars Served: " + getTotalCarsServed());
        System.out.println("Current Cars in Parking: " + getTotalCarsParked());
    }
}
