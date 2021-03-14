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

import java.util.ArrayList;
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
import ca.mcgill.ecse321.vehiclerepairshop.dao.BusinessInformationRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.GarageRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;

/**
 * @author aureliahaas
 *
 */
@ExtendWith(MockitoExtension.class)
public class TestBusinessInformationService {
	@Mock
	private BusinessInformationRepository businessInformationDao;


	@InjectMocks
	private BusinessInformationService businessInformationService;

	private static final String BUSINESS_INFORMATION_KEY = "TestID";
	//private static final int APPOINTMENT_KEY = 1; 
	private static final String NONEXISTING_KEY = "NotATestID";
	
	private static final String NAME = "name";
	private static final String ADDRESS = "address"; 
	private static final String PHONE_NUMBER = "phoneNumber";
	private static final String EMAIL_ADDRESS = "emailAddress"; 

	@BeforeEach
	public void setMockOutput() {
		lenient().when(businessInformationDao.findById(anyString())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(BUSINESS_INFORMATION_KEY)) {
				BusinessInformation businessInformation = new BusinessInformation();
				businessInformation.setName(BUSINESS_INFORMATION_KEY);
				return businessInformation;
			}
			else {
				return null;
			}
		});
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(businessInformationDao.save(any(BusinessInformation.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	//----------------------------------- createBusinessInformation --------------------------------------------------
	/**
	 * Testing createBusinessInformation
	 */
	@Test
	public void testBusinessInformation() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		
		String name = NAME;
		String address = ADDRESS;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			fail();
		}
		checkResultBusinessInformation(businessInformation, name, address, phoneNumber, emailAddress);
	}

	/**
	 * Testing when we are creating a null BusinessInformation
	 */
	@Test
	public void testCreateNullBusinessInformation() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		
		String error = null;
		
		String name = null;
		String address = null;
		String phoneNumber = null;
		String emailAddress = null;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name, Address, phoneNumber and emailAddress cannot be empty!", error);
	}
	
	/**
	 * Testing when we are creating a business information with no name
	 */
	@Test
	public void testCreateBusinessInformationWithNoName() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		
		String error = null;
		
		String name = null;
		String address = ADDRESS;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name cannot be empty!", error);
	}
	
	/**
	 * Testing when we are creating a business information with a spaced name
	 */
	@Test
	public void testCreateBusinessInformationWithSpacedName() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		
		String error = null;
		
		String name = " ";
		String address = ADDRESS;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name cannot be empty!", error);
	}
	
	/**
	 * Testing when we are creating a business information with no Address
	 */
	@Test
	public void testCreateBusinessInformationWithNoAddress() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		
		String error = null;
		
		String name = NAME;
		String address = null;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Address cannot be empty!", error);
	}
	
	//Not sure 
	/**
	 * Testing when we are creating a business information with a spaced address
	 */
	@Test
	public void testCreateBusinessInformationWithSpacedAddress() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		
		String error = null;
		
		String name = NAME;
		String address = " ";
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Address cannot be empty!", error);
	}

	/**
	 * Testing when we are creating a business information with no phone number
	 */
	@Test
	public void testCreateBusinessInformationWithNoPhoneNumber() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		
		String error = null;
		
		String name = NAME;
		String address = ADDRESS;
		String phoneNumber = null;
		String emailAddress = EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("PhoneNumber cannot be empty!", error);
	}
	
	//Not sure 
	/**
	 * Testing when we are creating a business information with a spaced phone number
	 */
	@Test
	public void testCreateBusinessInformationWithSpacedPhoneNumber() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		
		String error = null;
		
		String name = NAME;
		String address = ADDRESS;
		String phoneNumber = " ";
		String emailAddress = EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("PhoneNumber cannot be empty!", error);
	}
	
	/**
	 * Testing when we are creating a business information with no email address
	 */
	@Test
	public void testCreateBusinessInformationWithNoEmailAddress() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		
		String error = null;
		
		String name = NAME;
		String address = ADDRESS;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = null;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("EmailAddress cannot be empty!", error);
	}
	
	//Not sure 
	/**
	 * Testing when we are creating a business information with a spaced email address
	 */
	@Test
	public void testCreateBusinessInformationWithSpacedEmailAddress() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		
		String error = null;
		
		String name = NAME;
		String address = ADDRESS;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = " ";

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("EmailAddress cannot be empty!", error);
	}

//	
//	//----------------------------------- getGarageByGarageId --------------------------------------------------
////	/**
////	 * Testing getGarage
////	 */
////	@Test
////	public void testGetGarage() {
////		assertEquals(0, garageService.getAllGarages().size());
////		
////		Boolean isAvailable = IS_AVAILABLE;
////		String garageId = GARAGE_ID;
////
////		Garage garage = garageService.createGarage(isAvailable, garageId);
////		Garage extractedGarage = null;
////		
////		try {
////			extractedGarage = garageService.getGarageByGarageId(garageId);
////		}catch (InvalidInputException e) {
////			fail();
////		}
////		//checkResultGarage(garage, isAvailable, garageId);
////		assertNotNull(extractedGarage);
//////		assertEquals(extractedGarage.getIsAvailable(), garage.getIsAvailable());
//////		assertEquals(extractedGarage.getGarageId(), garage.getGarageId());
////	}
//	
////	/**
////	 * testing getGarage with empty GarageId
////	 */
////	@Test
////	public void testGetGarageWithNoGarageId() {
////		assertEquals(0, garageService.getAllGarages().size());
////		
////		String error = null;
////		
////		Boolean isAvailable = IS_AVAILABLE;
////		String garageId = null;
////
////		Garage garage = null;
////		Garage extractedGarage = null;
////		//garage = garageService.createGarage(isAvailable, garageId);
////		
////		try {
////			extractedGarage = garageService.getGarageByGarageId(garageId);
////		}catch (IllegalArgumentException e) {
////			error = e.getMessage();
////		}
////		assertNull(extractedGarage);
////		assertEquals("GarageId cannot be empty!", error);
////	}
//	
//	//----------------------------------- 	updateGarage --------------------------------------------------
//	/**
//	 * Testing updateGarage
//	 */
////	@Test
////	public void testUpdateGarage() {
////		assertEquals(0, garageService.getAllGarages().size());
////		
////		Boolean isAvailable = false;//IS_AVAILABLE;
////		String garageId = GARAGE_ID;
////		
////		Boolean newIsAvailable = true; //!IS_AVAILABLE;
////
////		Garage garage = garageService.createGarage(isAvailable,garageId);;
////		
////		try {
////			garage = garageService.updateGarage(garageId, newIsAvailable);
////		}catch (InvalidInputException e){
////			e.printStackTrace();
////		}
////		checkResultGarage(garage, newIsAvailable, garageId);
////	}
//	
//	//----------------------------------- 	deleteGarage --------------------------------------------------
//	/**
//	 * Testing deleteGarage
//	 */
//	@Test
//	public void testDeleteGarage() {
//		assertEquals(0, garageService.getAllGarages().size());
//		String error = null;
//		
//		Boolean isDeleted = false;
//		
//		Boolean isAvailable = ADDRESS;
//		String garageId = NAME;
//
//		BusinessInformation garage = garageService.createGarage(isAvailable, garageId);
//		BusinessInformation deletedGarage = null;
//		try {
//			isDeleted = garageService.deleteGarage(garageId);
//		}catch (InvalidInputException e) {
//			e.printStackTrace();
//		}
//	
//		deletedGarage = garageService.getGarageByGarageId(garageId);
//		assertNull(deletedGarage);
//		assertEquals(true, isDeleted);
//	}
	
	//----------------------------------- helper method --------------------------------------------------

	/**
	 * This is a helper method which can help us determine if all the attribute statisfies the input value
	 * @param businessInformation
	 * @param name
	 * @param address, String phoneNumber, String emailAddress
	 * @param name
	 * @param duration
	 * @param reminderTime
	 * @param reminderDate
	 * @param description
	 */
	private void checkResultBusinessInformation(BusinessInformation businessInformation, String name, String address, String phoneNumber, String emailAddress) {
		assertNotNull(businessInformation);
		assertEquals(name, businessInformation.getName());
		assertEquals(address, businessInformation.getAddress());
		assertEquals(phoneNumber, businessInformation.getPhoneNumber());
		assertEquals(emailAddress, businessInformation.getEmailAddress());
		
	}
}
