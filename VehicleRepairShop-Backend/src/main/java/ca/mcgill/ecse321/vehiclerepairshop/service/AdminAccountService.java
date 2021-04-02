package ca.mcgill.ecse321.vehiclerepairshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.TechnicianAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.CustomerAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.AdminAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.BusinessInformationRepository;
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
	private BusinessInformationRepository businessInformationRepository;

	/**
	 * Create an Admin Account with given parameters
	 * @author Catherine
	 * @param username
	 * @param password
	 * @param name
	 * @return the account created
	 */
	@Transactional
	public AdminAccount createAdminAccount(String username, String password, String name)   {

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
			AdminAccount user = new AdminAccount();
			user.setUsername(username);
			user.setPassword(password);
			user.setName(name);
			user.setToken(username.hashCode());
			adminAccountRepository.save(user);
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
	public List<AdminAccount> getAllAdminAccountsWithBusinessInformation(String businessName) {
		BusinessInformation businessInfo = businessInformationRepository.findBusinessInformationByName(businessName);
		List<AdminAccount> adminAccountsWithBusinessInfo = adminAccountRepository.findByBusinessInformation(businessInfo);
		return adminAccountsWithBusinessInfo;
	}

	/**
	 * Update an Admin Account password and name. 
	 * If one parameter shouldn't change, pass old value as new value. 
	 * @author Catherine
	 * @param newPassword
	 * @param newName
	 * @return the account updated
	 */
	@Transactional
	public AdminAccount updateAdminAccount(String username, String newPassword, String newName)   {
		if (username.equals("undefined") || newPassword.equals("undefined") || newName.equals("undefined")) {
			throw new InvalidInputException("One or more fields empty. Please try again.");
		}
		else {
			AdminAccount user = adminAccountRepository.findByUsername(username);
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
				adminAccountRepository.save(user);
				return user;
			}
		}

	}


	/**
	 * Deletes the admin account
	 * @author Catherine
	 * @param username
	 * @return user
	 */
	@Transactional
	public AdminAccount deleteAdminAccount(String username)  {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username empty. Please try again.");
		}
		else {
			AdminAccount user = adminAccountRepository.findByUsername(username);
			if(user == null) {
				throw new InvalidInputException("The user cannot be found.");
			}
			else if (user.getToken() == 0) {
				throw new InvalidInputException("You do not have permission to delete this account.");
			}
			else {
				adminAccountRepository.delete(user);
				return user;
			}
		}
	}

	/**
	 * Deletes any account from admin account
	 * @author aureliahaas
	 * @param username
	 * @return user
	 */
	@Transactional
	public AdminAccount deleteAdminAccountFromAdmin(String username)  {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username empty. Please try again.");
		}
		else {
			AdminAccount user = adminAccountRepository.findByUsername(username);
			if(user == null) {
				throw new InvalidInputException("The user cannot be found.");
			}
			else {
				adminAccountRepository.delete(user);
				return user;
			}
		}
	}

	/**
	 * Deletes any account from admin account
	 * @author aureliahaas
	 * @param username
	 * @return user
	 */
	@Transactional
	public CustomerAccount deleteCustomerAccountFromAdmin(String username)  {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username empty. Please try again.");
		}
		else {
			CustomerAccount user = customerAccountRepository.findByUsername(username);
			if(user == null) {
				throw new InvalidInputException("The user cannot be found.");
			}
			else {
				customerAccountRepository.delete(user);
				return user;
			}
		}
	}

	/**
	 * Deletes any account from admin account
	 * @author aureliahaas
	 * @param username
	 * @return user
	 */
	@Transactional
	public TechnicianAccount deleteTechnicianAccountFromAdmin(String username)  {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username empty. Please try again.");
		}
		else {
			TechnicianAccount user = technicianAccountRepository.findByUsername(username);
			if(user == null) {
				throw new InvalidInputException("The user cannot be found.");
			}
			else {
				technicianAccountRepository.delete(user);
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
	public AdminAccount loginAdminAccount(String username, String password)   {
		if (username.equals("undefined") || password.equals("undefined")) {
			throw new InvalidInputException("Username or password empty. Please try again.");
		}
		else {
			AdminAccount user = adminAccountRepository.findByUsername(username);
			if (user == null) {
				throw new InvalidInputException("The user cannot be found. Please sign up if you do not have an account yet.");
			}
			else if (!user.getPassword().equals(password)) {
				throw new InvalidInputException("Username or password incorrect. Please try again.");
			}
			else {
				user.setToken(username.hashCode());
				adminAccountRepository.save(user);
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
	public AdminAccount logoutAdminAccount(String username) {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username empty. Please try again.");
		}
		else {
			AdminAccount user = adminAccountRepository.findByUsername(username);
			if (user == null) {
				throw new InvalidInputException("The user cannot be found.");
			}
			else if (user.getToken() == 0){
				throw new InvalidInputException("You do not have permission to access this account.");
			}
			else {
				System.out.println("log out " + username);
				user.setToken(0);
				adminAccountRepository.save(user);
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
	public AdminAccount authenticateAdminAccount(String username) {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username empty. Please try again.");
		}
		else {
			AdminAccount user = adminAccountRepository.findByUsername(username);
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
	 * Set the business information for an account
	 * @param businessInformation
	 * @param username
	 * @return user
	 */
	@Transactional 
	public AdminAccount setBusinessInformation(String businessName, String username) {
		BusinessInformation businessInformation = businessInformationRepository.findBusinessInformationByName(businessName);
		AdminAccount user = adminAccountRepository.findByUsername(username);
		user.setBusinessInformation(businessInformation);
		return user;
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
