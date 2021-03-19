package ca.mcgill.ecse321.vehiclerepairshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<GarageDto> getAllGarages() {
		return garageService.getAllGarages().stream().map(garage->convertToDto(garage)).collect(Collectors.toList());
	}

	/**
	 * Get garage by garageId
	 * @param garageId
	 * @return
	 */
	@GetMapping(value = {"/getGarageByGarageId/{garageId}", "/getGarageByGarageId/{garageId}/"})
	public GarageDto getGarageByGarageId(@PathVariable("garageId") String garageId){
		return convertToDto(garageService.getGarageByGarageId(garageId));
	}

	/**
	 * Get garage by appointment
	 * @param appointmentId
	 * @return
	 */
	@GetMapping(value = {"/getGarageByAppointment/getAppointmentByAppointment/{appointmentId}", "/getGarageByAppointment/getAppointmentByAppointment/{appointmentId}/"})
	public GarageDto getGarageByAppointment(@PathVariable("appointmentId") int appointmentId){
		Appointment appointment = appointmentService.getAppointmentById(appointmentId);
		return convertToDto(garageService.getGarageByAppointment(appointment));
	}

	/**
	 * Create a garage with its specific parameters
	 * @param garageId
	 * @return
	 */
	@PostMapping(value = { "/createGarage/{garageId}","/createGarage/{garageId}"})
	public GarageDto createGarage(@PathVariable("garageId") String garageId){
		Garage garage = garageService.createGarage(garageId);
		return convertToDto(garage);
	}

	/**
	 * Delete a garage
	 * @param garageId
	 * @return
	 */
	@DeleteMapping(value = {"/deleteGarage/{garageId}","/deleteGarage/{garageId}/"})
	public GarageDto deleteGarage(@PathVariable("garageId") String garageId) {
		Garage garage = garageRepository.findByGarageId(garageId);
		garageService.deleteGarage(garageId);
		return convertToDto(garage);
	}

	/**
	 * Delete all the garages
	 * @return
	 */
	@DeleteMapping(value = {"/deleteAllGarages","/deleteAllGarages/"})
	public List<GarageDto> deleteAllGarages() {
		List<Garage> garages = garageService.deleteAllGarages();
		List<GarageDto> garageDtos = new ArrayList<GarageDto>();
		for (Garage garage : garages) {
			garageDtos.add(convertToDto(garage));
		}
		return garageDtos;
	}


	// ---------------------------- Helper method ---------------------------
	/**
	 * Convert Garage to type GarageDto
	 * @param garage
	 * @return
	 */
	private GarageDto convertToDto(Garage garage) {
		if (garage == null) {
			throw new InvalidInputException("There is no such Garage");
		}

		GarageDto garageDto = new GarageDto(garage.getGarageId());
		return garageDto;
	}
}


