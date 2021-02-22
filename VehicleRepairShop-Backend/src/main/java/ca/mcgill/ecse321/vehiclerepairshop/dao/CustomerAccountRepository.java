package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;

public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, Integer>{
	List<CustomerAccount> findByName(String name); //name is not unique
	CustomerAccount findByCar(Car carLicience);
	CustomerAccount findByUsername(String username); 
}