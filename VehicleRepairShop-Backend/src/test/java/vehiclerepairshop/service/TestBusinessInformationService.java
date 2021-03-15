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
	private BusinessInformationRepository businessInformationRepository;


	@InjectMocks
	private BusinessInformationService businessInformationService;

	private static final String NAME1 = "name1";
	private static final String ADDRESS1 = "address1"; 
	private static final String PHONE_NUMBER1 = "phoneNumber1";
	private static final String EMAIL_ADDRESS1 = "emailAddress1"; 

	private static final String NAME2 = "name2";
	private static final String ADDRESS2 = "address2"; 
	private static final String PHONE_NUMBER2 = "phoneNumber2";
	private static final String EMAIL_ADDRESS2 = "emailAddress2"; 

	private static final String NON_EXISTING_NAME = "nonExistingName";
	private static final String NON_EXISTING_ADDRESS = "nonExistingAddress"; 
	private static final String NON_EXISTING_PHONE_NUMBER = "nonExistingPhoneNumber";
	private static final String NON_EXISTING_EMAIL_ADDRESS = "nonExistingEmailAddress";

	@BeforeEach
	public void setMockOutput() {
		lenient().when(businessInformationRepository.findBusinessInformationByName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(NAME1)) {
				BusinessInformation businessInformation = new BusinessInformation();
				businessInformation.setName(NAME1);
				businessInformation.setAddress(ADDRESS1);
				businessInformation.setPhoneNumber(PHONE_NUMBER1);
				businessInformation.setEmailAddress(EMAIL_ADDRESS1);
				return businessInformation;
			} 
			else if(invocation.getArgument(0).equals(NAME2)) {
				BusinessInformation businessInformation = new BusinessInformation();
				businessInformation.setName(NAME2);
				businessInformation.setAddress(ADDRESS2);
				businessInformation.setPhoneNumber(PHONE_NUMBER2);
				businessInformation.setEmailAddress(EMAIL_ADDRESS2);
				return businessInformation;
			}else {
				return null;
			}
		});

		lenient().when(businessInformationRepository.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
			BusinessInformation businessInformation1 = new BusinessInformation();
			businessInformation1.setName(NAME1);
			businessInformation1.setAddress(ADDRESS1);
			businessInformation1.setPhoneNumber(PHONE_NUMBER1);
			businessInformation1.setEmailAddress(EMAIL_ADDRESS1);

			BusinessInformation businessInformation2 = new BusinessInformation();
			businessInformation2.setName(NAME2);
			businessInformation2.setAddress(ADDRESS2);
			businessInformation2.setPhoneNumber(PHONE_NUMBER2);
			businessInformation2.setEmailAddress(EMAIL_ADDRESS2);

			List<BusinessInformation> businessInformation = new ArrayList<BusinessInformation>();
			businessInformation.add(businessInformation1);
			businessInformation.add(businessInformation2);

			return businessInformation;
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
		lenient().when(businessInformationRepository.save(any(BusinessInformation.class))).thenAnswer(returnParameterAsAnswer);
	}


	//----------------------------------- createBusinessInformation --------------------------------------------------
	/**
	 * Testing createBusinessInformation
	 */
	@Test
	public void testCreateBusinessInformation() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			fail(e.getMessage());
		}
		checkResultBusinessInformation(businessInformation, name, address, phoneNumber, emailAddress);
	}

	/**
	 * Testing when we are creating a business information with a taken name
	 */
	@Test
	public void testCreateBusinessInformationWithTakenName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = NAME1;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name not available!", error);
	}
	
	/**
	 * Testing when we are creating a null BusinessInformation
	 */
	@Test
	public void testCreateNullBusinessInformation() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

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
		assertEquals("Name cannot be empty!", error);
	}
	
	/**
	 * Testing when we are creating an empty BusinessInformation
	 */
	@Test
	public void testCreateEmptyBusinessInformation() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = "";
		String address = "";
		String phoneNumber = "";
		String emailAddress = "";

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
	 * Testing when we are creating a spaced BusinessInformation
	 */
	@Test
	public void testCreateSpacedBusinessInformation() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = "  ";
		String address = "  ";
		String phoneNumber = "  ";
		String emailAddress = "  ";

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
	 * Testing when we are creating a business information with a null name
	 */
	@Test
	public void testCreateBusinessInformationWithNullName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = null;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

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
	 * Testing when we are creating a business information with an empty name
	 */
	@Test
	public void testCreateBusinessInformationWithEmptyName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = "";
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;;

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
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = "  ";
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

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
	 * Testing when we are creating a business information with a null address
	 */
	@Test
	public void testCreateBusinessInformationWithNullAddress() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = NON_EXISTING_NAME;
		String address = null;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

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
	 * Testing when we are creating a business information with an empty address
	 */
	@Test
	public void testCreateBusinessInformationWithEmptyAddress() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = NON_EXISTING_NAME;
		String address = "";
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

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
	 * Testing when we are creating a business information with a spaced address
	 */
	@Test
	public void testCreateBusinessInformationWithSpacedAddress() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = NON_EXISTING_NAME;
		String address = "  ";
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

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
	 * Testing when we are creating a business information with a null phone number
	 */
	@Test
	public void testCreateBusinessInformationWithNullPhoneNumber() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = null;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

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
	 * Testing when we are creating a business information with an empty phone number
	 */
	@Test
	public void testCreateBusinessInformationWithEmptyPhoneNumber() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = "";
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

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
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = "  ";
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

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
	 * Testing when we are creating a business information with a null email address
	 */
	@Test
	public void testCreateBusinessInformationWithNullEmailAddress() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
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
	/**
	 * Testing when we are creating a business information with an empty email address
	 */
	@Test
	public void testCreateBusinessInformationWithEmptyEmailAddress() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = "";

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("EmailAddress cannot be empty!", error);
	}

	/**
	 * Testing when we are creating a business information with a spaced email address
	 */
	@Test
	public void testCreateBusinessInformationWithSpacedEmailAddress() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;

		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = "  ";

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("EmailAddress cannot be empty!", error);
	}


	//----------------------------------- getBusinessInformation --------------------------------------------------
	/**
	 * Testing getBusinessInformationByName
	 */
	@Test
	public void testGetBusinessInformationByName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String name = NAME1;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.getBusinessInformationByName(name);
		}catch (InvalidInputException e) {
			fail(e.getMessage());
		}
		checkResultBusinessInformation(businessInformation, NAME1, ADDRESS1, PHONE_NUMBER1, EMAIL_ADDRESS1);
	}

	/**
	 * Testing getBusinessInformation with a non-existing name
	 */
	@Test
	public void testGetBusinessInformationWithNonExsitingName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;
		String name = NON_EXISTING_NAME;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.getBusinessInformationByName(name);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name does not exist!", error);
	}

	/**
	 * Testing getBusinessInformation with a null name
	 */
	@Test
	public void testGetBusinessInformationWithNullName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;
		String name = null;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.getBusinessInformationByName(name);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name cannot be empty!", error);
	}

	/**
	 * Testing getBusinessInformation with an empty name
	 */
	@Test
	public void testGetBusinessInformationWithEmptyName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;
		String name = "";

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.getBusinessInformationByName(name);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name cannot be empty!", error);
	}

	/**
	 * Testing getBusinessInformation with a spaced name
	 */
	@Test
	public void testGetBusinessInformationWithSpacedName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;
		String name = "  ";

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.getBusinessInformationByName(name);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name cannot be empty!", error);
	}
	
	//----------------------------------- getAllBusinessInformation ----------------------------------------------------
	/**
	 * Test getAllBusinessInformation
	 */
	@Test
	public void testGetAllBusinessInformation() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		List<BusinessInformation> businessInformation = new ArrayList<BusinessInformation>();
		businessInformation = businessInformationService.getAllBusinessInformation();

		BusinessInformation businessInformation1 = businessInformation.get(0);
		checkResultBusinessInformation(businessInformation1, NAME1, ADDRESS1, PHONE_NUMBER1, EMAIL_ADDRESS1);
		BusinessInformation businessInformation2 = businessInformation.get(1);
		checkResultBusinessInformation(businessInformation2, NAME2, ADDRESS2, PHONE_NUMBER2, EMAIL_ADDRESS2);
	}

	//----------------------------------- 	updateBusinessInformation --------------------------------------------------
	/**
	 * Testing updateBusinessInformationByName, update name, update address, phoneNumber and emailAddress
	 */
	@Test
	public void testUpdateBusinessInformationByName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String currentName = NAME1;
		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;

		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			fail(e.getMessage());
		}
		checkResultBusinessInformation(businessInformation, name, address, phoneNumber, emailAddress);
	}

	/**
	 * Testing updateBusinessInformation with a taken name
	 */
	@Test
	public void testUpdateBusinessInformationWithTakenName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = NAME1;
		String name = NAME2;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name not available!", error);
	}
	
	/**
	 * Testing updateBusinessInformation with a non-existing currentName
	 */
	@Test
	public void testUpdateBusinessInformationWithNonExistingCurrentName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = NON_EXISTING_NAME;
		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("CurrentName does not exist!", error);
	}
	
	/**
	 * Testing updateBusinessInformation with null parameters
	 */
	@Test
	public void testUpdateBusinessInformationWithNullBusinessInformation() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = null;
		String name = null;
		String address = null;
		String phoneNumber = null;
		String emailAddress = null;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("CurrentName cannot be empty!", error);
	}
	
	/**
	 * Testing updateBusinessInformation with empty parameters
	 */
	@Test
	public void testUpdateBusinessInformationWithEmptyBusinessInformation() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = "";
		String name = "";
		String address = "";
		String phoneNumber = "";
		String emailAddress = "";

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("CurrentName cannot be empty!", error);
	}
	
	/**
	 * Testing updateBusinessInformation with spaced parameters
	 */
	@Test
	public void testUpdateBusinessInformationWithSpacedBusinessInformation() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = "  ";
		String name = "  ";
		String address = "  ";
		String phoneNumber = "  ";
		String emailAddress = "  ";

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("CurrentName cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a null currentName
	 */
	@Test
	public void testUpdateBusinessInformationWithNullCurrentName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = null;
		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;


		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName,name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("CurrentName cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with an empty currentName
	 */
	@Test
	public void testUpdateBusinessInformationWithEmptyCurrentName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = "";
		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("CurrentName cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a spaced currentName
	 */
	@Test
	public void testUpdateBusinessInformationWithSpacedCurrentName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = "  ";
		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("CurrentName cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a null name
	 */
	@Test
	public void testUpdateBusinessInformationWithNullName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = NAME1;
		String name = null;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;


		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName,name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with an empty name
	 */
	@Test
	public void testUpdateBusinessInformationWithEmptyName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = NAME1;
		String name = "";
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a spaced name
	 */
	@Test
	public void testUpdateBusinessInformationWithSpacedName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = NAME1;
		String name = "  ";
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Name cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a null address
	 */
	@Test
	public void testUpdateBusinessInformationWithNullAddress() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = NAME1;
		String name = NON_EXISTING_NAME;
		String address = null;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName,name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Address cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with an empty address
	 */
	@Test
	public void testUpdateBusinessInformationWithEmptyAddress() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = NAME1;
		String name = NON_EXISTING_NAME;
		String address = "";
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Address cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a spaced address
	 */
	@Test
	public void testUpdateBusinessInformationWithSpacedAddress() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = NAME1;
		String name = NON_EXISTING_NAME;
		String address = "  ";
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("Address cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a null phoneNumber
	 */
	@Test
	public void testUpdateBusinessInformationWithNullPhoneNumber() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = NAME1;
		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = null;
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;


		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName,name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("PhoneNumber cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with an empty phoneNumber
	 */
	@Test
	public void testUpdateBusinessInformationWithEmptyPhoneNumber() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = NAME1;
		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = "";
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("PhoneNumber cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a spaced phoneNumber
	 */
	@Test
	public void testUpdateBusinessInformationWithSpacedPhoneNumber() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = NAME1;
		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = "  ";
		String emailAddress = NON_EXISTING_EMAIL_ADDRESS;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("PhoneNumber cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a null emailAddress
	 */
	@Test
	public void testUpdateBusinessInformationWithNullEmailAddress() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = NAME1;
		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = null;

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName,name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("EmailAddress cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with an empty emailAddress
	 */
	@Test
	public void testUpdateBusinessInformationWithEmptyEmailAddress() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = NAME1;
		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = "";

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("EmailAddress cannot be empty!", error);
	}

	/**
	 * Testing updateBusinessInformation with a spaced emailAddress
	 */
	@Test
	public void testUpdateBusinessInformationWithSpacedEmailAddress() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());
		String error = null;

		String currentName = NAME1;
		String name = NON_EXISTING_NAME;
		String address = NON_EXISTING_ADDRESS;
		String phoneNumber = NON_EXISTING_PHONE_NUMBER;
		String emailAddress = "  ";

		BusinessInformation businessInformation = null;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(currentName, name, address, phoneNumber, emailAddress);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(businessInformation);
		assertEquals("EmailAddress cannot be empty!", error);
	}


	//----------------------------------- 	deleteBusinessInformation --------------------------------------------------
	/**
	 * Testing deleteBusinessInformationByName
	 */
	@Test
	public void testDeleteBusinessInformationByName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String name = NAME1;

		BusinessInformation deletedBusinessInformation = null;
		BusinessInformation businessInformation = null;
		try {
			deletedBusinessInformation = businessInformationService.deleteBusinessInformation(name);
		}catch (InvalidInputException e) {
			fail(e.getMessage());
		}
		businessInformation = businessInformationService.getBusinessInformationByName(name);
		checkResultBusinessInformation(deletedBusinessInformation, businessInformation.getName(), businessInformation.getAddress(), businessInformation.getPhoneNumber(), businessInformation.getEmailAddress());
	}

	/**
	 * Testing deleteBusinessInformation with a non-existing name
	 */
	@Test
	public void testDeleteBusinessInformationWithNonExsitingName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;
		String name = NON_EXISTING_NAME;

		BusinessInformation deletedBusinessInformation = null;
		try {
			deletedBusinessInformation = businessInformationService.deleteBusinessInformation(name);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(deletedBusinessInformation);
		assertEquals("Name does not exist!", error);
	}

	/**
	 * Testing deleteBusinessInformation with a null name
	 */
	@Test
	public void testDeleteBusinessInformationWithNullName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;
		String name = null;

		BusinessInformation deletedBusinessInformation = null;
		try {
			deletedBusinessInformation = businessInformationService.deleteBusinessInformation(name);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(deletedBusinessInformation);
		assertEquals("Name cannot be empty!", error);
	}

	/**
	 * Testing deleteBusinessInformation with an empty name
	 */
	@Test
	public void testDeleteBusinessInformationWithEmptyName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;
		String name = "";

		BusinessInformation deletedBusinessInformation = null;
		try {
			deletedBusinessInformation = businessInformationService.deleteBusinessInformation(name);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(deletedBusinessInformation);
		assertEquals("Name cannot be empty!", error);
	}

	/**
	 * Testing deleteBusinessInformation with a spaced name
	 */
	@Test
	public void testDeleteBusinessInformationWithSpacedName() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		String error = null;
		String name = "  ";

		BusinessInformation deletedBusinessInformation = null;
		try {
			deletedBusinessInformation = businessInformationService.deleteBusinessInformation(name);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(deletedBusinessInformation);
		assertEquals("Name cannot be empty!", error);
	}

	/**
	 * Testing deleteAllBusinessInformation
	 */
	@Test
	public void testDeleteAllBusinessInformation() {
		assertEquals(2, businessInformationService.getAllBusinessInformation().size());

		List<BusinessInformation> businessInformation = new ArrayList<BusinessInformation>();
		businessInformation = businessInformationService.deleteAllBusinessInformation();

		BusinessInformation businessInformation1 = businessInformation.get(0);
		checkResultBusinessInformation(businessInformation1, NAME1, ADDRESS1, PHONE_NUMBER1, EMAIL_ADDRESS1);
		BusinessInformation businessInformation2 = businessInformation.get(1);
		checkResultBusinessInformation(businessInformation2, NAME2, ADDRESS2, PHONE_NUMBER2, EMAIL_ADDRESS2);	
	}

	//----------------------------------- helper method --------------------------------------------------

	/**
	 * This is a helper method which can help us determine if all the attribute statisfies the input value
	/**
	 * @param businessInformation
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @param emailAddress
	 */
	private void checkResultBusinessInformation(BusinessInformation businessInformation, String name, String address, String phoneNumber, String emailAddress) {
		assertNotNull(businessInformation);
		assertEquals(name, businessInformation.getName());
		assertEquals(address, businessInformation.getAddress());
		assertEquals(phoneNumber, businessInformation.getPhoneNumber());
		assertEquals(emailAddress, businessInformation.getEmailAddress());

	}
}
