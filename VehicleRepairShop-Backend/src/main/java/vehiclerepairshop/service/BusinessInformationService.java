package vehiclerepairshop.service;

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
			BusinessInformation businessInformation = new BusinessInformation();
			businessInformation.setName(name);
			businessInformation.setAddress(address);
			businessInformation.setPhoneNumber(phoneNumber);
			businessInformation.setEmailAddress(emailAddress);
			businessInformationRepository.save(businessInformation);
			
			return businessInformation;
		}
		
		/**
		 * Find garage through a garage id
		 * @param garageId
		 * @return
		 */
		@Transactional
		public BusinessInformation getBusinessInformationByName(String name) {
			BusinessInformation businessInformation = businessInformationRepository.findBusinessInformationByName(name);
			return businessInformation;
		}
}
