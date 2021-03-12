package vehiclerepairshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	

//	@Autowired
//	private JWTTokenProvider jwtTokenProvider;

	//create -- done
	//delete -- done
	//update -- done
	//login 
	//logout
	//valid user/token 
	//get by username -- done
	//get by name -- done
	//get all -- done

	
	/**
	 * Return a list of all Admin Account Dtos 
	 * @author Catherine
	 * @return list of Admin Dtos
	 */
	@GetMapping(value = { "/adminAccount", "/adminAccounts/" })
	public List<AdminAccountDto> getAllAdminAccounts() {
		return adminAccountService.getAllAdminAccounts().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}

	/**
	 * Return the admin account with specified username
	 * @author Catherine
	 * @param username
	 * @return Admin Dto
	 */
	@GetMapping(value = { "/adminAccount", "/adminAccounts/" })
	public AdminAccountDto getAdminAccountByUsername(@PathVariable("username") String username) {
		return convertToDto(adminAccountService.getAdminAccountByUsername(username));
	}
	/**
	 * Return a list of all Admin Accounts with specified name
	 * @author Catherine
	 * @param name
	 * @return list of Admin Dtos
	 */
	@GetMapping(value = { "/adminAccount", "/adminAccounts/" })
	public List<AdminAccountDto> getAdminAccountsByName(@RequestParam String name) {
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
	@PostMapping(value = { "/adminAccount/{username}", "/adminAccount/{username}/" })
	public AdminAccountDto createAdminAccount(@PathVariable("username") String username, @RequestParam String password, @RequestParam String name) throws InvalidInputException {
		AdminAccount user = adminAccountService.createAdminAccount(username, password, name);
		return convertToDto(user);
	}

	
	/**
	 * Update an Admin Account Dto with provided parameters
	 * @author Catherine
	 * @param currentUsername
	 * @param newUsername
	 * @param newPassword
	 * @param newName
	 * @return Admin Account Dto
	 * @throws InvalidInputException
	 */
	@PostMapping(value = { "/adminAccount/{username}", "/adminAccount/{username}/" })
	public AdminAccountDto updateAdminAccount(@PathVariable("username") String currentUsername, @RequestParam String newUsername, @RequestParam String newPassword, @RequestParam String newName) throws InvalidInputException {
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
	@DeleteMapping(value = { "/adminAccount/{username}", "/adminAccount/{username}/" })
	public boolean deleteAdminAccount(@PathVariable("username") String username) throws InvalidInputException {
		boolean successful = adminAccountService.deleteAdminAccount(username);
		return successful;
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