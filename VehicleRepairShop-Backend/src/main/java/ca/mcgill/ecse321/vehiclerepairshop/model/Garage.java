/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/
package ca.mcgill.ecse321.vehiclerepairshop.model;

import java.util.*;
import java.sql.Time;
import java.sql.Date;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;

// line 76 "model.ump"
// line 128 "model.ump"
public class Garage
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Garage Attributes
  private boolean isAvailable;
  private String garageId;

  //Garage Associations
  private List<Appointment> appointment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Garage(boolean aIsAvailable, String aGarageId)
  {
    isAvailable = aIsAvailable;
    garageId = aGarageId;
    appointment = new ArrayList<Appointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsAvailable(boolean aIsAvailable)
  {
    boolean wasSet = false;
    isAvailable = aIsAvailable;
    wasSet = true;
    return wasSet;
  }

  public boolean setGarageId(String aGarageId)
  {
    boolean wasSet = false;
    garageId = aGarageId;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsAvailable()
  {
    return isAvailable;
  }

  public String getGarageId()
  {
    return garageId;
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
  public Appointment addAppointment(Time aStartTime, Time aEndTime, Date aStartDate, Date aEndDate, String aComment, Car aCar, Service aService, TimeSlot aTimeSlot, Technician... allWorker)
  {
    return new Appointment(aStartTime, aEndTime, aStartDate, aEndDate, aComment, aCar, this, aService, aTimeSlot, allWorker);
  }

  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointment.contains(aAppointment)) { return false; }
    Garage existingGarage = aAppointment.getGarage();
    boolean isNewGarage = existingGarage != null && !this.equals(existingGarage);
    if (isNewGarage)
    {
      aAppointment.setGarage(this);
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
    //Unable to remove aAppointment, as it must always have a garage
    if (!this.equals(aAppointment.getGarage()))
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
            "isAvailable" + ":" + getIsAvailable()+ "," +
            "garageId" + ":" + getGarageId()+ "]";
  }
}