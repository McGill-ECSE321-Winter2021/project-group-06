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

import org.junit.jupiter.api.AfterEach;
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

	private Appointment apt = new Appointment();
	private static final String OFFERED_SERVICE_KEY = "Test1";
	private static final String EMPTY_SERVICE_KEY = "";
	private static int DURATION = 10;
	private static double PRICE = 10.0;
	private static String NAME = "wash";
	private static Time REMINDER_TIME= java.sql.Time.valueOf(LocalTime.of(11, 35));
	private static int REMINDER_DATE = 30;
	private static String DESCRIPTION = "this is a testing Wash service";
	private static final int APPOINTMENT_KEY = 1; 
	private static final String NONEXISTING_KEY = "NotATestID";
	
	
	@Mock
	private AppointmentRepository appointmentRepository;
	@Mock
	private OfferedServiceRepository offeredServiceRepository;
	
	@InjectMocks
	private OfferedServiceService offeredServiceService;

	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(offeredServiceRepository.findByOfferedServiceId(anyString())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(OFFERED_SERVICE_KEY)) {
				OfferedService offeredService = new OfferedService();
				offeredService.setOfferedServiceId(OFFERED_SERVICE_KEY);
				offeredService.setName(NAME);
				offeredService.setPrice(PRICE);
				offeredService.setReminderTime(REMINDER_TIME);
				offeredService.setReminderDate(REMINDER_DATE);
				offeredService.setDescription(DESCRIPTION);
				offeredService.setDuration(DURATION);
				return offeredService;
			}
			else {
				return null;
			}
		});
		
		/**
		 * @TODO: finish this if understand how to implement
		 */
		lenient().when(offeredServiceRepository.findByAppointment(any(Appointment.class))).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(apt)) {
				apt.setAppointmentId(APPOINTMENT_KEY);
				List<Appointment> apts = new ArrayList<Appointment>(); 
				apts.add(apt);
				OfferedService offeredService = new OfferedService();
				offeredService.setOfferedServiceId(OFFERED_SERVICE_KEY);
				offeredService.setName(NAME);
				offeredService.setPrice(PRICE);
				offeredService.setReminderTime(REMINDER_TIME);
				offeredService.setReminderDate(REMINDER_DATE);
				offeredService.setDescription(DESCRIPTION);
				offeredService.setDuration(DURATION);
				offeredService.setAppointment(apts);
				return offeredService;
			}
			else {
				return null;
			}
			
		});
		
		
		lenient().when(offeredServiceRepository.findAll()).thenAnswer((InvocationOnMock invocation)->{
			apt.setAppointmentId(APPOINTMENT_KEY);
			List<Appointment> apts = new ArrayList<Appointment>(); 
			apts.add(apt);
			OfferedService offeredService = new OfferedService();
			offeredService.setOfferedServiceId(OFFERED_SERVICE_KEY);
			offeredService.setName(NAME);
			offeredService.setPrice(PRICE);
			offeredService.setReminderTime(REMINDER_TIME);
			offeredService.setReminderDate(REMINDER_DATE);
			offeredService.setDescription(DESCRIPTION);
			offeredService.setDuration(DURATION);
			offeredService.setAppointment(apts);
			
			List<OfferedService> offeredServices = new ArrayList<OfferedService>();
			offeredServices.add(offeredService);
			return offeredServices;
		});
		
		
		
		//Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(offeredServiceRepository.save(any(OfferedService.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(appointmentRepository.save(any(Appointment.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@AfterEach
	public void clearDatabase() {
			//order matters
			offeredServiceRepository.deleteAll();
	
		}
	
	
	
	
	
	
	/**
	 * testing if we can create an offeredService and stored in the persistence 
	 */
	@Test
	public void testCreateOfferedServiceSuccessfully() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		
		String error = "";
		
		String testOfferedServiceId = "Test2";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		OfferedService offeredService = null;
		try {
			offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
					testOfferedServicePrice,  testOfferedServiceName, testOfferedServiceDuration,
					testOfferedServiceReminderTime, testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		
		assertEquals("",error);
		checkResultOfferedService(offeredService, testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
	}
	
	
	/**
	 * testing if we can create an offeredService with an exisiting Id 
	 */
	@Test
	public void testCreateAnotherOfferedServiceWithTheSameId() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
	
		String error = "";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		
		OfferedService offeredService = null;
		try {
			offeredService = offeredServiceService.createOfferedService(OFFERED_SERVICE_KEY,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (IllegalArgumentException e){
			error = error + e.getMessage();
		}
		
		assertEquals("can not create OfferedService with same Id!", error);
	}
	
	
	
	/**
	 * testing when we are creating an null OfferedService
	 */
	
	@Test
	public void testCreateNullOfferedService() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		
		String testOfferedServiceId =  null;
		int testOfferedServiceDuration = 0;
		double testOfferedServicePrice = 0.0;
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
		assertEquals("OfferedServiceId cannot be empty!name cannot be empty!duration cannot be zero!reminderTime cannot be empty!reminderDate cannot be zero!Offered service description cannot be empty!", error);
	}
	
	/**
	 * testing create an offeredService object with empty Id
	 */
	@Test
	public void testCreateOfferedServiceWithEmptyId() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
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
	public void testCreateOfferedServiceWithSpaceId() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = " ";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
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
	public void testCreateOfferedServiceWithEmptyDuration() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 0;
		double testOfferedServicePrice = 10.0;
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
	public void testCreateOfferedServiceWithNegativeDuration() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = -1;
		double testOfferedServicePrice = 10.0;
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
	public void testCreateOfferedServiceWithNegativeReminderDate() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
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
	public void testCreateOfferedServiceWithZeroReminderDate() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
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
	 * testing create an offeredService object with negative price
	 */
	@Test
	public void testCreateOfferedServiceWithNegativePrice() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = -10.0;
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
	public void testCreateOfferedServiceWithEmptyServiceName() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
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
	public void testCreateOfferedServiceWithSpaceServiceName() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
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
	public void testCreateOfferedServiceWithEmptyReminderTime() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
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
	public void testCreateOfferedServiceWithEmptyDescription() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 10;
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
	public void testCreateOfferedServiceWithSpaceDescription() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "TEST1";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 10;
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
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "testGetOfferedServiceWithEmptyOfferedServiceId";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
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
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "testGetOfferedServiceWithSpaceOfferedServiceId";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
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
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = "";

		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService offeredService = null;
		OfferedService extractedOfferedService = null;
		try {
			extractedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(OFFERED_SERVICE_KEY);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("",error);
		assertNotNull(extractedOfferedService);
		checkResultOfferedService(extractedOfferedService, OFFERED_SERVICE_KEY,testOfferedServicePrice,
				testOfferedServiceName,testOfferedServiceDuration,testOfferedServiceReminderTime,
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		
	}
	

	
	/**
	 * testing getOfferedService  with valid appointment input 
	 */
	@Test
	public void testGetOfferedServiceWithValidAppointment() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = "";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService extractedOfferedService = null;
		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(apt);


		try {
			extractedOfferedService = offeredServiceService.getOfferedServiceByAppointment(apt);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("", error);
		assertNotNull(extractedOfferedService);
		checkResultOfferedServicePlusAppointment(extractedOfferedService,OFFERED_SERVICE_KEY,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription,appointments);
	}
	
	
	/**
	 * testing getOfferedService  with null appointment input 
	 */
	@Test
	public void testGetOfferedServiceWithNullAppointment() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "testGetOfferedServiceWithNullAppointment";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
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
		appointmentRepository.save(apt);
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
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = "";
		Boolean isDeleted = false;
		
		try {
			isDeleted = offeredServiceService.deleteOfferedService(OFFERED_SERVICE_KEY);
		}catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals("",error);
		assertEquals(true, isDeleted);
	}
	
	
	/**
	 * testing delete offered service with a empty offeredServiceId
	 *
	 */	
	@Test
	public void testDeleteOfferedServiceWithEmptyOfferedServiceId() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		Boolean isDeleted = false;
		//String testOfferedServiceId = "TEST1";
		String deletedOfferedServiceId = "";
		OfferedService deletedOfferedService = null;
		try {
			isDeleted = offeredServiceService.deleteOfferedService(deletedOfferedServiceId);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(false, isDeleted);
		assertNull(deletedOfferedService);
		assertEquals("the serviceId can not be empty!the offered service can not found in the system!", error);
	}

	/**
	 * testing delete offered service with a space offeredServiceId
	 * 
	 */
	@Test
	public void testDeleteOfferedServiceWithSpaceOfferedServiceId() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		Boolean isDeleted = false;
		
		String deletedOfferedServiceId = " ";
		OfferedService deletedOfferedService = null;

		try {
			isDeleted = offeredServiceService.deleteOfferedService(deletedOfferedServiceId);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		deletedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(OFFERED_SERVICE_KEY);
		assertNotNull(deletedOfferedService);
		assertEquals("the serviceId can not be empty!the offered service can not found in the system!", error);
		assertEquals(false, isDeleted);
	}
	
	
	
	/**
	 * testing delete offered service with a null offeredService
	 * @throws InvalidInputException
	 */
	@Test
	public void testDeleteOfferedServiceWithEmptyOfferedService() throws InvalidInputException {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String testOfferedServiceId = "TEST2";
		String error = "";
		Boolean isDeleted = false;
		OfferedService deletedOfferedService = null;
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
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;
		
		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 40;
		String newTestOfferedServiceDescription = "this is a testing inspection service";

		OfferedService modifiedService = null;

		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(modifiedService);
		checkResultOfferedService(modifiedService, OFFERED_SERVICE_KEY,
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
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;

		
		int newTestOfferedServiceDuration = 0;
		double newTestOfferedServicePrice = 0.0;
		String newTestOfferedServiceName = null;
		Time newTestOfferedServiceReminderTime = null;
		int newTestOfferedServiceReminderDate = 0;
		String newTestOfferedServiceDescription = null;

		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		assertEquals("name cannot be empty!duration cannot be zero!reminderTime cannot be empty!reminderDate cannot be zero!Offered service description cannot be empty!", error);
	}
	
	
	/**
	 * testing updateOfferedService with new duration as 0
	 * @throws InvalidInputException
	 */
	@Test
	public void testUpdateOfferedServiceWithZeroNewDuration() throws InvalidInputException {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;

		
		int newTestOfferedServiceDuration = 0;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 40;
		String newTestOfferedServiceDescription = "this is a testing inspection service";

		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		assertEquals("duration cannot be zero!", error);
	}
	
	
	
	
	/**
	 * testing updateOfferedService with new duration as negative number
	 * @throws InvalidInputException
	 */
	@Test
	public void testUpdateOfferedServiceWithNegativeNewDuration() throws InvalidInputException {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		
		int newTestOfferedServiceDuration = -1;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 40;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		assertEquals("duration cannot be negative!", error);
	}
	
	/**
	 * testing updateOfferedService with new price as negative number
	 * @throws InvalidInputException
	 */
	@Test
	public void testUpdateOfferedServiceWithNegativeNewPrice() throws InvalidInputException {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		
		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = -20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 40;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		assertEquals("price cannot be negative!", error);
	}
	

	
	
	/**
	 * test update OfferedService with an new name as empty string 
	 * @throws InvalidInputException
	 */
	@Test
	public void testUpdateOfferedServiceWithEmptyNewName() throws InvalidInputException {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		
		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 40;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		assertEquals("name cannot be empty!", error);
	}
	
	
	
	
	/**
	 * test update OfferedService with an new name as a space
	 * 
	 */
	@Test
	public void testUpdateOfferedServiceWithSpaceNewName(){
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String error = null;

		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = " ";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 40;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		assertEquals("name cannot be empty!", error);
	}
	
	
	
	/**
	 * test update OfferedService with an reminderTime as null
	 * @throws InvalidInputException
	 */
	
	@Test
	public void testUpdateOfferedServiceWithNullNewReminderTime() throws InvalidInputException {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;
		
		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = null;
		int newTestOfferedServiceReminderDate = 40;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		assertEquals("reminderTime cannot be empty!", error);
	}
	
	
	
	/**
	 * test update OfferedService with an reminderDate as a 0
	 * 
	 */
	@Test
	public void testUpdateOfferedServiceWithZeroNewReminderDate(){
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;
		
		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 0;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		assertEquals("reminderDate cannot be zero!", error);
	}
	
	
	/**
	 * test update OfferedService with an reminderDate as a negative number
	 * @throws InvalidInputException
	 */
	@Test
	public void testUpdateOfferedServiceWithNegativeReminderDate() throws InvalidInputException {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = -1;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		assertEquals("reminderDate cannot be negative!", error);
	}
	
	
	
	/**
	 * test update OfferedService with an description as a empty
	 *
	 */
	@Test
	public void testUpdateOfferedServiceWithEmptyDescription() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;

		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 10;
		String newTestOfferedServiceDescription = "";
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		assertEquals("Offered service description cannot be empty!", error);
	}
	
	
	
	/**
	 * test update OfferedService with an description as a space
	 * 
	 */
	@Test
	public void testUpdateOfferedServiceWithSpaceDescription() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;

		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 10;
		String newTestOfferedServiceDescription = " ";
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		assertEquals("Offered service description cannot be empty!", error);
	}
	
	
	
	
	/**
	 * test update OfferedService with an OfferedServiceid as a empty 
	 * 
	 */
	@Test
	public void testUpdateOfferedServiceWithEmptyOfferServiceId() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;


		String modifyingServiceId = "";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 10;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(modifyingServiceId,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		assertEquals("the serviceId can not be empty!the offered service can not found in the system!", error);
	}
	
	
	
	
	/**
	 * test update OfferedService with an OfferedServiceid as a space 
	 * @throws InvalidInputException
	 */
	@Test
	public void testUpdateOfferedServiceWithSpaceOfferServiceId() throws InvalidInputException {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;

		String modifyingServiceId = " ";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 10;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(modifyingServiceId,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		assertEquals("the serviceId can not be empty!the offered service can not found in the system!", error);
	}
	
	
	/**
	 * test updating offeredService with non found OfferedServiceId 
	 * @throws InvalidInputException
	 */
	@Test
	public void testUpdateOfferedServiceWithNonFoundOfferServiceId() throws InvalidInputException {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		
		String error = null;
		String modifyingServiceId = "TEST2";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 10;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService offeredService = null;
		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(modifyingServiceId,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(modifiedService);
		assertEquals("the offered service can not found in the system!", error);
	}
//	
//	
//	
	/**
	 * test get all offered service
	 */
	
	public void testGetAllOfferedService() {
		assertEquals(1, offeredServiceService.getAllOfferedServices().size());
		String OfferedServiceId = "testGetAllOfferedService";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		
		String newModifyingServiceId = "testGetAllOfferedService2";
		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 10;
		String newTestOfferedServiceDescription = "this is a testing inspection service";
		
		OfferedService offeredService = offeredServiceService.createOfferedService(OfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		
		OfferedService offeredService2 = offeredServiceService.updateService(newModifyingServiceId,
				newTestOfferedServicePrice,  newTestOfferedServiceName,
				newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
				newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		List<OfferedService> offeredServices = new ArrayList<OfferedService>();
		offeredServices.add(offeredService);
		offeredServices.add(offeredService2);
		
		List<OfferedService> extractedOfferedServices = new ArrayList<OfferedService>();
		extractedOfferedServices = offeredServiceService.getAllOfferedServices();
		
		assertEquals(2, extractedOfferedServices.size());
		assertEquals(extractedOfferedServices, offeredServices);
		
		
	}
	
//	
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
	
//	
//	
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
	private void checkResultOfferedService(OfferedService offeredService, String offeredServiceId, double price, String name, 
			int duration, Time reminderTime, int reminderDate, String description) {
		assertNotNull(offeredService);
		assertEquals(offeredServiceId, offeredService.getOfferedServiceId());
		assertEquals(price, offeredService.getPrice());
		assertEquals(duration, offeredService.getDuration());
		assertEquals(reminderTime, offeredService.getReminderTime());
		assertEquals(reminderDate, offeredService.getReminderDate());
		assertEquals(description, offeredService.getDescription());
	}
	
	private void checkResultOfferedServicePlusAppointment(OfferedService offeredService, String offeredServiceId, double price, String name, 
			int duration, Time reminderTime, int reminderDate, String description, List<Appointment> apts) {
		assertNotNull(offeredService);
		assertEquals(offeredServiceId, offeredService.getOfferedServiceId());
		assertEquals(price, offeredService.getPrice());
		assertEquals(duration, offeredService.getDuration());
		assertEquals(reminderTime, offeredService.getReminderTime());
		assertEquals(reminderDate, offeredService.getReminderDate());
		assertEquals(description, offeredService.getDescription());
		assertEquals(apts, offeredService.getAppointment());
	}
	

}
	
