package vehiclerepairshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.TechnicianAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.CustomerAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.AdminAccountRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.AdminAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;

@Service
public class AdminAccountService {
	
	@Autowired
	private AdminAccountRepository adminAccountRepository;
	@Autowired
	private CustomerAccountRepository customerAccountRepository;
	@Autowired
	private TechnicianAccountRepository technicianAccountRepository;
	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * Create an Admin Account with given parameters
	 * @author Catherine
	 * @param username
	 * @param password
	 * @param name
	 * @return the account created
	 */
	@Transactional
	public AdminAccount createAdminAccount(String username, String password, String name) throws InvalidInputException {
		
		if (username == null || username.replaceAll("\\s+", "").length() == 0) {
			throw new InvalidInputException("Username cannot be empty.");
		}
		else if (username.contains(" ")) {
			throw new InvalidInputException("Username cannot contain spaces.");
		}
		else if (isUsernameAvailable(username) == false) {
			throw new InvalidInputException("This username is not available.");
		}
		else if (password == null || password.replaceAll("\\s+", "").length() == 0) {
			throw new InvalidInputException("Password cannot be empty.");
		}
		else if (password.contains(" ")) {
			throw new InvalidInputException("Password cannot contain spaces.");
		}
		else if (name == null || name.replaceAll("\\s+", "").length() == 0){ //name.trim().length() == 0
			throw new InvalidInputException("Name cannot be empty.");
		}
		else {
			AdminAccount user = new AdminAccount();
			user.setUsername(username);
			user.setPassword(password);
			if (name.contains(" ")) {
				name.replaceAll("\\s+", "_"); 
			}
			user.setName(name);
			
			adminAccountRepository.save(user);
			createToken(username);
			return user;
		}
	}

	
	/**
	 * Find admin account by username
	 * @author Catherine
	 * @param username
	 * @return the account
	 */
	@Transactional
	public AdminAccount getAdminAccountByUsername(String username) {
		AdminAccount user = adminAccountRepository.findByUsername(username);
		return user;
	}


	/**
	 * Find admin accounts by name
	 * @author Catherine
	 * @param username
	 * @return a list of accounts
	 */
	@Transactional
	public List<AdminAccount> getAdminAccountsByName(String name) {
		List<AdminAccount> users = adminAccountRepository.findAdminAccountByName(name);
		return users;
	}

	/**
	 * Find all Admin Accounts
	 * @author Catherine
	 * @return List of all accounts
	 */
	@Transactional
	public List<AdminAccount> getAllAdminAccounts() {
		return toList(adminAccountRepository.findAll());
	}

	/**
	 * Find all Admin Accounts by business information
	 * @author Catherine
	 * @return List of all accounts
	 */
	@Transactional
	public List<AdminAccount> getAllAdminAccountsWithBusinessInformation(BusinessInformation businessInfo) {
		List<AdminAccount> adminAccountsWithBusinessInfo = adminAccountRepository.findByBusinessInformation(businessInfo);
		return adminAccountsWithBusinessInfo;
	}
	
	/**
	 * Update an Admin Account username, password, and name. 
	 * If one parameter shouldn't change, pass old value as new value. 
	 * @author Catherine
	 * @param newUsername
	 * @param newPassword
	 * @param newName
	 * @return the account updated
	 */
	@Transactional
	public AdminAccount updateAdminAccount(String currentUsername, String newUsername, String newPassword, String newName) throws InvalidInputException {
		if (!authenticationService.authenticateToken(currentUsername)) {
			throw new InvalidInputException("You do not have permission to modify this account.");
		}
		else if (newUsername == null || newUsername.replaceAll("\\s+", "").length() == 0) {
			throw new InvalidInputException("Username cannot be empty.");
		}
		else if (newUsername.contains(" ")) {
			throw new InvalidInputException("Username cannot contain spaces.");
		}
		else if (!currentUsername.equals(newUsername) && (adminAccountRepository.findByUsername(newUsername) != null 
				|| customerAccountRepository.findByUsername(newUsername)!= null || technicianAccountRepository.findByUsername(newUsername) != null)) {
			throw new InvalidInputException("This username is not available.");
		}
		else if (newPassword == null || newPassword.replaceAll("\\s+", "").length() == 0) {
			throw new InvalidInputException("Password cannot be empty.");
		}
		else if (newPassword.contains(" ")) {
			throw new InvalidInputException("Password cannot contain spaces.");
		}
		else if (newName == null || newName.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("Name cannot be empty.");
		}
		else {
			AdminAccount user = adminAccountRepository.findByUsername(currentUsername);
			user.setUsername(newUsername);
			user.setPassword(newPassword);
			if (newName.contains(" ")) {
				newName.replaceAll("\\s+", "_"); 
			}
			user.setName(newName);
			adminAccountRepository.save(user);
			return user;
		}
			
	}
	
	
	/**
	 * Deletes the admin account
	 * @author Catherine
	 * @param username
	 * @return boolean for if it was successful
	 */
	@Transactional
	public boolean deleteAdminAccount(String username) throws InvalidInputException{
		boolean successful = false;
		AdminAccount user = adminAccountRepository.findByUsername(username);
		if(user == null) {
			throw new InvalidInputException("The user cannot be found.");
		}
		else if (!authenticationService.authenticateToken(username)) {
			throw new InvalidInputException("You do not have permission to delete this account.");
		}
		else {
			adminAccountRepository.delete(user);
			successful = true;
			return successful;
		}
	}

	/**
	 * Login the account and create a token for the account
	 * @param username
	 * @param password
	 * @return boolean for success
	 * @throws InvalidInputException
	 */
	@Transactional
	public boolean loginAdminAccount(String username, String password) throws InvalidInputException{
		boolean successful = false;
		AdminAccount user = adminAccountRepository.findByUsername(username);
		if(user == null) {
			throw new InvalidInputException("The user cannot be found. Please sign up if you do not have an account yet.");
		}
		else {
			successful = authenticationService.login(username, password);
			if (successful) {
				return successful;
			}
			else {
				throw new InvalidInputException("Username or password incorrect. Please try again.");
			}
		}
	}
	
	/**
	 * Logout the account and delete token for the account
	 * @param username
	 * @param password
	 * @return boolean for success
	 * @throws InvalidInputException
	 */
	@Transactional
	public boolean logoutAdminAccount(String username) throws InvalidInputException{
		boolean successful = false;
		AdminAccount user = adminAccountRepository.findByUsername(username);
		if(user == null) {
			throw new InvalidInputException("The user cannot be found.");
		}
		else {
			successful = authenticationService.logout(username);
			return successful; // should always be true, because it's only false if user is not found, which is captured above
		}
	}
	
	/**
	 * Authenticate token
	 * @author Catherine
	 * @param username
	 * @return
	 * @throws InvalidInputException
	 */
	@Transactional
	public boolean authenticateAdminAccount(String username) throws InvalidInputException{
		boolean authentic = false;
		AdminAccount user = adminAccountRepository.findByUsername(username);
		if(user == null) {
			throw new InvalidInputException("The user cannot be found.");
		}
		else {
			authentic = authenticationService.authenticateToken(username);
			if (authentic) {
				return authentic;
			}
			else {
				//General error message to capture if the session expired or the user does not have permission
				throw new InvalidInputException("An error occured. Please try again."); 
			}
		}
	}
	
	
	//----------------------------- Helper Methods --------------------------------

	/**
	 * Helper method to search through all accounts and see if the username is already in use
	 * @author Catherine
	 * @param username
	 * @return
	 */
	private boolean isUsernameAvailable(String username) {
		boolean available = false;
		if (adminAccountRepository.findByUsername(username) != null) {
			return available;
		}
		else if (customerAccountRepository.findByUsername(username) != null) {
			return available;
		}
		else if (technicianAccountRepository.findByUsername(username) != null) {
			return available;
		}
		else {
			available = true;
			return available;
		}
	}
	
	public int createToken(String username) {

		int token = 0;
		
		if (adminAccountRepository.findByUsername(username) != null) {
			AdminAccount user = adminAccountRepository.findByUsername(username);
			user.setToken(user.getUsername().hashCode());
			adminAccountRepository.save(user);
			return token;
		}
		else if (customerAccountRepository.findByUsername(username) != null) {
			CustomerAccount user = customerAccountRepository.findByUsername(username);
			user.setToken(user.getUsername().hashCode());
			customerAccountRepository.save(user);
			return token;
		}
		else if (technicianAccountRepository.findByUsername(username) != null) {
			TechnicianAccount user = technicianAccountRepository.findByUsername(username);
			user.setToken(user.getUsername().hashCode());
			technicianAccountRepository.save(user);
			return token;
		}
		else return token;
	}
	
	
	/**
	 * helper method that converts iterable to list
	 * @param <T>
	 * @param iterable
	 * @return
	 */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
