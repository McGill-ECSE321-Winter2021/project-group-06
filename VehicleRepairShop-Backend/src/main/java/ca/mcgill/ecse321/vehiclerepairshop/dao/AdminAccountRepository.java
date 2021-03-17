package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.AdminAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "AdminAccount_data", path = "AdminAccount_data")
public interface AdminAccountRepository extends CrudRepository<AdminAccount, String>{
	List<AdminAccount> findAdminAccountByName(String name); 
	AdminAccount findByUsername(String username); 
	List<AdminAccount> findByBusinessInformation(BusinessInformation businessInformation);
	AdminAccount findByToken(int token);
}
