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

import ca.mcgill.ecse321.vehiclerepairshop.model.AdminAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;
import vehiclerepairshop.dto.AdminAccountDto;
import vehiclerepairshop.dto.BusinessInformationDto;
import vehiclerepairshop.service.AdminAccountService;
import vehiclerepairshop.service.InvalidInputException;

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
	@GetMapping(value = { "/adminAccounts", "/adminAccounts/" })
	public List<AdminAccountDto> getAllAdminAccounts() {
		return adminAccountService.getAllAdminAccounts().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}

	/**
	 * Return the admin account with specified username
	 * @author Catherine
	 * @param username
	 * @return Admin Dto
	 */
	@GetMapping(value = { "/adminAccount/{username}", "/adminAccount/{username}/" })
	public AdminAccountDto getAdminAccountByUsername(@PathVariable("username") String username) {
		return convertToDto(adminAccountService.getAdminAccountByUsername(username));
	}
	
	/**
	 * Return a list of all Admin Accounts with specified name
	 * @author Catherine
	 * @param name
	 * @return list of Admin Dtos
	 */
	@GetMapping(value = { "/adminAccounts/{name}", "/adminAccounts/{name}" })
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
	 * @throws InvalidInputException
	 */
	@PostMapping(value = { "/adminAccount/{username}/{password}/{name}", "/adminAccount/{username}/{password}/{name}/" })
	public AdminAccountDto createAdminAccount(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("name") String name) throws InvalidInputException {
		AdminAccount user = adminAccountService.createAdminAccount(username, password, name);
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
	 * @throws InvalidInputException
	 */
	@PostMapping(value = {"/updateAdminAccount/{currentUsername}/{newUsername}/{newPassword}/{newName}", "/adminAccount/updateAdminAccount/{currentUsername}/{newUsername}/{newPassword}/{newName}/" })
	public AdminAccountDto updateAdminAccount(@PathVariable("currentUsername") String currentUsername, @PathVariable("newUsername") String newUsername, @PathVariable("newPassword") String newPassword, @PathVariable("newName") String newName) throws InvalidInputException {
		AdminAccount user = adminAccountService.updateAdminAccount(currentUsername, newUsername, newPassword, newName);
		return convertToDto(user);
	}

	/**
	 * Delete admin account
	 * @author Catherine
	 * @param username
	 * @return boolean if successful
	 * @throws InvalidInputException
	 */
	@DeleteMapping(value = { "/deleteAdminAccount/{username}", "/adminAccount/deleteAdminAccount/{username}/" })
	public boolean deleteAdminAccount(@PathVariable("username") String username) throws InvalidInputException {
		boolean successful = adminAccountService.deleteAdminAccount(username);
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
	@PostMapping(value = {"/loginAdminAccount/{username}/{password}", "/loginAdminAccount/{username}/{password}/" })
	public boolean loginAdminAccount(@PathVariable("username") String username, @PathVariable("password") String password) throws InvalidInputException {
		boolean successful = adminAccountService.loginAdminAccount(username, password);
		return successful;
	}
	
	/**
	 * Logout and delete token
	 * @author Catherine
	 * @param username
	 * @return boolean if successful
	 * @throws InvalidInputException
	 */
	@PostMapping(value = {"/logoutAdminAccount/{username}", "/logoutAdminAccount/{username}/" })
	public boolean logoutAdminAccount(@PathVariable("username") String username) throws InvalidInputException {
		boolean successful = adminAccountService.logoutAdminAccount(username);
		return successful;
	}
	
	/**
	 * Authenticate token
	 * @author Catherine
	 * @param username
	 * @return boolean authenticity
	 * @throws InvalidInputException
	 */
	@PostMapping(value = {"/authenticateAdminAccount/{username}", "/authenticateAdminAccount/{username}/" })
	public boolean authenticateAdminAccount(@PathVariable("username") String username) throws InvalidInputException{
		boolean authentic = adminAccountService.authenticateAdminAccount(username);
		return authentic;
	}
	
	
	//-------------------------- Helper Methods -----------------------------
	
	/**
	 * Helper Method to convert an admin account to a Dto
	 * @author Catherine
	 * @param user
	 * @return
	 */
	private AdminAccountDto convertToDto(AdminAccount user) throws IllegalArgumentException{
		if (user == null) {
			throw new IllegalArgumentException("This user does not exist");
		}
		AdminAccountDto adminAccountDto = new AdminAccountDto(user.getUsername(), user.getPassword(), user.getName());
		adminAccountDto.setBusinessInformation(convertToDto(user.getBusinessInformation()));
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