package vehiclerepairshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	//create
	//delete
	//update
	//login
	//logout
	//get
	//get all
	//valid user/token
	
	/**
	 * Return a list of all Admin Account Dtos 
	 * @return
	 */
	@GetMapping(value = { "/adminAccount", "/adminAccounts/" })
	public List<AdminAccountDto> getAllAdminAccounts() {
		return adminAccountService.getAllAdminAccounts().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}

	/*
	 * Create an Admin Account Dto with provided parameters
	 */
	@PostMapping(value = { "/adminAccount/{username}", "/adminAccount/{username}/" })
	public AdminAccountDto createAdminAccount(@PathVariable("username") String username, @RequestParam String password, @RequestParam String name) throws InvalidInputException {
		AdminAccount user = adminAccountService.createAdminAccount(username, password, name);
		return convertToDto(user);
	}

	/**
	 * Helper Method to convert an admin account to a Dto
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

//	private BusinessInformationDto createBusinessInformationDtosForAdminAccount(AdminAccount user) {
//		BusinessInformation businessInfo = adminAccountService.getAdminAccountByUsername(user.getUsername()).getBusinessInformation();
//		return null;
//
//	}	
		
		
}