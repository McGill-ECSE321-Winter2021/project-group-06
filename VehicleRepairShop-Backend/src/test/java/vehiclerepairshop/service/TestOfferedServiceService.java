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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
		OfferedService offeredService = null;
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
		OfferedService offeredService = null;
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("OfferedServiceId cannot be empty!price cannot be empty!name cannot be empty!duration cannot be zero!reminderTime cannot be empty!reminderDate cannot be zero!Offered service description cannot be empty!", error);
	}
	
	/**
	 * testing create an offeredService object with empty Id
	 */
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
		OfferedService offeredService = null;
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
	
	
	/**
	 * testing create an offeredService object with space Id
	 */
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
		OfferedService offeredService = null;
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
	
	/**
	 * testing create an offeredService object with empty duration
	 */
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
		OfferedService offeredService = null;
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
	
	/**
	 * testing create an offeredService object with negative duration
	 */
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
		OfferedService offeredService = null;
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
	
	/**
	 * testing create an offeredService object with negative reminder date
	 */
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
		OfferedService offeredService = null;
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
	
	
	/**
	 * testing create an offeredService object with zero reminder date
	 */
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
		OfferedService offeredService = null;
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
	
	/**
	 * testing create an offeredService object with empty price 
	 */
	@Test
	public void testCreatOfferedServiceWithEmptyPrice() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = null;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 10;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = null;
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("price cannot be empty!", error);
	}
	
	/**
	 * testing create an offeredService object with negative price
	 */
	@Test
	public void testCreatOfferedServiceWithNegativePrice() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = -10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 10;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = null;
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("price cannot be negative!", error);
	}
	
	/**
	 * testing create an offeredService object with empty service name 
	 */
	@Test
	public void testCreatOfferedServiceWithEmptyServiceName() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = null;
		String testOfferedServiceName = "";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 10;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = null;
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("name cannot be empty!", error);
	}
	
	
	/**
	 * testing create an offeredService object with space service name 
	 */
	@Test
	public void testCreatOfferedServiceWithSpaceServiceName() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = null;
		String testOfferedServiceName = " ";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 10;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = null;
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("name cannot be empty!", error);
	}
	
	
	/**
	 * testing create an offeredService object with empty reminder time
	 */
	@Test
	public void testCreatOfferedServiceWithEmptyReminderTime() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = null;
		int testOfferedServiceReminderDate = 10;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = null;
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("reminderTime cannot be empty!", error);
	}
	
	
	/**
	 * testing create an offeredService object with empty description
	 */
	@Test
	public void testCreatOfferedServiceWithEmptyDescription() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 0;
		String testOfferedServiceDescription = "";
		OfferedService offeredService = null;
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("Offered service description cannot be empty!", error);
	}
	
	
	/**
	 * testing create an offeredService object with space description
	 */
	@Test
	public void testCreatOfferedServiceWithSpaceDescription() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 0;
		String testOfferedServiceDescription = " ";
		OfferedService offeredService = null;
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(offeredService);
		assertEquals("Offered service description cannot be empty!", error);
	}
	
	
	/**
	 * testing getOfferedService with empty offeredServiceId
	 */
	@Test
	public void testGetOfferedServiceWithEmptyOfferedServiceId() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "Test1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = null;
		OfferedService extractedOfferedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			extractedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId("");
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(extractedOfferedService);
		assertEquals("offeredServiceId cannot be null!", error);
	}
	
	
	/**
	 * testing getOfferedService with space offeredServiceId
	 */
	@Test
	public void testGetOfferedServiceWithSpaceOfferedServiceId() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "Test1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = null;
		OfferedService extractedOfferedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			extractedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(" ");
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(extractedOfferedService);
		assertEquals("offeredServiceId cannot be null!", error);
	}
	
	
	/**
	 * testing getOfferedService with valid offeredServiceId
	 */
	@Test
	public void testGetOfferedServiceWithValidOfferedServiceId() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = null;
		OfferedService extractedOfferedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			extractedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId("Test1");
		}catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(extractedOfferedService);
		assertEquals(extractedOfferedService.getOfferedServiceId(),offeredService.getOfferedServiceId() );
	}
	
	/**
	 * testing getting offered service with valid appointment input 
	 */
	@Test
	public void testGetOfferedServiceWithValidAppointment() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = null;
		OfferedService extractedOfferedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		Appointment apt = new Appointment();
		apt.setAppointmentId(APPOINTMENT_KEY);
		appointmentDao.save(apt);
		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(apt);
		offeredService.setAppointment(appointments);
		try {
			extractedOfferedService = offeredServiceService.getOfferedServiceByAppointment(apt);
		}catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(extractedOfferedService);
		assertEquals(extractedOfferedService.getOfferedServiceId(),offeredService.getOfferedServiceId() );
	}
	
	
	/**
	 * testing getting offered service with null appointment input 
	 */
	@Test
	public void testGetOfferedServiceWithNullAppointment() {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = null;
		OfferedService extractedOfferedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		Appointment apt = null;
		appointmentDao.save(apt);
		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(apt);
		offeredService.setAppointment(appointments);
		try {
			extractedOfferedService = offeredServiceService.getOfferedServiceByAppointment(apt);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(extractedOfferedService);
		assertEquals("appointment cannot be null!", error);
	}
	
	/**
	 * testing delete offered service with a valid offeredServiceId
	 * @throws InvalidInputException
	 */
	@Test
	public void testDeleteOfferedServiceWithValidOfferedServiceId() throws InvalidInputException {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		Boolean isDeleted = false;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = null;
		OfferedService deletedOfferedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			isDeleted = offeredServiceService.deleteOfferedService(testOfferedServiceId);
		}catch (IllegalArgumentException e) {
			fail();
		}
		
		deletedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(testOfferedServiceId);
		assertNull(deletedOfferedService);
		assertEquals(true, isDeleted);
	}
	
	
	/**
	 * testing delete offered service with a empty offeredServiceId
	 * @throws InvalidInputException
	 */	
	@Test
	public void testDeleteOfferedServiceWithEmptyOfferedServiceId() throws InvalidInputException {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		Boolean isDeleted = false;
		String testOfferedServiceId = "TEST1";
		String deletedOfferedServiceId = "";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = null;
		OfferedService deletedOfferedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			isDeleted = offeredServiceService.deleteOfferedService(deletedOfferedServiceId);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		deletedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(testOfferedServiceId);
		assertNotNull(deletedOfferedService);
		assertEquals("the serviceId can not be empty!", error);
	}

	/**
	 * testing delete offered service with a space offeredServiceId
	 * @throws InvalidInputException
	 */
	@Test
	public void testDeleteOfferedServiceWithSpaceOfferedServiceId() throws InvalidInputException {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		Boolean isDeleted = false;
		String testOfferedServiceId = "TEST1";
		String deletedOfferedServiceId = " ";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = null;
		OfferedService deletedOfferedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			isDeleted = offeredServiceService.deleteOfferedService(deletedOfferedServiceId);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		deletedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(testOfferedServiceId);
		assertNotNull(deletedOfferedService);
		assertEquals("the serviceId can not be empty!", error);
		assertEquals(false, isDeleted);
	}
	
	
	
	/**
	 * testing delete offered service with a null offeredService
	 * @throws InvalidInputException
	 */
	@Test
	public void testDeleteOfferedServiceWithEmptyOfferedService() throws InvalidInputException {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		String testOfferedServiceId = "TEST1";
		String error = null;
		Boolean isDeleted = null;
		OfferedService deletedOfferedService = new OfferedService();
		try {
			isDeleted = offeredServiceService.deleteOfferedService(testOfferedServiceId);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		deletedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(testOfferedServiceId);
		assertNull(deletedOfferedService);
		assertEquals("the offered service can not found in the system!", error);
		assertEquals(false, isDeleted);
	}
	

	
	/**
	 * test updateOfferedService with valid input 
	 * @throws InvalidInputException
	 */
	@Test
	public void testUpdateOfferedServiceWithValidInput() throws InvalidInputException {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;

		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		int newTestOfferedServiceDuration = 20;
		Double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 40;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			modifiedService = offeredServiceService.updateService(testOfferedServiceId,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(modifiedService);
		checkResultOfferedService(modifiedService, testOfferedServiceId,
				newTestOfferedServicePrice,  newTestOfferedServiceName,
				newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
				newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
	}
	
	
	/**
	 * test updateOfferedService with all null or zero input 
	 * @throws InvalidInputException
	 */
	@Test
	public void testUpdateOfferedServiceWithAllNullOrZeroInput() throws InvalidInputException {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;

		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		int newTestOfferedServiceDuration = 0;
		Double newTestOfferedServicePrice = null;
		String newTestOfferedServiceName = null;
		Time newTestOfferedServiceReminderTime = null;
		int newTestOfferedServiceReminderDate = 0;
		String newTestOfferedServiceDescription = null;
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			modifiedService = offeredServiceService.updateService(testOfferedServiceId,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		checkResultOfferedService(offeredService, testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		assertEquals("price cannot be empty!name cannot be empty!duration cannot be zero!reminderTime cannot be empty!Offered service description cannot be empty!", error);
	}
	
	
	/**
	 * testing updateOfferedService with new duration as 0
	 * @throws InvalidInputException
	 */
	@Test
	public void testUpdateOfferedServiceWithZeroNewDuration() throws InvalidInputException {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;

		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		int newTestOfferedServiceDuration = 0;
		Double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 40;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			modifiedService = offeredServiceService.updateService(testOfferedServiceId,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		checkResultOfferedService(offeredService, testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		assertEquals("duration cannot be zero!", error);
	}
	
	
	
	
	/**
	 * testing updateOfferedService with new duration as negative number
	 * @throws InvalidInputException
	 */
	@Test
	public void testUpdateOfferedServiceWithNegativeNewDuration() throws InvalidInputException {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;

		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		int newTestOfferedServiceDuration = -1;
		Double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 40;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			modifiedService = offeredServiceService.updateService(testOfferedServiceId,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		checkResultOfferedService(offeredService, testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		assertEquals("duration cannot be negative!", error);
	}
	
	/**
	 * testing updateOfferedService with new price as negative number
	 * @throws InvalidInputException
	 */
	@Test
	public void testUpdateOfferedServiceWithNegativeNewPrice() throws InvalidInputException {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;

		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		int newTestOfferedServiceDuration = 20;
		Double newTestOfferedServicePrice = -20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 40;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			modifiedService = offeredServiceService.updateService(testOfferedServiceId,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		checkResultOfferedService(offeredService, testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		assertEquals("price cannot be Negative!", error);
	}
	
	
	/**
	 * testing updateOfferedService with new price as null
	 * @throws InvalidInputException
	 */
	@Test
	public void testUpdateOfferedServiceWithNullNewPrice() throws InvalidInputException {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;

		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		int newTestOfferedServiceDuration = 20;
		Double newTestOfferedServicePrice = null;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 40;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			modifiedService = offeredServiceService.updateService(testOfferedServiceId,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		checkResultOfferedService(offeredService, testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		assertEquals("price cannot be empty!", error);
	}
	
	
	
	@Test
	public void testUpdateOfferedServiceWithEmptyNewName() throws InvalidInputException {
		assertEquals(0, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;

		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		Double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		int newTestOfferedServiceDuration = 20;
		Double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 40;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			modifiedService = offeredServiceService.updateService(testOfferedServiceId,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		checkResultOfferedService(offeredService, testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		assertEquals("name cannot be empty!", error);
	}
	
	
	
	
	
	
	/**
	 * testing to extract an existing offered service 
	 */
	@Test
	public void testGetExistingOfferedService() {
		assertEquals(OFFERED_SERVICE_KEY, offeredServiceService.getOfferedServiceByOfferedServiceId(OFFERED_SERVICE_KEY).getOfferedServiceId());
	}
	
	
	/**
	 * testing to extract an non-existing offered service
	 */
	
	@Test
	public void testGetNonExistingOfferedService() {
		assertNull(offeredServiceService.getOfferedServiceByOfferedServiceId(NONEXISTING_KEY));
	}
	
	
	
	//----------------------------------- helper method --------------------------------------------------
	
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
	
