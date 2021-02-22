
package ca.mcgill.ecse321.vehiclerepairshop.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;

// line 20 "model.ump"
// line 125 "model.ump"
public class TechnicianAccount extends UserAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TechnicianAccount Associations
  private List<TimeSlot> availability;
  private List<Appointment> appointment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TechnicianAccount(String aName, String aPassword, String aUsername)
  {
    super(aName, aPassword, aUsername);
    availability = new ArrayList<TimeSlot>();
    appointment = new ArrayList<Appointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public TimeSlot getAvailability(int index)
  {
    TimeSlot aAvailability = availability.get(index);
    return aAvailability;
  }

  public List<TimeSlot> getAvailability()
  {
    List<TimeSlot> newAvailability = Collections.unmodifiableList(availability);
    return newAvailability;
  }

  public int numberOfAvailability()
  {
    int number = availability.size();
    return number;
  }

  public boolean hasAvailability()
  {
    boolean has = availability.size() > 0;
    return has;
  }

  public int indexOfAvailability(TimeSlot aAvailability)
  {
    int index = availability.indexOf(aAvailability);
    return index;
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
  public static int minimumNumberOfAvailability()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addAvailability(TimeSlot aAvailability)
  {
    boolean wasAdded = false;
    if (availability.contains(aAvailability)) { return false; }
    availability.add(aAvailability);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAvailability(TimeSlot aAvailability)
  {
    boolean wasRemoved = false;
    if (availability.contains(aAvailability))
    {
      availability.remove(aAvailability);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAvailabilityAt(TimeSlot aAvailability, int index)
  {  
    boolean wasAdded = false;
    if(addAvailability(aAvailability))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAvailability()) { index = numberOfAvailability() - 1; }
      availability.remove(aAvailability);
      availability.add(index, aAvailability);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAvailabilityAt(TimeSlot aAvailability, int index)
  {
    boolean wasAdded = false;
    if(availability.contains(aAvailability))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAvailability()) { index = numberOfAvailability() - 1; }
      availability.remove(aAvailability);
      availability.add(index, aAvailability);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAvailabilityAt(aAvailability, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointment()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointment.contains(aAppointment)) { return false; }
    appointment.add(aAppointment);
    if (aAppointment.indexOfWorker(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAppointment.addWorker(this);
      if (!wasAdded)
      {
        appointment.remove(aAppointment);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAppointment(Appointment aAppointment)
  {
    boolean wasRemoved = false;
    if (!appointment.contains(aAppointment))
    {
      return wasRemoved;
    }

    int oldIndex = appointment.indexOf(aAppointment);
    appointment.remove(oldIndex);
    if (aAppointment.indexOfWorker(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAppointment.removeWorker(this);
      if (!wasRemoved)
      {
        appointment.add(oldIndex,aAppointment);
      }
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
    availability.clear();
    ArrayList<Appointment> copyOfAppointment = new ArrayList<Appointment>(appointment);
    appointment.clear();
    for(Appointment aAppointment : copyOfAppointment)
    {
      if (aAppointment.numberOfWorker() <= Appointment.minimumNumberOfWorker())
      {
        aAppointment.delete();
      }
      else
      {
        aAppointment.removeWorker(this);
      }
    }
    super.delete();
  }

}

//import java.util.*;
//import javax.persistence.*;
//@Entity
//public class Technician extends Account
//{
//
//  private List<TimeSlot> availability;
//  private List<Appointment> appointment;
//
//  @ManyToMany
//  public List<TimeSlot> getAvailability()
//  {
//    List<TimeSlot> newAvailability = Collections.unmodifiableList(availability);
//    return newAvailability;
//  }
//  
//  public void setAvailability(List<TimeSlot> availability) {
//	  this.availability = availability;	  
//  }
//  
//  @ManyToMany
//  public List<Appointment> getAppointment()
//  {
//    return appointment;
//  }
//  
//  public void setAppointment(List<Appointment> appointment) {
//	  this.appointment = appointment;
//  }
//
//
//}