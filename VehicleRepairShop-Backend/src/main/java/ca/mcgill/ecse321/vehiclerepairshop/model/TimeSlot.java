
package ca.mcgill.ecse321.vehiclerepairshop.model;
import javax.persistence.Entity;
import javax.persistence.Id;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Time;
import java.sql.Date;

// line 48 "model.ump"
// line 91 "model.ump"
@Entity
public class TimeSlot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TimeSlot Attributes
  private String timeSlotId;
  private Time startTime;
  private Time endTime;
  private Date startDate;
  private Date endDate;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TimeSlot(String aTimeSlotId, Time aStartTime, Time aEndTime, Date aStartDate, Date aEndDate)
  {
    timeSlotId = aTimeSlotId;
    startTime = aStartTime;
    endTime = aEndTime;
    startDate = aStartDate;
    endDate = aEndDate;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTimeSlotId(String aTimeSlotId)
  {
    boolean wasSet = false;
    timeSlotId = aTimeSlotId;
    wasSet = true;
    return wasSet;
  }

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

  @Id
  public String getTimeSlotId()
  {
    return timeSlotId;
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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "timeSlotId" + ":" + getTimeSlotId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}
//import java.sql.Date;
//import java.sql.Time;
//import javax.persistence.Id;
//import javax.persistence.Entity;
//
//@Entity
//public class TimeSlot
//{
//
//  private Time startTime;
//  private Time endTime;
//  private Date startDate;
//  private Date endDate;
//
//
//  public boolean setStartTime(Time aStartTime)
//  {
//    boolean wasSet = false;
//    startTime = aStartTime;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setEndTime(Time aEndTime)
//  {
//    boolean wasSet = false;
//    endTime = aEndTime;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setStartDate(Date aStartDate)
//  {
//    boolean wasSet = false;
//    startDate = aStartDate;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setEndDate(Date aEndDate)
//  {
//    boolean wasSet = false;
//    endDate = aEndDate;
//    wasSet = true;
//    return wasSet;
//  }
//  
//  @Id
//  public Time getStartTime()
//  {
//    return startTime;
//  }
//
//  public Time getEndTime()
//  {
//    return endTime;
//  }
//
//  public Date getStartDate()
//  {
//    return startDate;
//  }
//
//  public Date getEndDate()
//  {
//    return endDate;
//  }
//
//}