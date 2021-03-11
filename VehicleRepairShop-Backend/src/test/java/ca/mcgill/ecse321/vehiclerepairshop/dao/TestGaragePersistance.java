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
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car.MotorType;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGaragePersistance {
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
	private OfferedServiceRepository offeredServiceRepository;
	
	@AfterEach
	public void clearDatabase() {
	
		
		appointmentRepository.deleteAll();
		technicianAccountRepository.deleteAll(); //technician needs to be deleted after appointment due to dependencies
		timeslotRepository.deleteAll();
		offeredServiceRepository.deleteAll();
		carRepository.deleteAll();
		customerAccountRepository.deleteAll();
		garageRepository.deleteAll();
		adminAccountRepository.deleteAll();
		businessInformationRepository.deleteAll();

	}
	
	/*
	 * @author: James Darby
	 */
	
	@Test
	public void testPersistAndLoadGarage() {
		
		boolean isAvailable = true;
		String garageId = "testGarageID";
		
		Garage garageToTest = new Garage();
		garageToTest.setGarageId(garageId);
		garageToTest.setIsAvailable(isAvailable);
		
		garageRepository.save(garageToTest);
		
		garageToTest = null;
		garageToTest = garageRepository.findByGarageId(garageId);
		
		assertNotNull(garageToTest);
		assertEquals(garageToTest.getGarageId(), garageId);
		assertEquals(garageToTest.getIsAvailable(), isAvailable);
	}
	
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
		String customerUsername = "customer1";
		
		int timeSlotId = 12;
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
		timeSlot.setTimeSlotId(timeSlotId);
		
		CustomerAccount customer = new CustomerAccount();
		customer.setName(customerName);
		customer.setPassword(customerPassword);
		customer.setUsername(customerUsername);
		
		OfferedService service = new OfferedService();
		service.setName(serviceName);
		service.setPrice(price);
		service.setOfferedServiceId(serviceId);
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
		
		int appointment1ID = 1;
		String appointment1Comment = "this is a test Appointment";
		Appointment appointment1 = new Appointment();
		appointment1.setAppointmentId(appointment1ID);
		appointment1.setComment(appointment1Comment);
		appointment1.setCar(car);
		appointment1.setGarage(garageToTest);
		appointment1.setOfferedService(service);
		appointment1.setOfferedService(service);
		appointment1.setTimeSlot(timeSlot);
		List<TechnicianAccount> workers = new ArrayList<TechnicianAccount>();
		workers.add(technician);
		appointment1.setWorker(workers);

		
		technicianAccountRepository.save(technician);
		customerAccountRepository.save(customer);
		carRepository.save(car);
		offeredServiceRepository.save(service);
		timeslotRepository.save(timeSlot);
		garageRepository.save(garageToTest);
		appointmentRepository.save(appointment1);
		
		
		garageToTest = null;
		garageToTest = garageRepository.findByAppointment(appointment1);
		assertNotNull(garageToTest);
		assertEquals(garageToTest.getGarageId(), garageID);
		assertEquals(garageToTest.getIsAvailable(), isAvailable);
	}
	
	
}


