package ca.mcgill.ecse321.vehiclerepairshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.CustomerAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.AdminAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.CarRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.TechnicianAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;

@Service
public class CustomerAccountService {

	@Autowired
	private CustomerAccountRepository customerAccountRepository;
	@Autowired
	private AdminAccountRepository adminAccountRepository;
	@Autowired
	private TechnicianAccountRepository technicianAccountRepository;
	@Autowired
	private CarRepository carRepository;

	/**
	 * Create an Customer Account with given parameters
	 * @author Catherine
	 * @param username
	 * @param password
	 * @param name
	 * @return the account created
	 */
	@Transactional
	public CustomerAccount createCustomerAccount(String username, String password, String name)   {

		if (username == null || username.replaceAll("\\s+", "").length() == 0 || username.equals("undefined")) {
			throw new InvalidInputException("Username cannot be empty.");
		}
		else if (username.contains(" ")) {
			throw new InvalidInputException("Username cannot contain spaces.");
		}
		else if (isUsernameAvailable(username) == false) {
			throw new InvalidInputException("This username is not available.");
		}
		else if (password == null || password.replaceAll("\\s+", "").length() == 0 || password.equals("undefined")) {
			throw new InvalidInputException("Password cannot be empty.");
		}
		else if (password.contains(" ")) {
			throw new InvalidInputException("Password cannot contain spaces.");
		}
		else if (name == null || name.replaceAll("\\s+", "").length() == 0 || name.equals("undefined")){ //name.trim().length() == 0
			throw new InvalidInputException("Name cannot be empty.");
		}
		else {
			CustomerAccount user = new CustomerAccount();
			user.setUsername(username);
			user.setPassword(password);
			user.setName(name);
			user.setToken(username.hashCode());
			customerAccountRepository.save(user);
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
		System.out.println(user);
		return user;
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
	 * Update an Customer Account password and name. 
	 * If one parameter shouldn't change, pass old value as new value. 
	 * @author Catherine
	 * @param newPassword
	 * @param newName
	 * @return the account updated
	 */
	@Transactional
	public CustomerAccount updateCustomerAccount(String username, String newPassword, String newName)   {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username cannot be null.");
		}
		else {
			CustomerAccount user = customerAccountRepository.findByUsername(username);
			if (user == null) {
				throw new InvalidInputException("The user cannot be found.");
			}
			else if (user.getToken() == 0) {
				throw new InvalidInputException("You do not have permission to modify this account.");
			}
			else if (newPassword == null || newPassword.replaceAll("\\s+", "").length() == 0 || newPassword.equals("undefined")) {
				throw new InvalidInputException("Password cannot be empty.");
			}
			else if (newPassword.contains(" ")) {
				throw new InvalidInputException("Password cannot contain spaces.");
			}
			else if (newName == null || newName.replaceAll("\\s+", "").length() == 0 || newName.equals("undefined")){
				throw new InvalidInputException("Name cannot be empty.");
			}
			else {
				user.setPassword(newPassword);
				user.setName(newName);
				customerAccountRepository.save(user);
				return user;
			}
		}
	}


	/**
	 * Deletes the customer account
	 * @author Catherine
	 * @param username
	 * @return user
	 */
	@Transactional
	public CustomerAccount deleteCustomerAccount(String username)  {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username cannot be null.");
		}
		else {
			CustomerAccount user = customerAccountRepository.findByUsername(username);
		
			if(user == null) {
				throw new InvalidInputException("The user cannot be found.");
			}
			else if (user.getToken() == 0) {
				throw new InvalidInputException("You do not have permission to delete this account.");
			}
			else {
				if (user.getCar() != null) {
					for (Car car : user.getCar()) {
						carRepository.delete(car);
					}
				}
				customerAccountRepository.delete(user);
				user.setCar(null);
				return user;
			}
		}
	}

	/**
	 * Login the account and create a token for the account
	 * @param username
	 * @param password
	 * @return user
	 */
	@Transactional
	public CustomerAccount loginCustomerAccount(String username, String password)   {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username cannot be null.");
		}
		else {
			CustomerAccount user = customerAccountRepository.findByUsername(username);
			if (user == null) {
				throw new InvalidInputException("The user cannot be found. Please sign up if you do not have an account yet.");
			}
			else if (!user.getPassword().equals(password)) {
				throw new InvalidInputException("Username or password incorrect. Please try again.");
			}
			else {
				user.setToken(username.hashCode());
				customerAccountRepository.save(user);
				return user;
			}
		}
	}



	/**
	 * Logout the account and delete token for the account
	 * @param username
	 * @return user
	 * @throws InvalidInputException
	 */
	@Transactional
	public CustomerAccount logoutCustomerAccount(String username) {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username cannot be null.");
		}
		else {
			CustomerAccount user = customerAccountRepository.findByUsername(username);
			
			if (user == null) {
				throw new InvalidInputException("The user cannot be found.");
			}
			else if (user.getToken() == 0){
				throw new InvalidInputException("You do not have permission to access this account.");
			}
			else {
				user.setToken(0);
				customerAccountRepository.save(user);
				return user;
			}
		}
	}

	/**
	 * Authenticate token
	 * @author Catherine
	 * @param username
	 * @return user
	 * @throws InvalidInputException
	 */
	@Transactional
	public CustomerAccount authenticateCustomerAccount(String username) {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username cannot be null.");
		}
		else {
			CustomerAccount user = customerAccountRepository.findByUsername(username);
			
			if(user == null) {
				throw new InvalidInputException("The user cannot be found.");
			}
			else if (user.getToken() != 0) {
				return user;
			}
			else {
				//General error message to capture if the session expired or the user does not have permission
				throw new InvalidInputException("An error occured. Please try again."); 
			}
		}
	}


	/**
	 * Find Customer Account by car
	 * @author Catherine
	 * @param licensePlate
	 * @return Account linked to car
	 */
	@Transactional
	public CustomerAccount getCustomerAccountWithCar(String licensePlate) {
		for (CustomerAccount customer : customerAccountRepository.findAll()) {
			if (!customer.getCar().isEmpty()) {
				for (Car car : customer.getCar()) {
					if (car.getLicensePlate().equals(licensePlate)){
						return customer;
					}
				}
			}
		}
		throw new InvalidInputException("Customer not found");
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
