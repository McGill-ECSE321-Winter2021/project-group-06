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
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAppointmentPersistence {
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
	private OfferedServiceRepository serviceRepository;


	@AfterEach
	public void clearDatabase() {


		appointmentRepository.deleteAll();
		technicianAccountRepository.deleteAll(); //technician needs to be deleted after appointment due to dependencies
		timeslotRepository.deleteAll();
		serviceRepository.deleteAll();
		carRepository.deleteAll();
		customerAccountRepository.deleteAll();
		garageRepository.deleteAll();
		adminAccountRepository.deleteAll();
		businessInformationRepository.deleteAll();

	}

	
	
	/*
	 * @author: Cheng & Mike
	 * Tests finding appointments trough searching car
	 */
	@Test
	public void testPersistAndLoadAppopintmentViaSearchCar() {
		String licensePlate = "TestCar";
		String model = "TestModel";
		int year = 2021;
		MotorType engine = MotorType.Gas;


		String customerName = "customer";
		String customerPassword = "123";
		String customerID = "customer1";


		int timeSlotId = 11;
		Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 20));
		Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));

		int timeSlotId2 = 12;
		Date startDate2 = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 23));
		Date endDate2 = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 24));
		Time startTime2 = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime2 = java.sql.Time.valueOf(LocalTime.of(13, 25));

		String serviceId = "service1";
		Double price = 50.0;
		String serviceName = "service";
		int duration = 1080;
		Time reminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int reminderDate = 30;
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



		OfferedService service = new OfferedService();
		service.setOfferedServiceId(serviceId);
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
		garage.setGarageId(gID);


		TechnicianAccount technician = new TechnicianAccount();
		technician.setName(techName);
		technician.setPassword(passWord);
		technician.setUsername(techID);

		TechnicianAccount technician2 = new TechnicianAccount();
		technician2.setName(techName2);
		technician2.setPassword(passWord2);
		technician2.setUsername(techID2);

		int appointment1ID = 1;
		String appointment1Comment = "this is a test Appointment";
		Appointment appointment1 = new Appointment();
		appointment1.setAppointmentId(appointment1ID);
		appointment1.setComment(appointment1Comment);
		appointment1.setCar(car);
		appointment1.setGarage(garage);
		appointment1.setOfferedService(service);
		appointment1.setTimeSlot(timeSlot);


		int appointment2ID = 2;
		String appointment2Comment = "this is a test Appointment";
		Appointment appointment2 = new Appointment();
		appointment2.setAppointmentId(appointment2ID);
		appointment2.setComment(appointment2Comment);
		appointment2.setCar(car);
		appointment2.setGarage(garage);
		appointment2.setOfferedService(service);
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

	}
	
	
	/*
	 * @author: Mike
	 * Tests loading appointment via searching timeslots and car
	 */
	@Test
	public void testPersistAndLoadAppointmentViaCarandTimeSlot() {
		String licensePlate = "TestCar";
		String model = "TestModel";
		int year = 2021;
		MotorType engine = MotorType.Gas;

		String customerName = "customer";
		String customerPassword = "123";
		String customerID = "customer1";

		int timeSlotId = 12;
		Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 20));
		Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));

		String serviceId = "service1";
		Double price = 50.0;
		String serviceName = "service";
		int duration = 1080;
		Time reminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int reminderDate = 30;
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
		OfferedService service = new OfferedService();
		service.setOfferedServiceId(serviceId);
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
		garage.setGarageId(gID);
		TechnicianAccount technician = new TechnicianAccount();
		technician.setName(techName);
		technician.setPassword(passWord);
		technician.setUsername(techID);


		int appointment1ID = 1;
		String appointment1Comment = "this is a test Appointment";
		Appointment appointment1 = new Appointment();
		appointment1.setAppointmentId(appointment1ID);
		appointment1.setComment(appointment1Comment);
		appointment1.setCar(car);
		appointment1.setGarage(garage);
		appointment1.setOfferedService(service);
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

		assertNotNull(appointment1);
		assertEquals(appointment1ID, appointment1.getAppointmentId());
		assertEquals(appointment1Comment, appointment1.getComment());
		assertEquals(licensePlate, appointment1.getCar().getLicensePlate());
		assertEquals(serviceName, appointment1.getOfferedService().getName());
		assertEquals(gID, appointment1.getGarage().getGarageId());

	}
	
	
	/*
	 * @author: Mike
	 * Tests loading appointment via searching garage
	 */
	@Test
	public void testPersistAndLoadAppopintmentViaSearchGarage() {
		String licensePlate = "TestCar";
		String model = "TestModel";
		int year = 2021;
		MotorType engine = MotorType.Gas;

		String licensePlate2 = "TestCar2";
		String model2 = "TestModel2";
		int year2 = 2021;
		MotorType engine2 = MotorType.Gas;

		String customerName = "customer";
		String customerPassword = "123";
		String customerID = "customer1";

		String customerName2 = "customer2";
		String customerPassword2 = "123";
		String customerID2 = "customer2";

		int timeSlotId = 11;
		Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 20));
		Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));

		int timeSlotId2 = 12;
		Date startDate2 = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
		Date endDate2 = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 22));
		Time startTime2 = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime2 = java.sql.Time.valueOf(LocalTime.of(13, 25));

		String serviceId = "service1";
		Double price = 50.0;
		String serviceName = "service";
		int duration = 1080;
		Time reminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		int reminderDate = 30;
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


		OfferedService service = new OfferedService();
		service.setOfferedServiceId(serviceId);
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
		garage.setGarageId(gID);


		TechnicianAccount technician = new TechnicianAccount();
		technician.setName(techName);
		technician.setPassword(passWord);
		technician.setUsername(techID);

		TechnicianAccount technician2 = new TechnicianAccount();
		technician2.setName(techName2);
		technician2.setPassword(passWord2);
		technician2.setUsername(techID2);

		int appointment1ID = 1;
		String appointment1Comment = "this is a test Appointment";
		Appointment appointment1 = new Appointment();
		appointment1.setAppointmentId(appointment1ID);
		appointment1.setComment(appointment1Comment);
		appointment1.setCar(car);
		appointment1.setGarage(garage);
		appointment1.setOfferedService(service);
		appointment1.setTimeSlot(timeSlot);


		int appointment2ID = 2;
		String appointment2Comment = "this is a test Appointment";
		Appointment appointment2 = new Appointment();
		appointment2.setAppointmentId(appointment2ID);
		appointment2.setComment(appointment2Comment);
		appointment2.setCar(car2);
		appointment2.setGarage(garage);
		appointment2.setOfferedService(service);
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

	}
	
	
}
