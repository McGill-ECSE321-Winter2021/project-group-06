package ca.mcgill.ecse321.vehiclerepairshop.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.OfferedServiceRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;



/**
 * 
 * @author mikewang
 *
 */
@ExtendWith(MockitoExtension.class)
public class TestOfferedServiceService {


	private static final String OFFERED_SERVICE_KEY = "Test1";
	private static final String OFFERED_SERVICE_KEY_2 = "OFFERED_SERVICE_KEY_2";
	private static int DURATION = 10;
	private static double PRICE = 10.0;
	private static String NAME = "wash";
	private static Time REMINDER_TIME= java.sql.Time.valueOf(LocalTime.of(11, 35));
	private static int REMINDER_DATE = 30;
	private static String DESCRIPTION = "this is a testing Wash service";
	private static final int APPOINTMENT_KEY = 1; 
	private static final String NONEXISTING_KEY = "NotATestID";
	List<OfferedService> offeredServicesFindAll = new ArrayList<OfferedService>();
	private Appointment apt = new Appointment();

	private OfferedService testOs = new OfferedService();
	//	InvalidInputException
	//	InvalidInputException

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
			}else if (invocation.getArgument(0).equals(OFFERED_SERVICE_KEY_2)) {
				testOs.setOfferedServiceId(OFFERED_SERVICE_KEY_2);
				testOs.setName(NAME);
				testOs.setPrice(PRICE);
				testOs.setReminderTime(REMINDER_TIME);
				testOs.setReminderDate(REMINDER_DATE);
				testOs.setDescription(DESCRIPTION);
				testOs.setDuration(DURATION);
				apt.setAppointmentId(APPOINTMENT_KEY);
				apt.setOfferedService(testOs);
				return testOs;
			}
			else {
				return null;
			}
		});

//		/**
//		 * @TODO: finish this if understand how to implement
//		 */
//		lenient().when(offeredServiceRepository.findByAppointment(any(Appointment.class))).thenAnswer((InvocationOnMock invocation)->{
//			if (invocation.getArgument(0).equals(apt)) {
//				apt.setAppointmentId(APPOINTMENT_KEY);
//				List<Appointment> apts = new ArrayList<Appointment>(); 
//				apts.add(apt);
//				OfferedService offeredService = new OfferedService();
//				offeredService.setOfferedServiceId(OFFERED_SERVICE_KEY);
//				offeredService.setName(NAME);
//				offeredService.setPrice(PRICE);
//				offeredService.setReminderTime(REMINDER_TIME);
//				offeredService.setReminderDate(REMINDER_DATE);
//				offeredService.setDescription(DESCRIPTION);
//				offeredService.setDuration(DURATION);
//				return offeredService;
//			}
//			else {
//				return null;
//			}
//
//		});


		lenient().when(appointmentRepository.findByAppointmentId(anyInt())).thenAnswer((InvocationOnMock invocation) ->{
			if (invocation.getArgument(0).equals(APPOINTMENT_KEY)) {
				apt.setAppointmentId(APPOINTMENT_KEY);
				return apt;
			}else {
				return null;
			}
		});

		lenient().when(offeredServiceRepository.findAll()).thenAnswer((InvocationOnMock invocation)->{
//			apt.setAppointmentId(APPOINTMENT_KEY);
//			List<Appointment> apts = new ArrayList<Appointment>(); 
//			apts.add(apt);
			OfferedService offeredServiceFindAll = new OfferedService();
			offeredServiceFindAll.setOfferedServiceId(OFFERED_SERVICE_KEY);
			offeredServiceFindAll.setName(NAME);
			offeredServiceFindAll.setPrice(PRICE);
			offeredServiceFindAll.setReminderTime(REMINDER_TIME);
			offeredServiceFindAll.setReminderDate(REMINDER_DATE);
			offeredServiceFindAll.setDescription(DESCRIPTION);
			offeredServiceFindAll.setDuration(DURATION);
			offeredServicesFindAll.add(offeredServiceFindAll);
			
			testOs.setOfferedServiceId(OFFERED_SERVICE_KEY_2);
			testOs.setName(NAME);
			testOs.setPrice(PRICE);
			testOs.setReminderTime(REMINDER_TIME);
			testOs.setReminderDate(REMINDER_DATE);
			testOs.setDescription(DESCRIPTION);
			testOs.setDuration(DURATION);
			apt.setAppointmentId(APPOINTMENT_KEY);
			apt.setOfferedService(testOs);
			offeredServicesFindAll.add(testOs);
			return offeredServicesFindAll;
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());

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
		}catch (InvalidInputException e){
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());

		String error = "";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";

		try {
			offeredServiceService.createOfferedService(OFFERED_SERVICE_KEY,
					testOfferedServicePrice,  testOfferedServiceName,
					testOfferedServiceDuration,testOfferedServiceReminderTime, 
					testOfferedServiceReminderDate, testOfferedServiceDescription);
		}catch (InvalidInputException e){
			error = error + e.getMessage();
		}

		assertEquals("can not create OfferedService with same Id!", error);
	}



	/**
	 * testing when we are creating an null OfferedService
	 */

	@Test
	public void testCreateNullOfferedService() {
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e){
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e){
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e){
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e){
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e){
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e){
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e){
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e){
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e){
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e){
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e){
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e){
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e){
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "testGetOfferedServiceWithEmptyOfferedServiceId";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService extractedOfferedService = null;
		offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			extractedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId("");
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		String testOfferedServiceId = "testGetOfferedServiceWithSpaceOfferedServiceId";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService extractedOfferedService = null;
		offeredServiceService.createOfferedService(testOfferedServiceId,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
		try {
			extractedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(" ");
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
		String error = "";

		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";

		OfferedService extractedOfferedService = null;
		try {
			extractedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(OFFERED_SERVICE_KEY);
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
		String error = "";
		int testOfferedServiceDuration = 10;
		double testOfferedServicePrice = 10.0;
		String testOfferedServiceName = "wash";
		Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		int testOfferedServiceReminderDate = 30;
		String testOfferedServiceDescription = "this is a testing Wash service";
		OfferedService extractedOfferedService = null;
//		OfferedService service = offeredServiceService.createOfferedService(offeredServiceId,
//				testOfferedServicePrice,  testOfferedServiceName,
//				testOfferedServiceDuration,testOfferedServiceReminderTime, 
//				testOfferedServiceReminderDate, testOfferedServiceDescription);
//		offeredServiceRepository.save(service);
//		Appointment testAppointment = new Appointment();
//		testAppointment.setOfferedService(service);
		apt.setOfferedService(testOs);
		try {
			extractedOfferedService = offeredServiceService.getOfferedServiceByAppointment(apt);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("", error);
		assertNotNull(extractedOfferedService);
		checkResultOfferedService(extractedOfferedService,OFFERED_SERVICE_KEY_2,
				testOfferedServicePrice,  testOfferedServiceName,
				testOfferedServiceDuration,testOfferedServiceReminderTime, 
				testOfferedServiceReminderDate, testOfferedServiceDescription);
	}


	/**
	 * testing getOfferedService  with null appointment input 
	 */
	@Test
	public void testGetOfferedServiceWithNullAppointment() {
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
		String error = null;

		OfferedService extractedOfferedService = null;

		Appointment apt = null;
		appointmentRepository.save(apt);
		assertNull(apt);
		try {
			extractedOfferedService = offeredServiceService.getOfferedServiceByAppointment(apt);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(extractedOfferedService);
		assertEquals("appointment cannot be null!", error);
	}

	
	/**
	 * testing getOfferedService  with null appointment input 
	 */
	@Test
	public void testGetOfferedServiceWithNotFoundAppointment() {
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		
		OfferedService extractedOfferedService = null;

		Appointment apt1 = new Appointment();

		testOs.setOfferedServiceId(OFFERED_SERVICE_KEY_2);
		apt1.setOfferedService(testOs);
		try {
			extractedOfferedService = offeredServiceService.getOfferedServiceByAppointment(apt1);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(extractedOfferedService);
		assertEquals("can not find this appointment in appointmentRepository!", error);
	}

	/**
	 * testing getOfferedService with appointment linked offeredService not found in offeredService repository 
	 */
	@Test
	public void testGetOfferedServiceWithNotFoundAppointmentLinkedOfferedService() {
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		Appointment apt = new Appointment();
		//appointmentRepository.save(apt);
		//assertNull(apt);
		apt.setAppointmentId(APPOINTMENT_KEY);
		apt.setOfferedService(offeredService);
		try {
			extractedOfferedService = offeredServiceService.getOfferedServiceByAppointment(apt);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(extractedOfferedService);
		assertEquals("this offeredService does not exist in the offeredServiceRepository!", error);
	}
	
	
	
	/**
	 * testing delete offered service with a valid offeredServiceId
	 * @throws InvalidInputException
	 */
	@Test
	public void testDeleteOfferedServiceWithValidOfferedServiceId() throws InvalidInputException {
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
		String error = "";
		OfferedService deletingOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(OFFERED_SERVICE_KEY);
		OfferedService deletedOfferedService = new OfferedService();

		apt.setAppointmentId(APPOINTMENT_KEY);
		apt.setOfferedService(deletingOfferedService);
		
		try {
			deletedOfferedService = offeredServiceService.deleteOfferedService(OFFERED_SERVICE_KEY);
		}catch (InvalidInputException e) {
			fail();
		}

		assertEquals("",error);
		assertEquals(deletedOfferedService.getOfferedServiceId(),deletingOfferedService.getOfferedServiceId());
	}


	/**
	 * testing delete offered service with a empty offeredServiceId
	 *
	 */	
	@Test
	public void testDeleteOfferedServiceWithEmptyOfferedServiceId() {
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
		String error = null;
		OfferedService deletingOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(OFFERED_SERVICE_KEY);
		//String testOfferedServiceId = "TEST1";
		String deletedOfferedServiceId = "";
		OfferedService deletedOfferedService = null;
		try {
			deletedOfferedService = offeredServiceService.deleteOfferedService(deletedOfferedServiceId);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertNull(deletedOfferedService);
		assertNotEquals(deletingOfferedService,deletedOfferedService);
		assertEquals("the serviceId can not be empty!the offered service can not found in the system!", error);
	}

	/**
	 * testing delete offered service with a space offeredServiceId
	 * 
	 */
	@Test
	public void testDeleteOfferedServiceWithSpaceOfferedServiceId() {
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
		String error = null;

		OfferedService deletingOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(OFFERED_SERVICE_KEY);
		String deletedOfferedServiceId = " ";
		OfferedService deletedOfferedService = null;
		OfferedService getDeletedOfferedService = null;

		try {
			deletedOfferedService = offeredServiceService.deleteOfferedService(deletedOfferedServiceId);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}

		getDeletedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(OFFERED_SERVICE_KEY);
		assertNull(deletedOfferedService);
		assertNotNull(getDeletedOfferedService);
		assertEquals(getDeletedOfferedService.getOfferedServiceId(),deletingOfferedService.getOfferedServiceId());
		assertEquals("the serviceId can not be empty!the offered service can not found in the system!", error);
	}



	/**
	 * testing delete offered service with a null offeredService
	 * @throws InvalidInputException
	 */
	@Test
	public void testDeleteOfferedServiceWithEmptyOfferedService() throws InvalidInputException {
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
		String testOfferedServiceId = "TEST2";
		String error = "";

		OfferedService deletingOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(OFFERED_SERVICE_KEY);
		OfferedService deletedOfferedService = null;
		OfferedService getDeletedOfferedService = null;
		try {
			deletedOfferedService = offeredServiceService.deleteOfferedService(testOfferedServiceId);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		getDeletedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(OFFERED_SERVICE_KEY);
		assertNotNull(getDeletedOfferedService);
		assertNull(deletedOfferedService);
		assertEquals(getDeletedOfferedService.getOfferedServiceId(),deletingOfferedService.getOfferedServiceId());
		assertEquals("the offered service can not found in the system!", error);
	}



	/**
	 * test updateOfferedService with valid input 
	 * @throws InvalidInputException
	 */
	@Test
	public void testUpdateOfferedServiceWithValidInput() throws InvalidInputException {
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());

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
		}catch (InvalidInputException e) {
			e.getMessage();
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());

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
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
		String error = null;

		int newTestOfferedServiceDuration = -1;
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
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
		String error = null;

		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = -20.0;
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
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());
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
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());

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
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());

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
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());

		String error = null;

		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = -1;
		String newTestOfferedServiceDescription = "this is a testing inspection service";

		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());

		String error = null;


		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 10;
		String newTestOfferedServiceDescription = "";

		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());

		String error = null;

		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 10;
		String newTestOfferedServiceDescription = " ";

		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(OFFERED_SERVICE_KEY,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());

		String error = null;


		String modifyingServiceId = "";

		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 10;
		String newTestOfferedServiceDescription = "this is a testing inspection service";

		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(modifyingServiceId,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());

		String error = null;

		String modifyingServiceId = " ";

		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 10;
		String newTestOfferedServiceDescription = "this is a testing inspection service";

		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(modifyingServiceId,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (InvalidInputException e) {
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
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());

		String error = null;
		String modifyingServiceId = "TEST2";

		int newTestOfferedServiceDuration = 20;
		double newTestOfferedServicePrice = 20.0;
		String newTestOfferedServiceName = "inspection";
		Time newTestOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int newTestOfferedServiceReminderDate = 10;
		String newTestOfferedServiceDescription = "this is a testing inspection service";

		OfferedService modifiedService = null;
		try {
			modifiedService = offeredServiceService.updateService(modifyingServiceId,
					newTestOfferedServicePrice,  newTestOfferedServiceName,
					newTestOfferedServiceDuration, newTestOfferedServiceReminderTime, 
					newTestOfferedServiceReminderDate, newTestOfferedServiceDescription);
		}catch (InvalidInputException e) {
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
	@Test
	public void testGetAllOfferedService() {
		assertEquals(2, offeredServiceService.getAllOfferedServices().size());

		List<OfferedService> extractedOfferedServices = new ArrayList<OfferedService>();
		extractedOfferedServices = offeredServiceService.getAllOfferedServices();

		assertEquals(4, extractedOfferedServices.size());
		assertEquals(extractedOfferedServices, offeredServicesFindAll);


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
}

