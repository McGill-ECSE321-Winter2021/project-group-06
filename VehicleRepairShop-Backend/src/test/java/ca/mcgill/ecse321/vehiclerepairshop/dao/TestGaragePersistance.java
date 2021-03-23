package ca.mcgill.ecse321.vehiclerepairshop.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGaragePersistance {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private GarageRepository garageRepository;
	
	Garage garage;
	boolean isAvailable;
	String garageId;


	@BeforeEach
	public void buildDatabase() {
		
		isAvailable = true;
		garageId = "testGarageId";

		garage = new Garage();
		
		garage.setGarageId(garageId);
		
		garageRepository.save(garage);
	}
	
	@AfterEach
	public void clearDatabase() {

		//order matters
		garageRepository.deleteAll();
		appointmentRepository.deleteAll();

	}

	/*
	 * @author: James Darby
	 */

	//tests finding garage with garageId
	@Test
	public void testPersistAndLoadGarage() {

		garage = null;
		garage = garageRepository.findByGarageId(garageId);
		
		assertNotNull(garage);
		assertEquals(garage.getGarageId(), garageId);
	}

//	//tests finding garage via appointment
//	@Test
//	public void testPersistAndLoadGarageViaAppointment() {
//		
//		// create an appointment
//		int appointmentID = 1;
//
//		Appointment apt = new Appointment();
//		apt.setAppointmentId(appointmentID);
//		appointmentRepository.save(apt);
//
//		List<Appointment> appointments = new ArrayList<Appointment>();
//		appointments.add(apt);
//		garage.setAppointment(appointments);
//		garageRepository.save(garage);
//			
//
//		garage = null;
//		garage = garageRepository.findByAppointment(apt);
//		
//		assertNotNull(garage);
//		assertEquals(garage.getGarageId(), garageId);
//	}


}
