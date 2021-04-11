package ca.mcgill.ecse321.vehiclerepairshop.dto;

import ca.mcgill.ecse321.vehiclerepairshop.model.Car.MotorType;

public class CarDto {
	
	  private String licensePlate;
	  private String model;
	  private int year;
	  private MotorType motorType;
	  
	  public CarDto(){
		  
	  }

	  public CarDto(String licensePlate, String model, int year, MotorType motorType){
		  this.licensePlate = licensePlate;
		  this.model = model;
		  this.year = year;
		  this.motorType = motorType;
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
	  
	  
	  public void setLicensePlate(String licensePlate) {
		  this.licensePlate = licensePlate;
	  }
	  
	  public void setModel(String model) {
		  this.model = model;
	  }
	  
	  public void setYear(int year) {
		  this.year = year;
	  }
	 

}