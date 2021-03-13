package vehiclerepairshop.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.OfferedServiceRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;

/**
 * 
 * @author mikewang
 *
 */
public class OfferedServiceService {
	@Autowired
	private OfferedServiceRepository offeredServiceRepository;
	
	// --------------------------- Mike starts here --------------------------------
	
	/**
	 * create a service object
	 * @param offeredServiceId
	 * @param price
	 * @param name
	 * @param duration
	 * @param reminderTime
	 * @param reminderDate
	 * @param description
	 * @return
	 * @throws InvalidInputException 
	 */
	@Transactional
	public OfferedService createOfferedService(String offeredServiceId, Double price, String name, int duration, Time reminderTime, int reminderDate, String description) {
		String error ="";
		if (offeredServiceId == null || offeredServiceId.trim().length()==0) {
			error = error + "OfferedServiceId cannot be empty!";
		}
		if (price == null) {
			error = error + "price cannot be empty!";
		}
		if (price < 0.0) {
			error = error + "price cannot be Negative!";
		}
		if (name == null || name.trim().length()==0) {
			error = error + "name cannot be empty!";
		}
		if (duration == 0) {
			error = error + "duration cannot be zero!";
		}
		if (duration < 0) {
			error = error + "duration cannot be negative!";
		}
		if (reminderTime == null) {
			error = error + "reminderTime cannot be empty!";
		}
		if (reminderDate == 0) {
			error = error + "reminderDate cannot be zero!";
		}
		if (reminderDate < 0) {
			error = error + "reminderDate cannot be negative!";
		}
		if (description == null || description.trim().length()==0) {
			error = error + "Offered service description cannot be empty!";
		}
		if (error.length()>0) {
			throw new IllegalArgumentException(error);
		}
		
		OfferedService offeredService = new OfferedService();
		offeredService.setOfferedServiceId(offeredServiceId);
		offeredService.setPrice(price);
		offeredService.setName(name);
		offeredService.setDuration(duration);
		offeredService.setReminderTime(reminderTime);
		offeredService.setReminderDate(reminderDate);
		offeredService.setDescription(description);
		offeredServiceRepository.save(offeredService);
		return offeredService;
	}
	
	
	
	/**
	 * find offered service through an appointment 
	 * @param appointment
	 * @return
	 * @throws InvalidInputException 
	 */
	@Transactional
	public OfferedService getOfferedServiceByAppointment(Appointment appointment){
		
		if(appointment == null) {
			throw new IllegalArgumentException("appointment cannot be null!");
		}
		OfferedService offeredService = offeredServiceRepository.findByAppointment(appointment);
		return offeredService;
	}
	
	/**
	 * find offered service through a offered service id
	 * @param serviceId
	 * @return
	 * @throws InvalidInputException s
	 */
	@Transactional
	public  OfferedService getOfferedServiceByOfferedServiceId(String serviceId) {
		if(serviceId == null || serviceId.trim().length()==0) {
			throw new IllegalArgumentException("offeredServiceId cannot be null!");
		}
		OfferedService offeredService = offeredServiceRepository.findByOfferedServiceId(serviceId);
		return offeredService;
	}
	
	/**
	 * find all offered Service 
	 * @return
	 */
	@Transactional
	public List<OfferedService> getAllOfferedServices() {
		Iterable<OfferedService> offeredServices = offeredServiceRepository.findAll();
		return toList(offeredServices);
	}
	
	/**
	 * delete service in the repository 
	 * @param serviceId
	 * @throws InvalidInputException 
	 */
	@Transactional
	public boolean deleteOfferedService(String serviceId) throws InvalidInputException{
		String error = null;
		boolean isDeleted = false;
		if(serviceId == null || serviceId.trim().length()==0) {
			error = error + "the serviceId can not be empty!";
		}
		OfferedService offeredService = offeredServiceRepository.findByOfferedServiceId(serviceId);
		if (offeredService == null) {
			error = error + "the offered service can not found in the system!" ;
		}
		if (error.length() >0) {
			throw new InvalidInputException(error);
		}
		offeredServiceRepository.delete(offeredService);
		isDeleted = true;
		return isDeleted;
	}
	
	/**
	 * update service information by offering new information and offeredServiceId
	 * @param serviceId
	 * @param newPrice
	 * @param newName
	 * @param newDuration
	 * @param newReminderTime
	 * @param newReminderDate
	 * @param newDescription
	 * @throws InvalidInputException 
	 */
	@Transactional
	public OfferedService updateService(String serviceId, Double newPrice, String newName, int newDuration, 
										Time newReminderTime, int newReminderDate, String newDescription) throws InvalidInputException {
		
		String error = null;
		if(serviceId == null || serviceId.trim().length()==0) {
			throw new InvalidInputException("the serviceId can not be empty!");
		}
		if (newPrice == null) {
			error = error + "price cannot be empty!";
		}
		if (newPrice < 0.0) {
			error = error + "price cannot be Negative!";
		}
		if (newName == null || newName.trim().length()==0) {
			error = error + "name cannot be empty!";
		}
		if (newDuration == 0) {
			error = error + "duration cannot be zero!";
		}
		if (newDuration < 0) {
			error = error + "duration cannot be negative!";
		}
		if (newReminderTime == null) {
			error = error + "reminderTime cannot be empty!";
		}
		if (newReminderDate == 0) {
			error = error + "reminderDate cannot be zero!";
		}
		if (newReminderDate < 0) {
			error = error + "reminderDate cannot be negative!";
		}
		if (newDescription == null || newDescription.trim().length()==0) {
			error = error + "Offered service description cannot be empty!";
		}
		
		OfferedService offeredService = offeredServiceRepository.findByOfferedServiceId(serviceId);
		if(offeredService == null) {
			error = error + "the offered service can not found in the system!";
		}
		if (error.length()>0) {
			throw new IllegalArgumentException(error);
		}
		offeredService.setPrice(newPrice);
		offeredService.setName(newName);
		offeredService.setDuration(newDuration);
		offeredService.setReminderTime(newReminderTime);
		offeredService.setReminderDate(newReminderDate);
		offeredService.setDescription(newDescription);
		offeredServiceRepository.save(offeredService);
		
		return offeredService;
	}
	
	// helper method that converts iterable to list
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	// --------------------------- Mike ends here ----------------------------------
}
