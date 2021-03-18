package ca.mcgill.ecse321.vehiclerepairshop.dto;

import java.util.Collections;
import java.util.List;

import ca.mcgill.ecse321.vehiclerepairshop.model.Car.MotorType;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;

public class CarDto {
	
	  private String licensePlate;
	  private String model;
	  private int year;
	  private MotorType motorType;

	  private CustomerAccount owner;
	  private List<AppointmentDto> appointmentsDTO;
	  
	  public CarDto(){
		  
	  }
	  
	  public CarDto(String licensePlate, String model, int year, MotorType motorType){
		  this.licensePlate = licensePlate;
		  this.model = model;
		  this.year = year;
		  this.motorType = motorType;
	  }
	  
	  public CarDto(String licensePlate, String model, int year, MotorType motorType, CustomerAccount owner, List<AppointmentDto> appointments){
		  this.licensePlate = licensePlate;
		  this.model = model;
		  this.year = year;
		  this.motorType = motorType;
		  this.owner = owner;
		  this.appointmentsDTO = appointments;		  
	  }
	  
	  public String getLicensePlate(){
		  return licensePlate;
	  }
	  
	  public String getModel() {
		  return model;
	  }
	  
	  public int getYear() {
		  return year;
	  }
	  
	  public MotorType getMotorType() {
		  return motorType;
	  }
	  
	  public CustomerAccount getOwner() {
		  return owner;
	  }
	  
	  public List<AppointmentDto> getAppointments(){
		  return appointmentsDTO;
	  }
	  
	  public void setLicensePlate(String licensePlate) {
		  this.licensePlate = licensePlate;
	  }
	  
	  public void setModel(String model) {
		  this.model = model;
	  }
	  
	  public void setYear(int year) {
		  this.year = year;
	  }
	  
	  public void setOwner(CustomerAccount owner) {
		  this.owner = owner;
	  }
	  
	  public void setAppointments(List<AppointmentDto> appointments) {
		  appointmentsDTO = appointments;
	  }
	  
	  public void addAppointments(List<AppointmentDto> appointments) {
		  appointmentsDTO.addAll(appointments);
	  }

}