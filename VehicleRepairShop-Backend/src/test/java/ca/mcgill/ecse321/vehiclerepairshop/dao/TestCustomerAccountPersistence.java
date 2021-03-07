package ca.mcgill.ecse321.vehiclerepairshop.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;


@ExtendWith(SpringExtension.class)
@SpringBootTest

public class TestCustomerAccountPersistence {
	
	CustomerAccount user1;
	CustomerAccount user2;
	
	String name1;
	String username1;
	String password1;

	String name2;
	String username2;
	String password2;
	
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private CustomerAccountRepository customerAccountRepository;
		
	@BeforeEach
	public void buildDatabase() {
		this.name1 = "First";
		this.username1 = "username1";
		this.password1 = "password123";
	
		this.name2 = "Second";
		this.username2 = "username2";
		this.password2 = "security123";
		
		this.user1 = new CustomerAccount();
		this.user2 = new CustomerAccount();
		
		this.user1.setName(name1);
		this.user1.setUsername(username1);
		this.user1.setPassword(password1);
		
		this.user2.setName(this.name2);
		this.user2.setUsername(this.username2);
		this.user2.setPassword(this.password2);
		
		customerAccountRepository.save(this.user1);
		customerAccountRepository.save(this.user2);
	}
	
	@AfterEach
	public void clearDatabase() {
		customerAccountRepository.deleteAll();
		carRepository.deleteAll();	
	}

	/**
	 * Tests finding a customer account by a unique username
	 */
	@Test
	public void testPersistAndLoadCustomerAccount() {
		
		user1 = null;
		
		user1 = customerAccountRepository.findByUsername(username1);
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);
	
	}
	
	/**
	 * Tests finding customer accounts by name when there are many accounts with the same name
	 */
	@Test
	public void testPersistAndLoadCustomerAccountByName() {

		user2.setName(name1);
		
		customerAccountRepository.save(user2);

		user1 = null;
		user2 = null;
		List<CustomerAccount> users = new ArrayList<CustomerAccount>();
		
		users = customerAccountRepository.findCustomerAccountByName(name1);
		System.out.println(users);
		if (users.get(0).getUsername().equals(username1)) {
			user1 = users.get(0); 
			user2 = users.get(1);
		}
		else if (users.get(0).getUsername().equals(username2)) {
			user2 = users.get(0); 
			user1 = users.get(1);
		}
		else {
			assertEquals(0,1); //always fails
		}
		
		
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);
		
		assertNotNull(user2);
		assertEquals(user2.getName(), name1);
		assertEquals(user2.getPassword(), password2);
		assertEquals(user2.getUsername(), username2);
		
	}
	
	/**
	 * Tests a finding customer account by car
	 */
	@Test
	public void testPersistAndLoadCustomerAccountByCar() {
		// create a car
		String licensePlate = "3JOH22A";
	
		Car car = new Car();
		car.setLicensePlate(licensePlate);
		car.setOwner(user1);
		
		carRepository.save(car);
		List<Car> cars = new ArrayList<Car>();
		cars.add(car);
		user1.setCar(cars);
		customerAccountRepository.save(user1);
		
		user1 = null;
		
		user1 = customerAccountRepository.findByCar(car);
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);
		
//		car.setOwner(null);
//		carRepository.save(car);
		
//		carRepository.delete(car);
		
		
	}
	

}
