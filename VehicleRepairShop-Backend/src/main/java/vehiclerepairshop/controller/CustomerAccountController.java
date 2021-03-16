package vehiclerepairshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import vehiclerepairshop.dto.AppointmentDto;
import vehiclerepairshop.dto.CarDto;
import vehiclerepairshop.dto.CustomerAccountDto;
import vehiclerepairshop.dto.GarageDto;
import vehiclerepairshop.dto.OfferedServiceDto;
import vehiclerepairshop.dto.TechnicianAccountDto;
import vehiclerepairshop.dto.TimeSlotDto;
import vehiclerepairshop.service.CarService;
import vehiclerepairshop.service.CustomerAccountService;
import vehiclerepairshop.service.InvalidInputException;

@CrossOrigin(origins = "*")
@RestController
public class CustomerAccountController {

	@Autowired
	private CustomerAccountService customerAccountService;
	@Autowired
	private CarService carService;

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
	 * @throws InvalidInputException
	 */
	@PostMapping(value = { "/createCustomerAccount/{username}/{password}/{name}", "/createCustomerAccount/{username}/{password}/{name}/" })
	public CustomerAccountDto createCustomerAccount(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("name") String name) throws InvalidInputException {
		CustomerAccount user = customerAccountService.createCustomerAccount(username, password, name);
		return convertToDto(user);
	}


	/**
	 * Update an Customer Account Dto username, password, and name
	 * If not changing something, pass old value
	 * @author Catherine
	 * @param currentUsername
	 * @param newUsername
	 * @param newPassword
	 * @param newName
	 * @return Customer Account Dto
	 * @throws InvalidInputException
	 */
	@PostMapping(value = {"/updateCustomerAccount/{currentUsername}/{newUsername}/{newPassword}/{newName}", "/customerAccount/updateCustomerAccount/{currentUsername}/{newUsername}/{newPassword}/{newName}/" })
	public CustomerAccountDto updateCustomerAccount(@PathVariable("currentUsername") String currentUsername, @PathVariable("newUsername") String newUsername, @PathVariable("newPassword") String newPassword, @PathVariable("newName") String newName) throws InvalidInputException {
		CustomerAccount user = customerAccountService.updateCustomerAccount(currentUsername, newUsername, newPassword, newName);
		return convertToDto(user);
	}

	/**
	 * Delete customer account
	 * @author Catherine
	 * @param username
	 * @return boolean if successful
	 * @throws InvalidInputException
	 */
	@DeleteMapping(value = { "/deleteCustomerAccount/{username}", "/customerAccount/deleteCustomerAccount/{username}/" })
	public CustomerAccount deleteCustomerAccount(@PathVariable("username") String username) throws InvalidInputException {
		CustomerAccount user = customerAccountService.deleteCustomerAccount(username);
		return user;
	}

	/**
	 * Login and generate token
	 * @author Catherine
	 * @param username
	 * @param password
	 * @return boolean if successful
	 * @throws InvalidInputException
	 */
	@PostMapping(value = {"/loginCustomerAccount/{username}/{password}", "/loginCustomerAccount/{username}/{password}/" })
	public CustomerAccount loginCustomerAccount(@PathVariable("username") String username, @PathVariable("password") String password) throws InvalidInputException {
		CustomerAccount user = customerAccountService.loginCustomerAccount(username, password);
		return user;
	}

	/**
	 * Logout and delete token
	 * @author Catherine
	 * @param username
	 * @return boolean if successful
	 * @throws InvalidInputException
	 */
	@PostMapping(value = {"/logoutCustomerAccount/{username}", "/logoutCustomerAccount/{username}/" })
	public CustomerAccount logoutCustomerAccount(@PathVariable("username") String username) throws InvalidInputException {
		CustomerAccount user = customerAccountService.logoutCustomerAccount(username);
		return user;
	}

	/**
	 * Authenticate token
	 * @author Catherine
	 * @param username
	 * @return boolean authenticity
	 * @throws InvalidInputException
	 */
	@PostMapping(value = {"/authenticateCustomerAccount/{username}", "/authenticateCustomerAccount/{username}/" })
	public CustomerAccount authenticateCustomerAccount(@PathVariable("username") String username) throws InvalidInputException{
		CustomerAccount user = customerAccountService.authenticateCustomerAccount(username);
		return user;
	}

	/**
	 * Get the customer account associated to specified car
	 * @author Catherine
	 * @param carDto
	 * @return
	 */
	// TODO fix URL once CarDto and Controller is complete
	@GetMapping(value = { "/getCustomerAccountByCar/getCarByLicensePlate/{licensePlate}", "/getCustomerAccountByCar/getCarByLicensePlate/{licensePlate}/" })
	public CustomerAccountDto getCustomerAccountByCar(@PathVariable("licensePlate") CarDto carDto) {
		return convertToDto(customerAccountService.getCustomerAccountWithCar(convertToDomainObject(carDto)));
	}

	//-------------------------- Helper Methods -----------------------------

	/**
	 * Helper Method to convert an customer account to a Dto
	 * @author Catherine
	 * @param user
	 * @return CustomerAccountDto
	 */
	private CustomerAccountDto convertToDto(CustomerAccount user) throws IllegalArgumentException{
		if (user == null) {
			throw new IllegalArgumentException("This user does not exist");
		}
		CustomerAccountDto customerAccountDto = new CustomerAccountDto(user.getUsername(), user.getPassword(), user.getName());
		customerAccountDto.setCars(user.getCar().stream().map(c -> convertToDto(c)).collect(Collectors.toList()));
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
	 * Helper method to get the car for the carDto
	 * @author Catherine
	 * @param carDto
	 * @return Car
	 */
	private Car convertToDomainObject(CarDto carDto)  {
		if (carDto == null) {
			return null;
		}
		else {
			return carService.getCarByLicensePlate(carDto.getLicensePlate());
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
					apt.getWorker().stream().map(a -> convertToDto(a)).collect(Collectors.toList()), convertToDto(apt.getOfferedService()));
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
		technicianAccountDto.setAppointments(user.getAppointment().stream().map(t -> convertToDto(t)).collect(Collectors.toList()));
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
		
		GarageDto garageDto = new GarageDto(garage.getIsAvailable(), garage.getGarageId(), 
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