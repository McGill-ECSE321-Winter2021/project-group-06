package ca.mcgill.ecse321.vehiclerepairshop.dto;

import java.util.List;



public class AppointmentDto {
	
	 

	  private int appointmentId;
	  private String comment;
	  private CarDto carDto;
	  private GarageDto garageDto;
	  private OfferedServiceDto serviceDto;
	  private TimeSlotDto timeSlotDto;
	  private List<TechnicianAccountDto> workerDto;

	  public AppointmentDto() {
		  
	  }
	  
	  public AppointmentDto(TimeSlotDto timeSlotDto,CarDto carDto, String comment, GarageDto garageDto, List<TechnicianAccountDto> workerDto, OfferedServiceDto serviceDto) {
		  this.carDto = carDto;
		  this.garageDto = garageDto;
		  this.comment = comment;
		  this.serviceDto = serviceDto;
		  this.timeSlotDto = timeSlotDto;
		  this.workerDto = workerDto;
		  this.appointmentId = timeSlotDto.getStartTime().hashCode()*serviceDto.getName().hashCode();
	  }
	  
	  public GarageDto getGarage() {
		  return this.garageDto;
	  }
	  
	  public CarDto getCar() {
		  return this.carDto;
	  }
	  
	  public OfferedServiceDto getOfferedService() {
		  return this.serviceDto;
	  }
	  
	  public TimeSlotDto getTimeSlot() {
		  return this.timeSlotDto;
	  }
	  
	  public List<TechnicianAccountDto> getWorker(){
		  return this.workerDto;
	  }
	  public String getComment() {
		  return this.comment;
	  }
	  
	  public int getAppointmentId() {
		  return this.appointmentId;
	  }
}
