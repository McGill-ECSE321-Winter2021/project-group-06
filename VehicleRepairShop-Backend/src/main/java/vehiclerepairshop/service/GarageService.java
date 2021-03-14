package vehiclerepairshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.GarageRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.AdminAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;


/**
 * @author aureliahaas
 *
 */
public class GarageService {
	@Autowired
	private GarageRepository garageRepository;


	/**
	 * Create a Garage with given parameters
	 * @param isAvailable
	 * @param offeredServiceId
	 * @return
	 */
	@Transactional
	public Garage createGarage(Boolean isAvailable, String garageId){
		if(isAvailable == null && (garageId == null || garageId.replaceAll("\\s+", "").length() == 0)){
			throw new InvalidInputException("IsAvailable and GarageId cannot be empty!");
		}
		else if (isAvailable == null) {
			throw new InvalidInputException("IsAvailable cannot be empty!");
		}
		else if (garageId == null || garageId.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("GarageId cannot be empty!");
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
		if (garageId == null || garageId.replaceAll("\\s+", "").length() == 0){
			throw new InvalidInputException("GarageId cannot be empty!");
		}
		Garage garage = garageRepository.findByGarageId(garageId);
		return garage;
	}

	/**
	 * Find if the garage exists by appointment
	 * @param appointment
	 * @return
	 */
	@Transactional
	public boolean garageExistsByAppointment(Appointment appointment) {
		boolean garageExists = garageRepository.existsByAppointment(appointment);
		return garageExists;
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
	public Garage updateGarage(String garageId, Boolean newIsAvailable) {
		if(garageId == null || garageId.trim().length()==0) {
			throw new InvalidInputException("the garageId cannot be empty!");
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