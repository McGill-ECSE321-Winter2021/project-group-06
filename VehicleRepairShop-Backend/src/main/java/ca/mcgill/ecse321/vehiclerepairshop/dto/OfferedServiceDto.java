package ca.mcgill.ecse321.vehiclerepairshop.dto;

import java.sql.Time;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author mikewang
 *
 */
public class OfferedServiceDto {

	private String offeredServiceId;
	private Double price;
	private String name;
	private int duration;
	private Time reminderTime;
	private int reminderDate;
	private String description;

	
	public OfferedServiceDto() {
	}
	
	public OfferedServiceDto(String offeredServiceId, Double price, String name, int duration, Time reminderTime, int reminderDate, String description) {
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
	
	public void setOfferedServiceId(String aOfferedServiceId) {
		this.offeredServiceId = aOfferedServiceId;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double aPrice) {
		this.price = aPrice;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String aName) {
		this.name = aName;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int aDuration) {
		this.duration = aDuration;
	}
	
	
	public Time getReminderTime() {
		return reminderTime;
	}
	
	public void setReminderTime(Time aTime) {
		this.reminderTime = aTime;
	}
	
	public int getReminderDate() {
		return reminderDate;
	}
	
	public void setReminderDate(int aDate) {
		this.reminderDate = aDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String aDescription) {
		this.description = aDescription;
	}
	
	
//	public void deleteOfferedService() {
//		this.delete();
//	}
}
