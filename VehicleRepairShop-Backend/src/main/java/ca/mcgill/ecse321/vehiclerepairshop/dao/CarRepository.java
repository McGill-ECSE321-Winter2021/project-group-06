package ca.mcgill.ecse321.vehiclerepairshop.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;

public interface CarRepository extends CrudRepository<Car, String>{

	Car findCarBylicensePlate(String licence);
}
