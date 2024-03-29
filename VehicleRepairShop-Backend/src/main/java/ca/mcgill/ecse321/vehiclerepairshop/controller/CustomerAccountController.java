package ca.mcgill.ecse321.vehiclerepairshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
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
	 */
	@PutMapping(value = { "/deleteCustomerAccount/{username}", "/deleteCustomerAccount/{username}/" })
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
			CarDto carDto = new CarDto(car.getLicensePlate(), car.getModel(), car.getYear(), car.getMotorType());
			return carDto;
		}
	}	




}