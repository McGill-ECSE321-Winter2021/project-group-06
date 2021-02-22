package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.AdminAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.UserAccount;

public interface AdminAccountRepository extends CrudRepository<AdminAccount, Integer>{
	AdminAccount findByUserAccount (UserAccount username);
}
