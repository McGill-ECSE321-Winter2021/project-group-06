
package ca.mcgill.ecse321.vehiclerepairshop.model;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.*;


import java.util.*;
import javax.persistence.*;
@Entity
public class TechnicianAccount extends UserAccount
{

  private List<TimeSlot> availability;
  private List<Appointment> appointment;

  @ManyToMany
  public List<TimeSlot> getAvailability()
  {
    return this.availability;
  }
  
  public void setAvailability(List<TimeSlot> availability) {
	  this.availability = availability;	  
  }
  
  @ManyToMany
  public List<Appointment> getAppointment()
  {
    return appointment;
  }
  
  public void setAppointment(List<Appointment> appointment) {
	  this.appointment =  appointment;
  }


}