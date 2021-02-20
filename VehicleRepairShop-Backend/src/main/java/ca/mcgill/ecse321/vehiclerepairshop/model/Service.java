
package ca.mcgill.ecse321.vehiclerepairshop.model;

import java.sql.Time;
import java.util.*;
import java.sql.Date;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

@Entity
public class Service
{

  private String price;
  private String name;
  private String duration;
  private Time reminderTime;
  private String description;
  private List<Appointment> appointment;


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

  @Id
  public String getName()
  {
    return name;
  }

  public String getDuration()
  {
    return duration;
  }

  public Time getReminderTime()
  {
    return reminderTime;
  }

  public String getDescription()
  {
    return description;
  }

 
  @OneToMany(cascade = {CascadeType.ALL})
  public List<Appointment> getAppointment()
  {
	  return this.appointment;
  }
  
  public void setAppointment(List<Appointment> appointment) {
	  this.appointment = appointment;
  }


}