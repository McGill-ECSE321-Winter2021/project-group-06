package vehiclerepairshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.vehiclerepairshop.dao.GarageRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import vehiclerepairshop.dto.AppointmentDto;
import vehiclerepairshop.dto.GarageDto;
import vehiclerepairshop.service.AppointmentService;
import vehiclerepairshop.service.GarageService;
import vehiclerepairshop.service.InvalidInputException;


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
	@GetMapping(value = {"/getGarageByGarageId/{garageId}", "//getGarageByGarageId/{garageId}/"})
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
	/**
	 * @param appointmentId
	 * @return
	 */
	@GetMapping(value = {"/getGarageByAppointment/getAppointmentByAppointmentId/{appointmentId}", "/getGarageByAppointment/{appointmentId}/"})
	public GarageDto getGarageByAppointment(@PathVariable("appointmentId") AppointmentDto appointmentId){
		GarageDto foundedGarage = new GarageDto();
		Garage garage = garageService.getGarageByAppointment(convertToDomainObject(appointmentId));
		foundedGarage = convertToDto(garage);
		return foundedGarage;
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
	@PostMapping(value = {"/updateGarage/{garageId}{isAvailable}", "/updateGarage/{garageId}{isAvailable}/"})
	public GarageDto updateGarage(@PathVariable("garageId")String garageId, @PathVariable("isAvailable")Boolean isAvailable) throws InvalidInputException{
		GarageDto updatedGarage = new GarageDto();
		Garage garage;
		try {
			garage = garageService.updateGarage(garageId, isAvailable);
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
	 */
	@Transactional
	public void deleteGarage(String garageId) {
		Garage garage = garageRepository.findByGarageId(garageId);
		garageRepository.delete(garage);
	}

	/**
	 * Delete all the garages
	 */
	@Transactional
	public void deleteAllGarages() {
		garageRepository.deleteAll();
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


