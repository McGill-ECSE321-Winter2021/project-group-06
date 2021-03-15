package vehiclerepairshop.service;

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
public class TestTechnicianAccountService {

	private static final String USERNAME1 = "Username1";
	private static final String NAME1 = "Alicia";
	private static final String PASSWORD1 = "PassWord123!";
	private static final int TOKEN1 = 1000000;
	
	private static final String USERNAME2 = "Username2";
	private static final String NAME2 = "Catherine The 1st";
	private static final String PASSWORD2 = "GhostPassword101?!";
	private static final int TOKEN2 = 0; //invalid

	private static final int APT_ID = 12;

	

	@Mock
	private TechnicianAccountRepository technicianAccountRepository;
	@Mock
	private AppointmentRepository AppointmentRepository;
	@Mock
	private AdminAccountRepository adminAccountRepository;
	@Mock
	private CustomerAccountRepository customerAccountRepository;


	@InjectMocks
	private TechnicianAccountService technicianAccountService;
	

	@BeforeEach
	public void setMockOutput() {
		lenient().when(technicianAccountRepository.findByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(USERNAME1)) {
				TechnicianAccount user = new TechnicianAccount();
				user.setUsername(USERNAME1);
				user.setPassword(PASSWORD1);
				user.setName(NAME1);
				user.setToken(TOKEN1);
				return user;
			} 
			else if (invocation.getArgument(0).equals(USERNAME2)) {
				TechnicianAccount user = new TechnicianAccount();
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
		lenient().when(technicianAccountRepository.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
			TechnicianAccount user = new TechnicianAccount();
			user.setUsername(USERNAME1);
			user.setPassword(PASSWORD1);
			user.setName(NAME1);
			user.setToken(TOKEN1);
			TechnicianAccount user2 = new TechnicianAccount();
			user2.setUsername(USERNAME2);
			user2.setPassword(PASSWORD2);
			user2.setName(NAME2);
			user2.setToken(TOKEN2);
			List<TechnicianAccount> technicianAccounts = new ArrayList<TechnicianAccount>();
			technicianAccounts.add(user);
			technicianAccounts.add(user2);
            return technicianAccounts;
         });
		lenient().when(technicianAccountRepository.findTechnicianAccountByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(NAME1)) {
				TechnicianAccount user = new TechnicianAccount();
				user.setUsername(USERNAME1);
				user.setPassword(PASSWORD1);
				user.setName(NAME1);
				user.setToken(TOKEN1);
				List<TechnicianAccount> technicianAccounts = new ArrayList<TechnicianAccount>();
				technicianAccounts.add(user);
				return technicianAccounts;
			} 
			else if (invocation.getArgument(0).equals(NAME2)) {
				TechnicianAccount user = new TechnicianAccount();
				user.setUsername(USERNAME2);
				user.setPassword(PASSWORD2);
				user.setName(NAME2);
				user.setToken(TOKEN2);
				List<TechnicianAccount> technicianAccounts = new ArrayList<TechnicianAccount>();
				technicianAccounts.add(user);
				return technicianAccounts;
			}
			else {
				return null;
			}
		});
		lenient().when(technicianAccountRepository.findTechnicianAccountByAppointment(any(Appointment.class))).thenAnswer( (InvocationOnMock invocation) -> { 
			Appointment appointment = invocation.getArgument(0);
			
			if (appointment.getAppointmentId() == APT_ID) {
				TechnicianAccount user = new TechnicianAccount();
				user.setUsername(USERNAME1);
				user.setPassword(PASSWORD1);
				user.setName(NAME1);
				user.setToken(TOKEN1);
				TechnicianAccount user2 = new TechnicianAccount();
				user2.setUsername(USERNAME2);
				user2.setPassword(PASSWORD2);
				user2.setName(NAME2);
				user2.setToken(TOKEN2);
				List<TechnicianAccount> technicianAccounts = new ArrayList<TechnicianAccount>();
				technicianAccounts.add(user);
				technicianAccounts.add(user2);
	            return technicianAccounts;
				
			}
			else {
				return null;
			}
			
         });
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(technicianAccountRepository.save(any(TechnicianAccount.class))).thenAnswer(returnParameterAsAnswer);
		// Used for Delete, Authenticate, and Login/out Tests
		lenient().when(AppointmentRepository.save(any(Appointment.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(adminAccountRepository.save(any(AdminAccount.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(customerAccountRepository.save(any(CustomerAccount.class))).thenAnswer(returnParameterAsAnswer);

	}
	
		/**
		 * Create Technician Account successfully
		 */
		@Test
		public void testCreateTechnicianAccountSuccessfully() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());
			
			String username = "newUsername";
			TechnicianAccount user = null;
			try {
				user = technicianAccountService.createTechnicianAccount(username, PASSWORD1, NAME1);
			} catch (InvalidInputException e) {
				// Check that no error occurred
				fail();
			}
			//TechnicianAccount savedUser = technicianAccountService.getTechnicianAccountByUsername(USERNAME1);
			assertNotNull(user);
			assertEquals(username, user.getUsername());
			assertEquals(PASSWORD1, user.getPassword());
			assertEquals(NAME1, user.getName());
		}
		
	

		/**
		 * Create Technician Account with empty username	
		 */
		@Test
		public void testCreateTechnicianAccountWithEmptyUsername() {
			String username = "";
			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.createTechnicianAccount(username, PASSWORD1, NAME1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Username cannot be empty.", error);
		}
				
		/**
		 * Create Technician Account with spaces in username	
		 */
		@Test
		public void testCreateTechnicianAccountWithSpacesInUsername() {
			String username = "This is a bad username";
			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.createTechnicianAccount(username, PASSWORD1, NAME1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Username cannot contain spaces.", error);
		}
		
		/**
		 * Create Technician Account with taken username	
		 */
		@Test
		public void testCreateTechnicianAccountWithTakenUsername() {
			
			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.createTechnicianAccount(USERNAME1, PASSWORD1, NAME1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("This username is not available.", error);
		}
		
		/**
		 * Create Technician Account with empty password	
		 */
		@Test
		public void testCreateTechnicianAccountWithEmptyPassword() {
			String username = "Catherine";
			String password = "";
			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.createTechnicianAccount(username, password, NAME1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Password cannot be empty.", error);
		}
		
		/**
		 * Create Technician Account with spaces in password	
		 */
		@Test
		public void testCreateTechnicianAccountWithSpacesInPassword() {
			String username = "Catherine";
			String password = "this is a bad password";
			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.createTechnicianAccount(username, password, NAME1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Password cannot contain spaces.", error);
		}
		
		/**
		 * Create Technician Account with empty name	
		 */
		@Test
		public void testCreateTechnicianAccountWithEmptyName() {
			String name = "";
			String username = "Catherine";
			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.createTechnicianAccount(username, PASSWORD1, name);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Name cannot be empty.", error);
		}
		
		/**
		 * Update Technician Account successfully	
		 */
		@Test
		public void testUpdateTechnicianAccountSuccessfully() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			String newName = "New Name";
			String newUsername = "Catherine";
			String newPassword = "newPassword";
			
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.updateTechnicianAccount(USERNAME1, newUsername, newPassword, newName);
			} catch (InvalidInputException e) {
				// Check that no error occurred
				fail();
			}
			assertNotNull(user);
			assertEquals(newUsername, user.getUsername());
			assertEquals(newPassword, user.getPassword());
			assertEquals(newName, user.getName());
		}
		
		/**
		 * Update Technician Account with invalid token	
		 */
		@Test
		public void testUpdateTechnicianAccountWithInvalidToken() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			String newUsername = "Catherine";

			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.updateTechnicianAccount(USERNAME2, newUsername, PASSWORD1, NAME1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("You do not have permission to modify this account.", error);
		}
		
		/**
		 * Update Technician Account with empty username	
		 */
		@Test
		public void testUpdateTechnicianAccountWithEmptyUsername() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			String newUsername = "";

			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.updateTechnicianAccount(USERNAME1, newUsername, PASSWORD1, NAME1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Username cannot be empty.", error);
		}
		
		/**
		 * Update Technician Account with spaces in username	
		 */
		@Test
		public void testUpdateTechnicianAccountWithSpacesInUsername() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			String newUsername = "this is a bad username";

			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.updateTechnicianAccount(USERNAME1, newUsername, PASSWORD1, NAME1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Username cannot contain spaces.", error);
		}
		
		/**
		 * Update Technician Account with taken username	
		 */
		@Test
		public void testUpdateTechnicianAccountWithTakenUsername() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.updateTechnicianAccount(USERNAME1, USERNAME2, PASSWORD1, NAME1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("This username is not available.", error);
		}
		
		/**
		 * Update Technician Account with empty password
		 */
		@Test
		public void testUpdateTechnicianAccountWithEmptyPassword() {
			String newUsername = "Catherine";
			String newPassword = "";

			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.updateTechnicianAccount(USERNAME1, newUsername, newPassword, NAME1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Password cannot be empty.", error);
		}
		
		/**
		 * Update Technician Account with spaces in password	
		 */
		@Test
		public void testUpdateTechnicianAccountWithSpacesInPassword() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			String newUsername = "Catherine";
			String newPassword = "this is a bad password";

			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.updateTechnicianAccount(USERNAME1, newUsername, newPassword, NAME1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Password cannot contain spaces.", error);
		}
		
		/**
		 * Update Technician Account with empty name	
		 */
		@Test
		public void testUpdateTechnicianAccountWithEmptyName() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			String newUsername = "Catherine";
			String newName = "";

			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.updateTechnicianAccount(USERNAME1, newUsername, PASSWORD1, newName);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Name cannot be empty.", error);
		}
		
		/**
		 * Delete Technician Account successfully
		 */
		@Test
		public void testDeleteTechnicianAccountSuccessfully() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.deleteTechnicianAccount(USERNAME1);
			} catch (InvalidInputException e) {
				fail();
			}
			TechnicianAccount savedUser = technicianAccountService.getTechnicianAccountByUsername(USERNAME1);
			assertNotNull(user);
			assertEquals(savedUser.getUsername(), user.getUsername());
			assertEquals(savedUser.getPassword(), user.getPassword());
			assertEquals(savedUser.getName(), user.getName());
		}
		
		/**
		 * Delete Technician Account with user null
		 */
		@Test
		public void testDeleteTechnicianAccountWithNonExistingUser() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			String username = "Catherine";
			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.deleteTechnicianAccount(username);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertNull(user);
			// check error
			assertEquals("The user cannot be found.", error);
		}
		
		
		/**
		 * Delete Technician Account with invalid token
		 */
		@Test
		public void testDeleteTechnicianAccountWithInvalidToken() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.deleteTechnicianAccount(USERNAME2);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertNull(user);
			// check error
			assertEquals("You do not have permission to delete this account.", error);
		}
		
		/**
		 * Login Technician Account successfully	
		 */
		@Test
		public void testLoginTechnicianAccountSuccessfully() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.loginTechnicianAccount(USERNAME2, PASSWORD2);
			} catch (InvalidInputException e) {
				fail();
			}
			 
			assertNotNull(user);			
			assertEquals(USERNAME2, user.getUsername());
			assertEquals(PASSWORD2, user.getPassword());
			assertEquals(NAME2, user.getName());
		}
		
		/**
		 * Login Technician Account with user null
		 */
		@Test
		public void testLoginTechnicianAccountWithNonExistingUser() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			String username = "Catherine";
			String password = "password";
			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.loginTechnicianAccount(username, password);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			 
			assertNull(user);
			// check error
			assertEquals("The user cannot be found. Please sign up if you do not have an account yet.", error);
		}
		
		/**
		 * Login Technician Account with invalid credentials
		 */
		@Test
		public void testLoginTechnicianAccountWithInvalidCredentials() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.loginTechnicianAccount(USERNAME2, PASSWORD1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			 
			assertNull(user);
			// check error
			assertEquals("Username or password incorrect. Please try again.", error);
		}
		
		/**
		 * Logout Technician Account successfully
		 */
		@Test
		public void testLogoutTechnicianAccountSuccessfully() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.logoutTechnicianAccount(USERNAME1);
			} catch (InvalidInputException e) {
				fail();
			}
			 
			assertNotNull(user);
			assertEquals(USERNAME1, user.getUsername());
			assertEquals(PASSWORD1, user.getPassword());
			assertEquals(NAME1, user.getName());
		}
		
		/**
		 * Logout Technician Account with user null
		 */
		@Test
		public void testLogoutTechnicianAccountWithNonExistingUser() {
			assertEquals(2, technicianAccountService.getAllTechnicianAccounts().size());

			String username = "Catherine";
			String error = null;
			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.logoutTechnicianAccount(username);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertNull(user);
			// check error
			assertEquals("The user cannot be found.", error);
		}
		
		/**
		 * Authenticate Technician Account successfully
		 */
		@Test
		public void testAuthenticateTechnicianAccountSuccessfully() {

			TechnicianAccount user = null; 
			try {
				user = technicianAccountService.authenticateTechnicianAccount(USERNAME1);
			} catch (InvalidInputException e) {
				fail();
			}
			assertNotNull(user);
			assertNotEquals(0, user.getToken());
		}
		
		/**
		 * Authenticate Technician Account with user null
		 */
		@Test
		public void testAuthenticateTechnicianAccountWithNonExistingUser() {
			
			String username = "Catherine";
			String error = null;
			TechnicianAccount user = null;
			try {
				user = technicianAccountService.authenticateTechnicianAccount(username);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertNull(user);
			// check error
			assertEquals("The user cannot be found.", error);
		}
		
		/**
		 * Authenticate Technician Account with invalid token
		 */
		@Test
		public void testAuthenticateTechnicianAccountWithInvalidToken() {
			
			String error = null;
			TechnicianAccount user = null;
			try {
				user = technicianAccountService.authenticateTechnicianAccount(USERNAME2);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertNull(user);
			// check error
			assertEquals("An error occured. Please try again.", error);
		
		}
		
		/**
		 * Get Technician Accounts by Name
		 */
		@Test
		public void testGetTechnicianAccountsByName() {
			
			List<TechnicianAccount> users = technicianAccountService.getTechnicianAccountsByName(NAME1);
			assertNotNull(users);
			assertEquals(users.get(0).getUsername(), USERNAME1);
		}
		
		/**
		 * Get Technician Accounts by Appointment
		 */
		@Test
		public void testGetAllTechnicianAccountsForAppointment() {
			Appointment appointment = new Appointment();
			appointment.setAppointmentId(APT_ID);;
			List<TechnicianAccount> users = technicianAccountService.getTechnicianAccountsForAppointment(appointment);
			assertNotNull(users);
			assertEquals(users.get(0).getUsername(), USERNAME1);
			assertEquals(users.get(1).getUsername(), USERNAME2);
		}
		

}