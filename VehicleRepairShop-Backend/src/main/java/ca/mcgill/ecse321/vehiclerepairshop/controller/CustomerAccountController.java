package ca.mcgill.ecse321.vehiclerepairshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.dto.*;
import ca.mcgill.ecse321.vehiclerepairshop.service.*;
@CrossOrigin(origins = "*")
@RestController
public class CustomerAccountController {

	@Autowired
	private CustomerAccountService customerAccountService;

	/**
	 * Return a list of all Customer Account Dtos 
	 * @author Catherine
	 * @return list of Customer Dtos
	 */
	@GetMapping(value = { "/getAllCustomerAccounts", "/getAllCustomerAccounts/" })
	public List<CustomerAccountDto> getAllCustomerAccounts() {
		return customerAccountService.getAllCustomerAccounts().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}

	/**
	 * Return the customer account with specified username
	 * @author Catherine
	 * @param username
	 * @return Customer Dto
	 */
	@GetMapping(value = { "/getCustomerAccountByUsername/{username}", "/getCustomerAccountByUsername/{username}/" })
	public CustomerAccountDto getCustomerAccountByUsername(@PathVariable("username") String username) {
		return convertToDto(customerAccountService.getCustomerAccountByUsername(username));
	}

	/**
	 * Return a list of all Customer Accounts with specified name
	 * @author Catherine
	 * @param name
	 * @return list of Customer Dtos
	 */
	@GetMapping(value = { "/getCustomerAccountsByName/{name}", "/getCustomerAccountsByName/{name}" })
	public List<CustomerAccountDto> getCustomerAccountsByName(@PathVariable("name") String name) {
		return customerAccountService.getCustomerAccountsByName(name).stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}

	/**
	 * Create an Customer Account Dto with provided parameters
	 * @author Catherine
	 * @param username
	 * @param password
	 * @param name
	 * @return Customer Account Dto
	 * @ 
	 */
	@PostMapping(value = { "/createCustomerAccount/{username}/{password}/{name}", "/createCustomerAccount/{username}/{password}/{name}/" })
	public CustomerAccountDto createCustomerAccount(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("name") String name)  {
		CustomerAccount user = customerAccountService.createCustomerAccount(username, password, name);
		return convertToDto(user);
	}


	/**
	 * Update a Customer Account Dto password and name
	 * If not changing something, pass old value
	 * @author Catherine
	 * @param username
	 * @param newPassword
	 * @param newName
	 * @return Customer Account Dto
	 * @ 
	 */
	@PutMapping(value = {"/updateCustomerAccount/{username}/{newPassword}/{newName}", "/updateCustomerAccount/{username}/{newPassword}/{newName}/" })
	public CustomerAccountDto updateCustomerAccount(@PathVariable("username") String username, @PathVariable("newPassword") String newPassword, @PathVariable("newName") String newName)   {
		CustomerAccount user = customerAccountService.updateCustomerAccount(username, newPassword, newName);
		return convertToDto(user);
	}

	/**
	 * Delete customer account
	 * @author Catherine
	 * @param username
	 * @return boolean if successful
	 * @ 
	 */
	@DeleteMapping(value = { "/deleteCustomerAccount/{username}", "/deleteCustomerAccount/{username}/" })
	public CustomerAccountDto deleteCustomerAccount(@PathVariable("username") String username)   {
		CustomerAccount user = customerAccountService.deleteCustomerAccount(username);
		return convertToDto(user);
	}

	/**
	 * Login and generate token
	 * @author Catherine
	 * @param username
	 * @param password
	 * @return boolean if successful
	 * @ 
	 */
	@PutMapping(value = {"/loginCustomerAccount/{username}/{password}", "/loginCustomerAccount/{username}/{password}/" })
	public CustomerAccountDto loginCustomerAccount(@PathVariable("username") String username, @PathVariable("password") String password)   {
		CustomerAccount user = customerAccountService.loginCustomerAccount(username, password);
		return convertToDto(user);
	}

	/**
	 * Logout and delete token
	 * @author Catherine
	 * @param username
	 * @return boolean if successful
	 * @ 
	 */
	@PutMapping(value = {"/logoutCustomerAccount/{username}", "/logoutCustomerAccount/{username}/" })
	public CustomerAccountDto logoutCustomerAccount(@PathVariable("username") String username)   {
		CustomerAccount user = customerAccountService.logoutCustomerAccount(username);
		return convertToDto(user);
	}

	/**
	 * Authenticate token
	 * @author Catherine
	 * @param username
	 * @return boolean authenticity
	 */
	@PostMapping(value = {"/authenticateCustomerAccount/{username}", "/authenticateCustomerAccount/{username}/" })
	public CustomerAccountDto authenticateCustomerAccount(@PathVariable("username") String username)  {
		CustomerAccount user = customerAccountService.authenticateCustomerAccount(username);
		return convertToDto(user);
	}

	/**
	 * Get the customer account associated to specified car
	 * @author Catherine
	 * @param license plate for car
	 * @return
	 */
	@GetMapping(value = { "/getCustomerAccountByCar/{licensePlate}", "/getCustomerAccountByCar/{licensePlate}/" })
	public CustomerAccountDto getCustomerAccountByCar(@PathVariable("licensePlate") String licensePlate) {
		return convertToDto(customerAccountService.getCustomerAccountWithCar(licensePlate));
	}
	
	/**
	 * add a car to customer account
	 * @author Catherine
	 * @param username
	 * @param licensePlate
	 * @return
	 */
	@PutMapping(value = {"/addCar/{username}/{licensePlate}", "/addCar/{username}/{licensePlate}/"})
	public CustomerAccountDto addCar(@PathVariable("username") String username, @PathVariable("licensePlate") String licensePlate) {
		CustomerAccount user = customerAccountService.addCar(licensePlate, username);
		return convertToDto(user);
	}

	//-------------------------- Helper Methods -----------------------------

	/**
	 * Helper Method to convert an customer account to a Dto
	 * @author Catherine
	 * @param user
	 * @return CustomerAccountDto
	 */
	private CustomerAccountDto convertToDto(CustomerAccount user){
		if (user == null) {
			throw new InvalidInputException("This user does not exist");
		}
		CustomerAccountDto customerAccountDto = new CustomerAccountDto(user.getUsername(), user.getPassword(), user.getName());

		if (user.getCar() != null) {
			customerAccountDto.setCars(user.getCar().stream().map(c -> convertToDto(c)).collect(Collectors.toList()));
		}
		customerAccountDto.setToken(user.getToken());
		if (user.getCar() != null) {
			customerAccountDto.setCars(user.getCar().stream().map(c -> convertToDto(c)).collect(Collectors.toList()));
		}
		return customerAccountDto;
	}

	/**
	 * Helper Method to convert a car to a Dto
	 * Will return null if you pass null
	 * @author Catherine
	 * @param car
	 * @return CarDto
	 */
	private CarDto convertToDto(Car car)  {
		if (car == null) {
			return null;
		}
		else {
			CarDto carDto = new CarDto(car.getLicensePlate(), car.getModel(), car.getYear(), car.getMotorType(), car.getOwner(), 
					car.getAppointment().stream().map(a -> convertToDto(a)).collect(Collectors.toList()));
			return carDto;
		}
	}


	/**
	 * Helper Method to convert an appointment to a Dto
	 * Will return null if you pass null
	 * @author Catherine
	 * @param car
	 * @return AppointmentDto
	 */
	private AppointmentDto convertToDto(Appointment apt)  {
		if (apt == null) {
			return null;
		}
		else {
			AppointmentDto aptDto = new AppointmentDto(convertToDto(apt.getTimeSlot()), convertToDto(apt.getCar()), 
					apt.getComment(), convertToDto(apt.getGarage()), 
					apt.getWorker().stream().map(a -> convertToDto(a)).collect(Collectors.toList()), convertToDto(apt.getOfferedService()),apt.getAppointmentId());
			return aptDto;
		}
	}

	/**
	 * Helper Method to convert an offeredService to a Dto
	 * Will return null if you pass null
	 * @author Catherine
	 * @param offeredService
	 * @return OfferedServiceDto
	 */
	private OfferedServiceDto convertToDto(OfferedService offeredService) {
		if (offeredService == null) {
			return null;
		}
		else {
		OfferedServiceDto offeredServiceDto = new OfferedServiceDto(offeredService.getOfferedServiceId(), offeredService.getPrice(), 
				offeredService.getName(), offeredService.getDuration(), offeredService.getReminderTime(), offeredService.getReminderDate(), offeredService.getDescription());
		return offeredServiceDto;
		}
	}
	

	/**
	 * Helper Method to convert an technician account to a Dto
	 * Will return null if you pass null
	 * @author Catherine
	 * @param user
	 * @return Dto
	 */
	private TechnicianAccountDto convertToDto(TechnicianAccount user){
		if (user == null) {
			return null;
		}
		TechnicianAccountDto technicianAccountDto = new TechnicianAccountDto(user.getUsername(), user.getPassword(), user.getName());
		if (user.getAppointment() != null) {
			technicianAccountDto.setAppointments(user.getAppointment().stream().map(t -> convertToDto(t)).collect(Collectors.toList()));
		}
		return technicianAccountDto;
	}
	
	/**
	 * Helper Method to convert a garage to a Dto
	 * Will return null if you pass null
	 * @author Catherine	 
	 * @param garage
	 * @return Dto
	 */
	private GarageDto convertToDto(Garage garage) {
		if (garage == null) {
			return null;
		}
		
		GarageDto garageDto = new GarageDto(garage.getGarageId(), 
				garage.getAppointment().stream().map(a -> convertToDto(a)).collect(Collectors.toList()));
		return garageDto;
	}

	/**
	 * Helper Method to convert a timeSlot to a Dto
	 * Will return null if you pass null
	 * @author Catherine	 
	 * @param timeSlot
	 * @return Dto
	 */
	private TimeSlotDto convertToDto(TimeSlot timeSlot) {
		if (timeSlot == null) {
			return null;
		}
		TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getStartDate(), timeSlot.getEndDate());
		return timeSlotDto;
	}


}