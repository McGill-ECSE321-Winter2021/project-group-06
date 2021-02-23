package ca.mcgill.ecse321.vehiclerepairshop.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;

public interface BusinessInformationRepository extends CrudRepository<BusinessInformation, String>{
	BusinessInformation findByName(String name);
}
