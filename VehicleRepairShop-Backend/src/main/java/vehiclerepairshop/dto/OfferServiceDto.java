package vehiclerepairshop.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.Collections;
import java.util.List;

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
/**
 * 
 * @author mikewang
 *
 */
public class OfferServiceDto {

	private String offeredServiceId;
	private String price;
	private String name;
	private String duration;
	private Time reminderTime;
	private Date reminderDate;
	private String description;
	private List<AppointmentDto> appointments;
	
	public OfferServiceDto() {
	}
	
	@SuppressWarnings("unchecked")
	public OfferServiceDto(String offeredServiceId, String price, String name, String duration, Time reminderTime, Date reminderDate, String description) {
		this(offeredServiceId, price, name, duration, reminderTime, reminderDate, description, Collections.EMPTY_LIST);
	}
	
	
	public OfferServiceDto(String offeredServiceId, String price, String name, String duration, Time reminderTime, Date reminderDate, String description, List<AppointmentDto> arrayList) {
		this.offeredServiceId = offeredServiceId;
		this.price = price;
		this.name = name;
		this.duration = duration;
		this.reminderTime = reminderTime;
		this.reminderDate = reminderDate;
		this.description = description;
		this.appointments = arrayList;
	}
	
	public String getOfferedServiceId() {
		return offeredServiceId;
	}
	
	public void setOfferedServiceId(String aOfferedServiceId) {
		this.offeredServiceId = aOfferedServiceId;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String aPrice) {
		this.price = aPrice;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String aName) {
		this.name = aName;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public void setDuration(String aDuration) {
		this.duration = aDuration;
	}
	
	
	public Time getReminderTime() {
		return reminderTime;
	}
	
	public void setReminderTime(Time aTime) {
		this.reminderTime = aTime;
	}
	
	public Date getReminderDate() {
		return reminderDate;
	}
	
	public void setReminderDate(Date aDate) {
		this.reminderDate = aDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String aDescription) {
		this.description = aDescription;
	}
	
	public List<AppointmentDto> getAppointments(){
		return appointments;
	}
	
	public void setAppointments(List<AppointmentDto> appointments) {
		this.appointments = appointments;
	}
}
