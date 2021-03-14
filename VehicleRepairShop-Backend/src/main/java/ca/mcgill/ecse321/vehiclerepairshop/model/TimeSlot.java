
package ca.mcgill.ecse321.vehiclerepairshop.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.Entity;


@Entity
public class TimeSlot
{

  private int timeSlotId;
  private Time startTime;
  private Time endTime;
  private Date startDate;
  private Date endDate;


  public void setStartTime(Time aStartTime)
  {
    this.startTime = aStartTime;
  }

  public void setTimeSlotId(int aTimeSlotId)
  {
	  this.timeSlotId = aTimeSlotId;
  }

  public void setEndTime(Time aEndTime)
  {
    this.endTime = aEndTime;
  }

  public void setStartDate(Date aStartDate)
  {
    this.startDate = aStartDate;
  }

  public void setEndDate(Date aEndDate)
  {
    this.endDate = aEndDate;
  }


  @Id
  public int getTimeSlotId()
  {
	  return this.timeSlotId;
  }

  public Time getStartTime()
  {
    return this.startTime;
  }

  public Time getEndTime()
  {
    return this.endTime;
  }

  public Date getStartDate()
  {
    return this.startDate;
  }

  public Date getEndDate()
  {
    return this.endDate;
  }
}
