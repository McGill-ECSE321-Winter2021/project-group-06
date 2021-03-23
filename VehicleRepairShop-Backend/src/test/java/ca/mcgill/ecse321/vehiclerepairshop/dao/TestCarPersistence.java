package ca.mcgill.ecse321.vehiclerepairshop.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car.MotorType;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCarPersistence {
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

		Car car2 = new Car();
		car2.setLicensePlate(licensePlate2);
		car2.setModel(model2);
		car2.setYear(year2);
		car2.setMotorType(engine);


		customerAccountRepository.save(customer);
		carRepository.save(car);
		carRepository.save(car2);

		List<Car> cars = new ArrayList<Car>();
		car = null;
		car2 = null;



		cars = carRepository.findByOwner(customer);
		assertNotNull(cars);

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
}




