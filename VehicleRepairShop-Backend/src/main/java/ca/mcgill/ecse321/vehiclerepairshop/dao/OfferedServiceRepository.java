package ca.mcgill.ecse321.vehiclerepairshop.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "OfferedService_data", path = "OfferedService_data")
public interface OfferedServiceRepository  extends CrudRepository<OfferedService, String>{
	OfferedService findByOfferedServiceId (String Id);
}
