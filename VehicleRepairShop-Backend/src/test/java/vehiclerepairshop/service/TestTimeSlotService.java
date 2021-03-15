package vehiclerepairshop.service;
import static org.mockito.Mockito.lenient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.anyInt;
import static org.mockito.ArgumentMatchers.any;
import java.sql.Date;
import java.sql.Time;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.ArrayList;
import ca.mcgill.ecse321.vehiclerepairshop.dao.TimeSlotRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;

/**
 * 
 * @author chengchen
 *
 */
public class TestTimeSlotService {
	
	@Mock
	private TimeSlotRepository timeSlotRepository;

	@InjectMocks
	private TimeSlotService timeSlotService;

	private static final int TIMESLOTID = 12;
	private static final String STARTTIME = "10:00:00";
	private static final String ENDTIME = "11:00:00";
	private static final String STARTDATE = "2021-03-01";
	private static final String ENDDATE = "2021-03-01";
	
	
	

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setMockOutput() {
		MockitoAnnotations.initMocks(this);
	    lenient().when(timeSlotRepository.findByTimeSlotId(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(TIMESLOTID)) {
	            TimeSlot timeSlot = new TimeSlot();
	            timeSlot.setTimeSlotId(TIMESLOTID);
	        	timeSlot.setEndDate(Date.valueOf(ENDDATE));
				timeSlot.setEndTime(Time.valueOf(ENDTIME));
				timeSlot.setStartDate(Date.valueOf(STARTDATE));
				timeSlot.setStartTime(Time.valueOf(STARTTIME));
	            return timeSlot;
	        } else {
	            return null;
	        }
	    });
	    
	    lenient().when(timeSlotRepository.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
		            TimeSlot timeSlot = new TimeSlot();
		            timeSlot.setTimeSlotId(TIMESLOTID);
		         	timeSlot.setEndDate(Date.valueOf(ENDDATE));
					timeSlot.setEndTime(Time.valueOf(ENDTIME));
					timeSlot.setStartDate(Date.valueOf(STARTDATE));
					timeSlot.setStartTime(Time.valueOf(STARTTIME));
					List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
					timeSlots.add(timeSlot);
		            return timeSlots;
	    });
	    
	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
		lenient().when(timeSlotRepository.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateTimeSlot() {
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = timeSlotService.createTimeSlot(Time.valueOf(STARTTIME), Time.valueOf(ENDTIME), Date.valueOf(STARTDATE), Date.valueOf(ENDDATE));
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		TimeSlot timeSlot2 = timeSlotService.getTimeSlot(TIMESLOTID);
		assertNotNull(timeSlot);
		assertEquals(timeSlot2.getStartTime(), timeSlot.getStartTime());
		assertEquals(timeSlot2.getEndTime(), timeSlot.getEndTime());
		assertEquals(timeSlot2.getStartDate(), timeSlot.getStartDate());
		assertEquals(timeSlot2.getEndDate(), timeSlot.getEndDate());
	}
	

	@Test
	public void testgetAllTimeSlot() {
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		timeSlots = timeSlotService.getAllTimeSlots();
		TimeSlot timeSlot = timeSlots.get(0);
		assertNotNull(timeSlot);
		assertEquals(STARTTIME, timeSlot.getStartTime().toString());
		assertEquals(ENDTIME, timeSlot.getEndTime().toString());
		assertEquals(STARTDATE, timeSlot.getStartDate().toString());
		assertEquals(ENDDATE, timeSlot.getEndDate().toString());
	}
	
	@Test
	public void testDeleteTimeSlot() {
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = timeSlotService.deleteTimeSlot(TIMESLOTID);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		TimeSlot timeSlot2 = timeSlotService.getTimeSlot(TIMESLOTID);
		assertNotNull(timeSlot);
		assertEquals(timeSlot2.getStartTime(), timeSlot.getStartTime());
		assertEquals(timeSlot2.getEndTime(), timeSlot.getEndTime());
		assertEquals(timeSlot2.getStartDate(), timeSlot.getStartDate());
		assertEquals(timeSlot2.getEndDate(), timeSlot.getEndDate());
	}
	@Test
	public void testMissingStartTimeTimeSlot() {
		TimeSlot timeSlot = null;
		String error = "";
		try {
			timeSlot = timeSlotService.createTimeSlot(null, Time.valueOf(ENDTIME), Date.valueOf(STARTDATE), Date.valueOf(ENDDATE));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error,"start time cannot be empty!");
	}
	
	@Test
	public void testMissingStartDateTimeSlot() {
		TimeSlot timeSlot = null;
		String error = "";
		try {
			timeSlot = timeSlotService.createTimeSlot(Time.valueOf(STARTTIME), Time.valueOf(ENDTIME), null, Date.valueOf(ENDDATE));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error,"start date cannot be empty!");
	}
	
	@Test
	public void testMissingEndDateTimeSlot() {
		TimeSlot timeSlot = null;
		String error = "";
		try {
			timeSlot = timeSlotService.createTimeSlot(Time.valueOf(STARTTIME), Time.valueOf(ENDTIME), Date.valueOf(STARTDATE), null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error,"end date cannot be empty!");
	}
	
	
	@Test
	public void testMissingEndTimeTimeSlot() {
		TimeSlot timeSlot = null;
		String error = "";
		try {
			timeSlot = timeSlotService.createTimeSlot(Time.valueOf(STARTTIME), null, Date.valueOf(STARTDATE), Date.valueOf(ENDDATE));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error,"end time cannot be empty!");
	}
	
	
	@Test
	public void testStartAfterEndTimeTimeSlot() {
		TimeSlot timeSlot = null;
		String newStartTime = "10:10:00";
		String newEndTime = "10:00:00";
		String error = "";
		try {
			timeSlot = timeSlotService.createTimeSlot(Time.valueOf(newStartTime), Time.valueOf(newEndTime), Date.valueOf(STARTDATE), Date.valueOf(ENDDATE));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error,"start time can't be after end time");
	}
	
	@Test
	public void testStartAfterEndDateTimeSlot() {
		TimeSlot timeSlot = null;
		String newStartDate = "2021-03-02";
		String newEndDate = "2021-03-01";
		String error = "";
		try {
			timeSlot = timeSlotService.createTimeSlot(Time.valueOf(STARTTIME), Time.valueOf(ENDTIME), Date.valueOf(newStartDate), Date.valueOf(newEndDate));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error,"start date can't be after end date");
	}
	
	

	@Test 
	public void testInvalidDeleteTimeSlot() {
		TimeSlot timeSlot = null;
		String error = "";
		try {
			timeSlotService.deleteTimeSlot(1);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error,"timeslot not found");
	}
	
	
	@Test 
	public void testInvalidGetTimeSlot() {
		TimeSlot timeSlot = null;
		String error = "";
		try {
			timeSlotService.getTimeSlot(1);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error,"timeslot not found");
	}
	
	
	@Test
	public void testDeleteAllTimeSlot() {
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		timeSlots = timeSlotService.deleteAllTimeSlot();
		TimeSlot timeSlot = timeSlots.get(0);
		assertNotNull(timeSlot);
		assertEquals(STARTTIME, timeSlot.getStartTime().toString());
		assertEquals(ENDTIME, timeSlot.getEndTime().toString());
		assertEquals(STARTDATE, timeSlot.getStartDate().toString());
		assertEquals(ENDDATE, timeSlot.getEndDate().toString());
	}
	
	@Test
	public void testUpdateTimeSlot() {
		TimeSlot timeSlot = null;
		String newStartTime = "10:10:00";
		String newStartDate = "2021-03-02";
		String newEndDate = "2021-03-02";
		try {
			timeSlot = timeSlotService.updateTimeSlot(TIMESLOTID, Time.valueOf(newStartTime), Time.valueOf(ENDTIME), Date.valueOf(newStartDate), Date.valueOf(newEndDate));
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}

		assertNotNull(timeSlot);
		assertEquals(newStartTime, timeSlot.getStartTime().toString());
		assertEquals(ENDTIME, timeSlot.getEndTime().toString());
		assertEquals(newStartDate, timeSlot.getStartDate().toString());
		assertEquals(newEndDate, timeSlot.getEndDate().toString());
	}
	
	@Test
	public void testMissingStartTimeUpdateTimeSlot() {
		TimeSlot timeSlot = null;
		String error = "";
		try {
			timeSlot = timeSlotService.updateTimeSlot(TIMESLOTID, null,Time.valueOf(ENDTIME), Date.valueOf(STARTDATE), Date.valueOf(ENDDATE));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error,"start time cannot be empty!");
	}
	
	@Test
	public void testMissingStartDateUpdateTimeSlot() {
		TimeSlot timeSlot = null;
		String error = "";
		try {
			timeSlot = timeSlotService.updateTimeSlot(TIMESLOTID, Time.valueOf(STARTTIME), Time.valueOf(ENDTIME), null, Date.valueOf(ENDDATE));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error,"start date cannot be empty!");
	}
	
	@Test
	public void testMissingEndDateUpdateTimeSlot() {
		TimeSlot timeSlot = null;
		String error = "";
		try {
			timeSlot = timeSlotService.updateTimeSlot(TIMESLOTID,Time.valueOf(STARTTIME), Time.valueOf(ENDTIME), Date.valueOf(STARTDATE), null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error,"end date cannot be empty!");
	}
	
	
	@Test
	public void testMissingEndTimeUpdateTimeSlot() {
		TimeSlot timeSlot = null;
		String error = "";
		try {
			timeSlot = timeSlotService.updateTimeSlot(TIMESLOTID, Time.valueOf(STARTTIME), null, Date.valueOf(STARTDATE), Date.valueOf(ENDDATE));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error,"end time cannot be empty!");
	}
	
	
	@Test
	public void testStartAfterEndTimeUpdateTimeSlot() {
		TimeSlot timeSlot = null;
		String newStartTime = "10:10:00";
		String newEndTime = "10:00:00";
		String error = "";
		try {
			timeSlot = timeSlotService.updateTimeSlot(TIMESLOTID, Time.valueOf(newStartTime), Time.valueOf(newEndTime), Date.valueOf(STARTDATE), Date.valueOf(ENDDATE));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error,"start time can't be after end time");
	}
	
	@Test
	public void testStartAfterEndDateUpdateTimeSlot() {
		TimeSlot timeSlot = null;
		String newStartDate = "2021-03-02";
		String newEndDate = "2021-03-01";
		String error = "";
		try {
			timeSlot = timeSlotService.updateTimeSlot(TIMESLOTID, Time.valueOf(STARTTIME), Time.valueOf(ENDTIME), Date.valueOf(newStartDate), Date.valueOf(newEndDate));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error,"start date can't be after end date");
	}
	
	@Test
	public void testInvalidIdUpdateTimeSlot() {
		TimeSlot timeSlot = null;
		String newStartTime = "10:10:00";
		String newStartDate = "2021-03-02";
		String newEndDate = "2021-03-02";
		String error = "";
		try {
			timeSlot = timeSlotService.updateTimeSlot(1, Time.valueOf(newStartTime), Time.valueOf(ENDTIME), Date.valueOf(newStartDate), Date.valueOf(newEndDate));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals(error, "non-existing time slot");
	}
	
	

}
