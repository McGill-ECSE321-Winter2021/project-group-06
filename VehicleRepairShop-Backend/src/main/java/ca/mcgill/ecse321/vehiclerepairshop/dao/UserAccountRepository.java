package ca.mcgill.ecse321.vehiclerepairshop.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.UserAccount;

public interface UserAccountRepository  extends CrudRepository<UserAccount, String>{
	CustomerAccount findByUsername(String username); 
}
