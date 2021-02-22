package ca.mcgill.ecse321.vehiclerepairshop.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car.MotorType;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.model.UserAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.Service;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.AdminAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;


@ExtendWith(SpringExtension.class)
@SpringBootTest

public class TestVehicleAppointmentPersistence {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private BusinessInformationRepository businessInformationRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private CustomerAccountRepository customerAccountRepository;
	@Autowired
	private TechnicianAccountRepository technicianAccountRepository;
	@Autowired
	private AdminAccountRepository adminAccountRepository;
	
	@AfterEach
	public void clearDatabase() {
		
		carRepository.deleteAll();
		businessInformationRepository.deleteAll();
		appointmentRepository.deleteAll();
		customerAccountRepository.deleteAll();
		technicianAccountRepository.deleteAll();
		adminAccountRepository.deleteAll();
		
	}
	
	
// ************************* Mike start here **************************
	/*
	 * @author: Cheng
	 */
	@Test
	public void testPersistAndLoadAppopintmentViaSearchCar() {
		MotorType engine = MotorType.Gas;
		String licensePlate = "TestCar";
		String model = "TestModel";
		Integer year = 2021;
		Car car = new Car();
		car.setLicensePlate(licensePlate);
		car.setMotorType(engine);
		car.setModel(model);
		car.setYear(year);

		String appointment1ID = "appointment1";
		String appointment1Comment = "this is a test Appointment";
		Appointment appointment1 = new Appointment();
		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(appointment1);
		appointment1.setAppointmentId(appointment1ID);
		appointment1.setComment(appointment1Comment);
		car.setAppointment(appointments);

		appointmentRepository.save(appointment1);
		carRepository.save(car);

		appointment1 = null;

		appointments = appointmentRepository.findByCar(car);
		appointment1 = appointments.get(0);
		assertNotNull(appointment1);
		assertEquals(appointment1ID, appointment1.getAppointmentId());
		assertEquals(appointment1Comment, appointment1.getComment());
		assertEquals(licensePlate, appointment1.getCar().getLicensePlate());

		
		
	}
	
	@Test
	public void testPersistAndLoadBusinessInformation(){
		String businessName = "testingName";
		String businessAddress = "123road";
		String businessPhoneNumber = "1234567";
		String businessEmail = "email@email.com";
		
		BusinessInformation businessInfo = new BusinessInformation(businessName, businessAddress,businessPhoneNumber, businessEmail);
		businessInformationRepository.save(businessInfo);

		businessInfo = null;

		businessInfo = businessInformationRepository.findBusinessInformationByName(businessName);
		assertNotNull(businessInfo);
		assertEquals(businessAddress, businessInfo.getAddress());
		assertEquals(businessPhoneNumber, businessInfo.getPhoneNumber());
		assertEquals(businessEmail, businessInfo.getEmailAddress());
	}
	
	
	
	@Test
	public void testPersistAndLoadCar() {
		String licensePlate = "TestCar";
		String model = "TestModel";
		Integer year = 2021;
		String customerName = "customer";
		String customerPassword = "123";
		String customerID = "customer1";
		CustomerAccount customer = new CustomerAccount(customerName, customerPassword, customerID);
		MotorType engine = MotorType.Gas;
		
		Car car = new Car(licensePlate,model,year,engine,customer);
		car.setLicensePlate(licensePlate);
		car.setModel(model);
		car.setYear(year);
		car.setMotorType(engine);
		carRepository.save(car);

		car = null;

		car = carRepository.findCarByLicensePlate(licensePlate);
		assertNotNull(car);
		assertEquals(licensePlate, car.getLicensePlate());
		assertEquals(model, car.getModel());
		assertEquals(year, car.getYear());
		assertEquals(engine, car.getMotorType());
	}
	
	
	
	@Test
	public void testPersistAndLoadCarViaSearchCustomer() {
		String licensePlate = "TestCar";
		String model = "TestModel";
		Integer year = 2021;
		MotorType engine = MotorType.Gas;
		String licensePlate2 = "TestCar2";
		String model2 = "TestModel2";
		Integer year2 = 2020;
		String customerName = "customer";
		String customerPassword = "123";
		String customerID = "customer1";
		CustomerAccount customer = new CustomerAccount(customerName, customerPassword, customerID);
		
		
		Car car = new Car(licensePlate,model,year,engine,customer);
		carRepository.save(car);
		Car car2 = new Car(licensePlate2, model2, year2, engine, customer);
		carRepository.save(car2);
		List<Car> cars = null;
		car = null; 
		car2 = null;

		cars = carRepository.findCarByCustomerAccount(customer);
		assertNotNull(cars);
//		Iterator iterator = cars.iterator();
//		int i=0;
//		while(iterator.hasNext()) {
//			i++;
//			if 
//			
//		}
		car = cars.get(0);
		car2 = cars.get(1);
		assertEquals(licensePlate, car.getLicensePlate());
		assertEquals(model, car.getModel());
		assertEquals(year, car.getYear());
		assertEquals(engine, car.getMotorType());
		
		assertEquals(licensePlate2, car2.getLicensePlate());
		assertEquals(model2, car2.getModel());
		assertEquals(year2, car2.getYear());
		assertEquals(engine, car2.getMotorType());
	}
	
	
	
	
	
	@Test
	public void testPersistAndLoadAppointment() {
		String licensePlate = "TestCar";
		String model = "TestModel";
		Integer year = 2021;
		MotorType engine = MotorType.Gas;
		
		String customerName = "customer";
		String customerPassword = "123";
		String customerID = "customer1";
		
		String timeSlotId = "timeSlot1";
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
		
		boolean available = true;
		String gID = "1";
		
		String techName = "techName1";
		String passWord = "123";
		String techID = "techID1";
		
		
		TimeSlot timeSlot = new TimeSlot(timeSlotId, startTime, endTime, startDate, endDate);
		CustomerAccount customer = new CustomerAccount(customerName, customerPassword, customerID);
		Service service = new Service(serviceId, price, serviceName, duration, reminderTime, reminderDate, description);
		Car car = new Car(licensePlate,model,year,engine,customer);
		Garage garage = new Garage(available, gID);
		TechnicianAccount technician = new TechnicianAccount(techName,passWord,techID);
		
		String appointment1ID = "appointment1";
		String appointment1Comment = "this is a test Appointment";
		Appointment appointment1 = new Appointment(appointment1ID, appointment1Comment,car,garage,service,timeSlot,technician);
		

		appointmentRepository.save(appointment1);

		appointment1 = null;

		appointment1 = appointmentRepository.findByCarAndTimeSlot(car, timeSlot);
		assertNotNull(appointment1);
		assertEquals(appointment1ID, appointment1.getAppointmentId());
		assertEquals(appointment1Comment, appointment1.getComment());
		assertEquals(licensePlate, appointment1.getCar().getLicensePlate());
		assertEquals(techID, appointment1.getWorker(0).getUsername());
		assertEquals(serviceName, appointment1.getService().getName());
		assertEquals(gID, appointment1.getGarage().getGarageId());

	}
	
	
	// ************************* Mike end here **************************/
	
	// ********************* Catherine & Aurelia start here **************/
	/**
	 * Tests finding a admin account by the unique username
	 */
	@Test
	public void testPersistAndLoadAdminAccount() {
		String name1 = "Wanda Maximoff";
		String username1 = "username1";
		String password1 = "password123";
		
		AdminAccount user1 = new AdminAccount(name1, password1, username1);
		
		adminAccountRepository.save(user1);

		user1 = null;
		
		user1 = adminAccountRepository.findAdminAccountByUsername(username1);
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
		String name1 = "Wanda";
		String username1 = "username1";
		String password1 = "password123";
	
		String username2 = "username2";
		String password2 = "321password";
		
		AdminAccount user1 = new AdminAccount(name1, password1, username1);
		AdminAccount user2 = new AdminAccount(name1, password2, username2);
		
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
		
	}
	/**
	 * Tests finding a customer account by a unique username
	 */
	@Test
	public void testPersistAndLoadCustomerAccount() {
		String name1 = "Steve McQueen";
		String username1 = "username1";
		String password1 = "password123";
		
		CustomerAccount user1 = new CustomerAccount(name1, password1, username1);
		
		customerAccountRepository.save(user1);

		user1 = null;
		
		user1 = customerAccountRepository.findCustomerAccountByUsername(username1);
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
		String name1 = "Steve";
		String username1 = "username1";
		String password1 = "password123";
	
		String username2 = "username2";
		String password2 = "321password";
		
		CustomerAccount user1 = new CustomerAccount(name1, password1, username1);
		CustomerAccount user2 = new CustomerAccount(name1, password2, username2);
		
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
		
		CustomerAccount user1 = new CustomerAccount(name1, password1, username1);
		Car car = new Car(licensePlate, model, year, motorType, user1);
		
		customerAccountRepository.save(user1);

		user1 = null;
		
		user1 = customerAccountRepository.findByCar(car);
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);
		
	}
	
	/**
	 * Tests finding a technician account by a unique username
	 */
	@Test
	public void testPersistAndLoadTechnicianAccount() {
		String name1 = "Tony Stark";
		String username1 = "username1";
		String password1 = "password123";
		
		TechnicianAccount user1 = new TechnicianAccount(name1, password1, username1);
		
		technicianAccountRepository.save(user1);

		user1 = null;
		
		user1 = technicianAccountRepository.findTechnicanAccountByUsername(username1);
		assertNotNull(user1);
		assertEquals(user1.getName(), name1);
		assertEquals(user1.getPassword(), password1);
		assertEquals(user1.getUsername(), username1);
	
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
		
		TechnicianAccount user1 = new TechnicianAccount(name1, password1, username1);
		TechnicianAccount user2 = new TechnicianAccount(name1, password2, username2);
		
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
		
		String licensePlate = "TestCar";
		String model = "TestModel";
		Integer year = 2021;
		MotorType engine = MotorType.Gas;
		
		String customerName = "customer";
		String customerPassword = "123";
		String customerID = "customer1";
		
		String timeSlotId = "timeSlot1";
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
		
		boolean available = true;
		String gID = "1";
		
		TimeSlot timeSlot = new TimeSlot(timeSlotId, startTime, endTime, startDate, endDate);
		CustomerAccount customer = new CustomerAccount(customerName, customerPassword, customerID);
		Service service = new Service(serviceId, price, serviceName, duration, reminderTime, reminderDate, description);
		Car car = new Car(licensePlate,model,year,engine,customer);
		Garage garage = new Garage(available, gID);
		
		String appointment1ID = "appointment1";
		String appointment1Comment = "this is a test Appointment";
		
		TechnicianAccount user1 = new TechnicianAccount(name1, password1, username1);
		TechnicianAccount user2 = new TechnicianAccount(name2, password2, username2);
		
		TechnicianAccount[] workers = {user1, user2};
		
		Appointment appointment1 = new Appointment(appointment1ID, appointment1Comment,car,garage,service,timeSlot, workers);
		
		
		technicianAccountRepository.save(user1);
		technicianAccountRepository.save(user2);
		
		user1 = null;
		user2 = null;
		List<TechnicianAccount> users;
		
		users = technicianAccountRepository.findByAppointment(appointment1); //this might return an array?
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
		
	}
	
	
	
	// ******************** Catherine & Aurelia end here ****************/
	
}
