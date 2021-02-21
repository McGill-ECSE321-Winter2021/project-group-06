package ca.mcgill.ecse321.vehiclerepairshop.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Iterator;
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
import ca.mcgill.ecse321.vehiclerepairshop.model.Service;
import ca.mcgill.ecse321.vehiclerepairshop.model.Technician;
import ca.mcgill.ecse321.vehiclerepairshop.model.Admin;
import ca.mcgill.ecse321.vehiclerepairshop.model.Customer;
import ca.mcgill.ecse321.vehiclerepairshop.model.BusinessInformation;


@ExtendWith(SpringExtension.class)
@SpringBootTest

public class TestVeichleAppointmentPersistence {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private BusinessInformationRepository businessInformationRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@AfterEach
	public void clearDatabase() {
		
		carRepository.deleteAll();
		businessInformationRepository.deleteAll();
		appointmentRepository.deleteAll();
	}
	
	
// ************************* Mike start here **************************
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
		Customer customer = new Customer(customerName, customerPassword, customerID);
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
	public void testPersistAndLoadCarViaSearchCustoemr() {
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
		Customer customer = new Customer(customerName, customerPassword, customerID);
		
		
		Car car = new Car(licensePlate,model,year,engine,customer);
		carRepository.save(car);
		Car car2 = new Car(licensePlate2, model2, year2, engine, customer);
		carRepository.save(car2);
		List<Car> cars = null;
		car = null; 
		car2 = null;

		cars = carRepository.findCarByCustomer(customer);
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
		
		Date startDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 20));
		Date endDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 21));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
		
		String price = "50";
		String serviceName = "service1";
		String duration = "18hrs";
		Time reminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		Date reminderDate = java.sql.Date.valueOf(LocalDate.of(2020, Month.FEBRUARY, 21));
		String description = "this is a test service";
		
		boolean available = true;
		String gID = "1";
		
		String techName = "techName1";
		String passWord = "123";
		String techID = "techID1";
		
		
		TimeSlot timeSlot = new TimeSlot(startTime, endTime, startDate, endDate);
		Customer customer = new Customer(customerName, customerPassword, customerID);
		Service service = new Service(price, serviceName, duration, reminderDate, reminderTime, description);
		Car car = new Car(licensePlate,model,year,engine,customer);
		Garage garage = new Garage(available, gID);
		Technician technician = new Technician(techName,passWord,techID);
		
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
		assertEquals(techID, appointment1.getWorker(0).getUniqueId());
		assertEquals(serviceName, appointment1.getService().getName());
		assertEquals(gID, appointment1.getGarage().getGarageId());

	}
	
	
	// ************************* Mike end here **************************
	
}
