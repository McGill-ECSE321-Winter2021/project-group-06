
package ca.mcgill.ecse321.vehiclerepairshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AdminAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.TechnicianAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.TimeSlotRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.CustomerAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;

@Service
public class TechnicianAccountService {

	@Autowired
	private AdminAccountRepository adminAccountRepository;
	@Autowired
	private TechnicianAccountRepository technicianAccountRepository;
	@Autowired
	private CustomerAccountRepository customerAccountRepository;
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;


	/**
	 * Create an Technician Account with given parameters
	 * @author Catherine
	 * @param username
	 * @param password
	 * @param name
	 * @return the account created
	 */
	@Transactional
	public TechnicianAccount createTechnicianAccount(String username, String password, String name)   {

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
			TechnicianAccount user = new TechnicianAccount();
			user.setUsername(username);
			user.setPassword(password);
			user.setName(name);
			user.setToken(username.hashCode());
			technicianAccountRepository.save(user);
			return user;
		}
	}


	/**
	 * Find technician account by username
	 * @author Catherine
	 * @param username
	 * @return the account
	 */
	@Transactional
	public TechnicianAccount getTechnicianAccountByUsername(String username) {
		TechnicianAccount user = technicianAccountRepository.findByUsername(username);
		return user;
	}


	/**
	 * Find technician accounts by name
	 * @author Catherine
	 * @param username
	 * @return a list of accounts
	 */
	@Transactional
	public List<TechnicianAccount> getTechnicianAccountsByName(String name) {
		List<TechnicianAccount> users = technicianAccountRepository.findTechnicianAccountByName(name);
		return users;
	}

	/**
	 * Find all Technician Accounts
	 * @author Catherine
	 * @return List of all accounts
	 */
	@Transactional
	public List<TechnicianAccount> getAllTechnicianAccounts() {
		return toList(technicianAccountRepository.findAll());
	}


	/**
	 * Update an Technician Account password and name. 
	 * If one parameter shouldn't change, pass old value as new value. 
	 * @author Catherine
	 * @param newPassword
	 * @param newName
	 * @return the account updated
	 */
	@Transactional
	public TechnicianAccount updateTechnicianAccount(String username, String newPassword, String newName)   {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username cannot be empty.");
		}
		else {
			TechnicianAccount user = technicianAccountRepository.findByUsername(username);
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
				technicianAccountRepository.save(user);
				return user;
			}
		}
	}


	/**
	 * Deletes the technician account
	 * @author Catherine
	 * @param username
	 * @return user
	 */
	@Transactional
	public TechnicianAccount deleteTechnicianAccount(String username)  {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username cannot be empty.");
		}
		else {
			TechnicianAccount user = technicianAccountRepository.findByUsername(username);
			if(user == null) {
				throw new InvalidInputException("The user cannot be found.");
			}
			else if (user.getToken() == 0) {
				throw new InvalidInputException("You do not have permission to delete this account.");
			}
			else {
				if(user.getAvailability() != null) {
					for (TimeSlot timeslot : user.getAvailability()) {
						timeSlotRepository.delete(timeslot); //cannot exist without technician
					}
				}
				technicianAccountRepository.delete(user);
				user.setAvailability(null);
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
	public TechnicianAccount loginTechnicianAccount(String username, String password)   {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username cannot be empty.");
		}
		else {
			TechnicianAccount user = technicianAccountRepository.findByUsername(username);
			if (user == null) {
				throw new InvalidInputException("The user cannot be found. Please sign up if you do not have an account yet.");
			}
			else if (!user.getPassword().equals(password)) {
				throw new InvalidInputException("Username or password incorrect. Please try again.");
			}
			else {
				user.setToken(username.hashCode());
				technicianAccountRepository.save(user);
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
	public TechnicianAccount logoutTechnicianAccount(String username) {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username cannot be empty.");
		}
		else {
			TechnicianAccount user = technicianAccountRepository.findByUsername(username);
			if (user == null) {
				throw new InvalidInputException("The user cannot be found.");
			}
			else if (user.getToken() == 0){
				throw new InvalidInputException("You do not have permission to access this account.");
			}
			else {
				user.setToken(0);
				technicianAccountRepository.save(user);
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
	public TechnicianAccount authenticateTechnicianAccount(String username) {
		if (username.equals("undefined")) {
			throw new InvalidInputException("Username cannot be empty.");
		}
		else {
			TechnicianAccount user = technicianAccountRepository.findByUsername(username);
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
	 * add a timeslot for an account
	 * @param timeSlotId
	 * @param username
	 * @return user
	 */
	@Transactional 
	public TechnicianAccount addTimeSlot(int timeSlotId, String username) {
		TechnicianAccount user = technicianAccountRepository.findByUsername(username);
		if (user.getAvailability() == null) {
			List<TimeSlot> timeslots = new ArrayList<TimeSlot>();
			timeslots.add(timeSlotRepository.findByTimeSlotId(timeSlotId));
			user.setAvailability(timeslots);
			return user;
		}
		else {
			List<TimeSlot> timeslots = user.getAvailability();	
			timeslots.add(timeSlotRepository.findByTimeSlotId(timeSlotId));
			user.setAvailability(timeslots);
			return user;
		}

	}


	/**
	 * Find all technician accounts linked to an appointment
	 * @author aureliahaas
	 * @param appointmentId
	 * @return
	 */
	@Transactional
	public List<TechnicianAccount> findTechnicianAccountByAppointment(int appointmentId) {
		List<Appointment> appointments = toList(appointmentRepository.findAll());
		for (Appointment appointment : appointments) {
			if (appointment.getAppointmentId() == appointmentId) {
				return appointment.getWorker();
			}
		}
		throw new InvalidInputException("AppointmentId does not exist!");
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
	 *  helper method that converts iterable to list
	 * 
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

