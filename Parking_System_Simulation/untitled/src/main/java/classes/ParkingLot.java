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
        synchronized (System.out) {
            System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " arrived at time " + car.getArriveTime());
        }

        if (spots.tryAcquire()) {
            synchronized (System.out) {
                System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " (Parking Status: " + totalParked.incrementAndGet() + " spots occupied.)");
            }
        } else {
            synchronized (System.out) {
                System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " waiting for a spot.");
            }
            spots.acquire();
            long waitedTime = (System.currentTimeMillis() - waitStartTime) / 1000;
            waitedTime++;
            synchronized (System.out) {
                System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " parked after waiting " + waitedTime + " seconds.  (Parking Status: " + totalParked.incrementAndGet() + " spots occupied.)");
            }
        }

        totalServed.incrementAndGet();
    }

    public void leaveCar(Car car) {
        spots.release();
        int occupiedSpots = totalParked.decrementAndGet();
        synchronized (System.out) {
            System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " left after " + car.getParkDuration() + " seconds. (Parking Status: " + occupiedSpots + " spots occupied)");
        }
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
