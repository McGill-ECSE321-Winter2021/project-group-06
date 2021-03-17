package ca.mcgill.ecse321.vehiclerepairshop.dto;

import java.sql.Date;
import java.sql.Time;

public class TimeSlotDto {
	
	private Time startTime;
	private Time endTime;
	private Date startDate;
	private Date endDate;
	private int timeslotId;
	
	public TimeSlotDto() {
		
	}
	
	public TimeSlotDto(Time startTime, Time endTime, Date startDate, Date endDate) {
		
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.timeslotId = startTime.hashCode()*startDate.hashCode();
		
	}
	
	
	public Time getStartTime() {
		return this.startTime;
	}
	
	public Time getEndTime() {
		return this.endTime;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public int getTimeslotId() {
		return this.timeslotId;
	}
	
	

}
