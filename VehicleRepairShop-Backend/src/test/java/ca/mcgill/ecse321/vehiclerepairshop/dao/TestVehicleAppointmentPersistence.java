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
	@Autowired
	private GarageRepository garageRepository;
	@Autowired
	private TimeSlotRepository timeslotRepository;
	@Autowired
	private ServiceRepository serviceRepository;
	
	@AfterEach
	public void clearDatabase() {
		
		carRepository.deleteAll();
		businessInformationRepository.deleteAll();
		appointmentRepository.deleteAll();
		customerAccountRepository.deleteAll();
		technicianAccountRepository.deleteAll();
		adminAccountRepository.deleteAll();
		garageRepository.deleteAll();
		timeslotRepository.deleteAll();
		serviceRepository.deleteAll();
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
		
		BusinessInformation businessInfo = new BusinessInformation();
		businessInfo.setName(businessName);
		businessInfo.setAddress(businessAddress);
		businessInfo.setPhoneNumber(businessPhoneNumber);
		businessInfo.setEmailAddress(businessEmail);
		businessInformationRepository.save(businessInfo);

		businessInfo = null;

		businessInfo = businessInformationRepository.findByName(businessName);
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
		CustomerAccount customer = new CustomerAccount();
		customer.setName(customerName);
		customer.setPassword(customerPassword);
		customer.setUsername(customerID);
		MotorType engine = MotorType.Gas;
		
		Car car = new Car();
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
		CustomerAccount customer = new CustomerAccount();
		customer.setName(customerName);
		customer.setPassword(customerPassword);
		customer.setUsername(customerID);
		
		Car car = new Car();
		car.setLicensePlate(licensePlate);
		car.setModel(model);
		car.setYear(year);
		car.setMotorType(engine);
		carRepository.save(car);
		Car car2 = new Car();
		car.setLicensePlate(licensePlate2);
		car.setModel(model2);
		car.setYear(year2);
		car.setMotorType(engine);
		carRepository.save(car2);
		List<Car> cars = null;
		car = null; 
		car2 = null;

		
		cars = carRepository.findCarByOwner(customer);
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
	public void testPersistAndLoadAppointmentViaCarandTimeSlot() {
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
		
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setTimeSlotId(timeSlotId);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		CustomerAccount customer = new CustomerAccount();
		customer.setName(customerName);
		customer.setPassword(customerPassword);
		customer.setUsername(customerID);
		Service service = new Service();
		service.setServiceId(serviceId);
		service.setPrice(price);
		service.setName(serviceName);
		service.setDuration(duration);
		service.setReminderTime(reminderTime);
		service.setDescription(description);
		Car car = new Car();
		car.setLicensePlate(licensePlate);
		car.setModel(model);
		car.setYear(year);
		car.setMotorType(engine);
		Garage garage = new Garage();
		garage.setIsAvailable(available);
		garage.setGarageId(gID);
		TechnicianAccount technician = new TechnicianAccount();
		technician.setName(techName);
		technician.setPassword(passWord);
		technician.setUsername(techID);
		
		
		String appointment1ID = "appointment1";
		String appointment1Comment = "this is a test Appointment";
		Appointment appointment1 = new Appointment();
		appointment1.setAppointmentId(appointment1ID);
		appointment1.setComment(appointment1Comment);
		appointment1.setCar(car);
		appointment1.setGarage(garage);
		appointment1.setService(service);
		appointment1.setTimeSlot(timeSlot);

		

		appointmentRepository.save(appointment1);

		appointment1 = null;

		appointment1 = appointmentRepository.findByCarAndTimeSlot(car, timeSlot);
		assertNotNull(appointment1);
		assertEquals(appointment1ID, appointment1.getAppointmentId());
		assertEquals(appointment1Comment, appointment1.getComment());
		assertEquals(licensePlate, appointment1.getCar().getLicensePlate());
		assertEquals(serviceName, appointment1.getService().getName());
		assertEquals(gID, appointment1.getGarage().getGarageId());

	}
	
	
	// ************************* Mike end here **************************/
	
	// ********************* Catherine & Aurelia start here **************/
	/**
	 * Tests finding an admin account by the unique username
	 */
	@Test
	public void testPersistAndLoadAdminAccount() {
		String name1 = "Wanda Maximoff";
		String username1 = "username1";
		String password1 = "password123";
		
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
		
		AdminAccount user1 = new AdminAccount();
		AdminAccount user2 = new AdminAccount();
		
		user1.setName(name1);
		user1.setUsername(username1);
		user1.setPassword(password1);
		
		user2.setName(name1);
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
		
	}
	/**
	 * Tests finding a customer account by a unique username
	 */
	@Test
	public void testPersistAndLoadCustomerAccount() {
		String name1 = "Steve McQueen";
		String username1 = "username1";
		String password1 = "password123";
		
		CustomerAccount user1 = new CustomerAccount();
		user1.setName(name1);
		user1.setUsername(username1);
		user1.setPassword(password1);
		
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
		
		car.setOwner(user1);
		
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
		
		String appointment1ID = "appointment1";
		String appointment1Comment = "this is a test Appointment";	
		
//		TimeSlot timeSlot = new TimeSlot();
//		CustomerAccount customer = new CustomerAccount();
//		Service service = new Service();
//		Car car = new Car();
//		Garage garage = new Garage();
//	
		TechnicianAccount user1 = new TechnicianAccount();
		TechnicianAccount user2 = new TechnicianAccount();
		
		Appointment appointment1 = new Appointment();
		
		user1.setName(name1);
		user1.setUsername(username1);
		user1.setPassword(password1);
		
		user2.setName(name2);
		user2.setUsername(username2);
		user2.setPassword(password2);
		
		List<TechnicianAccount> workers = new ArrayList<TechnicianAccount>();
		workers.add(user1);
		workers.add(user2);
	
		appointment1.setWorker(workers);
		
		technicianAccountRepository.save(user1);
		technicianAccountRepository.save(user2);
		
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
		
	}
	
	
	
	// ******************** Catherine & Aurelia end here ****************/
	
	// ************************* James starts here *************************
	
	/*
	 * @author: James Darby
	 */
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPersistAndLoadGarageViaAppointment() {
		
		//makes a garage
		boolean isAvailable = true;
		String garageID = "testGarageID";
		
		Garage garageToTest = new Garage();
		garageToTest.setGarageId(garageID);
		garageToTest.setIsAvailable(isAvailable);
		
		garageRepository.save(garageToTest);
		
		garageToTest = null;
		
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
		
		TechnicianAccount technician = new TechnicianAccount();
		technician.setName(techName);
		technician.setPassword(passWord);
		technician.setUsername(techID);
		
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
		appointment1.setWorker((List<TechnicianAccount>) technician);
		
		
		garageToTest = garageRepository.findByAppointment(appointment1);
		assertNotNull(garageToTest);
		assertEquals(garageToTest.getGarageId(), garageID);
		assertEquals(garageToTest.getIsAvailable(), isAvailable);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPersistAndLoadTimeSlotViaTechnician() {
		Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 20));
		Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
		
		String techName = "techName1";
		String passWord = "123";
		String techID = "techID1";
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		
		TechnicianAccount technician = new TechnicianAccount();
		technician.setName(techName);
		technician.setPassword(passWord);
		technician.setUsername(techID);
		technician.setAvailability((List<TimeSlot>) timeSlot);
		
		timeslotRepository.save(timeSlot);
		
		timeSlot = null;
		
		//List<TimeSlot> listOfTimeSlots = new ArrayList<TimeSlot>();
		
		List<TimeSlot> listOfTimeSlots = timeslotRepository.findByTechnician(technician);
		
		assertNotNull(listOfTimeSlots);
		assertEquals(listOfTimeSlots.get(0).getStartTime(), startTime);
		assertEquals(listOfTimeSlots.get(0).getEndTime(), endTime);
		assertEquals(listOfTimeSlots.get(0).getStartDate(), startDate);
		assertEquals(listOfTimeSlots.get(0).getEndDate(), endDate);
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPersistAndLoadTimeSlotViaAppointment() {
		Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 20));
		Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
		
		String licensePlate = "TestCar";
		String model = "TestModel";
		Integer year = 2021;
		MotorType engine = MotorType.Gas;
		
		String customerName = "customer";
		String customerPassword = "123";
		String customerID = "customer1";
		
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
		
	    Garage garage = new Garage();
		garage.setGarageId(gID);
		garage.setIsAvailable(available);
		
		TechnicianAccount technician = new TechnicianAccount();
		technician.setName(techName);
		technician.setPassword(passWord);
		technician.setUsername(techID);
		
		String appointment1ID = "appointment1";
		String appointment1Comment = "this is a test Appointment";
		Appointment appointment1 = new Appointment();
		appointment1.setAppointmentId(appointment1ID);
		appointment1.setComment(appointment1Comment);
		appointment1.setCar(car);
		appointment1.setGarage(garage);
		appointment1.setService(service);
		appointment1.setService(service);
		appointment1.setTimeSlot(timeSlot);
		appointment1.setWorker((List<TechnicianAccount>) technician);
		
		timeslotRepository.save(timeSlot);
		
		timeSlot = null;
		
		timeSlot = timeslotRepository.findByAppointment(appointment1);
		
		assertNotNull(timeSlot);
		assertEquals(timeSlot.getStartTime(), startTime);
		assertEquals(timeSlot.getEndTime(), endTime);
		assertEquals(timeSlot.getStartDate(), startDate);
		assertEquals(timeSlot.getEndDate(), endDate);
	}
	
	@SuppressWarnings({ "unchecked"})
	@Test
	public void testPersistAndLoadServiceViaAppointment() {
		
		String serviceId = "service1";
		String price = "50";
		String serviceName = "service";
		String duration = "18hrs";
		Time reminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		Date reminderDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.FEBRUARY, 21));
		String description = "this is a test service";
		
		Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 20));
		Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
		
		String licensePlate = "TestCar";
		String model = "TestModel";
		Integer year = 2021;
		MotorType engine = MotorType.Gas;
		
		String customerName = "customer";
		String customerPassword = "123";
		String customerID = "customer1";
		
		boolean available = true;
		String gID = "1";
		
		String techName = "techName1";
		String passWord = "123";
		String techID = "techID1";
		
		Service service = new Service();
		service.setName(serviceName);
		service.setPrice(price);
		service.setDuration(duration);
		service.setReminderDate(reminderDate);
		service.setReminderTime(reminderTime);
		service.setDescription(description);
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		
		CustomerAccount customer = new CustomerAccount();
		customer.setName(customerName);
		customer.setPassword(customerPassword);
		customer.setUsername(customerName);
		
		
		Car car = new Car();
		car.setLicensePlate(licensePlate);
		car.setModel(model);
		car.setYear(year);
		car.setMotorType(engine);
		car.setOwner(customer);
		
	    Garage garage = new Garage();
		garage.setGarageId(gID);
		garage.setIsAvailable(available);
		
		TechnicianAccount technician = new TechnicianAccount();
		technician.setName(techName);
		technician.setPassword(passWord);
		technician.setUsername(techID);
		
		String appointment1ID = "appointment1";
		String appointment1Comment = "this is a test Appointment";
		Appointment appointment1 = new Appointment();
		appointment1.setAppointmentId(appointment1ID);
		appointment1.setComment(appointment1Comment);
		appointment1.setCar(car);
		appointment1.setGarage(garage);
		appointment1.setService(service);
		appointment1.setTimeSlot(timeSlot);
		appointment1.setWorker((List<TechnicianAccount>) technician);
		
		serviceRepository.save(service);
		
		service = null;
		
		service = serviceRepository.findByAppointment(appointment1);
		
		assertNotNull(service);
		assertEquals(service.getName(), serviceName);
		assertEquals(service.getPrice(), price);
		assertEquals(service.getDuration(), duration);
		assertEquals(service.getReminderDate(), reminderDate);
		assertEquals(service.getReminderTime(), reminderTime);
		assertEquals(service.getDescription(), description);
		
	}
	
	// ************************* James ends here ***************************
	
}
