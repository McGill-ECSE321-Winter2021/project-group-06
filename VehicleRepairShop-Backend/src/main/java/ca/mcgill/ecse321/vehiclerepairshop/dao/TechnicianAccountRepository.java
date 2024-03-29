package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "TechnicianAccount_data", path = "TechnicianAccount_data")
public interface TechnicianAccountRepository extends CrudRepository<TechnicianAccount, String>{
	List<TechnicianAccount> findTechnicianAccountByName(String name); //name is not unique
	TechnicianAccount findByUsername(String username); 
	TechnicianAccount findByToken(int token);
}