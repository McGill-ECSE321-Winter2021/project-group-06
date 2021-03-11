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
	
	
	/**
	 * Create a Garage with given parameters
	 * @param isAvailable
	 * @param offeredServiceId
	 * @return
	 */
	@Transactional
	public Garage createGarage(boolean isAvailable, String offeredServiceId) {
		Garage garage = new Garage();
		garage.setIsAvailable(isAvailable);
		garage.setGarageId(offeredServiceId);
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
	public List<Garage>getAllGarage(){
		Iterable<Garage> garages = garageRepository.findAll();
		return toList(garages);
	}

	// Helper method that converts iterable to list
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
}