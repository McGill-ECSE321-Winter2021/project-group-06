package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.UserAccount;

public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, Integer>{
	List<CustomerAccount> findByCar(Car carLicience);
	CustomerAccount findByUserAccount(UserAccount username);
}