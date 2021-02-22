
package ca.mcgill.ecse321.vehiclerepairshop.model;
import java.sql.Time;
import java.sql.Date;
import java.util.*;
import java.sql.Date;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Entity;


@Entity
public class Service
{


  private String serviceId;
  private String price;
  private String name;
  private String duration;
  private Time reminderTime;
  private Date reminderDate;
  private String description;
  private List<Appointment> appointment;



  public void setPrice(String aPrice)
  {
	  this.price = aPrice;
  }

  public void setName(String aName)

  {
	  this.name = aName; 
  }

  public void setDuration(String aDuration)
  {
	  this.duration = aDuration; 
  }

  public void setReminderTime(Time aReminderTime)
  {
    this.reminderTime = aReminderTime; 
  }

  
  public void setReminderDate(Date aReminderDate)
  {
    this.reminderDate = aReminderDate; 

  }
  

  public void setDescription(String aDescription)
  {
    this.description = aDescription;

  }
  public void setServiceId(String aServiceId) {
	  this.serviceId = aServiceId;
  }

  public String getPrice()
  {
    return price;
  }

  @Id
  public String getServiceId()
  {
	  return this.serviceId;
  }
  public String getName()
  {
    return this.name;
  }


  public String getDuration()
  {
    return this.duration;

  }

  public Time getReminderTime()
  {

    return this.reminderTime;

  }
  
  public Date getReminderDate()
  {
    return this.reminderDate;
  }
  
  public String getDescription()
  {
    return this.description;
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
