package ca.mcgill.ecse321.vehiclerepairshop;

public class
Car {
    private String licensePlate;
    private String motorType;
    private int year;
    private String model;


    public Car(String licensePlate, String motorType, int year, String model) {
        this.licensePlate = licensePlate;
        this.motorType = motorType;
        this.year = year;
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getMotorType() {
        return motorType;
    }

    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setMotorType(String motorType) {
        this.motorType = motorType;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
