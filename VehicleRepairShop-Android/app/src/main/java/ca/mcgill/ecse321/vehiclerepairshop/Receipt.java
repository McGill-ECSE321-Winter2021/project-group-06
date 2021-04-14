package ca.mcgill.ecse321.vehiclerepairshop;

public class Receipt {
    private int appointmentId;
    private String startTime;
    private String endTime;
    private String startDate;
    private String endDate;
    private String service;
    private double price;

    /**
     * @param appointmentId
     * @param startTime
     * @param endTime
     * @param startDate
     * @param endDate
     * @param service
     * @param price
     */
    public Receipt(int appointmentId, String startTime, String endTime, String startDate, String endDate, String service, double price) {
        this.appointmentId = appointmentId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.service = service;
        this.price = price;
    }

    /**
     * get appointment id for receipt
     *
     * @return
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * set appointment id for receipt
     *
     * @param appointmentId
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * get appointment start time for receipt
     *
     * @return
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * set appointment start time for receipt
     *
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * get appointment end time for receipt
     *
     * @return
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * set appointment end time for receipt
     *
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * get appointment start date for receipt
     *
     * @return
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * set appointment start date for receipt
     *
     * @param startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * get appointment end date for receipt
     *
     * @return
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * set appointment end date for receipt
     *
     * @param endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * get appointment service for receipt
     *
     * @return
     */
    public String getService() {
        return service;
    }

    /**
     * set apppointment service for receipt
     *
     * @param service
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * get appointment price for receipt
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * set appointment price for receipt
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
