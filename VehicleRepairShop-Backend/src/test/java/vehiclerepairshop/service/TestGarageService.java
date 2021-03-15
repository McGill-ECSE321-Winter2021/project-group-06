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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.GarageRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;

/**
 * @author aureliahaas
 *
 */
@ExtendWith(MockitoExtension.class)
public class TestGarageService {
	@Mock
	private GarageRepository garageDao;
	@Mock
	private AppointmentRepository appointmentDao;

	@InjectMocks
	private GarageService garageService;

	private static final String GARAGE_KEY = "TestID";
	//private static final int APPOINTMENT_KEY = 1; 
	//private static final String NONEXISTING_KEY = "NotATestID";
	
	private static final String GARAGE_ID = "garageId";
	private static final boolean IS_AVAILABLE = false; 

	@BeforeEach
	public void setMockOutput() {
		lenient().when(garageDao.findByGarageId(anyString())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(GARAGE_KEY)) {
				Garage garage = new Garage();
				garage.setGarageId(GARAGE_KEY);
				return garage;
			}
			else {
				return null;
			}
		});
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(garageDao.save(any(Garage.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(appointmentDao.save(any(Appointment.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	//----------------------------------- createGarage --------------------------------------------------
	/**
	 * Testing if we can create a garage and store it in the persistence 
	 */
	@Test
	public void testCreateGarage() {
		assertEquals(0, garageService.getAllGarages().size());
		
		boolean isAvailable = IS_AVAILABLE;
		String garageId = GARAGE_ID;

		Garage garage = null;
		try {
			garage = garageService.createGarage(isAvailable,garageId);
		}catch (InvalidInputException e){
			fail();
		}
		checkResultGarage(garage, isAvailable, garageId);
	}
	
	/**
	 * Testing when we are creating a garage with an empty garageId
	 */
	@Test
	public void testCreateGarageWithEmptyGarageId() {
		assertEquals(0, garageService.getAllGarages().size());
		
		String error = null;
		
		boolean isAvailable = IS_AVAILABLE;
		String garageId = null;

		Garage garage = null;
		try {
			garage = garageService.createGarage(isAvailable, garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}
	
	/**
	 * Testing when we are creating a garage with a spaced garageId
	 */
	@Test
	public void testCreateGarageWithSpacedGarageId() {
		assertEquals(0, garageService.getAllGarages().size());
		
		String error = null;
		
		boolean isAvailable = IS_AVAILABLE;
		String garageId = " ";

		Garage garage = null;
		try {
			garage = garageService.createGarage(isAvailable, garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}
	
	/**
	 * Testing when we are creating a garage if the garageId is already taken
	 */
	@Test
	public void testCreateGarageWithTakenGarageId() {
		assertEquals(0, garageService.getAllGarages().size());
		
		String error = null;
		
		boolean isAvailable = IS_AVAILABLE;
		String garageId = GARAGE_ID;

		garageService.createGarage(isAvailable, garageId);
		Garage garage = null;
		
		try {
			garage = garageService.createGarage(isAvailable, garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId not available!", error);
	}
	
	//----------------------------------- getGarageByGarageId --------------------------------------------------
//	/**
//	 * Testing getGarage
//	 */
//	@Test
//	public void testGetGarage() {
//		assertEquals(0, garageService.getAllGarages().size());
//		
//		boolean isAvailable = IS_AVAILABLE;
//		String garageId = GARAGE_ID;
//
//		Garage garage = garageService.createGarage(isAvailable, garageId);
//		Garage extractedGarage = null;
//		
//		try {
//			extractedGarage = garageService.getGarageByGarageId(garageId);
//		}catch (InvalidInputException e) {
//			fail();
//		}
//		//checkResultGarage(garage, isAvailable, garageId);
//		assertNotNull(extractedGarage);
////		assertEquals(extractedGarage.getIsAvailable(), garage.getIsAvailable());
////		assertEquals(extractedGarage.getGarageId(), garage.getGarageId());
//	}
	
//	/**
//	 * testing getGarage with empty GarageId
//	 */
//	@Test
//	public void testGetGarageWithNoGarageId() {
//		assertEquals(0, garageService.getAllGarages().size());
//		
//		String error = null;
//		
//		boolean isAvailable = IS_AVAILABLE;
//		String garageId = null;
//
//		Garage garage = null;
//		Garage extractedGarage = null;
//		//garage = garageService.createGarage(isAvailable, garageId);
//		
//		try {
//			extractedGarage = garageService.getGarageByGarageId(garageId);
//		}catch (IllegalArgumentException e) {
//			error = e.getMessage();
//		}
//		assertNull(extractedGarage);
//		assertEquals("GarageId cannot be empty!", error);
//	}
	
	//----------------------------------- 	updateGarage --------------------------------------------------
	/**
	 * Testing updateGarage
	 */
	@Test
	public void testUpdateGarage() {
		assertEquals(0, garageService.getAllGarages().size());
		
		boolean isAvailable = IS_AVAILABLE;
		String garageId = GARAGE_ID;
		
		boolean newIsAvailable = !IS_AVAILABLE;

		garageService.createGarage(isAvailable, garageId);
		Garage garage = null;
		
		try {
			garage = garageService.updateGarage(garageId, newIsAvailable);
		}catch (InvalidInputException e){
			fail();//e.printStackTrace();
		}
		checkResultGarage(garage, newIsAvailable, garageId);
	}
	
	/**
	 * Testing updateGarage with an empty garageId
	 */
	@Test
	public void testUpdateGarageWithEmptyGarageId() {
		assertEquals(0, garageService.getAllGarages().size());
		String error = null;
		
		boolean isAvailable = IS_AVAILABLE;
		String garageId = null;
		
		//boolean newIsAvailable = !IS_AVAILABLE;

		Garage garage = null; // garageService.createGarage(isAvailable,garageId);
		
		try {
			garage = garageService.updateGarage(garageId, isAvailable);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}
	
	/**
	 * Testing updateGarage with a spaced garageId
	 */
	@Test
	public void testUpdateGarageWithSpacedGarageId() {
		assertEquals(0, garageService.getAllGarages().size());
		String error = null;
		
		boolean isAvailable = IS_AVAILABLE;
		String garageId = " ";
		
		//boolean newIsAvailable = !IS_AVAILABLE;

		Garage garage = null; //garageService.createGarage(isAvailable,garageId);
		
		try {
			garage = garageService.updateGarage(garageId, isAvailable);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}
	
	/**
	 * Testing updateGarage with a non-existing garageId
	 */
	@Test
	public void testUpdateGarageWithNonExistingGarageId() {
		assertEquals(0, garageService.getAllGarages().size());
		String error = null;
		
		boolean isAvailable = IS_AVAILABLE;
		String garageId = "nonExistingGarageId";
		
		//boolean newIsAvailable = !IS_AVAILABLE;

		Garage garage = null;
		
		try {
			garage = garageService.updateGarage(garageId, isAvailable);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId does not exist!", error);
	}
	
	//----------------------------------- 	deleteGarage --------------------------------------------------
	/**
	 * Testing deleteGarage
	 */
	@Test
	public void testDeleteGarage() {
		assertEquals(0, garageService.getAllGarages().size());
		boolean isDeleted = false;
		
		boolean isAvailable = IS_AVAILABLE;
		String garageId = GARAGE_ID;

		garageService.createGarage(isAvailable, garageId);
		Garage garage = null;
		try {
			isDeleted = garageService.deleteGarage(garageId);
		}catch (InvalidInputException e) {
			fail();//e.printStackTrace();
		}
		garage = garageService.getGarageByGarageId(garageId);
		//assertEquals(0, garageService.getAllGarages().size());
		assertNull(garage);
		assertEquals(true, isDeleted);
	}
	
	/**
	 * Testing deleteAllGarages 
	 */
	@Test
	public void testDeleteAllGarages() {
		assertEquals(0, garageService.getAllGarages().size());
		
		boolean isAvailable = IS_AVAILABLE;
		String garageId1 = GARAGE_ID;
		String garageId2 = "garageId2";
		
		garageService.createGarage(isAvailable, garageId1);
		garageService.createGarage(isAvailable, garageId2);
		
		Garage garage1 = null;
		Garage garage2 = null;
		
		try {
			garageService.deleteAllGarages();
		}catch (InvalidInputException e) {
			fail();
		}
	
		garage1 = garageService.getGarageByGarageId(garageId1);
		garage2 = garageService.getGarageByGarageId(garageId2);

		assertNull(garage1);
		assertNull(garage2);
		assertEquals(0, garageService.getAllGarages().size());
	}
	
	/**
	 * Testing deleteGarage with empty garageId
	 */
	@Test
	public void testDeleteGarageWithEmptyGarageId() {
		assertEquals(0, garageService.getAllGarages().size());
		String error = null;
		
		boolean isDeleted = false;
		
		//boolean isAvailable = IS_AVAILABLE;
		String garageId = null;

		//garageService.createGarage(isAvailable, garageId);
		Garage garage = null;
		try {
			isDeleted = garageService.deleteGarage(garageId);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
	
		garage = garageService.getGarageByGarageId(garageId);
		//assertEquals(0, garageService.getAllGarages().size());
		assertNull(garage);
		assertEquals(false, isDeleted);
		assertEquals("GarageId cannot be empty!", error);
	}
	
	/**
	 * Testing deleteGarage with a spaced garageId
	 */
	@Test
	public void testDeleteGarageWithSpacedGarageId() {
		assertEquals(0, garageService.getAllGarages().size());
		String error = null;
		
		boolean isDeleted = false;
		
		//boolean isAvailable = IS_AVAILABLE;
		String garageId = " ";

		//garageService.createGarage(isAvailable, garageId);
		Garage garage = null;
		try {
			isDeleted = garageService.deleteGarage(garageId);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
	
		garage = garageService.getGarageByGarageId(garageId);
		//assertEquals(0, garageService.getAllGarages().size());
		assertNull(garage);
		assertEquals(false, isDeleted);
		assertEquals("GarageId cannot be empty!", error);
	}
	
	/**
	 * Testing deleteGarage with a non-existing garageId
	 */
	@Test
	public void testDeleteGarageWithNonExistingGarageId() {
		assertEquals(0, garageService.getAllGarages().size());
		String error = null;
		boolean isDeleted = false;
		
		//boolean isAvailable = IS_AVAILABLE;
		String garageId = GARAGE_ID;
		
		Garage garage = null;
		try {
			isDeleted = garageService.deleteGarage(garageId);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
	
		garage = garageService.getGarageByGarageId(garageId);
		//assertEquals(0, garageService.getAllGarages().size());
		assertNull(garage);
		assertEquals(false, isDeleted);
		assertEquals("GarageId does not exist!", error);
	}
	
	//----------------------------------- helper method --------------------------------------------------

	/**
	 * This is a helper method which can help us determine if all the attribute statisfies the input value
	 * @param garage
	 * @param isAvailable
	 * @param garageId
	 * @param name
	 * @param duration
	 * @param reminderTime
	 * @param reminderDate
	 * @param description
	 */
	private void checkResultGarage(Garage garage, boolean isAvailable, String garageId) {
		assertNotNull(garage);
		assertEquals(isAvailable, garage.getIsAvailable());
		assertEquals(garageId, garage.getGarageId());
	}
}






//
///**
// * testing getOfferedService with empty offeredServiceId
// */
//@Test
//public void testGetOfferedServiceWithEmptyOfferedServiceId() {
//	assertEquals(0, garageService.getAllOfferedServices().size());
//	String error = null;
//	String testOfferedServiceId = "Test1";
//	int testOfferedServiceDuration = 10;
//	Double testOfferedServicePrice = 10.0;
//	String testOfferedServiceName = "wash";
//	Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
//	int testOfferedServiceReminderDate = 30;
//	String testOfferedServiceDescription = "this is a testing Wash service";
//	OfferedService offeredService = null;
//	OfferedService extractedOfferedService = null;
//	offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
//			testOfferedServicePrice,  testOfferedServiceName,
//			testOfferedServiceDuration,testOfferedServiceReminderTime, 
//			testOfferedServiceReminderDate, testOfferedServiceDescription);
//	try {
//		extractedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId("");
//	}catch (IllegalArgumentException e) {
//		error = e.getMessage();
//	}
//	assertNull(extractedOfferedService);
//	assertEquals("offeredServiceId cannot be null!", error);
//}
//
//
///**
// * testing getOfferedService with space offeredServiceId
// */
//@Test
//public void testGetOfferedServiceWithSpaceOfferedServiceId() {
//	assertEquals(0, garageService.getAllOfferedServices().size());
//	String error = null;
//	String testOfferedServiceId = "Test1";
//	int testOfferedServiceDuration = 10;
//	Double testOfferedServicePrice = 10.0;
//	String testOfferedServiceName = "wash";
//	Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
//	int testOfferedServiceReminderDate = 30;
//	String testOfferedServiceDescription = "this is a testing Wash service";
//	OfferedService offeredService = null;
//	OfferedService extractedOfferedService = null;
//	offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
//			testOfferedServicePrice,  testOfferedServiceName,
//			testOfferedServiceDuration,testOfferedServiceReminderTime, 
//			testOfferedServiceReminderDate, testOfferedServiceDescription);
//	try {
//		extractedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(" ");
//	}catch (IllegalArgumentException e) {
//		error = e.getMessage();
//	}
//	assertNull(extractedOfferedService);
//	assertEquals("offeredServiceId cannot be null!", error);
//}
//
//
///**
// * testing getOfferedService with valid offeredServiceId
// */
//@Test
//public void testGetOfferedServiceWithValidOfferedServiceId() {
//	assertEquals(0, garageService.getAllOfferedServices().size());
//	String testOfferedServiceId = "TEST1";
//	int testOfferedServiceDuration = 10;
//	Double testOfferedServicePrice = 10.0;
//	String testOfferedServiceName = "wash";
//	Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
//	int testOfferedServiceReminderDate = 30;
//	String testOfferedServiceDescription = "this is a testing Wash service";
//	OfferedService offeredService = null;
//	OfferedService extractedOfferedService = null;
//	offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
//			testOfferedServicePrice,  testOfferedServiceName,
//			testOfferedServiceDuration,testOfferedServiceReminderTime, 
//			testOfferedServiceReminderDate, testOfferedServiceDescription);
//	try {
//		extractedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId("Test1");
//	}catch (IllegalArgumentException e) {
//		fail();
//	}
//	assertNotNull(extractedOfferedService);
//	assertEquals(extractedOfferedService.getOfferedServiceId(),offeredService.getOfferedServiceId() );
//}
//
///**
// * testing getting offered service with valid appointment input 
// */
//@Test
//public void testGetOfferedServiceWithValidAppointment() {
//	assertEquals(0, garageService.getAllOfferedServices().size());
//	String testOfferedServiceId = "TEST1";
//	int testOfferedServiceDuration = 10;
//	Double testOfferedServicePrice = 10.0;
//	String testOfferedServiceName = "wash";
//	Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
//	int testOfferedServiceReminderDate = 30;
//	String testOfferedServiceDescription = "this is a testing Wash service";
//	OfferedService offeredService = null;
//	OfferedService extractedOfferedService = null;
//	offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
//			testOfferedServicePrice,  testOfferedServiceName,
//			testOfferedServiceDuration,testOfferedServiceReminderTime, 
//			testOfferedServiceReminderDate, testOfferedServiceDescription);
//	Appointment apt = new Appointment();
//	apt.setAppointmentId(APPOINTMENT_KEY);
//	appointmentDao.save(apt);
//	List<Appointment> appointments = new ArrayList<Appointment>();
//	appointments.add(apt);
//	offeredService.setAppointment(appointments);
//	try {
//		extractedOfferedService = offeredServiceService.getOfferedServiceByAppointment(apt);
//	}catch (IllegalArgumentException e) {
//		fail();
//	}
//	assertNotNull(extractedOfferedService);
//	assertEquals(extractedOfferedService.getOfferedServiceId(),offeredService.getOfferedServiceId() );
//}
//
//
///**
// * testing getting offered service with null appointment input 
// */
//@Test
//public void testGetOfferedServiceWithNullAppointment() {
//	assertEquals(0, garageService.getAllOfferedServices().size());
//	String error = null;
//	String testOfferedServiceId = "TEST1";
//	int testOfferedServiceDuration = 10;
//	Double testOfferedServicePrice = 10.0;
//	String testOfferedServiceName = "wash";
//	Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
//	int testOfferedServiceReminderDate = 30;
//	String testOfferedServiceDescription = "this is a testing Wash service";
//	OfferedService offeredService = null;
//	OfferedService extractedOfferedService = null;
//	offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
//			testOfferedServicePrice,  testOfferedServiceName,
//			testOfferedServiceDuration,testOfferedServiceReminderTime, 
//			testOfferedServiceReminderDate, testOfferedServiceDescription);
//	Appointment apt = null;
//	appointmentDao.save(apt);
//	List<Appointment> appointments = new ArrayList<Appointment>();
//	appointments.add(apt);
//	offeredService.setAppointment(appointments);
//	try {
//		extractedOfferedService = offeredServiceService.getOfferedServiceByAppointment(apt);
//	}catch (IllegalArgumentException e) {
//		error = e.getMessage();
//	}
//	assertNull(extractedOfferedService);
//	assertEquals("appointment cannot be null!", error);
//}
//
///**
// * testing delete offered service with a valid offeredServiceId
// * @throws InvalidInputException
// */
//@Test
//public void testDeleteOfferedServiceWithValidOfferedServiceId() throws InvalidInputException {
//	assertEquals(0, garageService.getAllOfferedServices().size());
//	String error = null;
//	boolean isDeleted = false;
//	String testOfferedServiceId = "TEST1";
//	int testOfferedServiceDuration = 10;
//	Double testOfferedServicePrice = 10.0;
//	String testOfferedServiceName = "wash";
//	Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
//	int testOfferedServiceReminderDate = 30;
//	String testOfferedServiceDescription = "this is a testing Wash service";
//	OfferedService offeredService = null;
//	OfferedService deletedOfferedService = null;
//	offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
//			testOfferedServicePrice,  testOfferedServiceName,
//			testOfferedServiceDuration,testOfferedServiceReminderTime, 
//			testOfferedServiceReminderDate, testOfferedServiceDescription);
//	try {
//		isDeleted = offeredServiceService.deleteOfferedService(testOfferedServiceId);
//	}catch (IllegalArgumentException e) {
//		fail();
//	}
//
//	deletedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(testOfferedServiceId);
//	assertNull(deletedOfferedService);
//	assertEquals(true, isDeleted);
//}
//
//
///**
// * testing delete offered service with a empty offeredServiceId
// * @throws InvalidInputException
// */	
//@Test
//public void testDeleteOfferedServiceWithEmptyOfferedServiceId() throws InvalidInputException {
//	assertEquals(0, garageService.getAllOfferedServices().size());
//	String error = null;
//	boolean isDeleted = false;
//	String testOfferedServiceId = "TEST1";
//	String deletedOfferedServiceId = "";
//	int testOfferedServiceDuration = 10;
//	Double testOfferedServicePrice = 10.0;
//	String testOfferedServiceName = "wash";
//	Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
//	int testOfferedServiceReminderDate = 30;
//	String testOfferedServiceDescription = "this is a testing Wash service";
//	OfferedService offeredService = null;
//	OfferedService deletedOfferedService = null;
//	offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
//			testOfferedServicePrice,  testOfferedServiceName,
//			testOfferedServiceDuration,testOfferedServiceReminderTime, 
//			testOfferedServiceReminderDate, testOfferedServiceDescription);
//	try {
//		isDeleted = offeredServiceService.deleteOfferedService(deletedOfferedServiceId);
//	}catch (IllegalArgumentException e) {
//		error = e.getMessage();
//	}
//
//	deletedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(testOfferedServiceId);
//	assertNotNull(deletedOfferedService);
//	assertEquals("the serviceId can not be empty!", error);
//}
//
///**
// * testing delete offered service with a space offeredServiceId
// * @throws InvalidInputException
// */
//@Test
//public void testDeleteOfferedServiceWithSpaceOfferedServiceId() throws InvalidInputException {
//	assertEquals(0, garageService.getAllOfferedServices().size());
//	String error = null;
//	boolean isDeleted = false;
//	String testOfferedServiceId = "TEST1";
//	String deletedOfferedServiceId = " ";
//	int testOfferedServiceDuration = 10;
//	Double testOfferedServicePrice = 10.0;
//	String testOfferedServiceName = "wash";
//	Time testOfferedServiceReminderTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
//	int testOfferedServiceReminderDate = 30;
//	String testOfferedServiceDescription = "this is a testing Wash service";
//	OfferedService offeredService = null;
//	OfferedService deletedOfferedService = null;
//	offeredService = offeredServiceService.createOfferedService(testOfferedServiceId,
//			testOfferedServicePrice,  testOfferedServiceName,
//			testOfferedServiceDuration,testOfferedServiceReminderTime, 
//			testOfferedServiceReminderDate, testOfferedServiceDescription);
//	try {
//		isDeleted = offeredServiceService.deleteOfferedService(deletedOfferedServiceId);
//	}catch (IllegalArgumentException e) {
//		error = e.getMessage();
//	}
//
//	deletedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(testOfferedServiceId);
//	assertNotNull(deletedOfferedService);
//	assertEquals("the serviceId can not be empty!", error);
//	assertEquals(false, isDeleted);
//}
//
//
//
///**
// * testing delete offered service with a null offeredService
// * @throws InvalidInputException
// */
//@Test
//public void testDeleteOfferedServiceWithEmptyOfferedService() throws InvalidInputException {
//	assertEquals(0, garageService.getAllOfferedServices().size());
//	String testOfferedServiceId = "TEST1";
//	String error = null;
//	boolean isDeleted = null;
//	OfferedService deletedOfferedService = new OfferedService();
//	try {
//		isDeleted = offeredServiceService.deleteOfferedService(testOfferedServiceId);
//	}catch (IllegalArgumentException e) {
//		error = e.getMessage();
//	}
//
//	deletedOfferedService = offeredServiceService.getOfferedServiceByOfferedServiceId(testOfferedServiceId);
//	assertNull(deletedOfferedService);
//	assertEquals("the offered service can not found in the system!", error);
//	assertEquals(false, isDeleted);
//}
//
//
//
//
//
///**
// * testing to extract an existing offered service 
// */
//@Test
//public void testGetExistingOfferedService() {
//	assertEquals(GARAGE_KEY, garageService.getOfferedServiceByOfferedServiceId(GARAGE_KEY).getOfferedServiceId());
//}
//
//
///**
// * testing to extract an non-existing offered service
// */
//
//@Test
//public void testGetNonExistingOfferedService() {
//	assertNull(garageService.getOfferedServiceByOfferedServiceId(NONEXISTING_KEY));
//}
//
//
//
//
//
//
//}
//
