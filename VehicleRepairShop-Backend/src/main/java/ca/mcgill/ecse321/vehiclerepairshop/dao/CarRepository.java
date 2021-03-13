package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;

public interface CarRepository extends CrudRepository<Car, String>{
	List<Car> findByOwner(CustomerAccount customerAccount);
	boolean existsByOwner(CustomerAccount customerAccount);
	Car findByLicensePlate(String license);
}