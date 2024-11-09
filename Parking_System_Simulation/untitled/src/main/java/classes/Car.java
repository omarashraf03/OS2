package classes;

public class Car extends Thread {
    public Gate gate;
    private final int carId;
    private final ParkingLot parkingLot;
    private final int duration;
    private final int arrivalTime;


    public Car(int carId , int arrivalTime, ParkingLot parkingLot, int duration) {
        this.carId = carId;
        this.arrivalTime = arrivalTime;
        this.parkingLot = parkingLot;
        this.duration = duration;
    }
    public int arrivalTime() {
        return arrivalTime;
    }
    public int GateId(){
        return gate.getGateId();
    }
    public int getParkDuration(){
        return duration;
    }



    @Override
    public void run() {
        
    }




}
