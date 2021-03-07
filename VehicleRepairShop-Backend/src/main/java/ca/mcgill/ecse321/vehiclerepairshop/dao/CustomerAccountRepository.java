package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;

public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, String>{
	List<CustomerAccount> findCustomerAccountByName(String name); //name is not unique
	CustomerAccount findByCar(Car carLicense);
	CustomerAccount findByUsername(String username); 
}