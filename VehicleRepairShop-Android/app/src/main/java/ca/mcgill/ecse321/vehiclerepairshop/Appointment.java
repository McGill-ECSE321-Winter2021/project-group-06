package ca.mcgill.ecse321.vehiclerepairshop;

public class Appointment {

    private String startTime;
    private String endTime;
    private String startDate;
    private String endDate;
    private String service;

    /**
     *
     * @param startTime
     * @param endTime
     * @param startDate
     * @param endDate
     * @param service
     */
    public Appointment(String startTime, String endTime, String startDate, String endDate, String service) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.service = service;
    }

    /**
     * Get start time of an appointment
     * @return
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Get end time of an appointment
     * @return
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Get start date of an appointment
     * @return
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Get end date of an appointment
     * @return
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Get service of an appointment
     * @return
     */
    public String getService() {
        return service;
    }

    /**
     * Set service of an appointment
     * @param service
     */
    public void setService(String service) {
        this.service = service;
    }
}
