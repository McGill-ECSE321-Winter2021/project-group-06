package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.UserAccount;


public interface UserAccountRepository extends CrudRepository<UserAccount, Integer>{
	List<UserAccount> findUserAccountByName(String name); //name is not unique
	UserAccount findUserAccountByUsername(String username); 
	
	
}
