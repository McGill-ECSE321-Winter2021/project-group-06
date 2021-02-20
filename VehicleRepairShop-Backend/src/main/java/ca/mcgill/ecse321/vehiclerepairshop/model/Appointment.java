
package ca.mcgill.ecse321.vehiclerepairshop.model;
import java.sql.Time;
import java.sql.Date;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

@Entity
public class Appointment
{


  private Time startTime;
  private Time endTime;
  private Date startDate;
  private Date endDate;
  private String comment;
  private Car car;
  private Garage garage;
  private Service service;
  private TimeSlot timeSlot;
  private List<Technician> worker;

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setComment(String aComment)
  {
    boolean wasSet = false;
    comment = aComment;
    wasSet = true;
    return wasSet;
  }

  @Id
  public Time getStartTime()
  {
    return startTime;
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
    return endDate;
  }

  public String getComment()
  {
    return comment;
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
  public Service getService()
  {
    return this.service;
  }
  @ManyToOne
  public TimeSlot getTimeSlot()
  {
    return this.timeSlot;
  }
 
  @ManyToMany
  public List<Technician> getWorker()
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

  public void setService(Service aService)
  {
    this.service = aService;
  }

  public void setTimeSlot(TimeSlot aNewTimeSlot)
  {
    this.timeSlot = aNewTimeSlot;
  }
  
  public void setWorker(List<Technician> worker)
  {
    this.worker = worker;
  }

}