package vehiclerepairshop.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.OfferedServiceRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;



/**
 * 
 * @author mikewang
 *
 */
@ExtendWith(MockitoExtension.class)
public class TestOfferedServiceService {

	@Mock
	private AppointmentRepository appointmentDao;
	@Mock
	private OfferedServiceRepository offeredServiceDao;
	
	@InjectMocks
	private OfferedServiceService offeredServiceService;
	
	private static final String OFFERED_SERVICE_KEY = "TestID";
	private static final int APPOINTMENT_KEY = 1; 
	private static final String NONEXISTING_KEY = "NotATestID";
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(offeredServiceDao.findByOfferedServiceId(anyString())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(OFFERED_SERVICE_KEY)) {
				OfferedService offeredService = new OfferedService();
				offeredService.setOfferedServiceId(OFFERED_SERVICE_KEY);
				return offeredService;
			}
			else {
				return null;
			}
		});
		
		/**
		 * @TODO: finish this if understand how to implement
		 */
//		lenient().when(offeredServiceDao.findByAppointment(any(Appointment.class))).thenAnswer((InvocationOnMock invocation)->{
//			if (invocation.getArgument(0).equals(APPOINTMENT_KEY)) {
//				OfferedService offeredService = new OfferedService();
//				offeredService.setOfferedServiceId(OFFERED_SERVICE_KEY);
//				return offeredService;
//			}
//			else {
//				return null;
//			}
//		});
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(offeredServiceDao.save(any(OfferedService.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(appointmentDao.save(any(Appointment.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	/**
	 * testing if we can create an offeredService and stored in the persistence 
	 */
	@Test
	public void testCreatOfferedService() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = new OfferedService();
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			fail();
		}
		checkResultOfferedService(offeredService, testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
	}
	
	
	
	
	/**
	 * testing when we are creating an null OfferedService
	 */
	
	@Test
	public void testCreatNullOfferedService() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		
		String testOfferedServiceId =  null;
		int testOfferedServiceDuration = 0;
		Double testOfferedServicePrice = null;
		String testOfferedServiceName = null;
		Time testOfferedServiceReminderTime = null;
		int testOfferedServiceReminderDate = 0;
		String testOfferedServiceDescription = null;
		OfferedService offeredService = new OfferedService();
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("OfferedServiceId cannot be empty!price cannot be empty!name cannot be empty!duration cannot be zero!reminderTime cannot be empty!reminderDate cannot be zero!", error);
	}
	
	
	@Test
	public void testCreatOfferedServiceWithEmptyId() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = new OfferedService();
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("OfferedServiceId cannot be empty!", error);
	}
	
	@Test
	public void testCreatOfferedServiceWithSpaceId() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = " ";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = new OfferedService();
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("OfferedServiceId cannot be empty!", error);
	}
	
	
	@Test
	public void testCreatOfferedServiceWithEmptyDuration() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 0;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = new OfferedService();
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("duration cannot be zero!", error);
	}
	
	
	@Test
	public void testCreatOfferedServiceWithNegativeDuration() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = -1;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = new OfferedService();
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("duration cannot be negative!", error);
	}
	
	
	@Test
	public void testCreatOfferedServiceWithNegativeReminderDate() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = -1;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = new OfferedService();
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("reminderDate cannot be negative!", error);
	}
	
	@Test
	public void testCreatOfferedServiceWithZeroReminderDate() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 0;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = new OfferedService();
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("reminderDate cannot be zero!", error);
	}
	
	
	@Test
	public void testCreatOfferedServiceWithEmptyPrice() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 0;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = new OfferedService();
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("reminderDate cannot be zero!", error);
	}
	
	
	//------- helper method ------------------
	
	/**
	 * This is a helper method which can help us determine if all the attribute statisfies the input value
	 * @param offeredService
	 * @param offeredServiceId
	 * @param price
	 * @param name
	 * @param duration
	 * @param reminderTime
	 * @param reminderDate
	 * @param description
	 */
	private void checkResultOfferedService(OfferedService offeredService, String offeredServiceId, Double price, String name, 
			int duration, Time reminderTime, int reminderDate, String description) {
		assertNotNull(offeredService);
		assertEquals(offeredServiceId, offeredService.getOfferedServiceId());
		assertEquals(price, offeredService.getPrice());
		assertEquals(duration, offeredService.getDuration());
		assertEquals(reminderTime, offeredService.getReminderTime());
		assertEquals(reminderDate, offeredService.getReminderDate());
		assertEquals(description, offeredService.getDescription());
	}
	
	
	
}
	
