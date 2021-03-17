package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Car_data", path = "Car_data")
public interface CarRepository extends CrudRepository<Car, String>{
	List<Car> findByOwner(CustomerAccount customerAccount);
	Car findByLicensePlate(String license);
}