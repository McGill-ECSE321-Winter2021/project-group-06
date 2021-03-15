package vehiclerepairshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.GarageRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;


/**
 * @author aureliahaas
 *
 */
public class GarageService {
	@Autowired
	private GarageRepository garageRepository;
	@Autowired
	GarageService garageService;


	/**
	 * Create a Garage with given parameters
	 * @param isAvailable
	 * @param offeredServiceId
	 * @return
	 */
	@Transactional
	public Garage createGarage(boolean isAvailable, String garageId){
		if (garageId == null || garageId.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("GarageId cannot be empty!");
		}
		else if (garageRepository.findByGarageId(garageId) != null) {
			throw new InvalidInputException("GarageId not available!");
		}

		Garage garage = new Garage();
		garage.setIsAvailable(isAvailable);
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
		Garage garage = garageRepository.findByGarageId(garageId);
		return garage;
	}


	/**
	 * Find garage through an appointment
	 * @param appointment
	 * @return
	 */
	@Transactional
	public Garage getGarageByAppointment(Appointment appointment) {
		Garage garage = garageRepository.findByAppointment(appointment);
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

	/**
	 * Update garage information by offering new information and garageId
	 */
	@Transactional
	public Garage updateGarage(String garageId, boolean newIsAvailable) {
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
		garage.setIsAvailable(newIsAvailable);
		garageRepository.save(garage);

		return garage;
	}
	
	/**
	 * Delete a Garage
	 * @param garageId
	 */
	@Transactional
	public boolean deleteGarage(String garageId) {
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
		else {
			garageRepository.delete(garage);
			return true;
		}	
	}


	/**
	 * Delete all the Business Information
	 */
	@Transactional
	public void deleteAllGarages() {
		garageRepository.deleteAll();
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