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
	
//	@AfterEach
//	public void clearDatabase() {
////		appointmentRepository.deleteAll();
////		businessInformationRepository.deleteAll();
////		adminAccountRepository.deleteAll();
////		timeslotRepository.deleteAll();
////		
////		garageRepository.deleteAll();
//		
//		
////		customerAccountRepository.
////		carRepository.deleteAll();
//		
////		
////		technicianAccountRepository.deleteAll();
////		appointmentRepository.deleteAll();
////		timeslotRepository.deleteAll();
////		serviceRepository.deleteAll();
////		technicianAccountRepository.deleteAll();
////		carRepository.deleteAll();
////		customerAccountRepository.deleteAll();
////		garageRepository.deleteAll();
////		
//		
//		
//		
//	}
	
	
//// ************************* Mike start here **************************
	/*
	 * @author: Cheng & Mike
	 * Tests finding appointments trough searching car
	 */
	@Test
	public void testPersistAndLoadAppopintmentViaSearchCar() {
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
		
		String timeSlotId2 = "timeSlot2";
		Date startDate2 = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 23));
		Date endDate2 = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 24));
		Time startTime2 = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime2 = java.sql.Time.valueOf(LocalTime.of(13, 25));
		
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
		
		String techName2 = "techName2";
		String passWord2 = "123";
		String techID2 = "techID2";
		
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setTimeSlotId(timeSlotId);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		
		TimeSlot timeSlot2 = new TimeSlot();
		timeSlot2.setTimeSlotId(timeSlotId2);
		timeSlot2.setStartTime(startTime2);
		timeSlot2.setEndTime(endTime2);
		timeSlot2.setStartDate(startDate2);
		timeSlot2.setEndDate(endDate2);
		
		
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
		service.setReminderDate(reminderDate);
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
		
		TechnicianAccount technician2 = new TechnicianAccount();
		technician2.setName(techName2);
		technician2.setPassword(passWord2);
		technician2.setUsername(techID2);
		
		String appointment1ID = "appointment1";
		String appointment1Comment = "this is a test Appointment";
		Appointment appointment1 = new Appointment();
		appointment1.setAppointmentId(appointment1ID);
		appointment1.setComment(appointment1Comment);
		appointment1.setCar(car);
		appointment1.setGarage(garage);
		appointment1.setService(service);
		appointment1.setTimeSlot(timeSlot);
		
		
		String appointment2ID = "appointment2";
		String appointment2Comment = "this is a test Appointment";
		Appointment appointment2 = new Appointment();
		appointment2.setAppointmentId(appointment2ID);
		appointment2.setComment(appointment2Comment);
		appointment2.setCar(car);
		appointment2.setGarage(garage);
		appointment2.setService(service);
		appointment2.setTimeSlot(timeSlot2);
		
		customerAccountRepository.save(customer);
		carRepository.save(car);
		technicianAccountRepository.save(technician);
		technicianAccountRepository.save(technician2);
		serviceRepository.save(service);
		timeslotRepository.save(timeSlot);
		timeslotRepository.save(timeSlot2);
		garageRepository.save(garage);
		appointmentRepository.save(appointment1);
		appointmentRepository.save(appointment2);
		
		
		
		List<Appointment> appointments = new ArrayList<Appointment>();

		appointment1 = null;
		appointment2 = null;

		appointments = appointmentRepository.findByCar(car);
		
		appointment1 = appointments.get(0);
		appointment2 = appointments.get(1);
		assertNotNull(appointment1);
		assertNotNull(appointment2);
		assertEquals(appointment1ID, appointment1.getAppointmentId());
		assertEquals(appointment1Comment, appointment1.getComment());
		assertEquals(licensePlate, appointment1.getCar().getLicensePlate());
		assertEquals(appointment2ID, appointment2.getAppointmentId());
		assertEquals(appointment2Comment, appointment2.getComment());
		assertEquals(licensePlate, appointment2.getCar().getLicensePlate());
		
		appointmentRepository.delete(appointment2);
		appointmentRepository.delete(appointment1);
		timeslotRepository.delete(timeSlot2);
		timeslotRepository.delete(timeSlot);
		serviceRepository.delete(service);
		technicianAccountRepository.delete(technician2);
		technicianAccountRepository.delete(technician);
		carRepository.delete(car);
		customerAccountRepository.delete(customer);
		garageRepository.delete(garage);
		
		

	}
	
	/*
	 * @author: Mike
	 * Test loading business information via searching business name
	 */
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

		businessInfo = businessInformationRepository.findBusinessInformationByName(businessName);
		assertNotNull(businessInfo);
		assertEquals(businessAddress, businessInfo.getAddress());
		assertEquals(businessPhoneNumber, businessInfo.getPhoneNumber());
		assertEquals(businessEmail, businessInfo.getEmailAddress());
		
		businessInformationRepository.delete(businessInfo);
	}
	
	
	/*
	 * @author: Mike
	 * Tests loading car via searching licensePlate
	 */
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

		car = carRepository.findByLicensePlate(licensePlate);
		assertNotNull(car);
		assertEquals(licensePlate, car.getLicensePlate());
		assertEquals(model, car.getModel());
		assertEquals(year, car.getYear());
		assertEquals(engine, car.getMotorType());
		
		carRepository.delete(car);
	}
	
	
	
	/*
	 * @author: Mike
	 * Tests loading cars via searching customer
	 */
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
		car.setOwner(customer);
		
//		Car car2 = new Car();
//		car2.setLicensePlate(licensePlate2);
//		car2.setModel(model2);
//		car2.setYear(year2);
//		car2.setMotorType(engine);
//		car2.setOwner(customer);
		
		
		customerAccountRepository.save(customer);
		carRepository.save(car);
//		carRepository.save(car2);
		
		List<Car> cars = new ArrayList<Car>();
		car = null; 
//		car2 = null;
		

		
		cars = carRepository.findByOwner(customer);
		assertNotNull(cars);

		car = cars.get(0);
//		car2 = cars.get(1);
		assertEquals(licensePlate, car.getLicensePlate());
		assertEquals(model, car.getModel());
		assertEquals(year, car.getYear());
		assertEquals(engine, car.getMotorType());
//		
//		assertEquals(licensePlate2, car2.getLicensePlate());
//		assertEquals(model2, car2.getModel());
//		assertEquals(year2, car2.getYear());
//		assertEquals(engine, car2.getMotorType());
		
		
		
		carRepository.delete(car);
//		carRepository.delete(car2);
		customerAccountRepository.delete(customer);
		
		
		
		
	}
//	
//	
//	
//	
	/*
	 * @author: Mike
	 * Tests loading appointment via searching timeslots and car
	 */
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
		service.setReminderDate(reminderDate);
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

		

		customerAccountRepository.save(customer);
		carRepository.save(car);
		technicianAccountRepository.save(technician);
		serviceRepository.save(service);
		timeslotRepository.save(timeSlot);
		garageRepository.save(garage);
		appointmentRepository.save(appointment1);


		appointment1 = null;
		Boolean isExist;

		appointment1 = appointmentRepository.findByCarAndTimeSlot(car, timeSlot);
		isExist = appointmentRepository.existsByCarAndTimeSlot(car, timeSlot);
		
		assertNotNull(appointment1);
		assertEquals(true,isExist);
		assertEquals(appointment1ID, appointment1.getAppointmentId());
		assertEquals(appointment1Comment, appointment1.getComment());
		assertEquals(licensePlate, appointment1.getCar().getLicensePlate());
		assertEquals(serviceName, appointment1.getService().getName());
		assertEquals(gID, appointment1.getGarage().getGarageId());
		
		appointmentRepository.delete(appointment1);
		timeslotRepository.delete(timeSlot);
		serviceRepository.delete(service);
		technicianAccountRepository.delete(technician);
		carRepository.delete(car);
		customerAccountRepository.delete(customer);
		garageRepository.delete(garage);

	}
	
	
	
	/*
	 * @author: Mike
	 * Tests loading appointment via searching garage
	 */
	@Test
	public void testPersistAndLoadAppopintmentViaSearchGarage() {
		String licensePlate = "TestCar";
		String model = "TestModel";
		Integer year = 2021;
		MotorType engine = MotorType.Gas;
		
		String licensePlate2 = "TestCar2";
		String model2 = "TestModel2";
		Integer year2 = 2021;
		MotorType engine2 = MotorType.Gas;
		
		String customerName = "customer";
		String customerPassword = "123";
		String customerID = "customer1";
		
		String customerName2 = "customer2";
		String customerPassword2 = "123";
		String customerID2 = "customer2";
		
		String timeSlotId = "timeSlot1";
		Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 20));
		Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
		
		String timeSlotId2 = "timeSlot2";
		Date startDate2 = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
		Date endDate2 = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 22));
		Time startTime2 = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime2 = java.sql.Time.valueOf(LocalTime.of(13, 25));
		
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
		
		String techName2 = "techName2";
		String passWord2 = "123";
		String techID2 = "techID2";
		
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setTimeSlotId(timeSlotId);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		
		TimeSlot timeSlot2 = new TimeSlot();
		timeSlot2.setTimeSlotId(timeSlotId2);
		timeSlot2.setStartTime(startTime2);
		timeSlot2.setEndTime(endTime2);
		timeSlot2.setStartDate(startDate2);
		timeSlot2.setEndDate(endDate2);
		
		
		CustomerAccount customer = new CustomerAccount();
		customer.setName(customerName);
		customer.setPassword(customerPassword);
		customer.setUsername(customerID);
		
		CustomerAccount customer2 = new CustomerAccount();
		customer2.setName(customerName2);
		customer2.setPassword(customerPassword2);
		customer2.setUsername(customerID2);
		
		
		Service service = new Service();
		service.setServiceId(serviceId);
		service.setPrice(price);
		service.setName(serviceName);
		service.setDuration(duration);
		service.setReminderTime(reminderTime);
		service.setReminderDate(reminderDate);
		service.setDescription(description);
		
		
		Car car = new Car();
		car.setLicensePlate(licensePlate);
		car.setModel(model);
		car.setYear(year);
		car.setMotorType(engine);
		
		Car car2 = new Car();
		car2.setLicensePlate(licensePlate2);
		car2.setModel(model2);
		car2.setYear(year2);
		car2.setMotorType(engine2);
		
		
		Garage garage = new Garage();
		garage.setIsAvailable(available);
		garage.setGarageId(gID);
		
		
		TechnicianAccount technician = new TechnicianAccount();
		technician.setName(techName);
		technician.setPassword(passWord);
		technician.setUsername(techID);
		
		TechnicianAccount technician2 = new TechnicianAccount();
		technician2.setName(techName2);
		technician2.setPassword(passWord2);
		technician2.setUsername(techID2);
		
		String appointment1ID = "appointment1";
		String appointment1Comment = "this is a test Appointment";
		Appointment appointment1 = new Appointment();
		appointment1.setAppointmentId(appointment1ID);
		appointment1.setComment(appointment1Comment);
		appointment1.setCar(car);
		appointment1.setGarage(garage);
		appointment1.setService(service);
		appointment1.setTimeSlot(timeSlot);
		
		
		String appointment2ID = "appointment2";
		String appointment2Comment = "this is a test Appointment";
		Appointment appointment2 = new Appointment();
		appointment2.setAppointmentId(appointment2ID);
		appointment2.setComment(appointment2Comment);
		appointment2.setCar(car2);
		appointment2.setGarage(garage);
		appointment2.setService(service);
		appointment2.setTimeSlot(timeSlot2);

		
		customerAccountRepository.save(customer);
		customerAccountRepository.save(customer2);
		carRepository.save(car);
		carRepository.save(car2);
		technicianAccountRepository.save(technician);
		technicianAccountRepository.save(technician2);
		serviceRepository.save(service);
		timeslotRepository.save(timeSlot);
		timeslotRepository.save(timeSlot2);
		garageRepository.save(garage);
		appointmentRepository.save(appointment1);
		appointmentRepository.save(appointment2);
		
		List<Appointment> appointments = new ArrayList<Appointment>();
		
		appointment1 = null;
		appointment2 = null;
		appointments = appointmentRepository.findByGarage(garage);
		appointment1 = appointments.get(0);
		appointment2 = appointments.get(1);
		assertNotNull(appointment1);
		assertNotNull(appointment2);
		assertEquals(appointment1ID, appointment1.getAppointmentId());
		assertEquals(appointment1Comment, appointment1.getComment());
		assertEquals(appointment2ID, appointment2.getAppointmentId());
		assertEquals(appointment2Comment, appointment2.getComment());
		
		appointmentRepository.delete(appointment2);
		appointmentRepository.delete(appointment1);
		timeslotRepository.delete(timeSlot2);
		timeslotRepository.delete(timeSlot);
		serviceRepository.delete(service);
		technicianAccountRepository.delete(technician2);
		technicianAccountRepository.delete(technician);
		carRepository.delete(car2);
		carRepository.delete(car);
		customerAccountRepository.delete(customer2);
		customerAccountRepository.delete(customer);
		garageRepository.delete(garage);

	}
//	
//	
//	// ************************* Mike end here **************************/
//	
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
//	
//	// ************************* James starts here *************************
//	
//	/*
//	 * @author: James Darby
//	 */
//	
	@SuppressWarnings("unchecked")
	@Test
	public void testPersistAndLoadGarageViaAppointment() {
		
		//makes a garage
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
		List<TechnicianAccount> workers = new ArrayList<TechnicianAccount>();
		workers.add(technician);
		appointment1.setWorker(workers);

		
		technicianAccountRepository.save(technician);
		customerAccountRepository.save(customer);
		carRepository.save(car);
		serviceRepository.save(service);
		timeslotRepository.save(timeSlot);
		garageRepository.save(garageToTest);
		appointmentRepository.save(appointment1);
		
		
		garageToTest = null;
		garageToTest = garageRepository.findByAppointment(appointment1);
		assertNotNull(garageToTest);
		assertEquals(garageToTest.getGarageId(), garageID);
		assertEquals(garageToTest.getIsAvailable(), isAvailable);
		
		appointmentRepository.delete(appointment1);
		timeslotRepository.delete(timeSlot);
		serviceRepository.delete(service);
		technicianAccountRepository.delete(technician);
		carRepository.delete(car);
		customerAccountRepository.delete(customer);
		garageRepository.delete(garageToTest);
	}
	
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testPersistAndLoadTimeSlotViaTechnician() {
//		Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 20));
//		Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
//		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
//		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
//		
//		String techName = "techName1";
//		String passWord = "123";
//		String techID = "techID1";
//		
//		TimeSlot timeSlot = new TimeSlot();
//		timeSlot.setStartTime(startTime);
//		timeSlot.setEndTime(endTime);
//		timeSlot.setStartDate(startDate);
//		timeSlot.setEndDate(endDate);
//		
//		TechnicianAccount technician = new TechnicianAccount();
//		technician.setName(techName);
//		technician.setPassword(passWord);
//		technician.setUsername(techID);
//		technician.setAvailability((List<TimeSlot>) timeSlot);
//		
//		timeslotRepository.save(timeSlot);
//		
//		timeSlot = null;
//		
//		//List<TimeSlot> listOfTimeSlots = new ArrayList<TimeSlot>();
//		
//		List<TimeSlot> listOfTimeSlots = timeslotRepository.findByTechnician(technician);
//		
//		assertNotNull(listOfTimeSlots);
//		assertEquals(listOfTimeSlots.get(0).getStartTime(), startTime);
//		assertEquals(listOfTimeSlots.get(0).getEndTime(), endTime);
//		assertEquals(listOfTimeSlots.get(0).getStartDate(), startDate);
//		assertEquals(listOfTimeSlots.get(0).getEndDate(), endDate);
//		
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testPersistAndLoadTimeSlotViaAppointment() {
//		Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 20));
//		Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
//		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
//		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
//		
//		String licensePlate = "TestCar";
//		String model = "TestModel";
//		Integer year = 2021;
//		MotorType engine = MotorType.Gas;
//		
//		String customerName = "customer";
//		String customerPassword = "123";
//		String customerID = "customer1";
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
//		String techName = "techName1";
//		String passWord = "123";
//		String techID = "techID1";
//		
//		TimeSlot timeSlot = new TimeSlot();
//		timeSlot.setStartTime(startTime);
//		timeSlot.setEndTime(endTime);
//		timeSlot.setStartDate(startDate);
//		timeSlot.setEndDate(endDate);
//		
//		CustomerAccount customer = new CustomerAccount();
//		customer.setName(customerName);
//		customer.setPassword(customerPassword);
//		customer.setUsername(customerName);
//		
//		Service service = new Service();
//		service.setName(serviceName);
//		service.setPrice(price);
//		service.setDuration(duration);
//		service.setReminderDate(reminderDate);
//		service.setReminderTime(reminderTime);
//		service.setDescription(description);
//		
//		Car car = new Car();
//		car.setLicensePlate(licensePlate);
//		car.setModel(model);
//		car.setYear(year);
//		car.setMotorType(engine);
//		car.setOwner(customer);
//		
//	    Garage garage = new Garage();
//		garage.setGarageId(gID);
//		garage.setIsAvailable(available);
//		
//		TechnicianAccount technician = new TechnicianAccount();
//		technician.setName(techName);
//		technician.setPassword(passWord);
//		technician.setUsername(techID);
//		
//		String appointment1ID = "appointment1";
//		String appointment1Comment = "this is a test Appointment";
//		Appointment appointment1 = new Appointment();
//		appointment1.setAppointmentId(appointment1ID);
//		appointment1.setComment(appointment1Comment);
//		appointment1.setCar(car);
//		appointment1.setGarage(garage);
//		appointment1.setService(service);
//		appointment1.setService(service);
//		appointment1.setTimeSlot(timeSlot);
//		appointment1.setWorker((List<TechnicianAccount>) technician);
//		
//		timeslotRepository.save(timeSlot);
//		
//		timeSlot = null;
//		
//		timeSlot = timeslotRepository.findByAppointment(appointment1);
//		
//		assertNotNull(timeSlot);
//		assertEquals(timeSlot.getStartTime(), startTime);
//		assertEquals(timeSlot.getEndTime(), endTime);
//		assertEquals(timeSlot.getStartDate(), startDate);
//		assertEquals(timeSlot.getEndDate(), endDate);
//	}
//	
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
		String timeSlotId = "t1";
		
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
		service.setServiceId(serviceId);
		service.setPrice(price);
		service.setDuration(duration);
		service.setReminderDate(reminderDate);
		service.setReminderTime(reminderTime);
		service.setDescription(description);
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(startTime);
		timeSlot.setTimeSlotId(timeSlotId);
		timeSlot.setEndTime(endTime);
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		
		CustomerAccount customer = new CustomerAccount();
		customer.setName(customerName);
		customer.setPassword(customerPassword);
		customer.setUsername(customerName);
		
		
//		Car car = new Car();
//		car.setLicensePlate(licensePlate);
//		car.setModel(model);
//		car.setYear(year);
//		car.setMotorType(engine);
//		car.setOwner(customer);
		
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
//		appointment1.setCar(car);
		appointment1.setGarage(garage);
		appointment1.setService(service);
		appointment1.setTimeSlot(timeSlot);
		
		List<TechnicianAccount> workers = new ArrayList<TechnicianAccount>();
		workers.add(technician);
		appointment1.setWorker(workers);

		
		customerAccountRepository.save(customer);
//		carRepository.save(car);
		technicianAccountRepository.save(technician);
		serviceRepository.save(service);
		timeslotRepository.save(timeSlot);
		garageRepository.save(garage);
		appointmentRepository.save(appointment1);
		
		
		service = null;
		
		service = serviceRepository.findByAppointment(appointment1);
		
		assertNotNull(service);
		assertEquals(service.getName(), serviceName);
		assertEquals(service.getPrice(), price);
		assertEquals(service.getDuration(), duration);
		assertEquals(service.getReminderDate(), reminderDate);
		assertEquals(service.getReminderTime(), reminderTime);
		assertEquals(service.getDescription(), description);
		
		appointmentRepository.delete(appointment1);
		timeslotRepository.delete(timeSlot);
		serviceRepository.delete(service);
		technicianAccountRepository.delete(technician);
//		carRepository.delete(car);
		customerAccountRepository.delete(customer);
		garageRepository.delete(garage);
		

	}
	
	// ************************* James ends here ***************************
	
}
