package classes;

public class CarSchedule {
    private final int carId;
    private final int arrivalTime;
    private final int duration;

    public CarSchedule(int carId, int arrivalTime, int duration) {
        this.carId = carId;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
    }

    public int getCarId() { return carId; }
    public int getArrivalTime() { return arrivalTime; }
    public int getDuration() { return duration; }
}
