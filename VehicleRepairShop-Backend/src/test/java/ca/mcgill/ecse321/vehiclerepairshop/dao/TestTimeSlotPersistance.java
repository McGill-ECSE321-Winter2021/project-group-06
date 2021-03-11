package ca.mcgill.ecse321.vehiclerepairshop.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestTimeSlotPersistance {
	@Autowired
	private TimeSlotRepository timeslotRepository;
	
	@AfterEach
	public void clearDatabase() {
	
		timeslotRepository.deleteAll();

	}
	
	/*
	 * @author: James Darby
	 */
	
	@Test
	public void testPersistAndLoadTimeSlot() {
		
		String timeSlotId = "timeSlot1";
		Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 20));
		Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		timeSlot.setTimeSlotId(timeSlotId);
		
		timeslotRepository.save(timeSlot);
		
		timeSlot = null;
		timeSlot = timeslotRepository.findByTimeSlotId(timeSlotId);
		
		assertNotNull(timeSlot);
		assertEquals(timeSlot.getStartTime(), startTime);
		assertEquals(timeSlot.getStartDate(), startDate);
		assertEquals(timeSlot.getEndTime(), endTime);
		assertEquals(timeSlot.getEndDate(), endDate);
		
	}

}
