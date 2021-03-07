package ca.mcgill.ecse321.vehiclerepairshop.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
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
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car.MotorType;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.Service;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.AdminAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;


@ExtendWith(SpringExtension.class)
@SpringBootTest

public class TestTechnicianAccountPersistence {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private CustomerAccountRepository customerAccountRepository;
	@Autowired
	private TechnicianAccountRepository technicianAccountRepository;
	@Autowired
	private AdminAccountRepository adminAccountRepository;
	@Autowired
	private GarageRepository garageRepository;
	@Autowired
	private TimeSlotRepository timeslotRepository;
	@Autowired
	private ServiceRepository serviceRepository;
		
	@BeforeEach
	public void buildDatabase() {
		
	}
	
	@AfterEach
	public void clearDatabase() {
		
	}

	

	// ********************* Catherine & Aurelia start here **************/
	
	
	
	/**
	 * Tests finding an admin account by the unique username
	 */
	@Test
	public void testPersistAndLoadAdminAccount() {
		String name1 = "Wanda Maximoff";
		String username1 = "username1123";
		String password1 = "password1323";
		
		AdminAccount user1 = new AdminAccount();
		user1.setName(name1);
		user1.setUsername(username1);
		user1.setPassword(password1);
		
		adminAccountRepository.save(user1);

		user1 = null;
		
		user1 = adminAccountRepository.findAdminAccountByUsername(username1);
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);
		
		adminAccountRepository.delete(user1);
	
	}
	
	/**
	 * Tests finding admin accounts by name when there are many accounts with the same name
	 */
	@Test
	public void testPersistAndLoadAdminAccountByName() {
		String name1 = "Paul";
		String username1 = "username10";
		String password1 = "password123";
		
		String name2 = "Paul";
		String username2 = "username2";
		String password2 = "321password";
		
		AdminAccount user1 = new AdminAccount();
		AdminAccount user2 = new AdminAccount();
		
		user1.setName(name1);
		user1.setUsername(username1);
		user1.setPassword(password1);
		
		user2.setName(name2);
		user2.setUsername(username2);
		user2.setPassword(password2);
		
		adminAccountRepository.save(user1);
		adminAccountRepository.save(user2);

		
		user1 = null;
		user2 = null;
		List<AdminAccount> users;
		
		users = adminAccountRepository.findAdminAccountByName(name1);
		user1 = users.get(0); 
		user2 = users.get(1);
		
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);
		
		assertNotNull(user2);
		assertEquals(user2.getName(), name1);
		assertEquals(user2.getPassword(), password2);
		assertEquals(user2.getUsername(), username2);
		
		adminAccountRepository.delete(user2);
		adminAccountRepository.delete(user1);
		
	}
	/**
	 * Tests finding a customer account by a unique username
	 */
	@Test
	public void testPersistAndLoadCustomerAccount() {
		String name1 = "Steve McQueen";
		String username1 = "username12";
		String password1 = "password123";
		
		CustomerAccount user1 = new CustomerAccount();
		user1.setName(name1);
		user1.setUsername(username1);
		user1.setPassword(password1);
		
		customerAccountRepository.save(user1);

		user1 = null;
		
		user1 = customerAccountRepository.findByUsername(username1);
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);
		
		customerAccountRepository.delete(user1);
		
	
	}
	/**
	 * Tests finding customer accounts by name when there are many accounts with the same name
	 */
	@Test
	public void testPersistAndLoadCustomerAccountByName() {
		String name1 = "Steve";
		String username1 = "username1";
		String password1 = "password123";
	
		String username2 = "username2";
		String password2 = "321password";
		
		CustomerAccount user1 = new CustomerAccount();
		CustomerAccount user2 = new CustomerAccount();
		
		user1.setName(name1);
		user1.setUsername(username1);
		user1.setPassword(password1);
		
		user2.setName(name1);
		user2.setUsername(username2);
		user2.setPassword(password2);
		
		customerAccountRepository.save(user1);
		customerAccountRepository.save(user2);

		
		user1 = null;
		user2 = null;
		List<CustomerAccount> users;
		
		users = customerAccountRepository.findCustomerAccountByName(name1);
		user1 = users.get(0); 
		user2 = users.get(1);
		
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);
		
		assertNotNull(user2);
		assertEquals(user2.getName(), name1);
		assertEquals(user2.getPassword(), password2);
		assertEquals(user2.getUsername(), username2);
		
		customerAccountRepository.delete(user2);
		customerAccountRepository.delete(user1);
		
	}
	
	/**
	 * Tests finding customer accounts by car
	 */
	@Test
	public void testPersistAndLoadCustomerAccountByCar() {
		String name1 = "Aston Martin";
		String username1 = "username1";
		String password1 = "password123";
		
		String licensePlate = "3JOH22A";
		String model = "Ferrari";
		Integer year = 1998;
		MotorType motorType = MotorType.Hybrid;
		
		CustomerAccount user1 = new CustomerAccount();
		Car car = new Car();
		
		user1.setName(name1);
		user1.setUsername(username1);
		user1.setPassword(password1);
		
		car.setLicensePlate(licensePlate);
		
		
		
		customerAccountRepository.save(user1);
		car.setOwner(user1);
		carRepository.save(car);
		
		
		user1 = null;
		
		user1 = customerAccountRepository.findByCar(car);
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);
		
		carRepository.delete(car);
		customerAccountRepository.delete(user1);
		
		
		
	}
	
	/**
	 * Tests finding a technician account by a unique username
	 */
	@Test
	public void testPersistAndLoadTechnicianAccount() {
		String name1 = "Tony Stark";
		String username1 = "username1";
		String password1 = "password123";
		
		TechnicianAccount user1 = new TechnicianAccount();
		
		user1.setName(name1);
		user1.setUsername(username1);
		user1.setPassword(password1);
		
		technicianAccountRepository.save(user1);

		user1 = null;
		
		user1 = technicianAccountRepository.findTechnicanAccountByUsername(username1);
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);
		
		technicianAccountRepository.delete(user1);
	
	}
	/**
	 * Tests finding technician accounts by name when there are more than one account with the same name
	 */
	@Test
	public void testPersistAndLoadTechnicianAccountByName() {
		String name1 = "Jarvis";
		String username1 = "username1";
		String password1 = "password123";
	
		String username2 = "username2";
		String password2 = "321password";
		
		TechnicianAccount user1 = new TechnicianAccount();
		TechnicianAccount user2 = new TechnicianAccount();
		
		user1.setName(name1);
		user1.setPassword(password1);
		user1.setUsername(username1);
		
		user2.setName(name1);
		user2.setPassword(password2);
		user2.setUsername(username2);
		
		technicianAccountRepository.save(user1);
		technicianAccountRepository.save(user2);
		
		user1 = null;
		user2 = null;
		List<TechnicianAccount> users;
		
		users = technicianAccountRepository.findTechnicianAccountByName(name1);
		user1 = users.get(0); 
		user2 = users.get(1);
		
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);
		
		assertNotNull(user2);
		assertEquals(user2.getName(), name1);
		assertEquals(user2.getPassword(), password2);
		assertEquals(user2.getUsername(), username2);
		
		technicianAccountRepository.delete(user2);
		technicianAccountRepository.delete(user1);

		
	}
	/**
	 * Tests finding technician accounts by an appointment
	 */
	@Test
	public void testPersistAndLoadTechnicianAccountByAppointment() {
		String name1 = "Jarvis";
		String username1 = "username1";
		String password1 = "password123";
		
		String name2 = "Stark";
		String username2 = "username2";
		String password2 = "321password";
		
//		String licensePlate = "TestCar";
//		String model = "TestModel";
//		Integer year = 2021;
//		MotorType engine = MotorType.Gas;
//		
//		String customerName = "customer";
//		String customerPassword = "123";
//		String customerID = "customer1";
//		
//		String timeSlotId = "timeSlot1";
//		Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 20));
//		Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
//		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
//		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
//		
//		String serviceId = "service1";
//		String price = "50";
//		String serviceName = "service";
//		String duration = "18hrs";
//		Time reminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
//		Date reminderDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.FEBRUARY, 21));
//		String description = "this is a test service";
//		
//		boolean available = true;
//		String gID = "1";
//		
//		String appointment1ID = "appointment1";
//		String appointment1Comment = "this is a test Appointment";	
//		
//		TimeSlot timeSlot = new TimeSlot();
//		CustomerAccount customer = new CustomerAccount();
//		Service service = new Service();
//		Car car = new Car();
//		Garage garage = new Garage();
//	
//		TechnicianAccount user1 = new TechnicianAccount();
//		TechnicianAccount user2 = new TechnicianAccount();
//		
//		Appointment appointment1 = new Appointment();
//		
//		user1.setName(name1);
//		user1.setUsername(username1);
//		user1.setPassword(password1);
//		
//		user2.setName(name2);
//		user2.setUsername(username2);
//		user2.setPassword(password2);
//		
//		appointment1.setCar(car);
//		
//		List<TechnicianAccount> workers = new ArrayList<TechnicianAccount>();
//		workers.add(user1);
//		workers.add(user2);
//	
//		appointment1.setWorker(workers);
//		makes a garage
		boolean isAvailable = true;
		String garageID = "testGarageID";
		
		Garage garageToTest = new Garage();
		garageToTest.setGarageId(garageID);
		garageToTest.setIsAvailable(isAvailable);
		
		
		
		//makes an appointment
		String licensePlate = "TestCar";
		String model = "TestModel";
		Integer year = 2021;
		MotorType engine = MotorType.Gas;
		
		String customerName = "customer";
		String customerPassword = "123";
		String customerID = "customer1";
		
		//String timeSlotId = "timeSlot1";
		Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 20));
		Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
		
		String serviceId = "service1";
		String price = "50";
		String serviceName = "service";
		String duration = "18hrs";
		Time reminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		Date reminderDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.FEBRUARY, 21));
		String description = "this is a test service";
		
		String techName = "techName1";
		String passWord = "123";
		String techID = "techID1";
		
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		
		CustomerAccount customer = new CustomerAccount();
		customer.setName(customerName);
		customer.setPassword(customerPassword);
		customer.setUsername(customerName);
		
		Service service = new Service();
		service.setName(serviceName);
		service.setPrice(price);
		service.setDuration(duration);
		service.setReminderDate(reminderDate);
		service.setReminderTime(reminderTime);
		service.setDescription(description);
		
		Car car = new Car();
		car.setLicensePlate(licensePlate);
		car.setModel(model);
		car.setYear(year);
		car.setMotorType(engine);
		car.setOwner(customer);
		
		TechnicianAccount user1 = new TechnicianAccount();
		user1.setName(name1);
		user1.setPassword(password1);
		user1.setUsername(username1);
		TechnicianAccount user2 = new TechnicianAccount();
		user2.setName(name2);
		user2.setPassword(password2);
		user2.setUsername(username2);
		
		String appointment1ID = "appointment1";
		String appointment1Comment = "this is a test Appointment";
		Appointment appointment1 = new Appointment();
		appointment1.setAppointmentId(appointment1ID);
		appointment1.setComment(appointment1Comment);
		appointment1.setCar(car);
		appointment1.setGarage(garageToTest);
		appointment1.setService(service);
		appointment1.setService(service);
		appointment1.setTimeSlot(timeSlot);
		List<TechnicianAccount> workers = new ArrayList<TechnicianAccount>();
		workers.add(user1);
		workers.add(user2);
		appointment1.setWorker(workers);
		
		technicianAccountRepository.save(user1);
		technicianAccountRepository.save(user2);
		customerAccountRepository.save(customer);
		carRepository.save(car);
		serviceRepository.save(service);
		timeslotRepository.save(timeSlot);
		garageRepository.save(garageToTest);
		appointmentRepository.save(appointment1);
		
		user1 = null;
		user2 = null;
		List<TechnicianAccount> users;
		
		users = technicianAccountRepository.findByAppointment(appointment1); 
		user1 = users.get(0); 
		user2 = users.get(1);
		
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);
		
		assertNotNull(user2);
		assertEquals(user2.getName(), name2);
		assertEquals(user2.getPassword(), password2);
		assertEquals(user2.getUsername(), username2);
		

		appointmentRepository.delete(appointment1);
		timeslotRepository.delete(timeSlot);
		serviceRepository.delete(service);
		technicianAccountRepository.delete(user1);
		technicianAccountRepository.delete(user2);
		carRepository.delete(car);
		customerAccountRepository.delete(customer);
		garageRepository.delete(garageToTest);
		
		
	}
//	
//	
//	
//	// ******************** Catherine & Aurelia end here ****************/

}
