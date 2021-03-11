package vehiclerepairshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.CustomerAccountRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;

@Service
public class CustomerAccountService {

	@Autowired
	private CustomerAccountRepository customerAccountRepository;



	// --------------------------- Catherine starts here -------------------------


	/**
	 * Create a Customer Account with given parameters
	 * @param username
	 * @param password
	 * @param name
	 * @return the account created
	 */
	@Transactional
	public CustomerAccount createCustomerAccount(String username, String password, String name) {
		CustomerAccount user = new CustomerAccount();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		customerAccountRepository.save(user);
		return user;
	}


	/**
	 * Find customer account by username
	 * @param username
	 * @return the account
	 */
	@Transactional
	public CustomerAccount getCustomerAccountByUsername(String username) {
		CustomerAccount user = customerAccountRepository.findByUsername(username);
		return user;
	}

	/**
	 * Find customer accounts by name
	 * @param username
	 * @return a list of accounts
	 */
	@Transactional
	public List<CustomerAccount> getCustomerAccountsByName(String name) {
		List<CustomerAccount> users = customerAccountRepository.findCustomerAccountByName(name);
		return users;
	}

	/**
	 * Find all Customer Accounts
	 * @return List of all accounts
	 */
	@Transactional
	public List<CustomerAccount> getAllCustomerAccounts() {
		return toList(customerAccountRepository.findAll());
	}

	/**
	 * Find Customer Account by car
	 * @return Account linked to car
	 */
	@Transactional
	public CustomerAccount getCustomerAccountWithCar(Car car) {
		CustomerAccount user = customerAccountRepository.findByCar(car);
		return user;
	}



	/**
	 *  helper method that converts iterable to list
	 * @param <T>
	 * @param iterable
	 * @return
	 */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
