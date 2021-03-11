
package ca.mcgill.ecse321.vehiclerepairshop.model;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class TimeSlot
{

<<<<<<< Updated upstream
=======

  private int timeSlotId;
>>>>>>> Stashed changes
  private Time startTime;
  private Time endTime;
  private Date startDate;
  private Date endDate;


<<<<<<< Updated upstream
  public boolean setStartTime(Time aStartTime)
=======

  public void setStartTime(Time aStartTime)
  {
    this.startTime = aStartTime;
  }
  
  public void setTimeSlotId(int aTimeSlotId)
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
  public int getTimeSlotId() 
  {
	  return this.timeSlotId;
  }
  

>>>>>>> Stashed changes
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