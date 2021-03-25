package ca.mcgill.ecse321.vehiclerepairshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
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
	private AppointmentRepository appointmentRepository;
	@Autowired
	GarageService garageService;

	/**
	 * Create a Garage with given parameters
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
	 * Find all the garages
	 * @return
	 */
	@Transactional
	public List<Garage> getAllGarages(){
		Iterable<Garage> garages = garageRepository.findAll();
		return toList(garages);
	}

	/**
	 * Delete a Garage
	 * @param garageId
	 * @return
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
	 * @return
	 */
	@Transactional
	public List<Garage> deleteAllGarages() {
		Iterable<Garage> garages = garageRepository.findAll();
		garageRepository.deleteAll();
		return toList(garages);
	}

	/**
	 * Find Garage by appointmentId
	 * @param appointmentId
	 * @return
	 */
	@Transactional
	public Garage findGarageByAppointment(int appointmentId){
		List<Appointment> appointments = toList(appointmentRepository.findAll());
		for (Appointment appointment:appointments) {
			if (appointment.getAppointmentId() == appointmentId) {
				return appointment.getGarage();
			}
		}
		throw new InvalidInputException("AppointmentId not found!");

	}


	// ---------------------------- Helper method ---------------------------
	/* Converts iterable to list
	 * @param <T>
	 * @param iterable
	 * @return
	 */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}