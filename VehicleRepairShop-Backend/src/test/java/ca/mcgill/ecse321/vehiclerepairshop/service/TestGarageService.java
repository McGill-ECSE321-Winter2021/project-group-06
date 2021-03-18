package ca.mcgill.ecse321.vehiclerepairshop.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.GarageRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;

/**
 * @author aureliahaas
 *
 */
@ExtendWith(MockitoExtension.class)
public class TestGarageService {
	@Mock
	private GarageRepository garageRepository;
	@Mock
	private AppointmentRepository appointmentRepository;

	@InjectMocks
	private GarageService garageService;

	private static final String GARAGE_ID1 = "garageId1";
	private static final String GARAGE_ID2 = "garageId2";
	private static final String NON_EXISTING_GARAGE_ID = "nonExistingGarageId";

	private Appointment appointment1 = new Appointment();
	private static final int APPOINTMENT_ID = 211;

	@BeforeEach
	public void setMockOutput() {
		lenient().when(garageRepository.findByGarageId(anyString())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(GARAGE_ID1)) {
				Garage garage = new Garage();
				garage.setGarageId(GARAGE_ID1);
				return garage;
			}
			else if (invocation.getArgument(0).equals(GARAGE_ID2)) {
				Garage garage = new Garage();
				garage.setGarageId(GARAGE_ID2);
				return garage;
			}
			else {
				return null;
			}
		});

		lenient().when(garageRepository.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
			Garage garage1 = new Garage();
			garage1.setGarageId(GARAGE_ID1);

			Garage garage2 = new Garage();
			garage2.setGarageId(GARAGE_ID2);

			List<Garage> garages = new ArrayList<Garage>();
			garages.add(garage1);
			garages.add(garage2);

			return garages;
		});

		lenient().when(garageRepository.findByAppointment(any(Appointment.class))).thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(appointment1)) {
				appointment1.setAppointmentId(APPOINTMENT_ID);
				List<Appointment> appointments = new ArrayList<Appointment>(); 
				appointments.add(appointment1);

				Garage garage = new Garage();
				garage.setGarageId(GARAGE_ID1);
				garage.setAppointment(appointments);
				return garage;
			}
			else {
				return null;
			}
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(garageRepository.save(any(Garage.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(appointmentRepository.save(any(Appointment.class))).thenAnswer(returnParameterAsAnswer);
	}

	//----------------------------------- createGarage --------------------------------------------------
	/**
	 * Testing createGarage 
	 */
	@Test
	public void testCreateGarage() {
		assertEquals(2, garageService.getAllGarages().size());

		String garageId = NON_EXISTING_GARAGE_ID;

		Garage garage = null;
		try {
			garage = garageService.createGarage(garageId);
		}catch (InvalidInputException e){
			fail(e.getMessage());
		}
		checkResultGarage(garage, garageId);
	}

	/**
	 * Testing when we are creating a garage with a taken garageId
	 */
	@Test
	public void testCreateGarageWithTakenGarageId() {
		assertEquals(2, garageService.getAllGarages().size());

		String error = null;

		String garageId = GARAGE_ID1;

		Garage garage = null;

		try {
			garage = garageService.createGarage(garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId not available!", error);
	}

	/**
	 * Testing when we are creating a null garage / null garageId
	 */
	@Test
	public void testCreateNullGarage() {
		assertEquals(2, garageService.getAllGarages().size());

		String error = null;

		String garageId = null;

		Garage garage = null;
		try {
			garage = garageService.createGarage(garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}

	/**
	 * Testing when we are creating a garage with an empty garage / empty garageId
	 */
	@Test
	public void testCreateGarageWithEmptyGarageId() {
		assertEquals(2, garageService.getAllGarages().size());

		String error = null;

		String garageId = "";

		Garage garage = null;
		try {
			garage = garageService.createGarage(garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}


	/**
	 * Testing when we are creating a garage with a spaced garage / spaced garageId
	 */
	@Test
	public void testCreateGarageWithSpacedGarageId() {
		assertEquals(2, garageService.getAllGarages().size());

		String error = null;

		String garageId = "  ";

		Garage garage = null;
		try {
			garage = garageService.createGarage(garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}

	//----------------------------------- getGarage --------------------------------------------------
	//----------------------------------- getGarageByGarageId ----------------------------------------
	/**
	 * Testing getGarageByGarageId
	 */
	@Test
	public void testGetGarageByGarageId() {
		assertEquals(2, garageService.getAllGarages().size());

		String garageId = GARAGE_ID1;

		Garage garage = null;
		try {
			garage = garageService.getGarageByGarageId(garageId);
		}catch (InvalidInputException e) {
			fail(e.getMessage());
		}
		checkResultGarage(garage, GARAGE_ID1);
	}

	/**
	 * Testing getGarage with a non-existing garageId
	 */
	@Test
	public void testGetGarageWithNonExsitingGarageId() {
		assertEquals(2, garageService.getAllGarages().size());

		String error = null;
		String garageId = NON_EXISTING_GARAGE_ID;

		Garage garage = null;
		try {
			garage = garageService.getGarageByGarageId(garageId);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId does not exist!", error);
	}

	/**
	 * Testing getGarage with a null garageId
	 */
	@Test
	public void testGetGarageWithNullGarageId() {
		assertEquals(2, garageService.getAllGarages().size());

		String error = null;
		String garageId = null;

		Garage garage = null;
		try {
			garage = garageService.getGarageByGarageId(garageId);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}

	/**
	 * Testing getGarage with an empty garageId
	 */
	@Test
	public void testGetGarageWithEmptyGarageId() {
		assertEquals(2, garageService.getAllGarages().size());

		String error = null;
		String garageId = "";

		Garage garage = null;
		try {
			garage = garageService.getGarageByGarageId(garageId);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}

	/**
	 * Testing getGarage with a spaced garageId
	 */
	@Test
	public void testGetGarageWithSpacedGarageId() {
		assertEquals(2, garageService.getAllGarages().size());

		String error = null;
		String garageId = "  ";

		Garage garage = null;
		try {
			garage = garageService.getGarageByGarageId(garageId);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}

	//----------------------------------- getGarageByAppointment ----------------------------------------
	/**
	 * Testing getGarage
	 */
	@Test
	public void testGetGarageByAppointment() {
		assertEquals(2, garageService.getAllGarages().size());

		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(appointment1);

		Garage garage = null;
		try {
			garage = garageService.getGarageByAppointment(appointment1);
		}catch (InvalidInputException e) {
			fail(e.getMessage());
		}
		assertNotNull(garage);
		checkResultGarage(garage, GARAGE_ID1);
		assertEquals(appointments, garage.getAppointment());
	}

	/**
	 * Testing getGarage with a null appointment
	 */
	@Test
	public void testGetGarageWithNullAppointment() {
		assertEquals(2, garageService.getAllGarages().size());
		Appointment appointment = null;
		String error = null;

		Garage garage = null;
		try {
			garage = garageService.getGarageByAppointment(appointment);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("Appointment cannot be empty!", error);
	}

	//----------------------------------- getAllGarages -------------------------------------------------
	/**
	 * Test getAllGarages
	 */
	@Test
	public void testGetAllGarages() {
		assertEquals(2, garageService.getAllGarages().size());

		List<Garage> garages = new ArrayList<Garage>();
		garages = garageService.getAllGarages();

		Garage garage1 = garages.get(0);
		checkResultGarage(garage1, GARAGE_ID1);
		Garage garage2 = garages.get(1);
		checkResultGarage(garage2, GARAGE_ID2);
	}

	//----------------------------------- 	deleteGarage --------------------------------------------------
	/**
	 * Testing deleteGarage
	 */
	@Test
	public void testDeleteGarage() {
		assertEquals(2, garageService.getAllGarages().size());

		String garageId = GARAGE_ID1;

		Garage deletedGarage = null;
		Garage garage = null;
		try {
			deletedGarage = garageService.deleteGarage(garageId);
		}catch (InvalidInputException e) {
			fail(e.getMessage());
		}
		garage = garageService.getGarageByGarageId(garageId);
		checkResultGarage(deletedGarage, garage.getGarageId());
		//checkResultGarage(deletedGarage, garage.getIsAvailable(), garage.getGarageId());
	}

	/**
	 * Testing deleteGarage with a non-existing garageId
	 */
	@Test
	public void testDeleteGarageWithNonExsitingGarageId() {
		assertEquals(2, garageService.getAllGarages().size());

		String error = null;
		String garageId = NON_EXISTING_GARAGE_ID;

		Garage garage = null;
		try {
			garage = garageService.deleteGarage(garageId);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId does not exist!", error);
	}

	/**
	 * Testing deleteGarage with a null garageId
	 */
	@Test
	public void testDeleteGarageWithNullGarageId() {
		assertEquals(2, garageService.getAllGarages().size());

		String error = null;
		String garageId = null;

		Garage garage = null;
		try {
			garage = garageService.deleteGarage(garageId);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}

	/**
	 * Testing deleteGarage with an empty garageId
	 */
	@Test
	public void testDeleteGarageWithEmptyGarageId() {
		assertEquals(2, garageService.getAllGarages().size());

		String error = null;
		String garageId = "";

		Garage garage = null;
		try {
			garage = garageService.deleteGarage(garageId);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}

	/**
	 * Testing deleteGarage with a spaced garageId
	 */
	@Test
	public void testDeleteGarageWithSpacedGarageId() {
		assertEquals(2, garageService.getAllGarages().size());

		String error = null;
		String garageId = "  ";

		Garage garage = null;
		try {
			garage = garageService.deleteGarage(garageId);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}

	/**
	 * Testing deleteAllGarages
	 */
	@Test
	public void testDeleteAllGarages() {
		assertEquals(2, garageService.getAllGarages().size());

		List<Garage> garages = new ArrayList<Garage>();
		garages = garageService.deleteAllGarages();

		Garage garage1 = garages.get(0);
		checkResultGarage(garage1, GARAGE_ID1);
		Garage garage2 = garages.get(1);
		checkResultGarage(garage2, GARAGE_ID2);	
	}

	//----------------------------------- helper method --------------------------------------------------

	/**
	 * This is a helper method which can help us determine if all the attribute satisfY the input value
	 * @param garage
	 * @param garageId
	 */
	private void checkResultGarage(Garage garage, String garageId) {
		assertNotNull(garage);
		assertEquals(garageId, garage.getGarageId());
	}
}

