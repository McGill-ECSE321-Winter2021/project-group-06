package ca.mcgill.ecse321.vehiclerepairshop.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;


import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "BusinessInformation_data", path = "BusinessInformation_data")
public interface BusinessInformationRepository extends CrudRepository<BusinessInformation, String>{
	BusinessInformation findBusinessInformationByName(String name);
}
