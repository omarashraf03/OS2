package classes;
import java.util.concurrent.Semaphore;

class ParkingLot {
    private final Semaphore spots;
    private final int totalSpots;
    private int totalParked = 0;
    private int totalServed = 0;

    public ParkingLot(int totalSpots) {
        this.spots = new Semaphore(totalSpots, true);
        this.totalSpots = totalSpots;
    }

    public synchronized void enter(Car car) throws InterruptedException {
        long waitStartTime = System.currentTimeMillis();
        System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " arrived at time " + car.getArriveTime());

        if (!spots.tryAcquire()) {
            System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " waiting for a spot.");
//            spots.acquire(); // Wait until a spot is available
            long waitedTime = (System.currentTimeMillis() - waitStartTime) / 1000; // Calculate wait time in seconds
            System.out.print("Car " + car.getCarID() + " from Gate " + car.getGateID() + " parked after waiting for " + waitedTime + " units of time.");
        } else {
            System.out.print("Car " + car.getCarID() + " from Gate " + car.getGateID() + " parked.");
        }
        totalParked++;
        totalServed++;


        System.out.println("Parking Status: " + totalParked + " spots occupied.");

//        Thread.sleep(car.getParkDuration() * 1000);
//        leaveCar(car);
    }



    public synchronized void leaveCar(Car car) {
        spots.release();
        totalParked--;

        System.out.println("Car " + car.getCarID() + " from Gate " + car.getGateID() + " left after " + car.getParkDuration() + " units of time. (Parking Status: " + totalParked + " spots occupied)");
    }

    public int getTotalCarsParked() {
        return totalParked;
    }

    public int getTotalCarsServed() {
        return totalServed;
    }

    public void details() {
        System.out.println("...\nTotal Cars Served: " + getTotalCarsServed());
        System.out.println("Current Cars in Parking: " + getTotalCarsParked());
    }
}