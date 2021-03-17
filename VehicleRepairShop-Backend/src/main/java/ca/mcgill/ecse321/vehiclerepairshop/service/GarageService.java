package ca.mcgill.ecse321.vehiclerepairshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.GarageRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;

/**
 * @author aureliahaas
 *
 */
@Service
public class GarageService {
	@Autowired
	private GarageRepository garageRepository;
	@Autowired
	GarageService garageService;

	/**
	 * Create a Garage with given parameters
	 * @param isAvailable
	 * @param garageId
	 * @return
	 */
	@Transactional
	public Garage createGarage(String garageId){
		if (garageId == null || garageId.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("GarageId cannot be empty!");
		}
		else if (garageRepository.findByGarageId(garageId) != null) {
			throw new InvalidInputException("GarageId not available!");
		}

		Garage garage = new Garage();
		garage.setGarageId(garageId);
		garageRepository.save(garage);
		
		return garage;
	}

	/**
	 * Find garage through a garage id
	 * @param garageId
	 * @return
	 */
	@Transactional
	public Garage getGarageByGarageId(String garageId) {
		if (garageId == null || garageId.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("GarageId cannot be empty!");
		}
		else if (garageRepository.findByGarageId(garageId) == null) {
			throw new InvalidInputException("GarageId does not exist!");
		}
		
		Garage garage = garageRepository.findByGarageId(garageId);
		if(garage == null) {
			throw new InvalidInputException("The garage is not found in the system!");
		}
		return garage;
	}
	
	/**
	 * Find garage through an appointment
	 * @param appointment
	 * @return
	 */
	@Transactional
	public Garage getGarageByAppointment(Appointment appointment) {
		if (appointment == null) {
			throw new InvalidInputException("Appointment cannot be empty!");
		}
		
		Garage garage = garageRepository.findByAppointment(appointment);
		if (garage == null) {
			throw new InvalidInputException("The garage is not found in the system!");
		}
		return garage;
	}

	/**
	 * Find all the garages
	 * @return
	 */
	@Transactional
	public List<Garage>getAllGarages(){
		Iterable<Garage> garages = garageRepository.findAll();
		return toList(garages);
	}

//	/**
//	 * Update garage information by offering new information and garageId
//	 * @param currentGarageId
//	 * @param newIsAvailable
//	 * @param garageId
//	 * @return
//	 */
//	@Transactional
//	public Garage updateGarage(String currentGarageId, boolean newIsAvailable) {
//		if (currentGarageId == null || currentGarageId.replaceAll("\\s+", "").length() == 0){
//			throw new InvalidInputException("CurrentGarageId cannot be empty!");
//		}
////		else if (garageId == null || garageId.replaceAll("\\s+", "").length() == 0){
////			throw new InvalidInputException("GarageId cannot be empty!");
////		}
//		else if (garageRepository.findByGarageId(currentGarageId) == null) {
//			throw new InvalidInputException("CurrentGarageId does not exist!");
//		}
////		else if (garageRepository.findByGarageId(garageId) != null) {
////			throw new InvalidInputException("GarageId not available!");
////		}
//		
//		Garage garage = garageRepository.findByGarageId(currentGarageId);
//		if(garage == null) {
//			throw new InvalidInputException("The garage is not found in the system!");
//		}
//
//		garageRepository.save(garage);
//
//		return garage;
//	}
	
	/**
	 * Delete a Garage
	 * @param garageId
	 */
	@Transactional
	public Garage deleteGarage(String garageId) {
		if (garageId == null || garageId.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("GarageId cannot be empty!");
		}
		else if (garageRepository.findByGarageId(garageId) == null) {
			throw new InvalidInputException("GarageId does not exist!");
		}
		
		Garage garage = garageRepository.findByGarageId(garageId);
		if(garage == null) {
			throw new InvalidInputException("The garage cannot be found.");
		}
		
		garageRepository.delete(garage);
		return garage;
	}

	/**
	 * Delete all the Garages
	 */
	@Transactional
	public List<Garage> deleteAllGarages() {
		Iterable<Garage> garages = garageRepository.findAll();
		garageRepository.deleteAll();
		return toList(garages);
	}


	// ---------------------------- Helper method ---------------------------
	// Converts iterable to list
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}