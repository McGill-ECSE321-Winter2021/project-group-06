
package ca.mcgill.ecse321.vehiclerepairshop.model;
import javax.persistence.Entity;
import javax.persistence.Id;

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


  public void setIsAvailable(boolean aIsAvailable)
  {
    this.isAvailable = aIsAvailable; 
  }

  public void setGarageId(String aGarageId)
  {
    this.garageId = aGarageId;
  }

  public boolean getIsAvailable()
  {
    return this.isAvailable;
  }

  @Id
  public String getGarageId()
  {
    return this.garageId;
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
