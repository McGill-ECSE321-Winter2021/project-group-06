package ca.mcgill.ecse321.vehiclerepairshop.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

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
	
	@Test
	public void testPersistAndLoadCar() {
		String licensePlate = "TestCar";
		String model = "TestModel";
		Integer year = 2021;
		String customerName = "person1";
		String passward = "passward";
		String ID = "id";
		Customer customer = new Customer(customerName,passward,ID);
		MotorType engine = MotorType.Gas;
		
		Car car = new Car(licensePlate,model,year,engine,customer);
//		car.setLicensePlate(licensePlate);
//		car.setModel(model);
//		car.setYear(year);
//		car.setMotorType(engine);
		carRepository.save(car);

		car = null;

		car = carRepository.findCarByLicensePlate(licensePlate);
		assertNotNull(car);
		assertEquals(licensePlate, car.getLicensePlate());
		assertEquals(model, car.getModel());
		assertEquals(year, car.getYear());
		assertEquals(engine, car.getMotorType());
	}
	
//	
//	@Test
//	public void testPersistAndLoadCar() {
//		String licensePlate = "TestCar";
//		String model = "TestModel";
//		Integer year = 2021;
//		MotorType engine = MotorType.Gas;
//		
//		Car car = new Car();
//		car.setLicensePlate(licensePlate);
//		car.setModel(model);
//		car.setYear(year);
//		car.setMotorType(engine);
//		carRepository.save(car);
//
//		car = null;
//
//		car = carRepository.findCarByLicensePlate(licensePlate);
//		assertNotNull(car);
//		assertEquals(licensePlate, car.getLicensePlate());
//		assertEquals(model, car.getModel());
//		assertEquals(year, car.getYear());
//		assertEquals(engine, car.getMotorType());
//	}
	
	
	
}
