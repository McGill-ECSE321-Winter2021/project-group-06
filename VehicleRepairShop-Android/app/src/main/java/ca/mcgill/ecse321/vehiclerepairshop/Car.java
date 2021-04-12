package ca.mcgill.ecse321.vehiclerepairshop;

public class
Car {
    private String licensePlate;
    private String owner;
    private String year;
    private String motorType;


    public Car(String licensePlate, String owner, String year, String motorType) {
        this.licensePlate = licensePlate;
        this.owner = owner;
        this.year = year;
        this.motorType = motorType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getOwner() {
        return owner;
    }

    public String getYear() {
        return year;
    }

    public String getMotorType() {
        return motorType;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setYear(String year) {
        this.year = year;
    }

}