
package ca.mcgill.ecse321.vehiclerepairshop.model;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class TimeSlot
{

  private Time startTime;
  private Time endTime;
  private Date startDate;
  private Date endDate;


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

}