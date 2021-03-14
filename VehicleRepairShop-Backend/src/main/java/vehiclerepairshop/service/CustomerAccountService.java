package vehiclerepairshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AdminAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.CustomerAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.TechnicianAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.AdminAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;

@Service
public class CustomerAccountService {
	
	@Autowired
	private AdminAccountRepository adminAccountRepository;
	@Autowired
	private CustomerAccountRepository customerAccountRepository;
	@Autowired
	private TechnicianAccountRepository technicianAccountRepository;


	/**
	 * Create a Customer Account with given parameters
	 * @author Catherine
	 * @param username
	 * @param password
	 * @param name
	 * @return the account created
	 */
	@Transactional
	public CustomerAccount createCustomerAccount(String username, String password, String name) throws InvalidInputException {

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
		else if (name == null || name.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("Name cannot be empty.");
		}
		else {
			CustomerAccount user = new CustomerAccount();
			user.setUsername(username);
			user.setPassword(password);
			if (name.contains(" ")) {
				name.replaceAll("\\s+", "_"); 
			}
			user.setName(name);
			customerAccountRepository.save(user);
			createToken(user.getUsername());
			return user;
		}
	}


	/**
	 * Find customer account by username
	 * @author Catherine
	 * @param username
	 * @return the account
	 */
	@Transactional
	public CustomerAccount getCustomerAccountByUsername(String username) {
		CustomerAccount user = customerAccountRepository.findByUsername(username);
		return user;
	}

	/**
	 * Update a Customer Account username, password, and name. 
	 * If one parameter shouldn't change, pass old value as new value. 
	 * @author Catherine
	 * @param newUsername
	 * @param newPassword
	 * @param newName
	 * @return the account updated
	 */
	@Transactional
	public CustomerAccount updateCustomerAccount(String currentUsername, String newUsername, String newPassword, String newName) throws InvalidInputException {
		if (!authenticateToken(currentUsername)) {
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
			CustomerAccount user = customerAccountRepository.findByUsername(currentUsername);
			user.setUsername(newUsername);
			user.setPassword(newPassword);
			if (newName.contains(" ")) {
				newName.replaceAll("\\s+", "_"); 
			}
			user.setName(newName);
			customerAccountRepository.save(user);
			return user;
		}
			
	}
	
	
	/**
	 * Delete the account
	 * @author Catherine
	 * @param username
	 * @return boolean for successful
	 * @throws InvalidInputException 
	 */
	@Transactional
	public boolean deleteCustomerAccount(String username) throws InvalidInputException {
		boolean successful = false;
		CustomerAccount user = customerAccountRepository.findByUsername(username);
		if(user == null) {
			throw new InvalidInputException("The user cannot be found. Please sign up if you do not have an account yet.");
		}
		else if (!authenticateToken(username)) {
			throw new InvalidInputException("You do not have permission to delete this account.");
		}
		else {
			customerAccountRepository.delete(user);
			successful = true;
			return successful;
		}
	}
	
	/**
	 * Find customer accounts by name
	 * @author Catherine
	 * @param username
	 * @return a list of accounts
	 */
	@Transactional
	public List<CustomerAccount> getCustomerAccountsByName(String name) {
		List<CustomerAccount> users = customerAccountRepository.findCustomerAccountByName(name);
		return users;
	}

	/**
	 * Find all Customer Accounts
	 * @author Catherine
	 * @return List of all accounts
	 */
	@Transactional
	public List<CustomerAccount> getAllCustomerAccounts() {
		return toList(customerAccountRepository.findAll());
	}

	/**
	 * Find Customer Account by car
	 * @author Catherine
	 * @return Account linked to car
	 */
	@Transactional
	public CustomerAccount getCustomerAccountWithCar(Car car) {
		CustomerAccount user = customerAccountRepository.findByCar(car);
		return user;
	}


	/**
	 * Login the account and create a token for the account
	 * @param username
	 * @param password
	 * @return boolean for success
	 * @throws InvalidInputException
	 */
	@Transactional
	public boolean loginCustomerAccount(String username, String password) throws InvalidInputException{
		boolean successful = false;
		CustomerAccount user = customerAccountRepository.findByUsername(username);
		if(user == null) {
			throw new InvalidInputException("The user cannot be found.");
		}
		else {
			successful = login(username, password);
			if (successful) {
				return successful;
			}
			else {
				throw new InvalidInputException("An error occured. Please try again.");
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
	public boolean logoutCustomerAccount(String username) throws InvalidInputException{
		boolean successful = false;
		CustomerAccount user = customerAccountRepository.findByUsername(username);
		if(user == null) {
			throw new InvalidInputException("The user cannot be found.");
		}
		else {
			successful = logout(username);
			if (successful) {
				return successful;
			}
			else {
				throw new InvalidInputException("An error occured. Please try again.");
			}
		}
	}
	
	/**
	 * Authenticate token
	 * @author Catherine
	 * @param username
	 * @return authenticity
	 * @throws InvalidInputException
	 */
	@Transactional
	public boolean authenticateCustomerAccount(String username) throws InvalidInputException{
		boolean authentic = false;
		CustomerAccount user = customerAccountRepository.findByUsername(username);
		if(user == null) {
			throw new InvalidInputException("The user cannot be found.");
		}
		else {
			authentic = authenticateToken(username);
			if (authentic) {
				return authentic;
			}
			else {
				//General error message to capture if the session expired or the user does not have permission
				throw new InvalidInputException("An error occured. Please try again."); 
			}		
		}
	}
	
	//------------------------------- Helper Methods --------------------------
	
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

	/**
	 * Generates a token, sets it for the account, and saves it to the account database
	 * Account must exist in database first
	 * Returns 0 if not successful
	 * @param username
	 * @return token
	 */
	private int createToken(String username) {

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
	 * Authenticates token for the account
	 * @param token
	 * @param username
	 * @return boolean for authenticity
	 */
	private boolean authenticateToken(String username){
		boolean isAuthentic = false;
		if (adminAccountRepository.findByUsername(username) != null) {
			AdminAccount user = adminAccountRepository.findByUsername(username);
			if (user.getToken() != 0) {
				isAuthentic = true;
			}
		}
		else if (customerAccountRepository.findByUsername(username) != null) {
			CustomerAccount user = customerAccountRepository.findByUsername(username);
			if (user.getToken() != 0) {
				isAuthentic = true;
			}
		}
		else if (technicianAccountRepository.findByUsername(username) != null) {
			TechnicianAccount user = technicianAccountRepository.findByUsername(username);
			if (user.getToken() != 0) {
				isAuthentic = true;
			}
		}
		return isAuthentic;
	}
	
	/**
	 * Checks if username and password match
	 * @param username
	 * @param password
	 * @return boolean for authenticity
	 */
	private boolean authenticateCredentials(String username, String password){
		boolean isAuthentic = false;
		if (adminAccountRepository.findByUsername(username) != null) {
			AdminAccount user = adminAccountRepository.findByUsername(username);
			if (user.getPassword() == password) {
				isAuthentic = true;
			}
		}
		else if (customerAccountRepository.findByUsername(username) != null) {
			CustomerAccount user = customerAccountRepository.findByUsername(username);
			if (user.getPassword() == password) {
				isAuthentic = true;
			}
		}
		else if (technicianAccountRepository.findByUsername(username) != null) {
			TechnicianAccount user = technicianAccountRepository.findByUsername(username);
			if (user.getPassword() == password) {
				isAuthentic = true;
			}
		}
		return isAuthentic;
	}

	/**
	 * If credentials match, generates a token for the account
	 * @param username
	 * @param password
	 * @return boolean for success
	 */
	private boolean login(String username, String password){
		boolean successful = authenticateCredentials(username, password);
		if (successful == true) {
			createToken(username);
		}
		return successful;
	}


	/**
	 * Deletes the token for the account
	 * @param username
	 * @return boolean for success
	 */
	private boolean logout(String username){
		boolean successful = false;
		if (adminAccountRepository.findByUsername(username) != null) {
			AdminAccount user = adminAccountRepository.findByUsername(username);
			user.setToken(0);
			adminAccountRepository.save(user);
			successful = true;
		}
		else if (customerAccountRepository.findByUsername(username) != null) {
			CustomerAccount user = customerAccountRepository.findByUsername(username);
			user.setToken(0);
			customerAccountRepository.save(user);
			successful = true;
		}
		else if (technicianAccountRepository.findByUsername(username) != null) {
			TechnicianAccount user = technicianAccountRepository.findByUsername(username);
			user.setToken(0);
			technicianAccountRepository.save(user);
			successful = true;
		}
		return successful;
	}

	/**
	 *  helper method that converts iterable to list
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
