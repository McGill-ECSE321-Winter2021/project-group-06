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

import ca.mcgill.ecse321.vehiclerepairshop.model.AdminAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class TestAdminAccountPersistence {
	AdminAccount user1;
	AdminAccount user2;
	BusinessInformation business;

	String name1;
	String username1;
	String password1;

	String name2;
	String username2;
	String password2;

	String businessName;
	String businessAddress;
	String businessPhone;
	String businessEmail;


	@Autowired
	private AdminAccountRepository adminAccountRepository;
	@Autowired
	private BusinessInformationRepository businessInformationRepository;

	@BeforeEach
	public void buildDatabase() {
		this.name1 = "First";
		this.username1 = "admin1";
		this.password1 = "password123";

		this.name2 = "Second";
		this.username2 = "admin2";
		this.password2 = "security123";

		this.businessName = "Business";
		this.businessAddress = "address";
		this.businessPhone = "0123456789";
		this.businessEmail = "business@mail.com";

		this.user1 = new AdminAccount();
		this.user2 = new AdminAccount();
		this.business = new BusinessInformation();

		this.user1.setName(name1);
		this.user1.setUsername(username1);
		this.user1.setPassword(password1);

		this.user2.setName(this.name2);
		this.user2.setUsername(this.username2);
		this.user2.setPassword(this.password2);

		this.business.setName(businessName);
		this.business.setAddress(businessAddress);
		this.business.setPhoneNumber(businessPhone);
		this.business.setEmailAddress(businessEmail);

		businessInformationRepository.save(this.business);

		adminAccountRepository.save(this.user1);
		adminAccountRepository.save(this.user2);

	}

	@AfterEach
	public void clearDatabase() {
		adminAccountRepository.deleteAll();
		businessInformationRepository.deleteAll();
	}


	/**
	 * Tests finding an admin account by the unique username
	 */
	@Test
	public void testPersistAndLoadAdminAccount() {

		user1 = null;

		user1 = adminAccountRepository.findByUsername(username1);
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);

	}

	/**
	 * Tests finding admin accounts by name when there are many accounts with the same name
	 */
	@Test
	public void testPersistAndLoadAdminAccountByName() {

		user2.setName(name1);

		adminAccountRepository.save(user2);

		user1 = null;
		user2 = null;
		List<AdminAccount> users = new ArrayList<AdminAccount>();

		users = adminAccountRepository.findAdminAccountByName(name1);
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
	 * Tests finding admin accounts via searching business information
	 */
	@Test
	public void testPersistAndLoadAdminAccountViaBusinessInformation() {
		user1.setBusinessInformation(business);
		user2.setBusinessInformation(business);
		adminAccountRepository.save(user1);
		adminAccountRepository.save(user2);

		user1 = null;
		user2 = null;
		List<AdminAccount> users = new ArrayList<AdminAccount>();
		Boolean isExist;

		users = adminAccountRepository.findByBusinessInformation(business);


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
		assertEquals(user2.getName(), name2);
		assertEquals(user2.getPassword(), password2);
		assertEquals(user2.getUsername(), username2);
	}

}
