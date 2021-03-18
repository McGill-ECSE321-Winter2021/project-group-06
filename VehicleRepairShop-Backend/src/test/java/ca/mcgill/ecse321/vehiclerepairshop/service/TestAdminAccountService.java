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
public class TestAdminAccountService {

	private static final String USERNAME1 = "Username1";
	private static final String NAME1 = "Alicia";
	private static final String PASSWORD1 = "PassWord123!";
	private static final int TOKEN1 = 1000000;
	
	private static final String USERNAME2 = "Username2";
	private static final String NAME2 = "Catherine The 1st";
	private static final String PASSWORD2 = "GhostPassword101?!";
	private static final int TOKEN2 = 0; //invalid

	private static final String INFO_NAME = "business info name";

	

	@Mock
	private AdminAccountRepository adminAccountRepository;
	@Mock
	private BusinessInformationRepository businessInformationRepository;
	@Mock
	private CustomerAccountRepository customerAccountRepository;
	@Mock
	private TechnicianAccountRepository technicianAccountRepository;


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
			} 
			else if (invocation.getArgument(0).equals(USERNAME2)) {
				AdminAccount user = new AdminAccount();
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
		lenient().when(adminAccountRepository.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
			AdminAccount user = new AdminAccount();
			user.setUsername(USERNAME1);
			user.setPassword(PASSWORD1);
			user.setName(NAME1);
			user.setToken(TOKEN1);
			AdminAccount user2 = new AdminAccount();
			user2.setUsername(USERNAME2);
			user2.setPassword(PASSWORD2);
			user2.setName(NAME2);
			user2.setToken(TOKEN2);
			List<AdminAccount> adminAccounts = new ArrayList<AdminAccount>();
			adminAccounts.add(user);
			adminAccounts.add(user2);
            return adminAccounts;
         });
		lenient().when(adminAccountRepository.findAdminAccountByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(NAME1)) {
				AdminAccount user = new AdminAccount();
				user.setUsername(USERNAME1);
				user.setPassword(PASSWORD1);
				user.setName(NAME1);
				user.setToken(TOKEN1);
				List<AdminAccount> adminAccounts = new ArrayList<AdminAccount>();
				adminAccounts.add(user);
				return adminAccounts;
			} 
			else if (invocation.getArgument(0).equals(NAME2)) {
				AdminAccount user = new AdminAccount();
				user.setUsername(USERNAME2);
				user.setPassword(PASSWORD2);
				user.setName(NAME2);
				user.setToken(TOKEN2);
				List<AdminAccount> adminAccounts = new ArrayList<AdminAccount>();
				adminAccounts.add(user);
				return adminAccounts;
			}
			else {
				return null;
			}
		});
		lenient().when(businessInformationRepository.findBusinessInformationByName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(INFO_NAME)) {
				BusinessInformation businessInformation = new BusinessInformation();
				businessInformation.setName(INFO_NAME);
				return businessInformation;
			} 
			else {
				return null;
			}
		});
		lenient().when(adminAccountRepository.findByBusinessInformation(any(BusinessInformation.class))).thenAnswer( (InvocationOnMock invocation) -> { 
			BusinessInformation businessInfo = invocation.getArgument(0);
			
			if (businessInfo.getName().equals(INFO_NAME)) {
				AdminAccount user = new AdminAccount();
				user.setUsername(USERNAME1);
				user.setPassword(PASSWORD1);
				user.setName(NAME1);
				user.setToken(TOKEN1);
				AdminAccount user2 = new AdminAccount();
				user2.setUsername(USERNAME2);
				user2.setPassword(PASSWORD2);
				user2.setName(NAME2);
				user2.setToken(TOKEN2);
				List<AdminAccount> adminAccounts = new ArrayList<AdminAccount>();
				adminAccounts.add(user);
				adminAccounts.add(user2);
	            return adminAccounts;
				
			}
			else {
				return null;
			}
			
         });
		
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(adminAccountRepository.save(any(AdminAccount.class))).thenAnswer(returnParameterAsAnswer);
		// Used for Delete, Authenticate, and Login/out Tests
		lenient().when(businessInformationRepository.save(any(BusinessInformation.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(customerAccountRepository.save(any(CustomerAccount.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(technicianAccountRepository.save(any(TechnicianAccount.class))).thenAnswer(returnParameterAsAnswer);

	}
	
		/**
		 * Create Admin Account successfully
		 */
		@Test
		public void testCreateAdminAccountSuccessfully() {
			assertEquals(2, adminAccountService.getAllAdminAccounts().size());
			
			String username = "newUsername";
			AdminAccount user = null;
			try {
				user = adminAccountService.createAdminAccount(username, PASSWORD1, NAME1);
			} catch (InvalidInputException e) {
				// Check that no error occurred
				fail();
			}
			//AdminAccount savedUser = adminAccountService.getAdminAccountByUsername(USERNAME1);
			assertNotNull(user);
			assertEquals(username, user.getUsername());
			assertEquals(PASSWORD1, user.getPassword());
			assertEquals(NAME1, user.getName());
		}
		
	

		/**
		 * Create Admin Account with empty username	
		 */
		@Test
		public void testCreateAdminAccountWithEmptyUsername() {
			String username = "";
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.createAdminAccount(username, PASSWORD1, NAME1);
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
			String username = "This is a bad username";
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.createAdminAccount(username, PASSWORD1, NAME1);
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
			
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.createAdminAccount(USERNAME1, PASSWORD1, NAME1);
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
			String username = "Catherine";
			String password = "";
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.createAdminAccount(username, password, NAME1);
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
			String username = "Catherine";
			String password = "this is a bad password";
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.createAdminAccount(username, password, NAME1);
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
			String username = "Catherine";
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.createAdminAccount(username, PASSWORD1, name);
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
			assertEquals(2, adminAccountService.getAllAdminAccounts().size());

			String newName = "New Name";
			String newPassword = "newPassword";
			
			AdminAccount user = null; 
			try {
				user = adminAccountService.updateAdminAccount(USERNAME1, newPassword, newName);
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
		 * Update Admin Account with invalid token	
		 */
		@Test
		public void testUpdateAdminAccountWithInvalidToken() {
			assertEquals(2, adminAccountService.getAllAdminAccounts().size());

			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.updateAdminAccount(USERNAME2, PASSWORD1, NAME1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			assertNull(user);
			// check error
			assertEquals("You do not have permission to modify this account.", error);
		}

		/**
		 * Update Admin Account with empty password
		 */
		@Test
		public void testUpdateAdminAccountWithEmptyPassword() {
			
			String newPassword = "";

			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.updateAdminAccount(USERNAME1, newPassword, NAME1);
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
			assertEquals(2, adminAccountService.getAllAdminAccounts().size());

			String newPassword = "this is a bad password";

			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.updateAdminAccount(USERNAME1, newPassword, NAME1);
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
			assertEquals(2, adminAccountService.getAllAdminAccounts().size());

			String newName = "";

			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.updateAdminAccount(USERNAME1,  PASSWORD1, newName);
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
			assertEquals(2, adminAccountService.getAllAdminAccounts().size());

			AdminAccount user = null; 
			try {
				user = adminAccountService.deleteAdminAccount(USERNAME1);
			} catch (InvalidInputException e) {
				fail();
			}
			AdminAccount savedUser = adminAccountService.getAdminAccountByUsername(USERNAME1);
			assertNotNull(user);
			assertEquals(savedUser.getUsername(), user.getUsername());
			assertEquals(savedUser.getPassword(), user.getPassword());
			assertEquals(savedUser.getName(), user.getName());
		}
		
		/**
		 * Delete Admin Account with user null
		 */
		@Test
		public void testDeleteAdminAccountWithNonExistingUser() {
			assertEquals(2, adminAccountService.getAllAdminAccounts().size());

			String username = "Catherine";
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.deleteAdminAccount(username);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertNull(user);
			// check error
			assertEquals("The user cannot be found.", error);
		}
		
		
		/**
		 * Delete Admin Account with invalid token
		 */
		@Test
		public void testDeleteAdminAccountWithInvalidToken() {
			assertEquals(2, adminAccountService.getAllAdminAccounts().size());

			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.deleteAdminAccount(USERNAME2);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertNull(user);
			// check error
			assertEquals("You do not have permission to delete this account.", error);
		}
		
		/**
		 * Login Admin Account successfully	
		 */
		@Test
		public void testLoginAdminAccountSuccessfully() {
			assertEquals(2, adminAccountService.getAllAdminAccounts().size());

			AdminAccount user = null; 
			try {
				user = adminAccountService.loginAdminAccount(USERNAME2, PASSWORD2);
			} catch (InvalidInputException e) {
				fail();
			}
			 
			assertNotNull(user);			
			assertEquals(USERNAME2, user.getUsername());
			assertEquals(PASSWORD2, user.getPassword());
			assertEquals(NAME2, user.getName());
		}
		
		/**
		 * Login Admin Account with user null
		 */
		@Test
		public void testLoginAdminAccountWithNonExistingUser() {
			assertEquals(2, adminAccountService.getAllAdminAccounts().size());

			String username = "Catherine";
			String password = "password";
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.loginAdminAccount(username, password);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			 
			assertNull(user);
			// check error
			assertEquals("The user cannot be found. Please sign up if you do not have an account yet.", error);
		}
		
		/**
		 * Login Admin Account with invalid credentials
		 */
		@Test
		public void testLoginAdminAccountWithInvalidCredentials() {
			assertEquals(2, adminAccountService.getAllAdminAccounts().size());

			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.loginAdminAccount(USERNAME2, PASSWORD1);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			 
			assertNull(user);
			// check error
			assertEquals("Username or password incorrect. Please try again.", error);
		}
		
		/**
		 * Logout Admin Account successfully
		 */
		@Test
		public void testLogoutAdminAccountSuccessfully() {
			assertEquals(2, adminAccountService.getAllAdminAccounts().size());

			AdminAccount user = null; 
			try {
				user = adminAccountService.logoutAdminAccount(USERNAME1);
			} catch (InvalidInputException e) {
				fail();
			}
			 
			assertNotNull(user);
			assertEquals(USERNAME1, user.getUsername());
			assertEquals(PASSWORD1, user.getPassword());
			assertEquals(NAME1, user.getName());
		}
		
		/**
		 * Logout Admin Account with user null
		 */
		@Test
		public void testLogoutAdminAccountWithNonExistingUser() {
			assertEquals(2, adminAccountService.getAllAdminAccounts().size());

			String username = "Catherine";
			String error = null;
			AdminAccount user = null; 
			try {
				user = adminAccountService.logoutAdminAccount(username);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertNull(user);
			// check error
			assertEquals("The user cannot be found.", error);
		}
		
		/**
		 * Authenticate Admin Account successfully
		 */
		@Test
		public void testAuthenticateAdminAccountSuccessfully() {

			AdminAccount user = null; 
			try {
				user = adminAccountService.authenticateAdminAccount(USERNAME1);
			} catch (InvalidInputException e) {
				fail();
			}
			assertNotNull(user);
			assertNotEquals(0, user.getToken());
		}
		
		/**
		 * Authenticate Admin Account with user null
		 */
		@Test
		public void testAuthenticateAdminAccountWithNonExistingUser() {
			
			String username = "Catherine";
			String error = null;
			AdminAccount user = null;
			try {
				user = adminAccountService.authenticateAdminAccount(username);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertNull(user);
			// check error
			assertEquals("The user cannot be found.", error);
		}
		
		/**
		 * Authenticate Admin Account with invalid token
		 */
		@Test
		public void testAuthenticateAdminAccountWithInvalidToken() {
			
			String error = null;
			AdminAccount user = null;
			try {
				user = adminAccountService.authenticateAdminAccount(USERNAME2);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertNull(user);
			// check error
			assertEquals("An error occured. Please try again.", error);
		
		}
		
		/**
		 * Get Admin Accounts by Name
		 */
		@Test
		public void testGetAdminAccountsByName() {
			
			List<AdminAccount> users = adminAccountService.getAdminAccountsByName(NAME1);
			assertNotNull(users);
			assertEquals(users.get(0).getUsername(), USERNAME1);
		}
		
		/**
		 * Get Admin Accounts by BusinessInformation
		 */
		@Test
		public void testGetAllAdminAccountsWithBusinessInformation() {
			BusinessInformation businessInformation = new BusinessInformation();
			businessInformation.setName(INFO_NAME);
			List<AdminAccount> users = adminAccountService.getAllAdminAccountsWithBusinessInformation(INFO_NAME);
			assertNotNull(users);
			assertEquals(users.get(0).getUsername(), USERNAME1);
			assertEquals(users.get(1).getUsername(), USERNAME2);
		}
		
		/**
		 * Set Business Information for Account
		 */
		@Test 
		public void testSetBusinessinformation() {
			BusinessInformation businessInformation = new BusinessInformation();
			businessInformation.setName(INFO_NAME);
			AdminAccount user = adminAccountService.setBusinessInformation(INFO_NAME, USERNAME1);
			assertNotNull(user);
			assertEquals(user.getUsername(), USERNAME1);
		}

}