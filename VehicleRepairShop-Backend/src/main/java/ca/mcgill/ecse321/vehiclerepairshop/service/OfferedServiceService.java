package ca.mcgill.ecse321.vehiclerepairshop.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.OfferedServiceRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;

/**
 *
 * @author mikewang
 *
 */
@Service
public class OfferedServiceService {
	@Autowired
	private OfferedServiceRepository offeredServiceRepository;
	@Autowired
	private AppointmentRepository appointmentRepository; 

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
	public OfferedService createOfferedService(String offeredServiceId, double price, String name, int duration, Time reminderTime, int reminderDate, String description) {

		String error ="";
		if (offeredServiceId == null || offeredServiceId.trim().length()==0) {
			error = error + "OfferedServiceId cannot be empty!";
		}
		if (isOfferedServiceIdAvailable(offeredServiceId) == false) {
			error = error + "can not create OfferedService with same Id!";
		}
		if (price < 0.0) {
			error = error + "price cannot be negative!";
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
			throw new InvalidInputException(error);
		}
		else {
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
			throw new InvalidInputException("offeredServiceId cannot be null!");
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
	 *
	 */
	@Transactional
	public OfferedService deleteOfferedService(String serviceId){
		String error = "";

		if(serviceId == null || serviceId.trim().length()==0) {
			error = error + "the serviceId can not be empty!";
		}
		if (offeredServiceRepository.findByOfferedServiceId(serviceId) == null) {
			error = error + "the offered service can not found in the system!" ;
		}
		if (error.length() >0) {
			throw new InvalidInputException(error);
		}
		OfferedService offeredService = offeredServiceRepository.findByOfferedServiceId(serviceId);
		if (appointmentRepository.findAll() != null) {
			List<Appointment> apts = (List<Appointment>) appointmentRepository.findAll();
			for (Appointment appointment: apts) {
				if (appointment.getOfferedService().getOfferedServiceId() == serviceId) {
					//appointment.setOfferedService(null);
					appointmentRepository.delete(appointment);
				}
			}
			
		}
		offeredServiceRepository.delete(offeredService);


		return offeredService;
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
	 */
	@Transactional
	public OfferedService updateService(String serviceId, double newPrice, String newName, int newDuration,
			Time newReminderTime, int newReminderDate, String newDescription){

		String error = "";
		if(serviceId == null || serviceId.trim().length()==0) {
			error = error + "the serviceId can not be empty!";
		}
		//		if (newPrice == null) {
		//			error = error + "price cannot be empty!";
		//		}
		if (newPrice < 0.0) {
			error = error + "price cannot be negative!";
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
			throw new InvalidInputException(error);
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
	
	
	/**
	 * This is service method which can find the offeredService by given appointment
	 * @param apt
	 * @return
	 */
	@Transactional
	public OfferedService getOfferedServiceByAppointment(Appointment apt) {
		String error = "";
		OfferedService returnedService = null;
		if (apt == null) {
			error = error + "appointment cannot be null!";
		}else {
			if (appointmentRepository.findByAppointmentId(apt.getAppointmentId())!= null ) {
				List<OfferedService> offeredServices = (List<OfferedService>) offeredServiceRepository.findAll();
				OfferedService extractedOfferedService = apt.getOfferedService();
				for(OfferedService service: offeredServices){
					if (service.getOfferedServiceId() == extractedOfferedService.getOfferedServiceId()) {
						returnedService = service;
					}
				}
				if (returnedService == null) {
					error = error + "this offeredService does not exist in the offeredServiceRepository!";
				}
			}else {
				error = error + "can not find this appointment in appointmentRepository!";
			}
		}
		if (error.length()>0) {
			throw new InvalidInputException(error);
		}
		return returnedService;
		
		
	}
	
	
	

	// helper method that converts iterable to list
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}


	/**
	 * helper method to determine if the offered Service id has been taken
	 * @param Id
	 * @return
	 */
	private boolean isOfferedServiceIdAvailable(String Id) {
		boolean available = false;
		if (offeredServiceRepository.findByOfferedServiceId(Id) == null) {
			available = true;
		}
		return available;
	}
}

// --------------------------- Mike ends here ----------------------------------
