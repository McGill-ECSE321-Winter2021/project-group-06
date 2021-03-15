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
import vehiclerepairshop.dto.AppointmentDto;
import vehiclerepairshop.dto.CarDto;
import vehiclerepairshop.dto.CustomerAccountDto;
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
	public boolean deleteCustomerAccount(@PathVariable("username") String username) throws InvalidInputException {
		boolean successful = customerAccountService.deleteCustomerAccount(username);
		return successful;
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
	public boolean loginCustomerAccount(@PathVariable("username") String username, @PathVariable("password") String password) throws InvalidInputException {
		boolean successful = customerAccountService.loginCustomerAccount(username, password);
		return successful;
	}

	/**
	 * Logout and delete token
	 * @author Catherine
	 * @param username
	 * @return boolean if successful
	 * @throws InvalidInputException
	 */
	@PostMapping(value = {"/logoutCustomerAccount/{username}", "/logoutCustomerAccount/{username}/" })
	public boolean logoutCustomerAccount(@PathVariable("username") String username) throws InvalidInputException {
		boolean successful = customerAccountService.logoutCustomerAccount(username);
		return successful;
	}

	/**
	 * Authenticate token
	 * @author Catherine
	 * @param username
	 * @return boolean authenticity
	 * @throws InvalidInputException
	 */
	@PostMapping(value = {"/authenticateCustomerAccount/{username}", "/authenticateCustomerAccount/{username}/" })
	public boolean authenticateCustomerAccount(@PathVariable("username") String username) throws InvalidInputException{
		boolean authentic = customerAccountService.authenticateCustomerAccount(username);
		return authentic;
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
	 * @author Catherine
	 * @param car
	 * @return AppointmentDto
	 */
	private AppointmentDto convertToDto(Appointment apt)  {
		if (apt == null) {
			return null;
		}
		else {
			// TODO add appointment attributes once AppointmentDto is finished

			AppointmentDto aptDto = new AppointmentDto();

			return aptDto;
		}
	}



}