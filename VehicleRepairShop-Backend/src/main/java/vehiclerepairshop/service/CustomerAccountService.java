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
	@Autowired
	private AuthenticationService authenticationService;


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
			authenticationService.createToken(user.getUsername());
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
			throw new InvalidInputException("The user cannot be found.");
		}
		else if (!authenticationService.authenticateToken(username)) {
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
			successful = authenticationService.login(username, password);
			return successful;
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
			successful = authenticationService.logout(username);
			return successful;
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
			authentic = authenticationService.authenticateToken(username);
			return authentic;
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
