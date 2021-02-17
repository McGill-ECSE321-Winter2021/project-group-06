/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.vehiclerepairshop.model;
import java.sql.Time;
import java.sql.Date;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;

// line 37 "model.ump"
// line 133 "model.ump"
public class Appointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointment Attributes
  private Time startTime;
  private Time endTime;
  private Date startDate;
  private Date endDate;
  private String comment;

  //Appointment Associations
  private Car car;
  private Garage garage;
  private Service service;
  private TimeSlot timeSlot;
  private List<Technician> worker;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Appointment(Time aStartTime, Time aEndTime, Date aStartDate, Date aEndDate, String aComment, Car aCar, Garage aGarage, Service aService, TimeSlot aTimeSlot, Technician... allWorker)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    startDate = aStartDate;
    endDate = aEndDate;
    comment = aComment;
    boolean didAddCar = setCar(aCar);
    if (!didAddCar)
    {
      throw new RuntimeException("Unable to create appointment due to car. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGarage = setGarage(aGarage);
    if (!didAddGarage)
    {
      throw new RuntimeException("Unable to create appointment due to garage. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddService = setService(aService);
    if (!didAddService)
    {
      throw new RuntimeException("Unable to create appointment due to service. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setTimeSlot(aTimeSlot))
    {
      throw new RuntimeException("Unable to create Appointment due to aTimeSlot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    worker = new ArrayList<Technician>();
    boolean didAddWorker = setWorker(allWorker);
    if (!didAddWorker)
    {
      throw new RuntimeException("Unable to create Appointment, must have at least 1 worker. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

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
  /* Code from template association_GetOne */
  public Car getCar()
  {
    return car;
  }
  /* Code from template association_GetOne */
  public Garage getGarage()
  {
    return garage;
  }
  /* Code from template association_GetOne */
  public Service getService()
  {
    return service;
  }
  /* Code from template association_GetOne */
  public TimeSlot getTimeSlot()
  {
    return timeSlot;
  }
  /* Code from template association_GetMany */
  public Technician getWorker(int index)
  {
    Technician aWorker = worker.get(index);
    return aWorker;
  }

  public List<Technician> getWorker()
  {
    List<Technician> newWorker = Collections.unmodifiableList(worker);
    return newWorker;
  }

  public int numberOfWorker()
  {
    int number = worker.size();
    return number;
  }

  public boolean hasWorker()
  {
    boolean has = worker.size() > 0;
    return has;
  }

  public int indexOfWorker(Technician aWorker)
  {
    int index = worker.indexOf(aWorker);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCar(Car aCar)
  {
    boolean wasSet = false;
    if (aCar == null)
    {
      return wasSet;
    }

    Car existingCar = car;
    car = aCar;
    if (existingCar != null && !existingCar.equals(aCar))
    {
      existingCar.removeAppointment(this);
    }
    car.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGarage(Garage aGarage)
  {
    boolean wasSet = false;
    if (aGarage == null)
    {
      return wasSet;
    }

    Garage existingGarage = garage;
    garage = aGarage;
    if (existingGarage != null && !existingGarage.equals(aGarage))
    {
      existingGarage.removeAppointment(this);
    }
    garage.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setService(Service aService)
  {
    boolean wasSet = false;
    if (aService == null)
    {
      return wasSet;
    }

    Service existingService = service;
    service = aService;
    if (existingService != null && !existingService.equals(aService))
    {
      existingService.removeAppointment(this);
    }
    service.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setTimeSlot(TimeSlot aNewTimeSlot)
  {
    boolean wasSet = false;
    if (aNewTimeSlot != null)
    {
      timeSlot = aNewTimeSlot;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfWorkerValid()
  {
    boolean isValid = numberOfWorker() >= minimumNumberOfWorker();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWorker()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addWorker(Technician aWorker)
  {
    boolean wasAdded = false;
    if (worker.contains(aWorker)) { return false; }
    worker.add(aWorker);
    if (aWorker.indexOfAppointment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aWorker.addAppointment(this);
      if (!wasAdded)
      {
        worker.remove(aWorker);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeWorker(Technician aWorker)
  {
    boolean wasRemoved = false;
    if (!worker.contains(aWorker))
    {
      return wasRemoved;
    }

    if (numberOfWorker() <= minimumNumberOfWorker())
    {
      return wasRemoved;
    }

    int oldIndex = worker.indexOf(aWorker);
    worker.remove(oldIndex);
    if (aWorker.indexOfAppointment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aWorker.removeAppointment(this);
      if (!wasRemoved)
      {
        worker.add(oldIndex,aWorker);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setWorker(Technician... newWorker)
  {
    boolean wasSet = false;
    ArrayList<Technician> verifiedWorker = new ArrayList<Technician>();
    for (Technician aWorker : newWorker)
    {
      if (verifiedWorker.contains(aWorker))
      {
        continue;
      }
      verifiedWorker.add(aWorker);
    }

    if (verifiedWorker.size() != newWorker.length || verifiedWorker.size() < minimumNumberOfWorker())
    {
      return wasSet;
    }

    ArrayList<Technician> oldWorker = new ArrayList<Technician>(worker);
    worker.clear();
    for (Technician aNewWorker : verifiedWorker)
    {
      worker.add(aNewWorker);
      if (oldWorker.contains(aNewWorker))
      {
        oldWorker.remove(aNewWorker);
      }
      else
      {
        aNewWorker.addAppointment(this);
      }
    }

    for (Technician anOldWorker : oldWorker)
    {
      anOldWorker.removeAppointment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWorkerAt(Technician aWorker, int index)
  {  
    boolean wasAdded = false;
    if(addWorker(aWorker))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWorker()) { index = numberOfWorker() - 1; }
      worker.remove(aWorker);
      worker.add(index, aWorker);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWorkerAt(Technician aWorker, int index)
  {
    boolean wasAdded = false;
    if(worker.contains(aWorker))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWorker()) { index = numberOfWorker() - 1; }
      worker.remove(aWorker);
      worker.add(index, aWorker);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWorkerAt(aWorker, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Car placeholderCar = car;
    this.car = null;
    if(placeholderCar != null)
    {
      placeholderCar.removeAppointment(this);
    }
    Garage placeholderGarage = garage;
    this.garage = null;
    if(placeholderGarage != null)
    {
      placeholderGarage.removeAppointment(this);
    }
    Service placeholderService = service;
    this.service = null;
    if(placeholderService != null)
    {
      placeholderService.removeAppointment(this);
    }
    timeSlot = null;
    ArrayList<Technician> copyOfWorker = new ArrayList<Technician>(worker);
    worker.clear();
    for(Technician aWorker : copyOfWorker)
    {
      aWorker.removeAppointment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "comment" + ":" + getComment()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "car = "+(getCar()!=null?Integer.toHexString(System.identityHashCode(getCar())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "garage = "+(getGarage()!=null?Integer.toHexString(System.identityHashCode(getGarage())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "service = "+(getService()!=null?Integer.toHexString(System.identityHashCode(getService())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "timeSlot = "+(getTimeSlot()!=null?Integer.toHexString(System.identityHashCode(getTimeSlot())):"null");
  }
}