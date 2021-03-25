package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "CustomerAccount_data", path = "CustomerAccount_data")
public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, String>{
	List<CustomerAccount> findCustomerAccountByName(String name); //name is not unique
	CustomerAccount findByUsername(String username); 
	CustomerAccount findByToken(int token);
}