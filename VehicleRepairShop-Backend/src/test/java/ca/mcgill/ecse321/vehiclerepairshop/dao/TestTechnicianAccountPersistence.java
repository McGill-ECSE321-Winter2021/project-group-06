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

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;


@ExtendWith(SpringExtension.class)
@SpringBootTest

public class TestTechnicianAccountPersistence {
	TechnicianAccount user1;
	TechnicianAccount user2;
	
	String name1;
	String username1;
	String password1;

	String name2;
	String username2;
	String password2;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private TechnicianAccountRepository technicianAccountRepository;
		
	@BeforeEach
	public void buildDatabase() {
		this.name1 = "First";
		this.username1 = "username1";
		this.password1 = "password123";
	
		this.name2 = "Second";
		this.username2 = "username2";
		this.password2 = "security123";
		
		this.user1 = new TechnicianAccount();
		this.user2 = new TechnicianAccount();
		
		this.user1.setName(name1);
		this.user1.setUsername(username1);
		this.user1.setPassword(password1);
		
		this.user2.setName(this.name2);
		this.user2.setUsername(this.username2);
		this.user2.setPassword(this.password2);
		
		technicianAccountRepository.save(this.user1);
		technicianAccountRepository.save(this.user2);
	}
	
	@AfterEach
	public void clearDatabase() {
		technicianAccountRepository.deleteAll();
		appointmentRepository.deleteAll();	
	}

	/**
	 * Tests finding a technician account by a unique username
	 */
	@Test
	public void testPersistAndLoadTechnicianAccount() {
		
		user1 = null;
		
		user1 = technicianAccountRepository.findByUsername(username1);
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);
	
	}
	
	/**
	 * Tests finding technician accounts by name when there are many accounts with the same name
	 */
	@Test
	public void testPersistAndLoadTechnicianAccountByName() {

		user2.setName(name1);
		
		technicianAccountRepository.save(user2);

		user1 = null;
		user2 = null;
		List<TechnicianAccount> users = new ArrayList<TechnicianAccount>();
		
		users = technicianAccountRepository.findTechnicianAccountByName(name1);
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
	 * Tests a finding technician account by appointment
	 */
	@Test
	public void testPersistAndLoadTechnicianAccountByAppointment() {
		// create an appointment
		int appointmentID = 1;
		
		Appointment apt = new Appointment();
		apt.setAppointmentId(appointmentID);
		appointmentRepository.save(apt);
		
		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(apt);
		user1.setAppointment(appointments);
		technicianAccountRepository.save(user1);
		
		user1 = null;
		List<TechnicianAccount> users = new ArrayList<TechnicianAccount>();
		
		users = technicianAccountRepository.findTechnicianAccountByAppointment(apt);
		System.out.println(users);
		if (users.get(0).getUsername().equals(username1)) {
			user1 = users.get(0); 
		}
		else {
			assertEquals(0,1); //always fails
		}
		
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);

	}
}