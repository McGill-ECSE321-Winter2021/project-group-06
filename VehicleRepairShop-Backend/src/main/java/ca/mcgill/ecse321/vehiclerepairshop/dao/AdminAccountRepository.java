package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.AdminAccount;

public interface AdminAccountRepository extends CrudRepository<AdminAccount, String>{
	List<AdminAccount> findAdminAccountByName(String name); //name is not unique
	AdminAccount findAdminAccountByUsername(String username); 
}
