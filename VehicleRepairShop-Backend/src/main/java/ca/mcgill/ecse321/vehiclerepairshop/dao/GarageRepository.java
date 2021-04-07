package ca.mcgill.ecse321.vehiclerepairshop.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Garage_data", path = "Garage_data")
public interface GarageRepository extends CrudRepository<Garage, String>{
	Garage findByGarageId(String garageId);
}
