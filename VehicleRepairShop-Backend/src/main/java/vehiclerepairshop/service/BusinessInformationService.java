package vehiclerepairshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.BusinessInformationRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;

/**
 * @author aureliahaas
 *
 */
public class BusinessInformationService {


	@Autowired
	private BusinessInformationRepository businessInformationRepository;

	/**
	 * Create a Business Information with given parameters
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @param emailAddress
	 * @return
	 */
	@Transactional
	public BusinessInformation createBusinessInformation(String name, String address, String phoneNumber, String emailAddress) {
		if (name == null || name.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("Name cannot be empty!");
		}
		else if (address == null || address.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("Address cannot be empty!");
		}
		else if (phoneNumber == null || phoneNumber.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("PhoneNumber cannot be empty!");
		}
		else if (emailAddress == null || emailAddress.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("EmailAddress cannot be empty!");
		}
		else if (businessInformationRepository.findBusinessInformationByName(name) != null) {
			throw new InvalidInputException("Name not available!");
		}
		
		BusinessInformation businessInformation = new BusinessInformation();
		businessInformation.setName(name);
		businessInformation.setAddress(address);
		businessInformation.setPhoneNumber(phoneNumber);
		businessInformation.setEmailAddress(emailAddress);
		businessInformationRepository.save(businessInformation);

		return businessInformation;
	}


	/**
	 * Find all Business Information
	 * @return
	 */
	@Transactional
	public List<BusinessInformation> getAllBusinessInformation(){
		Iterable<BusinessInformation> businessInformation = businessInformationRepository.findAll();
		return toList(businessInformation);
	}


	/**
	 * Find Business Information through a name
	 * @param garageId
	 * @return
	 */
	@Transactional
	public BusinessInformation getBusinessInformationByName(String name) {
		if (name == null || name.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("Name cannot be empty!");
		}
		else if (businessInformationRepository.findBusinessInformationByName(name) == null) {
			throw new InvalidInputException("Name does not exist!");
		}
		
		BusinessInformation businessInformation = businessInformationRepository.findBusinessInformationByName(name);
		if(businessInformation == null) {
			throw new InvalidInputException("The business information is not found in the system!");
		}
		return businessInformation;
	}

	/**
	 * Update business information by offering new information and name
	 * @param currentName
	 * @param newAddress
	 * @param newPhoneNumber
	 * @param newEmailAddress
	 * @return
	 * @throws InvalidInputException
	 */
	@Transactional
	public BusinessInformation updateBusinessInformation(String currentName,String name, String newAddress, String newPhoneNumber, String newEmailAddress) throws InvalidInputException {
		if (currentName == null || currentName.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("CurrentName cannot be empty!");
		}
		if (name == null || name.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("Name cannot be empty!");
		}
		else if (newAddress == null || newAddress.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("Address cannot be empty!");
		}
		else if (newPhoneNumber == null || newPhoneNumber.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("PhoneNumber cannot be empty!");
		}
		else if (newEmailAddress == null || newEmailAddress.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("EmailAddress cannot be empty!");
		}
		else if (businessInformationRepository.findBusinessInformationByName(currentName) == null) {
			throw new InvalidInputException("CurrentName does not exist!");
		}
		else if (businessInformationRepository.findBusinessInformationByName(name) != null) {
			throw new InvalidInputException("Name not available!");
		}

		BusinessInformation businessInformation = businessInformationRepository.findBusinessInformationByName(currentName);
		if(businessInformation == null) {
			throw new InvalidInputException("The business information is not found in the system!");
		}

		businessInformation.setName(name);
		businessInformation.setAddress(newAddress);
		businessInformation.setPhoneNumber(newPhoneNumber);
		businessInformation.setEmailAddress(newEmailAddress);
		businessInformationRepository.save(businessInformation);

		return businessInformation;
	}

	/**
	 * Delete a Business Information
	 * @param name
	 */
	@Transactional
	public BusinessInformation deleteBusinessInformation(String name) {
		if (name == null || name.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("Name cannot be empty!");
		}
		else if (businessInformationRepository.findBusinessInformationByName(name) == null) {
			throw new InvalidInputException("Name does not exist!");
		}
		
		BusinessInformation businessInformation = businessInformationRepository.findBusinessInformationByName(name);
		if(businessInformation == null) {
			throw new InvalidInputException("The business information cannot be found.");
		}
		
		businessInformationRepository.delete(businessInformation);
		return businessInformation;
	}

	/**
	 * Delete all the Business Information
	 */
	@Transactional
	public List<BusinessInformation> deleteAllBusinessInformation() {
		Iterable<BusinessInformation> businessInformation = businessInformationRepository.findAll();
		businessInformationRepository.deleteAll();
		return toList(businessInformation);
	}


	//----------------------------- Helper Methods --------------------------------
	/**
	 * helper method that converts iterable to list
	 * @param <T>
	 * @param iterable
	 * @return
	 */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
