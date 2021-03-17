package ca.mcgill.ecse321.vehiclerepairshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.vehiclerepairshop.dao.GarageRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.service.InvalidInputException;
import ca.mcgill.ecse321.vehiclerepairshop.dto.*;
import ca.mcgill.ecse321.vehiclerepairshop.service.*;


/**
 * @author aureliahaas
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class GarageController {
	@Autowired
	private GarageService garageService;

	@Autowired
	private GarageRepository garageRepository;

	@Autowired
	private AppointmentService appointmentService;

	/**
	 * Get all garages
	 * @return
	 */
	@GetMapping(value = {"/getAllGarages", "/getAllGarages/"})
	public List<GarageDto> getAllOfferedServices(){
		List<GarageDto> garageDto = new ArrayList<>();
		for (Garage garage: garageService.getAllGarages()) {
			garageDto.add(convertToDto(garage));
		}
		return garageDto;
	}

	/**
	 * Get garage by garageId
	 * @param garageId
	 * @return
	 */
	@GetMapping(value = {"/getGarageByGarageId/{garageId}", "/getGarageByGarageId/{garageId}/"})
	public GarageDto getGarageByGarageId(@PathVariable("garageId") String garageId){
		GarageDto foundedGarage = new GarageDto();
		Garage garage = garageService.getGarageByGarageId(garageId);
		foundedGarage = convertToDto(garage);
		return foundedGarage;
	}

	/**
	 * Get garage by appointment
	 * @param appointmentId
	 * @return
	 */
	@GetMapping(value = {"/getGarageByAppointment/getAppointmentByAppointmentId/{appointmentId}", "/getGarageByAppointment/getGarageByAppointment/{appointmentId}/"})
	public GarageDto getGarageByAppointment(@PathVariable("appointmentId") AppointmentDto appointmentId){
		GarageDto foundGarage = new GarageDto();
		Garage garage = garageService.getGarageByAppointment(convertToDomainObject(appointmentId));
		foundGarage = convertToDto(garage);
		return foundGarage;
	}

	/**
	 * Create a garage with its specific parameters
	 * @param garageId
	 * @param isAvailable
	 * @return
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createGarage/{garageId}/{isAvailable}","/createGarage/{garageId}/{isAvailable}/"})
	public GarageDto createGarage(@PathVariable("garageId") String garageId,
			@PathVariable("isAvailable") Boolean isAvailable) throws IllegalArgumentException {
		Garage garage = garageService.createGarage(isAvailable, garageId);
		return convertToDto(garage);
	}

	/**
	 * Update garage 
	 * @param garageId
	 * @param isAvailable
	 * @return
	 * @throws InvalidInputException
	 */
	@PostMapping(value = {"/updateGarage/{currentGarageId}/{isAvailable}/{garageId}", "/updateGarage/{currentGarageId}/{isAvailable}/{garageId}/"})
	public GarageDto updateGarage(@PathVariable("currentGarageId")String currentGarageId, @PathVariable("isAvailable")Boolean isAvailable, @PathVariable("garageId")String garageId) throws InvalidInputException{
		GarageDto updatedGarage = new GarageDto();
		Garage garage;
		try {
			garage = garageService.updateGarage(currentGarageId, isAvailable, garageId);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			throw new InvalidInputException(e.getMessage());
		}
		updatedGarage = convertToDto(garage);
		return updatedGarage; 
	}

	/**
	 * Delete a garage
	 * @param garageId
	 * @return
	 */
	@DeleteMapping(value = {"/deleteGarage/{garageId}","/deleteGarage/{garageId}/"})
	public boolean deleteGarage(@PathVariable("garageId") String garageId) {
		boolean isSuccess = false; 
		Garage garage = garageRepository.findByGarageId(garageId);
		garageRepository.delete(garage);
		isSuccess = true;
		return isSuccess;
	}

	/**
	 * Delete all the garages
	 * @return
	 */
	@DeleteMapping(value = {"/deleteAllGarages","/deleteAllGarages/"})
	public boolean deleteAllGarages() {
		boolean isSuccess = false;
		garageRepository.deleteAll();
		isSuccess = true;
		return isSuccess;
	}


	// ---------------------------- Helper method ---------------------------
	/**
	 * Convert Garage to type GarageDto
	 * @param garage
	 * @return
	 */
	private GarageDto convertToDto(Garage garage) {
		if (garage == null) {
			throw new IllegalArgumentException("There is no such Garage");
		}

		GarageDto garageDto = new GarageDto(garage.getGarageId());
		return garageDto;
	}


	/**
	 *  Convert dto to domain objects 
	 * @param appointmentDto
	 * @return
	 */
	private Appointment convertToDomainObject(AppointmentDto appointmentDto) {
		if (appointmentDto == null) {
			throw new IllegalArgumentException("There is no such appointmentDto!");
		}

		Appointment appointment = appointmentService.getAppointmentById(appointmentDto.getAppointmentId());
		return appointment;
	}
}


