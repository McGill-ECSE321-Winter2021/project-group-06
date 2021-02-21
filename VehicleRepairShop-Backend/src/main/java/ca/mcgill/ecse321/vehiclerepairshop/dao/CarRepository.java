package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Customer;

public interface CarRepository extends CrudRepository<Car, String>{
	List<Car> findCarByCustomer(Customer customer);
	Car findCarByLicensePlate(String licence);
}
