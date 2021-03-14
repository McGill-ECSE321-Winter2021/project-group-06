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

import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;
import vehiclerepairshop.dto.BusinessInformationDto;
import vehiclerepairshop.service.BusinessInformationService;
import vehiclerepairshop.service.InvalidInputException;
import ca.mcgill.ecse321.vehiclerepairshop.dao.BusinessInformationRepository;

/**
 * @author aureliahaas
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class BusinessInformationController {
	@Autowired
	private BusinessInformationService businessInformationService;
	@Autowired 
	private BusinessInformationRepository businessInformationRepository;

	/**
	 * Return all the business information
	 * @return
	 * @throws IllegalArgumentException
	 */
	@GetMapping(value = {"/getAllBusinessInformation", "/getAllBusinessInformation/"})
	public List<BusinessInformationDto> getAllBusinessInformation() throws IllegalArgumentException {
		return businessInformationService.getAllBusinessInformation().stream().map(businessInformation->convertToDto(businessInformation)).collect(Collectors.toList());
	}

	/**
	 * Return the business information with specified name
	 * @param name
	 * @return
	 */
	@GetMapping(value = { "/getBusinessInformationByName/{name}", "/getBusinessInformationByName/{name}/" })
	public BusinessInformationDto getBusinessInformationByName(@PathVariable("name") String name) {
		return convertToDto(businessInformationService.getBusinessInformationByName(name));
	}

	/**
	 * Create a business information with its specific parameters
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @param emailAddress
	 * @return
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createBusinessInformation/{name}/{address}/{phoneNumber}/{emailAddress}","/createBusinessInformation/{name}/{address}/{phoneNumber}/{emailAddress}/"})
	public BusinessInformationDto createBusinessInformation(@PathVariable("name") String name,
			@PathVariable("address") String address,
			@PathVariable("phoneNumber") String phoneNumber,
			@PathVariable("emailAddress") String emailAddress) throws IllegalArgumentException {
		BusinessInformation businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		return convertToDto(businessInformation);
	}

	/**
	 * Update business information
	 * @param phoneNumber
	 * @param address
	 * @param phoneNumber
	 * @param emailAddress
	 * @param reminderTime
	 * @param reminderDate
	 * @param description
	 * @return
	 * @throws InvalidInputException 
	 */
	@PostMapping(value = {"/updateBusinessInformation/{name}/{address}/{phoneNumber}/{emailAddress}", "/updateBusinessInformation/{name}/{address}/{phoneNumber}/{emailAddress}/"})
	public BusinessInformationDto updateBusinessInformation(@PathVariable("name")String name, 
			@PathVariable("address")String address, 
			@PathVariable("phoneNumber")String phoneNumber, 
			@PathVariable("emailAddress")String emailAddress) throws InvalidInputException {
		BusinessInformationDto updatedBusinessInformation = new BusinessInformationDto();
		BusinessInformation businessInformation;
		try {
			businessInformation = businessInformationService.updateBusinessInformation(name, address, phoneNumber, emailAddress);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			throw new InvalidInputException(e.getMessage());
		}
		updatedBusinessInformation = convertToDto(businessInformation);
		return updatedBusinessInformation; 
	}

	/**
	 * Delete a business information
	 * @param name
	 * @return
	 */
	@DeleteMapping(value = {"/deleteBusinessInformation/{name}","/deleteBusinessInformation/{name}/"})
	public boolean deleteBusinessInformationDto(@PathVariable("name") String name) {
		boolean isSuccess = false; 
		BusinessInformation businessInformation = businessInformationRepository.findBusinessInformationByName(name);
		businessInformationRepository.delete(businessInformation);
		isSuccess = true;
		return isSuccess;
	}

	/**
	 * Delete all the business information
	 * @return
	 */
	@DeleteMapping(value = {"/deleteAllBusinessInformation","/deleteAllBusinessInformation/"})
	public boolean deleteBusinessInformationDto() {
		boolean isSuccess = false;
		businessInformationRepository.deleteAll();
		isSuccess = true;
		return isSuccess;
	}

	//----------------------------- Helper Methods --------------------------------

	/**
	 * Convert a business information to DTO
	 * @param businessInformation
	 * @return
	 */
	private BusinessInformationDto convertToDto(BusinessInformation businessInformation) {
		if (businessInformation == null) {
			throw new IllegalArgumentException("There is no such businessInformation!");
		}
		BusinessInformationDto businessInformationDto = new BusinessInformationDto(businessInformation.getName(),businessInformation.getAddress(),businessInformation.getPhoneNumber(),businessInformation.getEmailAddress());
		return businessInformationDto;
	}

}






