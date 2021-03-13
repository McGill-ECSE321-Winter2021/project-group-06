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
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import vehiclerepairshop.dto.AdminAccountDto;
import vehiclerepairshop.dto.AppointmentDto;
import vehiclerepairshop.dto.BusinessInformationDto;
import vehiclerepairshop.service.AdminAccountService;
import vehiclerepairshop.service.BusinessInformationService;
import vehiclerepairshop.service.InvalidInputException;

@CrossOrigin(origins = "*")
@RestController
public class AdminAccountController {

	@Autowired
	private AdminAccountService adminAccountService;
	@Autowired
	private BusinessInformationService businessInformationService;

	
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
	 * @throws InvalidInputException
	 */
	@PostMapping(value = { "/createAdminAccount/{username}/{password}/{name}", "/createAdminAccount/{username}/{password}/{name}/" })
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
	
	// TODO findByBusinessInformation
	@GetMapping(value = { "/getAdminAccountsByBusinessInformation/getBusinessInformationByName/{name}", "/getAdminAccountsByBusinessInformation/getBusinessInformationByName/{name}" })
	public List<AdminAccountDto> getAdminAccountsByBusinessInformation(@PathVariable("name") BusinessInformationDto businessInformationDto) {
		return adminAccountService.getAllAdminAccountsWithBusinessInformation(convertToDomainObject(businessInformationDto)).stream().map(u -> convertToDto(u)).collect(Collectors.toList());
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
	/**
	 * Helper method to get the businessInformation for the businessInformationDto
	 * @author Catherine
	 * @param businessInformationDto
	 * @return BusinessInformation
	 */
	private BusinessInformation convertToDomainObject(BusinessInformationDto appobusinessInformationDtointmentDto)  {
		if (appobusinessInformationDtointmentDto == null) {
			return null;
		}
		else {
			// TODO
			//return businessInformationService.getBusinessInformationByName(businessInformationDto.getName());
			return null;
		}
	}

		
}