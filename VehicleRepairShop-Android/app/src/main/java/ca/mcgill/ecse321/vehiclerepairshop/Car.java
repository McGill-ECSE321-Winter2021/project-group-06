package ca.mcgill.ecse321.vehiclerepairshop;

public class
Car {
    private String licensePlate;
    private String motorType;
    private int year;
    private String model;

    /**
     * @param licensePlate
     * @param motorType
     * @param year
     * @param model
     */
    public Car(String licensePlate, String motorType, int year, String model) {
        this.licensePlate = licensePlate;
        this.motorType = motorType;
        this.year = year;
        this.model = model;
    }

    /**
     * get license plate
     *
     * @return
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * get motor type
     *
     * @return
     */
    public String getMotorType() {
        return motorType;
    }

    /**
     * get year
     *
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * get model
     *
     * @return
     */
    public String getModel() {
        return model;
    }

    /**
     * set license plate
     *
     * @param licensePlate
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * set motor type
     *
     * @param motorType
     */
    public void setMotorType(String motorType) {
        this.motorType = motorType;
    }

    /**
     * set year
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

}
