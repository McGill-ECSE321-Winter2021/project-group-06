package ca.mcgill.ecse321.vehiclerepairshop.model;


import java.util.*;
import java.sql.Time;
import java.sql.Date;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.Entity;



@Entity
public class Appointment
{


  private Time startTime;
  private Time endTime;
  private Date startDate;
  private Date endDate;
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
  public void setEndDate(Date endDate){
	  this.endDate = endDate;
  }
  public void setEndTime(Time endTime) {
	  this.endTime = endTime;
  }

  public void setAppointmentId(int aAppointmentId)
  {
	  this.appointmentId = aAppointmentId;
  }

  public void setStartTime(Time startTime) {
	  this.startTime = startTime;
  }
  
  public void setStartDate(Date startDate) {
	  this.startDate = startDate;
  }
  public Time getStartTime(){
		return this.startTime;
	}

  @Id
  public int getAppointmentId()
  {
    return appointmentId;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return this.endDate;
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

  @ManyToMany(cascade= {CascadeType.ALL})
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
