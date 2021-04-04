package ca.mcgill.ecse321.vehiclerepairshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;
import ca.mcgill.ecse321.vehiclerepairshop.dto.*;
import ca.mcgill.ecse321.vehiclerepairshop.service.*;
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
	 */
	@GetMapping(value = {"/getAllBusinessInformation", "/getAllBusinessInformation/"})
	public List<BusinessInformationDto> getAllBusinessInformation(){
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
	 */
	@PostMapping(value = { "/createBusinessInformation/{name}/{address}/{phoneNumber}/{emailAddress}","/createBusinessInformation/{name}/{address}/{phoneNumber}/{emailAddress}/"})
	public BusinessInformationDto createBusinessInformation(@PathVariable("name") String name,
			@PathVariable("address") String address,
			@PathVariable("phoneNumber") String phoneNumber,
			@PathVariable("emailAddress") String emailAddress) {
		BusinessInformation businessInformation = businessInformationService.createBusinessInformation(name, address, phoneNumber, emailAddress);
		return convertToDto(businessInformation);
	}

	/**
	 * Update business information
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @param emailAddress
	 * @return
	 */
	@PutMapping(value = {"/updateBusinessInformation/{name}/{address}/{phoneNumber}/{emailAddress}", "/updateBusinessInformation/{name}/{address}/{phoneNumber}/{emailAddress}/"})
	public BusinessInformationDto updateBusinessInformation(@PathVariable("name")String name, 
			@PathVariable("address")String address, 
			@PathVariable("phoneNumber")String phoneNumber, 
			@PathVariable("emailAddress")String emailAddress) {
		BusinessInformationDto updatedBusinessInformation = new BusinessInformationDto();
		BusinessInformation businessInformation;
		businessInformation = businessInformationService.updateBusinessInformation(name, address, phoneNumber, emailAddress);
		updatedBusinessInformation = convertToDto(businessInformation);
		return updatedBusinessInformation; 
	}

	/**
	 * Delete a business information
	 * @param name
	 * @return
	 */
	@PutMapping(value = {"/deleteBusinessInformation/{name}","/deleteBusinessInformation/{name}/"})
	public BusinessInformationDto deleteBusinessInformation(@PathVariable("name") String name) {
		BusinessInformation businessInformation = businessInformationRepository.findBusinessInformationByName(name);
		businessInformationService.deleteBusinessInformation(name);
		return convertToDto(businessInformation);
	}

	/**
	 * Delete all the business information
	 * @return
	 */
	@PutMapping(value = {"/deleteAllBusinessInformation","/deleteAllBusinessInformation/"})
	public List<BusinessInformationDto> deleteBusinessInformation() {
		List<BusinessInformation> businessesInformation = businessInformationService.deleteAllBusinessInformation();
		List<BusinessInformationDto> businessInformationDtos = new ArrayList<BusinessInformationDto>();
		for (BusinessInformation businessInformation : businessesInformation) {
			businessInformationDtos.add(convertToDto(businessInformation));
		}
		return businessInformationDtos;
	}

	//----------------------------- Helper Methods --------------------------------

	/**
	 * Convert a business information to DTO
	 * @param businessInformation
	 * @return
	 */
	private BusinessInformationDto convertToDto(BusinessInformation businessInformation) {
		if (businessInformation == null) {
			throw new InvalidInputException("There is no such businessInformation!");
		}
		BusinessInformationDto businessInformationDto = new BusinessInformationDto(businessInformation.getName(),businessInformation.getAddress(),businessInformation.getPhoneNumber(),businessInformation.getEmailAddress());
		return businessInformationDto;
	}

}
