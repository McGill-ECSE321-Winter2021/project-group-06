
package ca.mcgill.ecse321.vehiclerepairshop.model;
import java.util.*;
import javax.persistence.*;
@Entity
public class Technician extends Account
{

  private List<TimeSlot> availability;
  private List<Appointment> appointment;

  @ManyToMany
  public List<TimeSlot> getAvailability()
  {
    List<TimeSlot> newAvailability = Collections.unmodifiableList(availability);
    return newAvailability;
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
	  this.appointment = appointment;
  }


}