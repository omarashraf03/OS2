package classes;

import java.util.List;

public class Gate extends Thread {
    private final int gateId;
    private final ParkingLot parkingLot;
    private final List<CarSchedule> carSchedule;
    private int carsServed = 0;

    public Gate(int gateId, ParkingLot parkingLot, List<CarSchedule> carSchedule) {
        this.gateId = gateId;
        this.parkingLot = parkingLot;
        this.carSchedule = carSchedule;
    }

    public int getCarsServed() {
        return carsServed;
    }
    public int getGateId(){
        return gateId;
    }



    @Override
    public void run() {
        
    }
}
