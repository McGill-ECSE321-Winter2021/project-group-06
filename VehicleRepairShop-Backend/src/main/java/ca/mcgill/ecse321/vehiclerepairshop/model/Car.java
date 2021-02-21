
package ca.mcgill.ecse321.vehiclerepairshop.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;
import java.sql.Time;
import java.sql.Date;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;

// line 26 "model.ump"
// line 103 "model.ump"
public class Car
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum MotorType { Electric, Hybrid, Gas, Diesel }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Car Attributes
  private String licensePlate;
  private String model;
  private int year;
  private MotorType motorType;

  //Car Associations
  private Customer owner;
  private List<Appointment> appointment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Car(String aLicensePlate, String aModel, int aYear, MotorType aMotorType, Customer aOwner)
  {
    licensePlate = aLicensePlate;
    model = aModel;
    year = aYear;
    motorType = aMotorType;
    boolean didAddOwner = setOwner(aOwner);
    if (!didAddOwner)
    {
      throw new RuntimeException("Unable to create car due to owner. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    appointment = new ArrayList<Appointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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
  /* Code from template association_GetOne */
  public Customer getOwner()
  {
    return owner;
  }
  /* Code from template association_GetMany */
  public Appointment getAppointment(int index)
  {
    Appointment aAppointment = appointment.get(index);
    return aAppointment;
  }

  public List<Appointment> getAppointment()
  {
    List<Appointment> newAppointment = Collections.unmodifiableList(appointment);
    return newAppointment;
  }

  public int numberOfAppointment()
  {
    int number = appointment.size();
    return number;
  }

  public boolean hasAppointment()
  {
    boolean has = appointment.size() > 0;
    return has;
  }

  public int indexOfAppointment(Appointment aAppointment)
  {
    int index = appointment.indexOf(aAppointment);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setOwner(Customer aOwner)
  {
    boolean wasSet = false;
    if (aOwner == null)
    {
      return wasSet;
    }

    Customer existingOwner = owner;
    owner = aOwner;
    if (existingOwner != null && !existingOwner.equals(aOwner))
    {
      existingOwner.removeCar(this);
    }
    owner.addCar(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointment()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Appointment addAppointment(String aAppointmentId, String aComment, Garage aGarage, Service aService, TimeSlot aTimeSlot, Technician... allWorker)
  {
    return new Appointment(aAppointmentId, aComment, this, aGarage, aService, aTimeSlot, allWorker);
  }

  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointment.contains(aAppointment)) { return false; }
    Car existingCar = aAppointment.getCar();
    boolean isNewCar = existingCar != null && !this.equals(existingCar);
    if (isNewCar)
    {
      aAppointment.setCar(this);
    }
    else
    {
      appointment.add(aAppointment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAppointment(Appointment aAppointment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAppointment, as it must always have a car
    if (!this.equals(aAppointment.getCar()))
    {
      appointment.remove(aAppointment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAppointmentAt(Appointment aAppointment, int index)
  {  
    boolean wasAdded = false;
    if(addAppointment(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointment()) { index = numberOfAppointment() - 1; }
      appointment.remove(aAppointment);
      appointment.add(index, aAppointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAppointmentAt(Appointment aAppointment, int index)
  {
    boolean wasAdded = false;
    if(appointment.contains(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointment()) { index = numberOfAppointment() - 1; }
      appointment.remove(aAppointment);
      appointment.add(index, aAppointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAppointmentAt(aAppointment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Customer placeholderOwner = owner;
    this.owner = null;
    if(placeholderOwner != null)
    {
      placeholderOwner.removeCar(this);
    }
    while (appointment.size() > 0)
    {
      Appointment aAppointment = appointment.get(appointment.size() - 1);
      aAppointment.delete();
      appointment.remove(aAppointment);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "licensePlate" + ":" + getLicensePlate()+ "," +
            "model" + ":" + getModel()+ "," +
            "year" + ":" + getYear()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "motorType" + "=" + (getMotorType() != null ? !getMotorType().equals(this)  ? getMotorType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "owner = "+(getOwner()!=null?Integer.toHexString(System.identityHashCode(getOwner())):"null");
  }
}


//import java.util.*;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.CascadeType;
//import javax.persistence.OneToMany;
//import javax.persistence.Entity;
//
//
//@Entity
//public class Car
//{
//
//  public enum MotorType { Electric, Hybrid, Gas, Diesel }
//
//
//  private String licensePlate;
//  private String model;
//  private int year;
//  private MotorType motorType;
//  private Customer owner;
//  private List<Appointment> appointment;
//
//
//  public boolean setLicensePlate(String aLicensePlate)
//  {
//    boolean wasSet = false;
//    licensePlate = aLicensePlate;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setModel(String aModel)
//  {
//    boolean wasSet = false;
//    model = aModel;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setYear(int aYear)
//  {
//    boolean wasSet = false;
//    year = aYear;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setMotorType(MotorType aMotorType)
//  {
//    boolean wasSet = false;
//    motorType = aMotorType;
//    wasSet = true;
//    return wasSet;
//  }
//
//  @Id
//  public String getLicensePlate()
//  {
//    return licensePlate;
//  }
//
//  public String getModel()
//  {
//    return model;
//  }
//
//  public int getYear()
//  {
//    return year;
//  }
//
//  public MotorType getMotorType()
//  {
//    return motorType;
//  }
//
//  @ManyToOne
//  public Customer getOwner()
//  {
//    return owner;
//  }
//
//  @OneToMany(cascade = {CascadeType.ALL})
//  public List<Appointment> getAppointment()
//  {
//    List<Appointment> newAppointment = Collections.unmodifiableList(appointment);
//    return newAppointment;
//  }
//  
//  public void setAppointment(List<Appointment> appointment) {
//	  this.appointment = appointment;
//  }
//  
//  public void setOwner(Customer aOwner)
//  {
//   this.owner = aOwner;
//  }
//
// 
//}