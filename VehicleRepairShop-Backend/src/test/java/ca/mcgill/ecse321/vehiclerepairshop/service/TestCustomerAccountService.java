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
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.vehiclerepairshop.dao.*;
import ca.mcgill.ecse321.vehiclerepairshop.model.*;




@ExtendWith(MockitoExtension.class)
public class TestCustomerAccountService {

	private static final String USERNAME1 = "Username1";
	private static final String NAME1 = "Alicia";
	private static final String PASSWORD1 = "PassWord123!";
	private static final int TOKEN1 = 1000000;

	private static final String USERNAME2 = "Username2";
	private static final String NAME2 = "Catherine The 1st";
	private static final String PASSWORD2 = "GhostPassword101?!";
	private static final int TOKEN2 = 0; //invalid

	private static final String LICENSE = "123ABC";
	private static final String LICENSE2 = "123ABCD";
	Car addCar = new Car();
	CustomerAccount ownerWithCar = new CustomerAccount();



	@Mock
	private CustomerAccountRepository customerAccountRepository;
	@Mock
	private CarRepository carRepository;
	@Mock
	private AdminAccountRepository adminAccountRepository;
	@Mock
	private TechnicianAccountRepository technicianAccountRepository;


	@InjectMocks
	private CustomerAccountService customerAccountService;


	@BeforeEach
	public void setMockOutput() {
		lenient().when(customerAccountRepository.findByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(USERNAME1)) {
				CustomerAccount user = new CustomerAccount();
				user.setUsername(USERNAME1);
				user.setPassword(PASSWORD1);
				user.setName(NAME1);
				user.setToken(TOKEN1);
				List<Car> cars = new ArrayList<Car>();
				Car car = new Car();
				car.setLicensePlate(LICENSE2);
				cars.add(car);
				user.setCar(cars);
				return user;
			} 
			else if (invocation.getArgument(0).equals(USERNAME2)) {
				CustomerAccount user = new CustomerAccount();
				user.setUsername(USERNAME2);
				user.setPassword(PASSWORD2);
				user.setName(NAME2);
				user.setToken(TOKEN2);
				return user;
			}
			else {
				return null;
			}
		});
		lenient().when(customerAccountRepository.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
			CustomerAccount user = new CustomerAccount();
			user.setUsername(USERNAME1);
			user.setPassword(PASSWORD1);
			user.setName(NAME1);
			user.setToken(TOKEN1);
			List<Car> cars = new ArrayList<Car>();
			Car car = new Car();
			car.setLicensePlate(LICENSE2);
			cars.add(car);
			user.setCar(cars);
			CustomerAccount user2 = new CustomerAccount();
			user2.setUsername(USERNAME2);
			user2.setPassword(PASSWORD2);
			user2.setName(NAME2);
			user2.setToken(TOKEN2);
			List<CustomerAccount> customerAccounts = new ArrayList<CustomerAccount>();
			customerAccounts.add(user);
			customerAccounts.add(user2);
			return customerAccounts;
		});
		lenient().when(customerAccountRepository.findCustomerAccountByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(NAME1)) {
				CustomerAccount user = new CustomerAccount();
				user.setUsername(USERNAME1);
				user.setPassword(PASSWORD1);
				user.setName(NAME1);
				user.setToken(TOKEN1);
				List<CustomerAccount> customerAccounts = new ArrayList<CustomerAccount>();
				customerAccounts.add(user);
				return customerAccounts;
			} 
			else if (invocation.getArgument(0).equals(NAME2)) {
				CustomerAccount user = new CustomerAccount();
				user.setUsername(USERNAME2);
				user.setPassword(PASSWORD2);
				user.setName(NAME2);
				user.setToken(TOKEN2);
				List<CustomerAccount> customerAccounts = new ArrayList<CustomerAccount>();
				customerAccounts.add(user);
				return customerAccounts;
			}
			else {
				return null;
			}
		});
		lenient().when(carRepository.findByLicensePlate(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(LICENSE)) {
				Car car = new Car();
				car.setLicensePlate(LICENSE);
				return car;
			} 
			else if(invocation.getArgument(0).equals(LICENSE2)) {
				addCar.setLicensePlate(LICENSE2);
				return addCar;
			} 
			else {
				return null;
			}
		});
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(customerAccountRepository.save(any(CustomerAccount.class))).thenAnswer(returnParameterAsAnswer);
		// Used for Delete, Authenticate, and Login/out Tests
		lenient().when(carRepository.save(any(Car.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(adminAccountRepository.save(any(AdminAccount.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(technicianAccountRepository.save(any(TechnicianAccount.class))).thenAnswer(returnParameterAsAnswer);

	}

	/**
	 * Create Customer Account successfully
	 */
	@Test
	public void testCreateCustomerAccountSuccessfully() {
		assertEquals(2, customerAccountService.getAllCustomerAccounts().size());

		String username = "newUsername";
		CustomerAccount user = null;
		try {
			user = customerAccountService.createCustomerAccount(username, PASSWORD1, NAME1);
		} catch (InvalidInputException e) {
			// Check that no error occurred
			fail();
		}
		//CustomerAccount savedUser = customerAccountService.getCustomerAccountByUsername(USERNAME1);
		assertNotNull(user);
		assertEquals(username, user.getUsername());
		assertEquals(PASSWORD1, user.getPassword());
		assertEquals(NAME1, user.getName());
	}



	/**
	 * Create Customer Account with empty username	
	 */
	@Test
	public void testCreateCustomerAccountWithEmptyUsername() {
		String username = "";
		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.createCustomerAccount(username, PASSWORD1, NAME1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertNull(user);
		// check error
		assertEquals("Username cannot be empty.", error);
	}

	/**
	 * Create Customer Account with spaces in username	
	 */
	@Test
	public void testCreateCustomerAccountWithSpacesInUsername() {
		String username = "This is a bad username";
		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.createCustomerAccount(username, PASSWORD1, NAME1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertNull(user);
		// check error
		assertEquals("Username cannot contain spaces.", error);
	}

	/**
	 * Create Customer Account with taken username	
	 */
	@Test
	public void testCreateCustomerAccountWithTakenUsername() {

		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.createCustomerAccount(USERNAME1, PASSWORD1, NAME1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertNull(user);
		// check error
		assertEquals("This username is not available.", error);
	}

	/**
	 * Create Customer Account with empty password	
	 */
	@Test
	public void testCreateCustomerAccountWithEmptyPassword() {
		String username = "Catherine";
		String password = "";
		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.createCustomerAccount(username, password, NAME1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertNull(user);
		// check error
		assertEquals("Password cannot be empty.", error);
	}

	/**
	 * Create Customer Account with spaces in password	
	 */
	@Test
	public void testCreateCustomerAccountWithSpacesInPassword() {
		String username = "Catherine";
		String password = "this is a bad password";
		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.createCustomerAccount(username, password, NAME1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertNull(user);
		// check error
		assertEquals("Password cannot contain spaces.", error);
	}

	/**
	 * Create Customer Account with empty name	
	 */
	@Test
	public void testCreateCustomerAccountWithEmptyName() {
		String name = "";
		String username = "Catherine";
		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.createCustomerAccount(username, PASSWORD1, name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertNull(user);
		// check error
		assertEquals("Name cannot be empty.", error);
	}

	/**
	 * Update Customer Account successfully	
	 */
	@Test
	public void testUpdateCustomerAccountSuccessfully() {
		assertEquals(2, customerAccountService.getAllCustomerAccounts().size());

		String newName = "New Name";
		String newPassword = "newPassword";

		CustomerAccount user = null; 
		try {
			user = customerAccountService.updateCustomerAccount(USERNAME1, newPassword, newName);
		} catch (InvalidInputException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(user);
		assertEquals(USERNAME1, user.getUsername());
		assertEquals(newPassword, user.getPassword());
		assertEquals(newName, user.getName());
	}

	/**
	 * Update Customer Account with invalid token	
	 */
	@Test
	public void testUpdateCustomerAccountWithInvalidToken() {
		assertEquals(2, customerAccountService.getAllCustomerAccounts().size());


		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.updateCustomerAccount(USERNAME2, PASSWORD1, NAME1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertNull(user);
		// check error
		assertEquals("You do not have permission to modify this account.", error);
	}

	/**
	 * Update Customer Account with empty password
	 */
	@Test
	public void testUpdateCustomerAccountWithEmptyPassword() {
		String newPassword = "";

		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.updateCustomerAccount(USERNAME1, newPassword, NAME1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertNull(user);
		// check error
		assertEquals("Password cannot be empty.", error);
	}

	/**
	 * Update Customer Account with spaces in password	
	 */
	@Test
	public void testUpdateCustomerAccountWithSpacesInPassword() {
		assertEquals(2, customerAccountService.getAllCustomerAccounts().size());

		String newPassword = "this is a bad password";

		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.updateCustomerAccount(USERNAME1, newPassword, NAME1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertNull(user);
		// check error
		assertEquals("Password cannot contain spaces.", error);
	}

	/**
	 * Update Customer Account with empty name	
	 */
	@Test
	public void testUpdateCustomerAccountWithEmptyName() {
		assertEquals(2, customerAccountService.getAllCustomerAccounts().size());

		String newName = "";

		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.updateCustomerAccount(USERNAME1, PASSWORD1, newName);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertNull(user);
		// check error
		assertEquals("Name cannot be empty.", error);
	}

	/**
	 * Delete Customer Account successfully
	 */
	@Test
	public void testDeleteCustomerAccountSuccessfully() {
		assertEquals(2, customerAccountService.getAllCustomerAccounts().size());

		CustomerAccount user = null; 
		try {
			user = customerAccountService.deleteCustomerAccount(USERNAME1);
		} catch (InvalidInputException e) {
			fail();
		}
		CustomerAccount savedUser = customerAccountService.getCustomerAccountByUsername(USERNAME1);
		assertNotNull(user);
		assertEquals(savedUser.getUsername(), user.getUsername());
		assertEquals(savedUser.getPassword(), user.getPassword());
		assertEquals(savedUser.getName(), user.getName());
	}

	/**
	 * Delete Customer Account with user null
	 */
	@Test
	public void testDeleteCustomerAccountWithNonExistingUser() {
		assertEquals(2, customerAccountService.getAllCustomerAccounts().size());

		String username = "Catherine";
		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.deleteCustomerAccount(username);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(user);
		// check error
		assertEquals("The user cannot be found.", error);
	}


	/**
	 * Delete Customer Account with invalid token
	 */
	@Test
	public void testDeleteCustomerAccountWithInvalidToken() {
		assertEquals(2, customerAccountService.getAllCustomerAccounts().size());

		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.deleteCustomerAccount(USERNAME2);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(user);
		// check error
		assertEquals("You do not have permission to delete this account.", error);
	}

	/**
	 * Login Customer Account successfully	
	 */
	@Test
	public void testLoginCustomerAccountSuccessfully() {
		assertEquals(2, customerAccountService.getAllCustomerAccounts().size());

		CustomerAccount user = null; 
		try {
			user = customerAccountService.loginCustomerAccount(USERNAME2, PASSWORD2);
		} catch (InvalidInputException e) {
			fail();
		}

		assertNotNull(user);			
		assertEquals(USERNAME2, user.getUsername());
		assertEquals(PASSWORD2, user.getPassword());
		assertEquals(NAME2, user.getName());
	}

	/**
	 * Login Customer Account with user null
	 */
	@Test
	public void testLoginCustomerAccountWithNonExistingUser() {
		assertEquals(2, customerAccountService.getAllCustomerAccounts().size());

		String username = "Catherine";
		String password = "password";
		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.loginCustomerAccount(username, password);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertNull(user);
		// check error
		assertEquals("The user cannot be found. Please sign up if you do not have an account yet.", error);
	}

	/**
	 * Login Customer Account with invalid credentials
	 */
	@Test
	public void testLoginCustomerAccountWithInvalidCredentials() {
		assertEquals(2, customerAccountService.getAllCustomerAccounts().size());

		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.loginCustomerAccount(USERNAME2, PASSWORD1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertNull(user);
		// check error
		assertEquals("Username or password incorrect. Please try again.", error);
	}

	/**
	 * Logout Customer Account successfully
	 */
	@Test
	public void testLogoutCustomerAccountSuccessfully() {
		assertEquals(2, customerAccountService.getAllCustomerAccounts().size());

		CustomerAccount user = null; 
		try {
			user = customerAccountService.logoutCustomerAccount(USERNAME1);
		} catch (InvalidInputException e) {
			fail();
		}

		assertNotNull(user);
		assertEquals(USERNAME1, user.getUsername());
		assertEquals(PASSWORD1, user.getPassword());
		assertEquals(NAME1, user.getName());
	}

	/**
	 * Logout Customer Account with user null
	 */
	@Test
	public void testLogoutCustomerAccountWithNonExistingUser() {
		assertEquals(2, customerAccountService.getAllCustomerAccounts().size());

		String username = "Catherine";
		String error = null;
		CustomerAccount user = null; 
		try {
			user = customerAccountService.logoutCustomerAccount(username);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(user);
		// check error
		assertEquals("The user cannot be found.", error);
	}

	/**
	 * Authenticate Customer Account successfully
	 */
	@Test
	public void testAuthenticateCustomerAccountSuccessfully() {

		CustomerAccount user = null; 
		try {
			user = customerAccountService.authenticateCustomerAccount(USERNAME1);
		} catch (InvalidInputException e) {
			fail();
		}
		assertNotNull(user);
		assertNotEquals(0, user.getToken());
	}

	/**
	 * Authenticate Customer Account with user null
	 */
	@Test
	public void testAuthenticateCustomerAccountWithNonExistingUser() {

		String username = "Catherine";
		String error = null;
		CustomerAccount user = null;
		try {
			user = customerAccountService.authenticateCustomerAccount(username);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(user);
		// check error
		assertEquals("The user cannot be found.", error);
	}

	/**
	 * Authenticate Customer Account with invalid token
	 */
	@Test
	public void testAuthenticateCustomerAccountWithInvalidToken() {

		String error = null;
		CustomerAccount user = null;
		try {
			user = customerAccountService.authenticateCustomerAccount(USERNAME2);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(user);
		// check error
		assertEquals("An error occured. Please try again.", error);

	}

	/**
	 * Get Customer Accounts by Name
	 */
	@Test
	public void testGetCustomerAccountsByName() {

		List<CustomerAccount> users = customerAccountService.getCustomerAccountsByName(NAME1);
		assertNotNull(users);
		assertEquals(users.get(0).getUsername(), USERNAME1);
	}

	/**
	 * Get Customer Account by Car
	 */
	@Test
	public void testGetCustomerAccountWithCar() {
		CustomerAccount user = customerAccountService.getCustomerAccountWithCar(LICENSE2);
		assertNotNull(user);
		assertEquals(user.getUsername(), USERNAME1);
	}

}