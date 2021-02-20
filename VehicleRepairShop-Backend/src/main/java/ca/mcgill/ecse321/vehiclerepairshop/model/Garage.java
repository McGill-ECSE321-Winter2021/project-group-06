
package ca.mcgill.ecse321.vehiclerepairshop.model;

import java.util.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

@Entity
public class Garage
{

  private boolean isAvailable;
  private String garageId;
  private List<Appointment> appointment;


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

  @Id
  public String getGarageId()
  {
    return garageId;
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