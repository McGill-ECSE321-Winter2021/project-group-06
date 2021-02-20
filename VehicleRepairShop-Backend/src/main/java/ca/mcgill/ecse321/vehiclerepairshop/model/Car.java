
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
  private Customer owner;
  private List<Appointment> appointment;


  public boolean setLicensePlate(String aLicensePlate)
  {
    boolean wasSet = false;
    licensePlate = aLicensePlate;
    wasSet = true;
    return wasSet;
  }

  public boolean setModel(String aModel)
  {
    boolean wasSet = false;
    model = aModel;
    wasSet = true;
    return wasSet;
  }

  public boolean setYear(int aYear)
  {
    boolean wasSet = false;
    year = aYear;
    wasSet = true;
    return wasSet;
  }

  public boolean setMotorType(MotorType aMotorType)
  {
    boolean wasSet = false;
    motorType = aMotorType;
    wasSet = true;
    return wasSet;
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
  public Customer getOwner()
  {
    return owner;
  }

  @OneToMany(cascade = {CascadeType.ALL})
  public List<Appointment> getAppointment()
  {
    List<Appointment> newAppointment = Collections.unmodifiableList(appointment);
    return newAppointment;
  }
  
  public void setAppointment(List<Appointment> appointment) {
	  this.appointment = appointment;
  }
  
  public void setOwner(Customer aOwner)
  {
   this.owner = aOwner;
  }

 
}