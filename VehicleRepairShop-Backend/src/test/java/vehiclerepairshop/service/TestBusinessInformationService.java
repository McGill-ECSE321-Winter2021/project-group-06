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

import ca.mcgill.ecse321.vehiclerepairshop.dao.BusinessInformationRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;

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
	//private static final String NONEXISTING_KEY = "NotATestID";

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

	//	/**
	//	 * Testing when we are creating a null BusinessInformation
	//	 */
	//	@Test
	//	public void testCreateNullBusinessInformation() {
	//		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
	//		
	//		String error = null;
	//		
	//		String name = null;
	//		String address = null;
	//		String phoneNumber = null;
	//		String emailAddress = null;
	//
	//		BusinessInformation businessInformation = null;
	//		try {
	//			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
	//		}catch (InvalidInputException e){
	//			error = e.getMessage();
	//		}
	//		assertNull(businessInformation);
	//		assertEquals("Name, Address, phoneNumber and emailAddress cannot be empty!", error);
	//	}
	//	
	/**
	 * Testing when we are creating a business information with an empty name
	 */
	@Test
	public void testCreateBusinessInformationWithEmptyName() {
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
	 * Testing when we are creating a business information with a taken name
	 */
	@Test
	public void testCreateBusinessInformationWithTakenName() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = NAME;
		String address = ADDRESS;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = EMAIL_ADDRESS;

		String address2 = "address2";
		String phoneNumber2 = "phoneNumber2";
		String emailAddress2 = "emailAddress2";

		businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address2, phoneNumber2, emailAddress2);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name not available!", error);
	}

	/**
	 * Testing when we are creating a business information with an empty address
	 */
	@Test
	public void testCreateBusinessInformationWithEmptyAddress() {
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
	 * Testing when we are creating a business information with an empty phone number
	 */
	@Test
	public void testCreateBusinessInformationWithEmptyPhoneNumber() {
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
	 * Testing when we are creating a business information with an empty email address
	 */
	@Test
	public void testCreateBusinessInformationWithEmptyEmailAddress() {
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
	//----------------------------------- 	updateBusinessInformation --------------------------------------------------
	/**
	 * Testing updateBusinessInformation, update address, phoneNumber and emailAddress
	 */
	@Test
	public void testUpdateBusinessInformation() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());

		String name = NAME;
		String address = ADDRESS;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = EMAIL_ADDRESS;

		String address2 = "address2";
		String phoneNumber2 = "phoneNumber2";
		String emailAddress2 = "emailAddress2";

		businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		BusinessInformation businessInformation = null;

		try {
			businessInformation = businessInformationService.updateBusinessInformation(name, address2, phoneNumber2, emailAddress2);
		}catch (InvalidInputException e){
			fail();//e.printStackTrace();
		}
		checkResultBusinessInformation(businessInformation, name, address2, phoneNumber2, emailAddress2);
	}

	/**
	 * Testing updateBusinessInformation with an empty name
	 */
	@Test
	public void testUpdateBusinessInformationWithEmptyName() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String name = null;
		String address = ADDRESS;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = EMAIL_ADDRESS;

		//boolean newIsAvailable = !IS_AVAILABLE;

		BusinessInformation garage = null; // garageService.createGarage(isAvailable,garageId);

		try {
			garage = businessInformationService.updateBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("Name cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a spaced name
	 */
	@Test
	public void testUpdateBusinessInformationWithSpacedName() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String name = " ";
		String address = ADDRESS;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;

		try {
			businessInformation = businessInformationService.updateBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a non-existing name
	 */
	@Test
	public void testUpdateBusinessInformationWithNonExistingName() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String name = NAME;
		String address = ADDRESS;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;

		try {
			businessInformation = businessInformationService.updateBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name does not exist!", error);
	}

	/**
	 * Testing updateBusinessInformation with an empty address
	 */
	@Test
	public void testUpdateBusinessInformationWithEmptyAddress() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String name = NAME;
		String address = null;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = EMAIL_ADDRESS;

		BusinessInformation garage = null;

		try {
			garage = businessInformationService.updateBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("Address cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a spaced address
	 */
	@Test
	public void testUpdateBusinessInformationWithSpacedAddress() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String name = NAME;
		String address = " ";
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;

		try {
			businessInformation = businessInformationService.updateBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Address cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with an empty phoneNumber
	 */
	@Test
	public void testUpdateBusinessInformationWithEmptyPhoneNumber() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String name = NAME;
		String address = ADDRESS;
		String phoneNumber = null;
		String emailAddress = EMAIL_ADDRESS;

		BusinessInformation garage = null;

		try {
			garage = businessInformationService.updateBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("PhoneNumber cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a spaced phoneNumber
	 */
	@Test
	public void testUpdateBusinessInformationWithSpacedPhoneNumber() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String name = NAME;
		String address = ADDRESS;
		String phoneNumber = null;
		String emailAddress = EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;

		try {
			businessInformation = businessInformationService.updateBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("PhoneNumber cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with an empty emailAddress
	 */
	@Test
	public void testUpdateBusinessInformationWithEmptyEmailAddress() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String name = NAME;
		String address = ADDRESS;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = null;

		BusinessInformation garage = null;

		try {
			garage = businessInformationService.updateBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("EmailAddress cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a spaced emailAddress
	 */
	@Test
	public void testUpdateBusinessInformationWithSpacedEmailAddress() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String name = NAME;
		String address = ADDRESS;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = " ";

		BusinessInformation businessInformation = null;

		try {
			businessInformation = businessInformationService.updateBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("EmailAddress cannot be empty!", error);
	}

	//----------------------------------- 	deleteGarage --------------------------------------------------
	/**
	 * Testing deleteBusinessInformation
	 */
	@Test
	public void testDeleteBusinessInformation() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		boolean isDeleted = false;

		String name = NAME;
		String address = ADDRESS;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = EMAIL_ADDRESS;

		businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		BusinessInformation businessInformation = null;
		try {
			isDeleted = businessInformationService.deleteBusinessInformation(name);
		}catch (InvalidInputException e) {
			fail();//e.printStackTrace();
		}
		businessInformation = businessInformationService.getBusinessInformationByName(name);
		//assertEquals(0, garageService.getAllGarages().size());
		assertNull(businessInformation);
		assertEquals(true, isDeleted);
	}

	/**
	 * Testing deleteAllBusinessInformation
	 */
	@Test
	public void testDeleteAllBusinessInformation() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());

		String name1 = NAME;
		String address = ADDRESS;
		String phoneNumber = PHONE_NUMBER;
		String emailAddress = EMAIL_ADDRESS;

		String name2 = "name2";

		businessInformationService.createBusinessInformation(name1, address, phoneNumber, emailAddress);
		businessInformationService.createBusinessInformation(name2, address, phoneNumber, emailAddress);

		BusinessInformation businessInformation1 = null;
		BusinessInformation businessInformation2 = null;

		try {
			businessInformationService.deleteAllBusinessInformation();
		}catch (InvalidInputException e) {
			fail();
		}

		businessInformation1 = businessInformationService.getBusinessInformationByName(name1);
		businessInformation2 = businessInformationService.getBusinessInformationByName(name2);
		//assertEquals(0, garageService.getAllGarages().size());
		assertNull(businessInformation1);
		assertNull(businessInformation2);
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
	}

	/**
	 * Testing deleteBusinessInformation with empty name
	 */
	@Test
	public void testDeleteBusinessInformationWithEmptyName() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		String error = null;
		boolean isDeleted = false;

		String name = null;

		BusinessInformation businessInformation = null;
		try {
			isDeleted = businessInformationService.deleteBusinessInformation(name);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}

		businessInformation = businessInformationService.getBusinessInformationByName(name);
		//assertEquals(0, garageService.getAllGarages().size());
		assertNull(businessInformation);
		assertEquals(false, isDeleted);
		assertEquals("Name cannot be empty!", error);
	}

	/**
	 * Testing deleteBusinessInformation with a spaced name
	 */
	@Test
	public void testDeleteBusinessInformationWithSpacedName() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		String error = null;
		boolean isDeleted = false;

		String name = " ";

		BusinessInformation businessInformation = null;
		try {
			isDeleted = businessInformationService.deleteBusinessInformation(name);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}

		businessInformation = businessInformationService.getBusinessInformationByName(name);
		//assertEquals(0, garageService.getAllGarages().size());
		assertNull(businessInformation);
		assertEquals(false, isDeleted);
		assertEquals("Name cannot be empty!", error);
	}

	/**
	 * Testing deleteBusinessInformation with a non-existing name
	 */
	@Test
	public void testDeleteBusinessInformationWithNonExistingName() {
		assertEquals(0, businessInformationService.getAllBusinessInformation().size());
		String error = null;
		boolean isDeleted = false;

		String name = "nonExistingName";

		BusinessInformation businessInformation = null;
		try {
			isDeleted = businessInformationService.deleteBusinessInformation(name);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}

		businessInformation = businessInformationService.getBusinessInformationByName(name);
		//assertEquals(0, garageService.getAllGarages().size());
		assertNull(businessInformation);
		assertEquals(false, isDeleted);
		assertEquals("Name does not exist!", error);
	}

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
