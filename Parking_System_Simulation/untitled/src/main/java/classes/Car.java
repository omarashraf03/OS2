package classes;

public class Car extends Thread {
    private final int carId;
    private final ParkingLot parkingLot;
    private final int duration;


    public Car(int carId, ParkingLot parkingLot, int duration) {
        this.carId = carId;
        this.parkingLot = parkingLot;
        this.duration = duration;
    }



    @Override
    public void run() {}




}
