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
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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
	private static final String NAME1 = "Catherine The 1st";
	private static final String PASSWORD1 = "PassWord123!";
	private static final int TOKEN1 = 1000000;


	@Mock
	private AdminAccountRepository adminAccountRepository;


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
//		lenient().when(eventDao.save(any(Event.class))).thenAnswer(returnParameterAsAnswer);
//		lenient().when(registrationDao.save(any(Registration.class))).thenAnswer(returnParameterAsAnswer);
	

		/**
		 * Create Admin Account successfully
		 */
		@Test
		public void testCreateAdminAccount() {
			assertEquals(0, adminAccountService.getAllAdminAccounts().size());

			String name = "Felicity Francine";
			String username = "Username2";
			String password = "GhostPassword101";
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
			String name = "Mira";
			String username = "";
			String password = "GhostPassword101";
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
		
		/**
		 * Create Admin Account with taken username	
		 */
		
		/**
		 * Create Admin Account with empty password	
		 */
		
		/**
		 * Create Admin Account with spaces in password	
		 */
		
		/**
		 * Create Admin Account with empty name	
		 */
		
		/**
		 * Update Admin Account successfully	
		 */
		
		/**
		 * Update Admin Account with invalid token	
		 */
		
		/**
		 * Update Admin Account with empty username	
		 */
		
		/**
		 * Update Admin Account with spaces in username	
		 */
		
		/**
		 * Update Admin Account with taken username	
		
		/**
		 * Update Admin Account with empty password
		 */
		
		/**
		 * Update Admin Account with spaces in password	
		 */
		
		/**
		 * Update Admin Account with empty name	
		 */
		
		/**
		 * Delete Admin Account successfully
		 */
		
		/**
		 * Delete Admin Account with user null
		 */
		
		/**
		 * Delete Admin Account with invalid token
		 */
		
		/**
		 * Login Admin Account successfully	
		 */
		
		/**
		 * Login Admin Account with user null
		 */
		
		/**
		 * Login Admin Account with invalid credentials
		 */
		
		/**
		 * Logout Admin Account successfully
		 */
		
		/**
		 * Logout Admin Account with user null
		 */
		
		/**
		 * Authenticate Admin Account successfully
		 */
		
		/**
		 * Authenticate Admin Account with user null
		 */
		
		/**
		 * Authenticate Admin Account with invalid token
		 */


		

}