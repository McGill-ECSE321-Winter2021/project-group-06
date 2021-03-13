package vehiclerepairshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AdminAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.CustomerAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.TechnicianAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.AdminAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;

@Service
public class AuthenticationService {
	
	@Autowired
	private AdminAccountRepository adminAccountRepository;
	@Autowired
	private CustomerAccountRepository customerAccountRepository;
	@Autowired
	private TechnicianAccountRepository technicianAccountRepository;

	/**
	 * Generates a token, sets it for the account, and saves it to the account database
	 * Account must exist in database first
	 * Returns 0 if not successful
	 * @param username
	 * @return token
	 */
	public int createToken(String username) {

		int token = 0;
		
		if (adminAccountRepository.findByUsername(username) != null) {
			AdminAccount user = adminAccountRepository.findByUsername(username);
			user.setToken(user.getUsername().hashCode());
			adminAccountRepository.save(user);
			return token;
		}
		else if (customerAccountRepository.findByUsername(username) != null) {
			CustomerAccount user = customerAccountRepository.findByUsername(username);
			user.setToken(user.getUsername().hashCode());
			customerAccountRepository.save(user);
			return token;
		}
		else if (technicianAccountRepository.findByUsername(username) != null) {
			TechnicianAccount user = technicianAccountRepository.findByUsername(username);
			user.setToken(user.getUsername().hashCode());
			technicianAccountRepository.save(user);
			return token;
		}
		else return token;
	}
	/**
	 * Authenticates token for the account
	 * @param token
	 * @param username
	 * @return boolean for authenticity
	 */
	public boolean authenticateToken(String username){
		boolean isAuthentic = false;
		if (adminAccountRepository.findByUsername(username) != null) {
			AdminAccount user = adminAccountRepository.findByUsername(username);
			if (user.getToken() != 0) {
				isAuthentic = true;
			}
		}
		else if (customerAccountRepository.findByUsername(username) != null) {
			CustomerAccount user = customerAccountRepository.findByUsername(username);
			if (user.getToken() != 0) {
				isAuthentic = true;
			}
		}
		else if (technicianAccountRepository.findByUsername(username) != null) {
			TechnicianAccount user = technicianAccountRepository.findByUsername(username);
			if (user.getToken() != 0) {
				isAuthentic = true;
			}
		}
		return isAuthentic;
	}
	/**
	 * Checks if username and password match
	 * @param username
	 * @param password
	 * @return boolean for authenticity
	 */
	public boolean authenticateCredentials(String username, String password){
		boolean isAuthentic = false;
		if (adminAccountRepository.findByUsername(username) != null) {
			AdminAccount user = adminAccountRepository.findByUsername(username);
			if (user.getPassword() == password) {
				isAuthentic = true;
			}
		}
		else if (customerAccountRepository.findByUsername(username) != null) {
			CustomerAccount user = customerAccountRepository.findByUsername(username);
			if (user.getPassword() == password) {
				isAuthentic = true;
			}
		}
		else if (technicianAccountRepository.findByUsername(username) != null) {
			TechnicianAccount user = technicianAccountRepository.findByUsername(username);
			if (user.getPassword() == password) {
				isAuthentic = true;
			}
		}
		return isAuthentic;
	}

	/**
	 * If credentials match, generates a token for the account
	 * @param username
	 * @param password
	 * @return boolean for success
	 */
	public boolean login(String username, String password){
		boolean successful = authenticateCredentials(username, password);
		if (successful == true) {
			createToken(username);
		}
		return successful;
	}


	/**
	 * Deletes the token for the account
	 * @param username
	 * @return boolean for success
	 */
	public boolean logout(String username){
		boolean successful = false;
		if (adminAccountRepository.findByUsername(username) != null) {
			AdminAccount user = adminAccountRepository.findByUsername(username);
			user.setToken(0);
			adminAccountRepository.save(user);
			successful = true;
		}
		else if (customerAccountRepository.findByUsername(username) != null) {
			CustomerAccount user = customerAccountRepository.findByUsername(username);
			user.setToken(0);
			customerAccountRepository.save(user);
			successful = true;
		}
		else if (technicianAccountRepository.findByUsername(username) != null) {
			TechnicianAccount user = technicianAccountRepository.findByUsername(username);
			user.setToken(0);
			technicianAccountRepository.save(user);
			successful = true;
		}
		return successful;
	}



}
