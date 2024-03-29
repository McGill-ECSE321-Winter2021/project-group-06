package ca.mcgill.ecse321.vehiclerepairshop.model;


import java.util.*;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.FetchType;



@Entity
public class Appointment
{
	
  private int appointmentId;
  private String comment;
  private Car car;
  private Garage garage;
  private OfferedService service;
  private TimeSlot timeSlot;
  private List<TechnicianAccount> worker;




  public void setComment(String aComment)
  {
	  this.comment = aComment;

  }

  public void setAppointmentId(int aAppointmentId)
  {
	  this.appointmentId = aAppointmentId;
  }

  @Id
  public int getAppointmentId()
  {
    return this.appointmentId;
  }

  public String getComment()
  {
    return this.comment;
  }

  @ManyToOne
  public Car getCar()
  {
    return this.car;
  }
  @ManyToOne
  public Garage getGarage()
  {
    return this.garage;
  }
  @ManyToOne
  public OfferedService getOfferedService()
  {
    return this.service;
  }
  @ManyToOne
  public TimeSlot getTimeSlot()
  {

    return this.timeSlot;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  public List<TechnicianAccount> getWorker()

  {
    return this.worker;
  }


  public void setCar(Car aCar)
  {
    this.car = aCar;
  }

  public void setGarage(Garage aGarage)
  {
    this.garage = aGarage;
  }

  public void setOfferedService(OfferedService aService)
  {
    this.service = aService;
  }

  public void setTimeSlot(TimeSlot aNewTimeSlot)
  {

    this.timeSlot = aNewTimeSlot;
  }

  public void setWorker(List<TechnicianAccount> worker)
  {
    this.worker = worker;
  }

}
