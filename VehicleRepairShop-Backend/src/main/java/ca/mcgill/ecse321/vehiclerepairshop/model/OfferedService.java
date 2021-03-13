
package ca.mcgill.ecse321.vehiclerepairshop.model;
import java.sql.Time;
import java.sql.Date;
import java.util.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Entity;


@Entity
public class OfferedService
{

  private String offeredServiceId;
  private Double price;
  private String name;
  private Integer duration;
  private Time reminderTime;
  private Integer reminderDate;
  private String description;
  private List<Appointment> appointment;



  public void setPrice(Double aPrice)
  {
	  this.price = aPrice;
  }

  public void setName(String aName)

  {
	  this.name = aName; 
  }

  public void setDuration(int aDuration)
  {
	  this.duration = aDuration; 
  }

  public void setReminderTime(Time aReminderTime)
  {
    this.reminderTime = aReminderTime; 
  }

  
  public void setReminderDate(int aReminderDate)
  {
    this.reminderDate = aReminderDate; 

  }
  

  public void setDescription(String aDescription)
  {
    this.description = aDescription;

  }
  public void setOfferedServiceId(String aServiceId) {
	  this.offeredServiceId = aServiceId;
  }

  public Double getPrice()
  {
    return this.price;
  }

  @Id
  public String getOfferedServiceId()
  {
	  return this.offeredServiceId;
  }
  public String getName()
  {
    return this.name;
  }


  public int getDuration()
  {
    return this.duration;

  }

  public Time getReminderTime()
  {

    return this.reminderTime;

  }
  
  public int getReminderDate()
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
