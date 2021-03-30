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

import ca.mcgill.ecse321.vehiclerepairshop.model.AdminAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;
import ca.mcgill.ecse321.vehiclerepairshop.service.*;
import ca.mcgill.ecse321.vehiclerepairshop.service.InvalidInputException;
import ca.mcgill.ecse321.vehiclerepairshop.dto.*;


@CrossOrigin(origins = "*")
@RestController
public class AdminAccountController {

	@Autowired
	private AdminAccountService adminAccountService;

	/**
	 * Return a list of all Admin Account Dtos 
	 * @author Catherine
	 * @return list of Admin Dtos
	 */
	@GetMapping(value = { "/getAllAdminAccounts", "/getAllAdminAccounts/" })
	public List<AdminAccountDto> getAllAdminAccounts() {
		return adminAccountService.getAllAdminAccounts().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}

	/**
	 * Return the admin account with specified username
	 * @author Catherine
	 * @param username
	 * @return Admin Dto
	 */
	@GetMapping(value = { "/getAdminAccountByUsername/{username}", "/getAdminAccountByUsername/{username}/" })
	public AdminAccountDto getAdminAccountByUsername(@PathVariable("username") String username) {
		return convertToDto(adminAccountService.getAdminAccountByUsername(username));
	}

	/**
	 * Return a list of all Admin Accounts with specified name
	 * @author Catherine
	 * @param name
	 * @return list of Admin Dtos
	 */
	@GetMapping(value = { "/getAdminAccountsByName/{name}", "/getAdminAccountsByName/{name}" })
	public List<AdminAccountDto> getAdminAccountsByName(@PathVariable("name") String name) {
		return adminAccountService.getAdminAccountsByName(name).stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}

	/**
	 * Create an Admin Account Dto with provided parameters
	 * @author Catherine
	 * @param username
	 * @param password
	 * @param name
	 * @return Admin Account Dto
	 */
	@PostMapping(value = { "/createAdminAccount/{username}/{password}/{name}", "/createAdminAccount/{username}/{password}/{name}/" })
	public AdminAccountDto createAdminAccount(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("name") String name)  {
		AdminAccount user = adminAccountService.createAdminAccount(username, password, name);
		System.out.println(username + password + name);
		return convertToDto(user);
	}


	/**
	 * Update an Admin Account Dto username, password, and name
	 * If not changing something, pass old value
	 * @author Catherine
	 * @param currentUsername
	 * @param newUsername
	 * @param newPassword
	 * @param newName
	 * @return Admin Account Dto
	 */
	@PutMapping(value = {"/updateAdminAccount/{username}/{newPassword}/{newName}", "/updateAdminAccount/{currentUsername}/{newUsername}/{newPassword}/{newName}/" })
	public AdminAccountDto updateAdminAccount(@PathVariable("username") String username, @PathVariable("newPassword") String newPassword, @PathVariable("newName") String newName) {
		AdminAccount user = adminAccountService.updateAdminAccount(username, newPassword, newName);
		return convertToDto(user);
	}

	/**
	 * Delete admin account
	 * @author Catherine
	 * @param username
	 * @return boolean if successful
	 */
	@DeleteMapping(value = { "/deleteAdminAccount/{username}", "/deleteAdminAccount/{username}/" })
	public AdminAccountDto deleteAdminAccount(@PathVariable("username") String username) {
		AdminAccount user = adminAccountService.deleteAdminAccount(username);
		return convertToDto(user);
	}

	/**
	 * Login and generate token
	 * @author Catherine
	 * @param username
	 * @param password
	 * @return boolean if successful
	 */
	@PutMapping(value = {"/loginAdminAccount/{username}/{password}", "/loginAdminAccount/{username}/{password}" })
	public AdminAccountDto loginAdminAccount(@PathVariable("username") String username, @PathVariable("password") String password) {
		AdminAccount user = adminAccountService.loginAdminAccount(username, password);
		System.out.println(username + password);
		return convertToDto(user);
	}

	/**
	 * Logout and delete token
	 * @author Catherine
	 * @param username
	 * @return boolean if successful
	 */
	@PutMapping(value = {"/logoutAdminAccount/{username}", "/logoutAdminAccount/{username}/" })
	public AdminAccountDto logoutAdminAccount(@PathVariable("username") String username) {
		AdminAccount user = adminAccountService.logoutAdminAccount(username);
		return convertToDto(user);
	}

	/**
	 * Authenticate token
	 * @author Catherine
	 * @param username
	 * @return boolean authenticity
	 */
	@PostMapping(value = {"/authenticateAdminAccount/{username}", "/authenticateAdminAccount/{username}/" })
	public AdminAccountDto authenticateAdminAccount(@PathVariable("username") String username) {
		AdminAccount user = adminAccountService.authenticateAdminAccount(username);
		return convertToDto(user);
	}

	/**
	 * Get admin account associated to business information
	 * @author Catherine
	 * @param businessInformation name
	 * @return
	 */
	@GetMapping(value = { "/getAdminAccountsByBusinessInformation/{businessName}", "/getAdminAccountsByBusinessInformation/{businessName}/" })
	public List<AdminAccountDto> getAdminAccountsByBusinessInformation(@PathVariable("businessName") String businessName) {
		return adminAccountService.getAllAdminAccountsWithBusinessInformation(businessName).stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}

	/**
	 * Set business information for an admin account
	 * @author Catherine
	 * @param username
	 * @param businessInformationDto
	 * @return
	 */
	@PutMapping(value = {"/setBusinessInformation/{username}/{businessName}", "/setBusinessInformation/{username}/{businessName}/"})
	public AdminAccountDto setBusinessInformation(@PathVariable("username") String username, @PathVariable("businessName") String businessName) {
		AdminAccount user = adminAccountService.setBusinessInformation(businessName, username);
		return convertToDto(user);
	}

	//-------------------------- Helper Methods -----------------------------

	/**
	 * Helper Method to convert an admin account to a Dto
	 * @author Catherine
	 * @param user
	 * @return
	 */
	private AdminAccountDto convertToDto(AdminAccount user){
		if (user == null) {
			throw new InvalidInputException("This user does not exist");
		}
		AdminAccountDto adminAccountDto = new AdminAccountDto(user.getUsername(), user.getPassword(), user.getName());
		adminAccountDto.setToken(user.getToken());
		if (user.getBusinessInformation() != null) {
			adminAccountDto.setBusinessInformation(convertToDto(user.getBusinessInformation()));
		}
		return adminAccountDto;
	}

	/**
	 * Helper Method to convert a business info to a Dto
	 * Will return null if you pass null
	 * @author Catherine
	 * @param businessInformation
	 * @return
	 */
	private BusinessInformationDto convertToDto(BusinessInformation businessInformation)  {
		if (businessInformation == null) {
			return null;
		}
		else {
			BusinessInformationDto businessInfoDto = new BusinessInformationDto(businessInformation.getName(), businessInformation.getAddress(), businessInformation.getPhoneNumber(), businessInformation.getEmailAddress());
			return businessInfoDto;
		}
	}



}