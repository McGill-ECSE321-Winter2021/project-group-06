package vehiclerepairshop.dto;

import java.util.Collections;
import java.util.List;

import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;

public class CarDto {
	public enum MotorType { Electric, Hybrid, Gas, Diesel }


	  private String licensePlate;
	  private String model;
	  private int year;
	  private MotorType motorType;

	  private CustomerAccount owner;
	  private List<AppointmentDto> appointmentsDTO;
	  
	  CarDto(){
		  
	  }
	  
	  @SuppressWarnings("unchecked")
	  CarDto(String licensePlate, String model, int year, MotorType motorType, CustomerAccount owner){
		  this(licensePlate, model, year, motorType, owner, Collections.EMPTY_LIST);  
	  }
	  
	  CarDto(String licensePlate, String model, int year, MotorType motorType, CustomerAccount owner, List<AppointmentDto> appointments){
		  this.licensePlate = licensePlate;
		  this.model = model;
		  this.year = year;
		  this.motorType = motorType;
		  this.owner = owner;
		  this.appointmentsDTO = appointments;		  
	  }
	  
	  String getLicensePlate(){
		  return licensePlate;
	  }
	  
	  String getModel() {
		  return model;
	  }
	  
	  int getYear() {
		  return year;
	  }
	  
	  MotorType getMotorType() {
		  return motorType;
	  }
	  
	  CustomerAccount getOwner() {
		  return owner;
	  }
	  
	  List<AppointmentDto> getAppointments(){
		  return appointmentsDTO;
	  }
	  
	  void setLicensePlate(String licensePlate) {
		  this.licensePlate = licensePlate;
	  }
	  
	  void setModel(String model) {
		  this.model = model;
	  }
	  
	  void setYear(int year) {
		  this.year = year;
	  }
	  
	  void setOwner(CustomerAccount owner) {
		  this.owner = owner;
	  }
	  
	  void setAppointments(List<AppointmentDto> appointments) {
		  appointmentsDTO = appointments;
	  }
	  
	  void addAppointments(List<AppointmentDto> appointments) {
		  appointmentsDTO.addAll(appointments);
	  }

}