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


    public OfferedServiceReminder(String offeredServiceId, double price, String name, int duration, Time reminderTime, int reminderDate, String description) {
        this.offeredServiceId = offeredServiceId;
        this.price = price;
        this.name = name;
        this.duration = duration;
        this.reminderTime = reminderTime;
        this.reminderDate = reminderDate;
        this.description = description;
    }

    public String getOfferedServiceId() {
        return offeredServiceId;
    }

    public void setOfferedServiceId(String offeredServiceId) {
        this.offeredServiceId = offeredServiceId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Time getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Time reminderTime) {
        this.reminderTime = reminderTime;
    }

    public int getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(int reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
