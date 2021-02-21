
package ca.mcgill.ecse321.vehiclerepairshop.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 56 "model.ump"
// line 116 "model.ump"
public class Service
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Service Attributes
  private String price;
  private String name;
  private String duration;
  private Date reminderDate;
  private Time reminderTime;
  private String description;

  //Service Associations
  private List<Appointment> appointment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Service(String aPrice, String aName, String aDuration, Date aReminderDate, Time aReminderTime, String aDescription)
  {
    price = aPrice;
    name = aName;
    duration = aDuration;
    reminderDate = aReminderDate;
    reminderTime = aReminderTime;
    description = aDescription;
    appointment = new ArrayList<Appointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPrice(String aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setDuration(String aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public boolean setReminderDate(Date aReminderDate)
  {
    boolean wasSet = false;
    reminderDate = aReminderDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setReminderTime(Time aReminderTime)
  {
    boolean wasSet = false;
    reminderTime = aReminderTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public String getPrice()
  {
    return price;
  }

  public String getName()
  {
    return name;
  }

  public String getDuration()
  {
    return duration;
  }

  public Date getReminderDate()
  {
    return reminderDate;
  }

  public Time getReminderTime()
  {
    return reminderTime;
  }

  public String getDescription()
  {
    return description;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointment()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Appointment addAppointment(String aAppointmentId, String aComment, Car aCar, Garage aGarage, TimeSlot aTimeSlot, Technician... allWorker)
  {
    return new Appointment(aAppointmentId, aComment, aCar, aGarage, this, aTimeSlot, allWorker);
  }

  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointment.contains(aAppointment)) { return false; }
    Service existingService = aAppointment.getService();
    boolean isNewService = existingService != null && !this.equals(existingService);
    if (isNewService)
    {
      aAppointment.setService(this);
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
    //Unable to remove aAppointment, as it must always have a service
    if (!this.equals(aAppointment.getService()))
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
    for(int i=appointment.size(); i > 0; i--)
    {
      Appointment aAppointment = appointment.get(i - 1);
      aAppointment.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "," +
            "name" + ":" + getName()+ "," +
            "duration" + ":" + getDuration()+ "," +
            "description" + ":" + getDescription()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reminderDate" + "=" + (getReminderDate() != null ? !getReminderDate().equals(this)  ? getReminderDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "reminderTime" + "=" + (getReminderTime() != null ? !getReminderTime().equals(this)  ? getReminderTime().toString().replaceAll("  ","    ") : "this" : "null");
  }
}




//import java.sql.Time;
//import java.util.*;
//import java.sql.Date;
//
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//
//@Entity
//public class Service
//{
//
//  private String price;
//  private String name;
//  private String duration;
//  private Time reminderTime;
//  private String description;
//  private List<Appointment> appointment;
//
//
//  public boolean setPrice(String aPrice)
//  {
//    boolean wasSet = false;
//    price = aPrice;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setName(String aName)
//  {
//    boolean wasSet = false;
//    name = aName;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setDuration(String aDuration)
//  {
//    boolean wasSet = false;
//    duration = aDuration;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setReminderTime(Time aReminderTime)
//  {
//    boolean wasSet = false;
//    reminderTime = aReminderTime;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setDescription(String aDescription)
//  {
//    boolean wasSet = false;
//    description = aDescription;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public String getPrice()
//  {
//    return price;
//  }
//
//  @Id
//  public String getName()
//  {
//    return name;
//  }
//
//  public String getDuration()
//  {
//    return duration;
//  }
//
//  public Time getReminderTime()
//  {
//    return reminderTime;
//  }
//
//  public String getDescription()
//  {
//    return description;
//  }
//
// 
//  @OneToMany(cascade = {CascadeType.ALL})
//  public List<Appointment> getAppointment()
//  {
//	  return this.appointment;
//  }
//  
//  public void setAppointment(List<Appointment> appointment) {
//	  this.appointment = appointment;
//  }
//
//
//}