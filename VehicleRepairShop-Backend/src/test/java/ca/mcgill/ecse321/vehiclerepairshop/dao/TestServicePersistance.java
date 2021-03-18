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
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car.MotorType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestServicePersistance {

	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private OfferedServiceRepository offeredServiceRepository;
	
	OfferedService service;
	String serviceId;
	double price;
	String serviceName;
	int duration;
	Time reminderTime;
	int reminderDate;
	String description;
	
	@BeforeEach
	public void buildDatabase() {
		
		serviceId = "service1";
		price = 50.0;
		serviceName = "service";
		duration = 1080;
		reminderTime = java.sql.Time.valueOf(LocalTime.of(9, 00));
		reminderDate = 30;
		description = "this is a test service";

		service = new OfferedService();
		service.setName(serviceName);
		service.setOfferedServiceId(serviceId);
		service.setPrice(price);
		service.setDuration(duration);
		service.setReminderDate(reminderDate);
		service.setReminderTime(reminderTime);
		service.setDescription(description);

		offeredServiceRepository.save(service);
		
	}
	
	@AfterEach
	public void clearDatabase() {
		
		//order matters
		offeredServiceRepository.deleteAll();
		appointmentRepository.deleteAll();

	}

	/*
	 * @author: James Darby
	 */

	//tests finding service from serviceId
	@Test
	public void testPersistAndLoadService() {

		service = null;

		service = offeredServiceRepository.findByOfferedServiceId(serviceId);

		assertNotNull(service);
		assertEquals(service.getName(), serviceName);
		assertEquals(service.getPrice(), price);
		assertEquals(service.getDuration(), duration);
		assertEquals(service.getReminderDate(), reminderDate);
		assertEquals(service.getReminderTime(), reminderTime);
		assertEquals(service.getDescription(), description);

	}

	
	@Test
	public void testPersistAndLoadServiceViaAppointment() {
		
		// create an appointment
		int appointmentID = 1;

		Appointment apt = new Appointment();
		apt.setAppointmentId(appointmentID);
		appointmentRepository.save(apt);

		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(apt);
		service.setAppointment(appointments);
		offeredServiceRepository.save(service);

		service = null;

		service = offeredServiceRepository.findByAppointment(apt);

		assertNotNull(service);
		assertEquals(service.getName(), serviceName);
		assertEquals(service.getPrice(), price);
		assertEquals(service.getDuration(), duration);
		assertEquals(service.getReminderDate(), reminderDate);
		assertEquals(service.getReminderTime(), reminderTime);
		assertEquals(service.getDescription(), description);

	}

}
