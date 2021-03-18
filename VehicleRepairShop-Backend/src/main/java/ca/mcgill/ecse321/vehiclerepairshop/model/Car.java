package ca.mcgill.ecse321.vehiclerepairshop.model;


import java.util.*;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;


@Entity
public class Car
{
	

  public enum MotorType { Electric, Hybrid, Gas, Diesel }


  private String licensePlate;
  private String model;
  private int year;
  private MotorType motorType;

  private CustomerAccount owner;
  private List<Appointment> appointment;

  public void setLicensePlate(String aLicensePlate)
  {
    this.licensePlate = aLicensePlate;
  }

  public void setModel(String aModel)
  {
    this.model = aModel;
  }

  public void setYear(int aYear)
  {
    this.year = aYear;
  }

  public void setMotorType(MotorType aMotorType)
  {
    this.motorType = aMotorType;
  }

  @Id
  public String getLicensePlate()
  {
    return licensePlate;
  }

  public String getModel()
  {
    return model;
  }

  public int getYear()
  {
    return year;
  }

  public MotorType getMotorType()
  {
    return motorType;
  }


  @ManyToOne
  public CustomerAccount getOwner()
  {
    return owner;
  }

  @OneToMany(cascade = {CascadeType.ALL})
  public List<Appointment> getAppointment()
  {
    return this.appointment;
  }

  
  public void setAppointment(List<Appointment> appointment) {
	  this.appointment = appointment;
  }
  
  public void setOwner(CustomerAccount aOwner)

  {
   this.owner = aOwner;
  }
  
  

}

