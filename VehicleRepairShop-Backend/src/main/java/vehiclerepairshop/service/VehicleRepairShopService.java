package vehiclerepairshop.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.TechnicianAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.OfferedServiceRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.TimeSlotRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.GarageRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.CustomerAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.CarRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.BusinessInformationRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.AdminAccountRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.AdminAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
//import ca.mcgill.ecse321.vehiclerepairshop.model.Service; // not sure why this fails???

@Service
public class VehicleRepairShopService {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private BusinessInformationRepository businessInformationRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private CustomerAccountRepository customerAccountRepository;
	@Autowired
	private TechnicianAccountRepository technicianAccountRepository;
	@Autowired
	private AdminAccountRepository adminAccountRepository;
	@Autowired
	private GarageRepository garageRepository;
	@Autowired
	private TimeSlotRepository timeslotRepository;
	@Autowired
	private OfferedServiceRepository offeredServiceRepository;



	// --------------------------- Catherine starts here -------------------------

	//--------- Admin Account Methods ---------

	/**
	 * Create an Admin Account with given parameters
	 * @param username
	 * @param password
	 * @param name
	 * @return the account created
	 */
	@Transactional
	public AdminAccount createAdminAccount(String username, String password, String name) {
		AdminAccount user = new AdminAccount();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		adminAccountRepository.save(user);
		return user;
	}


	/**
	 * Find admin account by username
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
	 * @return List of all accounts
	 */
	@Transactional
	public List<AdminAccount> getAllAdminAccounts() {
		return toList(adminAccountRepository.findAll());
	}

	/**
	 * Find all Admin Accounts by business information
	 * @return List of all accounts
	 */
	@Transactional
	public List<AdminAccount> getAllAdminAccountsWithBusinessInformation(BusinessInformation businessInfo) {
		List<AdminAccount> adminAccountsWithBusinessInfo = adminAccountRepository.findByBusinessInformation(businessInfo);
		return adminAccountsWithBusinessInfo;
	}



	//--------- Customer Account Methods ---------

	/**
	 * Create a Customer Account with given parameters
	 * @param username
	 * @param password
	 * @param name
	 * @return the account created
	 */
	@Transactional
	public CustomerAccount createCustomerAccount(String username, String password, String name) {
		CustomerAccount user = new CustomerAccount();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		customerAccountRepository.save(user);
		return user;
	}


	/**
	 * Find customer account by username
	 * @param username
	 * @return the account
	 */
	@Transactional
	public CustomerAccount getCustomerAccountByUsername(String username) {
		CustomerAccount user = customerAccountRepository.findByUsername(username);
		return user;
	}

	/**
	 * Find customer accounts by name
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
	 * @return List of all accounts
	 */
	@Transactional
	public List<CustomerAccount> getAllCustomerAccounts() {
		return toList(customerAccountRepository.findAll());
	}

	/**
	 * Find Customer Account by car
	 * @return Account linked to car
	 */
	@Transactional
	public CustomerAccount getCustomerAccountWithCar(Car car) {
		CustomerAccount user = customerAccountRepository.findByCar(car);
		return user;
	}


	//--------- Technician Account Methods ---------

	/**
	 * Create a Technician Account with given parameters
	 * @param username
	 * @param password
	 * @param name
	 * @return the account created
	 */
	@Transactional
	public TechnicianAccount createTechnicianAccount(String username, String password, String name) {
		TechnicianAccount user = new TechnicianAccount();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		technicianAccountRepository.save(user);
		return user;
	}


	/**
	 * Find technician account by username
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
	 * @return List of all accounts
	 */
	@Transactional
	public List<TechnicianAccount> getAllTechnicianAccounts() {
		return toList(technicianAccountRepository.findAll());
	}

	/**
	 * Find all technician accounts linked to an appointment
	 * @param username
	 * @return a list of accounts
	 */
	@Transactional
	public List<TechnicianAccount> getTechnicianAccountsForAppointment(Appointment appointment) {
		List<TechnicianAccount> users = technicianAccountRepository.findTechnicianAccountByAppointment(appointment);
		return users;
	}

	// --------------------------- Catherine ends here ----------------------------



	// helper method that converts iterable to list
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
