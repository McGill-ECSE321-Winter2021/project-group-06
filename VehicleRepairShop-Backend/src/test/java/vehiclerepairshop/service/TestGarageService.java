package vehiclerepairshop.service;

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
	private static final boolean IS_AVAILABLE1 = false; 
	
	private static final String GARAGE_ID2 = "garageId2";
	private static final boolean IS_AVAILABLE2 = true;
	
	private static final String NON_EXISTING_GARAGE_ID = "nonExistingGarageId";
	private static final boolean IS_AVAILABLE = false; 
	
	private static final int APPOINTMENT_ID = 211;
	private static final String COMMENT = "comment";
	
	private static final int NON_EXISTING_APPOINTMENT_ID = 321;
	private static final String NON_EXISTING_COMMENT = "nonExistingComment";
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(garageRepository.findByGarageId(anyString())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(GARAGE_ID1)) {
				Garage garage = new Garage();
				garage.setGarageId(GARAGE_ID1);
				garage.setIsAvailable(IS_AVAILABLE1);
				return garage;
			}
			else if (invocation.getArgument(0).equals(GARAGE_ID2)) {
				Garage garage = new Garage();
				garage.setGarageId(GARAGE_ID2);
				garage.setIsAvailable(IS_AVAILABLE2);
				return garage;
			}
			else {
				return null;
			}
		});
		
		lenient().when(garageRepository.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
			Garage garage1 = new Garage();
			garage1.setGarageId(GARAGE_ID1);
			garage1.setIsAvailable(IS_AVAILABLE1);

			Garage garage2 = new Garage();
			garage2.setGarageId(GARAGE_ID2);
			garage2.setIsAvailable(IS_AVAILABLE2);

			List<Garage> garages = new ArrayList<Garage>();
			garages.add(garage1);
			garages.add(garage2);

			return garages;
		});
		
		lenient().when(garageRepository.findByAppointment(any())).thenAnswer( (InvocationOnMock invocation) -> {
			Appointment appointment = new Appointment();
			appointment.setAppointmentId(APPOINTMENT_ID);
			appointment.setComment(COMMENT);
			List<Appointment> appointments = new ArrayList<Appointment>();
			appointments.add(appointment);
			
			if (invocation.getArgument(0).equals(appointment)) {
				Garage garage = new Garage();
				garage.setGarageId(GARAGE_ID1);
				garage.setIsAvailable(IS_AVAILABLE1);
				appointment.setGarage(garage);
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
		
		boolean isAvailable = IS_AVAILABLE;
		String garageId = NON_EXISTING_GARAGE_ID;

		Garage garage = null;
		try {
			garage = garageService.createGarage(isAvailable,garageId);
		}catch (InvalidInputException e){
			fail();
		}
		checkResultGarage(garage, isAvailable, garageId);
	}
	
	/**
	 * Testing when we are creating a garage with a taken garageId
	 */
	@Test
	public void testCreateGarageWithTakenGarageId() {
		assertEquals(2, garageService.getAllGarages().size());
		
		String error = null;
		
		boolean isAvailable = IS_AVAILABLE;
		String garageId = GARAGE_ID1;

		Garage garage = null;
		
		try {
			garage = garageService.createGarage(isAvailable, garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId not available!", error);
	}
	
	/**
	 * Testing when we are creating a null garage (null garageId)
	 */
	@Test
	public void testCreateNullGarage() {
		assertEquals(2, garageService.getAllGarages().size());

		String error = null;

		boolean isAvailable = IS_AVAILABLE;
		String garageId = null;

		Garage garage = null;
		try {
			garage = garageService.createGarage(isAvailable, garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}
	
	/**
	 * Testing when we are creating a garage with an empty garageId
	 */
	@Test
	public void testCreateGarageWithEmptyGarageId() {
		assertEquals(2, garageService.getAllGarages().size());
		
		String error = null;
		
		boolean isAvailable = IS_AVAILABLE;
		String garageId = "";

		Garage garage = null;
		try {
			garage = garageService.createGarage(isAvailable, garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}
	
	
	/**
	 * Testing when we are creating a garage with a spaced garageId
	 */
	@Test
	public void testCreateGarageWithSpacedGarageId() {
		assertEquals(2, garageService.getAllGarages().size());
		
		String error = null;
		
		boolean isAvailable = IS_AVAILABLE;
		String garageId = "  ";

		Garage garage = null;
		try {
			garage = garageService.createGarage(isAvailable, garageId);
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
	public void testGetGarageByGarage() {
		assertEquals(2, garageService.getAllGarages().size());

		String garageId = GARAGE_ID1;

		Garage garage = null;
		try {
			garage = garageService.getGarageByGarageId(garageId);
		}catch (InvalidInputException e) {
			fail();//e.printStackTrace();
		}
		checkResultGarage(garage, IS_AVAILABLE1, GARAGE_ID1);
	}

	/**
	 * Testing getGarage with a non-existing name
	 */
	@Test
	public void testGetGarageWithNonExsitingName() {
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
	 * Testing getGarage with a null name
	 */
	@Test
	public void testGetGarageWithNullName() {
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
	 * Testing getGarage with an empty name
	 */
	@Test
	public void testGetGarageWithEmptyName() {
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
	 * Testing getGarage with a spaced name
	 */
	@Test
	public void testGetGarageWithSpacedName() {
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
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(APPOINTMENT_ID);
		appointment.setComment(COMMENT);
		assertEquals(1, garageService.getGarageByAppointment(appointment));

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
	
	/**
	 * Testing getGarage with a null appointment
	 */
	@Test
	public void testGetGarageWithNullAppointment() {
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(NON_EXISTING_APPOINTMENT_ID);
		appointment.setComment(NON_EXISTING_COMMENT);
		//assertEquals(1, garageService.getGarageByAppointment(appointment));

		Garage garage = null;
		try {
			garage = garageService.getGarageByAppointment(appointment);
		}catch (InvalidInputException e) {
			fail();//e.printStackTrace();
		}
		checkResultGarage(garage, IS_AVAILABLE1, GARAGE_ID1);
	}
	
	//----------------------------------- getAllGarages -------------------------------------------------
	/**
	 * Test getAllGarages
	 */
	@Test
	public void testGetAllGaragest() {
		assertEquals(2, garageService.getAllGarages().size());

		List<Garage> garages = new ArrayList<Garage>();
		garages = garageService.getAllGarages();

		Garage garage1 = garages.get(0);
		checkResultGarage(garage1, IS_AVAILABLE1, GARAGE_ID1);
		Garage garage2 = garages.get(1);
		checkResultGarage(garage2, IS_AVAILABLE2, GARAGE_ID2);
	}
	
	//----------------------------------- 	updateGarage --------------------------------------------------
	/**
	 * Testing updateGarage, update isAvailable and garageId
	 */
	@Test
	public void testUpdateGarage() {
		assertEquals(2, garageService.getAllGarages().size());

		String currentGarageId = GARAGE_ID1;
		boolean isAvailable = IS_AVAILABLE;
		String garageId = NON_EXISTING_GARAGE_ID;

		Garage garage = null;

		try {
			garage = garageService.updateGarage(currentGarageId, isAvailable, garageId);
		}catch (InvalidInputException e){
			fail();//e.printStackTrace();
		}
		checkResultGarage(garage, isAvailable, garageId);
	}

	/**
	 * Testing updateGarage with null parameters
	 */
	@Test
	public void testUpdateNullGarage() {
		assertEquals(2, garageService.getAllGarages().size());
		String error = null;

		String currentGarageId = null;
		boolean isAvailable = IS_AVAILABLE; //cannot be null
		String garageId = null;

		Garage garage = null;
		try {
			garage = garageService.updateGarage(currentGarageId, isAvailable, garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("CurrentGarageId cannot be empty!", error);
	}

	/**
	 * Testing updateGarage with a taken name
	 */
	@Test
	public void testUpdateGarageWithTakenName() {
		assertEquals(2, garageService.getAllGarages().size());
		
		String error = null;

		String currentGarageId = GARAGE_ID1;
		boolean isAvailable = IS_AVAILABLE;
		String garageId = GARAGE_ID2;

		Garage garage = null;
		try {
			garage = garageService.updateGarage(currentGarageId, isAvailable, garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId not available!", error);
	}

	/**
	 * Testing updateGarage with a null currentGarageId
	 */
	@Test
	public void testUpdateGarageWithNullCurrentGarageId() {
		assertEquals(2, garageService.getAllGarages().size());
		String error = null;

		String currentGarageId = null;
		boolean isAvailable = IS_AVAILABLE;
		String garageId = NON_EXISTING_GARAGE_ID;

		Garage garage = null;
		try {
			garage = garageService.updateGarage(currentGarageId, isAvailable, garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("CurrentGarageId cannot be empty!", error);
	}

	/**
	 * Testing updateGarage with an empty currentGarageId
	 */
	@Test
	public void testUpdateGarageWithEmptyCurrentGarageId() {
		assertEquals(2, garageService.getAllGarages().size());
		String error = null;

		String currentGarageId = "";
		boolean isAvailable = IS_AVAILABLE;
		String garageId = NON_EXISTING_GARAGE_ID;

		Garage garage = null;
		try {
			garage = garageService.updateGarage(currentGarageId, isAvailable, garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("CurrentGarageId cannot be empty!", error);
	}

	/**
	 * Testing updateGaragen with a spaced currentGarageId
	 */
	@Test
	public void testUpdateGarageWithSpacedCurrentGarageId() {
		assertEquals(2, garageService.getAllGarages().size());
		String error = null;

		String currentGarageId = "  ";
		boolean isAvailable = IS_AVAILABLE;
		String garageId = NON_EXISTING_GARAGE_ID;

		Garage garage = null;
		try {
			garage = garageService.updateGarage(currentGarageId, isAvailable, garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("CurrentGarageId cannot be empty!", error);
	}

	/**
	 * Testing updateGarage with a null garageId
	 */
	@Test
	public void testUpdateGarageWithNullGarageId() {
		assertEquals(2, garageService.getAllGarages().size());
		String error = null;

		String currentGarageId = GARAGE_ID1;
		boolean isAvailable = IS_AVAILABLE;
		String garageId = null;

		Garage garage = null;
		try {
			garage = garageService.updateGarage(currentGarageId, isAvailable, garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}

	/**
	 * Testing updateGarage with an empty garageId
	 */
	@Test
	public void testUpdateGarageWithEmptyGarageId() {
		assertEquals(2, garageService.getAllGarages().size());
		String error = null;

		String currentGarageId = GARAGE_ID1;
		boolean isAvailable = IS_AVAILABLE;
		String garageId = "";

		Garage garage = null;
		try {
			garage = garageService.updateGarage(currentGarageId, isAvailable, garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
	}

	/**
	 * Testing updateGarage with a spaced garageId
	 */
	@Test
	public void testUpdateGarageWithSpacedGarageId() {
		assertEquals(2, garageService.getAllGarages().size());
		String error = null;

		String currentGarageId = GARAGE_ID1;
		boolean isAvailable = IS_AVAILABLE;
		String garageId = "  ";

		Garage garage = null;
		try {
			garage = garageService.updateGarage(currentGarageId, isAvailable, garageId);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		assertNull(garage);
		assertEquals("GarageId cannot be empty!", error);
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
			fail();//e.printStackTrace();
		}
		garage = garageService.getGarageByGarageId(garageId);
		checkResultGarage(deletedGarage, garage.getIsAvailable(), garage.getGarageId());
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
		checkResultGarage(garage1, IS_AVAILABLE1, GARAGE_ID1);
		Garage garage2 = garages.get(1);
		checkResultGarage(garage2, IS_AVAILABLE2, GARAGE_ID2);	
	}
	
	//----------------------------------- helper method --------------------------------------------------

	/**
	 * This is a helper method which can help us determine if all the attribute satisfY the input value
	 * @param garage
	 * @param isAvailable
	 * @param garageId
	 */
	private void checkResultGarage(Garage garage, boolean isAvailable, String garageId) {
		assertNotNull(garage);
		assertEquals(isAvailable, garage.getIsAvailable());
		assertEquals(garageId, garage.getGarageId());
	}
}

