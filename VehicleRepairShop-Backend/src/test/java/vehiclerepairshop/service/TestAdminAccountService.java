package vehiclerepairshop.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.vehiclerepairshop.dao.*;
import ca.mcgill.ecse321.vehiclerepairshop.model.*;
import vehiclerepairshop.dto.AdminAccountDto;




@ExtendWith(MockitoExtension.class)
public class TestAdminAccountService {

	private static final String USERNAME1 = "Username1";
	private static final String NAME1 = "Alicia";
	private static final String PASSWORD1 = "PassWord123!";
	private static final int TOKEN1 = 1000000;
	
	private static final String USERNAME2 = "Username2";
	private static final String NAME2 = "Catherine The 1st";
	private static final String PASSWORD2 = "GhostPassword101?!";


	@Mock
	private AdminAccountRepository adminAccountRepository;
	@Mock
	private BusinessInformationRepository businessInformationRepository;
	@Mock
	private CustomerAccountRepository customerAccountRepository;
	@Mock
	private CarRepository carRepository;
	@Mock
	private TechnicianAccountRepository technicianAccountRepository;
	@Mock
	private AppointmentRepository appointmentRepository;
	@Mock
	private TimeSlotRepository timeSlotRepository;
	@Mock
	private OfferedServiceRepository offeredServiceRepository;
	@Mock
	private GarageRepository garageRepository;


	@InjectMocks
	private AdminAccountService adminAccountService;
	

	@BeforeEach
	public void setMockOutput() {
		lenient().when(adminAccountRepository.findByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(USERNAME1)) {
				AdminAccount user = new AdminAccount();
				user.setUsername(USERNAME1);
				user.setPassword(PASSWORD1);
				user.setName(NAME1);
				user.setToken(TOKEN1);
				return user;
			} else {
				return null;
			}
		});
		// TODO find by name (returns a list) -- do we need it tho?
//		lenient().when(adminAccountRepository.fin(anyString())).thenAnswer((InvocationOnMock invocation) -> {
//			if (invocation.getArgument(0).equals(USER_EMAIL)) {
//				User user = new User();
//				user.setUserName(USER_NAME);
//				user.setEmail(USER_EMAIL);
//				user.setPassword(passwordEncoder.encode(USER_PASSWORD));
//				return user;
//			} else {
//				return null;
//			}
//		});
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(adminAccountRepository.save(any(AdminAccount.class))).thenAnswer(returnParameterAsAnswer);
		// Used for Delete Tests
		lenient().when(businessInformationRepository.save(any(BusinessInformation.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(customerAccountRepository.save(any(CustomerAccount.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(carRepository.save(any(Car.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(technicianAccountRepository.save(any(TechnicianAccount.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(appointmentRepository.save(any(Appointment.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(timeSlotRepository.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(offeredServiceRepository.save(any(OfferedService.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(garageRepository.save(any(Garage.class))).thenAnswer(returnParameterAsAnswer);
	}
	
		/**
		 * Create Admin Account successfully
		 */
		@Test
		public void testCreateAdminAccountSuccessfully() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			AdminAccount user = null;
			try {
				user = adminAccountService.createAdminAccount(username, password, name);
			} catch (InvalidInputException e) {
				// Check that no error occurred
				fail();
			}
			assertNotNull(user);
			assertEquals(username, user.getUsername());
			assertEquals(password, user.getPassword());
			assertEquals(name, user.getName());
		}
		
	

		/**
		 * Create Admin Account with empty username	
		 */
		@Test
		public void testCreateAdminAccountWithEmptyUsername() {
			String name = NAME2;
			String username = "";
			String password = PASSWORD2;
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.createAdminAccount(username, password, name);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Username cannot be empty.", error);
		}
				
		/**
		 * Create Admin Account with spaces in username	
		 */
		@Test
		public void testCreateAdminAccountWithSpacesInUsername() {
			String name = NAME2;
			String username = "This is a bad username";
			String password = PASSWORD2;
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.createAdminAccount(username, password, name);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Username cannot contain spaces.", error);
		}
		
		/**
		 * Create Admin Account with taken username	
		 */
		@Test
		public void testCreateAdminAccountWithTakenUsername() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			
			assertEquals(1, adminAccountService.getAllAdminAccounts().size());
			
			String name2 = NAME1;
			String password2 = PASSWORD1;
			
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.createAdminAccount(username, password2, name2);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("This username is not available.", error);
		}
		
		/**
		 * Create Admin Account with empty password	
		 */
		@Test
		public void testCreateAdminAccountWithEmptyPassword() {
			String name = NAME2;
			String username = USERNAME2;
			String password = "";
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.createAdminAccount(username, password, name);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Password cannot be empty.", error);
		}
		
		/**
		 * Create Admin Account with spaces in password	
		 */
		@Test
		public void testCreateAdminAccountWithSpacesInPassword() {
			String name = NAME2;
			String username = USERNAME2;
			String password = "This is a bad password!";
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.createAdminAccount(username, password, name);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Password cannot contain spaces.", error);
		}
		
		/**
		 * Create Admin Account with empty name	
		 */
		@Test
		public void testCreateAdminAccountWithEmptyName() {
			String name = "";
			String username = USERNAME2;
			String password = PASSWORD2;
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.createAdminAccount(username, password, name);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Name cannot be empty.", error);
		}
		
		/**
		 * Update Admin Account successfully	
		 */
		@Test
		public void testUpdateAdminAccountSuccessfully() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			
			assertEquals(1, adminAccountService.getAllAdminAccounts().size());
			
			String newName = NAME1;
			String newUsername = USERNAME1;
			String newPassword = PASSWORD1;
			
			
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.updateAdminAccount(username, newUsername, newPassword, newName);
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
		 * Update Admin Account with invalid token	
		 */
		@Test
		public void testUpdateAdminAccountWithInvalidToken() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			
			assertEquals(1, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			
			//adminAccountService.getAdminAccountByUsername(username).setToken(0);
			adminAccountService.logoutAdminAccount(username); // this should set token as invalid
			
			// from tutorial
//			lenient().when(adminAccountService.existsByUsername(anyString())).thenReturn(true);
//			lenient().when(eventDao.existsById(anyString())).thenReturn(true);
			
			String newName = NAME1;
			String newUsername = USERNAME1;
			String newPassword = PASSWORD1;

			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.updateAdminAccount(username, newUsername, newPassword, newName);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("You do not have permission to modify this account.", error);
		}
		
		/**
		 * Update Admin Account with empty username	
		 */
		@Test
		public void testUpdateAdminAccountWithEmptyUsername() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			assertEquals(1, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			
			String newName = NAME1;
			String newUsername = "";
			String newPassword = PASSWORD1;

			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.updateAdminAccount(username, newUsername, newPassword, newName);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Username cannot be empty.", error);
		}
		
		/**
		 * Update Admin Account with spaces in username	
		 */
		@Test
		public void testUpdateAdminAccountWithSpacesInUsername() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			assertEquals(1, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			
			String newName = NAME1;
			String newUsername = "This is a bad username";
			String newPassword = PASSWORD1;

			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.updateAdminAccount(username, newUsername, newPassword, newName);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Username cannot contain spaces.", error);
		}
		
		/**
		 * Update Admin Account with taken username	
		 */
		@Test
		public void testUpdateAdminAccountWithTakenUsername() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());
			
			String name1 = NAME1;
			String username1 = USERNAME1;
			String password1 = PASSWORD1;
			adminAccountService.createAdminAccount(username1, password1, name1);
			assertEquals(2, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			
			String name2 = NAME2;
			String username2 = USERNAME2;
			String password2 = PASSWORD2;
			adminAccountService.createAdminAccount(username2, password2, name2);
			assertEquals(1, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			
			String newName = "New Name";
			String newPassword = "NewPassword";

			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.updateAdminAccount(username1, username2, newPassword, newName);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("This username is not available.", error);
		}
		
		/**
		 * Update Admin Account with empty password
		 */
		@Test
		public void testUpdateAdminAccountWithEmptyPassword() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			assertEquals(1, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			
			String newName = NAME1;
			String newUsername = USERNAME1;
			String newPassword = "";

			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.updateAdminAccount(username, newUsername, newPassword, newName);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Password cannot be empty.", error);
		}
		
		/**
		 * Update Admin Account with spaces in password	
		 */
		@Test
		public void testUpdateAdminAccountWithSpacesInPassword() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			assertEquals(1, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			
			String newName = NAME1;
			String newUsername = USERNAME1;
			String newPassword = "This is a bad password!";

			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.updateAdminAccount(username, newUsername, newPassword, newName);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Password cannot contain spaces.", error);
		}
		
		/**
		 * Update Admin Account with empty name	
		 */
		@Test
		public void testUpdateAdminAccountWithEmptyName() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			assertEquals(1, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			
			String newName = "";
			String newUsername = USERNAME1;
			String newPassword = PASSWORD1;

			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.updateAdminAccount(username, newUsername, newPassword, newName);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("Name cannot be empty.", error);
		}
		
		/**
		 * Delete Admin Account successfully
		 */
		@Test
		public void testDeleteAdminAccountSuccessfully() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			assertEquals(1, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			
			boolean successful = false;
			try {
				successful = adminAccountService.deleteAdminAccount(username);
			} catch (InvalidInputException e) {
				fail();
			}
			assertTrue(successful);
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());
		}
		
		/**
		 * Delete Admin Account with user null
		 */
		@Test
		public void testDeleteAdminAccountWithUserNull() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());
			
			String error = null;
			boolean successful = false;
			try {
				successful = adminAccountService.deleteAdminAccount("genericUsername");
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertFalse(successful);
			// check error
			assertEquals("The user cannot be found.", error);
		}
		
		
		/**
		 * Delete Admin Account with invalid token
		 */
		@Test
		public void testDeleteAdminAccountWithInvalidToken() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());
			
			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			assertEquals(1, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			adminAccountService.logoutAdminAccount(username); // this should set token as invalid
			
			String error = null;
			boolean successful = false;
			try {
				successful = adminAccountService.deleteAdminAccount(username);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertFalse(successful);
			// check error
			assertEquals("You do not have permission to delete this account.", error);
		}
		
		/**
		 * Login Admin Account successfully	
		 */
		@Test
		public void testLoginAdminAccountSuccessfully() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			assertEquals(1, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			
			boolean successful = false;
			try {
				successful = adminAccountService.loginAdminAccount(username, password);
			} catch (InvalidInputException e) {
				fail();
			}
			assertTrue(successful);
			assertNotEquals(0, adminAccountService.getAdminAccountByUsername(username).getToken());
		}
		
		/**
		 * Login Admin Account with user null
		 */
		@Test
		public void testLoginAdminAccountWithUserNull() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());
			
			String error = null;
			boolean successful = false;
			try {
				successful = adminAccountService.loginAdminAccount("aUsername", "aPassword");
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertFalse(successful);
			// check error
			assertEquals("The user cannot be found. Please sign up if you do not have an account yet.", error);
		}
		
		/**
		 * Login Admin Account with invalid credentials
		 */
		@Test
		public void testLoginAdminAccountWithInvalidCredentials() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());
			
			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			assertEquals(1, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			
			String wrongPassword = "wrongPassword";
			
			String error = null;
			boolean successful = false;
			try {
				successful = adminAccountService.loginAdminAccount(username, wrongPassword);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertFalse(successful);
			// check error
			assertEquals("Username or password incorrect. Please try again.", error);
		}
		
		/**
		 * Logout Admin Account successfully
		 */
		@Test
		public void testLogoutAdminAccountSuccessfully() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			assertEquals(1, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			
			boolean successful = false;
			try {
				successful = adminAccountService.logoutAdminAccount(username);
			} catch (InvalidInputException e) {
				fail();
			}
			assertTrue(successful);
			assertEquals(0, adminAccountService.getAdminAccountByUsername(username).getToken());
		}
		
		/**
		 * Logout Admin Account with user null
		 */
		@Test
		public void testLogoutAdminAccountWithUserNull() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());
			
			String error = null;
			boolean successful = false;
			try {
				successful = adminAccountService.logoutAdminAccount("someUsername");
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertFalse(successful);
			// check error
			assertEquals("The user cannot be found.", error);
		}
		
		/**
		 * Authenticate Admin Account successfully
		 */
		@Test
		public void testAuthenticateAdminAccountSuccessfully() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			assertEquals(1, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			
			boolean successful = false;
			try {
				successful = adminAccountService.authenticateAdminAccount(username);
			} catch (InvalidInputException e) {
				fail();
			}
			assertTrue(successful);
		}
		
		/**
		 * Authenticate Admin Account with user null
		 */
		@Test
		public void testAuthenticateAdminAccountWithUserNull() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());
			
			String error = null;
			boolean successful = false;
			try {
				successful = adminAccountService.authenticateAdminAccount("username");
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertFalse(successful);
			// check error
			assertEquals("The user cannot be found.", error);
		}
		
		/**
		 * Authenticate Admin Account with invalid token
		 */
		@Test
		public void testAuthenticateAdminAccountWithInvalidToken() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = NAME2;
			String username = USERNAME2;
			String password = PASSWORD2;
			adminAccountService.createAdminAccount(username, password, name);
			
			assertEquals(1, adminAccountService.getAllAdminAccounts().size()); // may not be needed
			adminAccountService.logoutAdminAccount(username); // this should set token as invalid	

			String error = null;
			boolean successful = false;
			try {
				successful = adminAccountService.authenticateAdminAccount(username);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertFalse(successful);
			// check error
			assertEquals("An error occured. Please try again.", error);
		
		}
		

}