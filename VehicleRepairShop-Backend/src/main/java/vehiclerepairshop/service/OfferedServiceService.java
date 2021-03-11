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
	 */
	@Transactional
	public OfferedService createOfferedService(String offeredServiceId, String price, String name, String duration, Time reminderTime, Date reminderDate, String description) {
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
	 */
	@Transactional
	public OfferedService getOfferedServiceByAppointment(Appointment appointment){
		OfferedService offeredService = offeredServiceRepository.findByAppointment(appointment);
		return offeredService;
	}
	
	/**
	 * find offered service through a offered service id
	 * @param serviceId
	 * @return
	 */
	@Transactional
	public  OfferedService getOfferedServiceByOfferedSeriveId(String serviceId) {
		OfferedService offeredService = offeredServiceRepository.findByOfferedServiceId(serviceId);
		return offeredService;
	}
	
	/**
	 * find all offered Service 
	 * @return
	 */
	@Transactional
	public List<OfferedService>getAllOfferedService() {
		Iterable<OfferedService> offeredServices = offeredServiceRepository.findAll();
		return toList(offeredServices);
	}
	
//	public void deleteOfferedService(String serviceId) {
//		OfferedService offeredService = offeredServiceRepository.findByOfferedServiceId(serviceId);
//		offeredService.delete();
//	}
	
	/**
	 * update service information by offering new information and offeredServiceId
	 * @param serviceId
	 * @param newPrice
	 * @param newName
	 * @param newDuration
	 * @param newReminderTime
	 * @param newReminderDate
	 * @param newDescription
	 */
	public void updateService(String serviceId, String newPrice, String newName, String newDuration, Time newReminderTime, Date newReminderDate, String newDescription) {
		OfferedService offeredService = offeredServiceRepository.findByOfferedServiceId(serviceId);
		offeredService.setPrice(newPrice);
		offeredService.setName(newName);
		offeredService.setDuration(newDuration);
		offeredService.setReminderTime(newReminderTime);
		offeredService.setReminderDate(newReminderDate);
		offeredService.setDescription(newDescription);
		offeredServiceRepository.save(offeredService);
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
