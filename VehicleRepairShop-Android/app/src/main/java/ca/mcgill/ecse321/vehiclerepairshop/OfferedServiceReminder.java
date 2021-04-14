package ca.mcgill.ecse321.vehiclerepairshop;

import java.sql.Time;

public class OfferedServiceReminder {

    private String offeredServiceId;
    private double price;
    private String name;
    private int duration;
    private Time reminderTime;
    private int reminderDate;
    private String description;

    /**
     * @param offeredServiceId
     * @param price
     * @param name
     * @param duration
     * @param reminderTime
     * @param reminderDate
     * @param description
     */
    public OfferedServiceReminder(String offeredServiceId, double price, String name, int duration, Time reminderTime, int reminderDate, String description) {
        this.offeredServiceId = offeredServiceId;
        this.price = price;
        this.name = name;
        this.duration = duration;
        this.reminderTime = reminderTime;
        this.reminderDate = reminderDate;
        this.description = description;
    }

    /**
     * get offered service id for reminder
     *
     * @return
     */
    public String getOfferedServiceId() {
        return offeredServiceId;
    }

    /**
     * set offered service id for reminder
     *
     * @param offeredServiceId
     */
    public void setOfferedServiceId(String offeredServiceId) {
        this.offeredServiceId = offeredServiceId;
    }

    /**
     * get offered service price for reminder
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * set offered service price for reminder
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * get offered service name for reminder
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set offered service name for reminder
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get offered service duration for reminder
     *
     * @return
     */
    public int getDuration() {
        return duration;
    }

    /**
     * set offered service duration for reminder
     *
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * get offered service reminder time for reminder
     *
     * @return
     */
    public Time getReminderTime() {
        return reminderTime;
    }

    /**
     * set offered service reminder time for reminder
     *
     * @param reminderTime
     */
    public void setReminderTime(Time reminderTime) {
        this.reminderTime = reminderTime;
    }

    /**
     * get offered service reminder date for reminder
     *
     * @return
     */
    public int getReminderDate() {
        return reminderDate;
    }

    /**
     * set offered service reminder date for reminder
     *
     * @param reminderDate
     */
    public void setReminderDate(int reminderDate) {
        this.reminderDate = reminderDate;
    }

    /**
     * get offered service description for reminder
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * set offered service description for reminder
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
