package ca.mcgill.ecse321.vehiclerepairshop.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.service.InvalidInputException;
import ca.mcgill.ecse321.vehiclerepairshop.dto.*;
import ca.mcgill.ecse321.vehiclerepairshop.service.*;

/**
 * 
 * @author mikewang
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class OfferedServiceController {

	@Autowired
	private OfferedServiceService offeredServiceService;
	@Autowired
	private AppointmentService appointmentService;

	// naming must be the same as the method name

	/**
	 * get all offered services from the persistence as DTO
	 * @return
	 */
	@GetMapping(value = {"/getAllOfferedServices", "/getAllOfferedServices/"})
	public List<OfferedServiceDto> getAllOfferedServices() {
		List<OfferedServiceDto> offeredServiceDtos = new ArrayList<>();
		for (OfferedService offeredService: offeredServiceService.getAllOfferedServices()) {
			offeredServiceDtos.add(convertToDto(offeredService));
		}
		return offeredServiceDtos;
	}

	/**
	 * get offered service by appointment as Dto
	 * @param appointmentId
	 * @return
	 */
	@GetMapping(value = {"/getOfferedServiceByAppointment/{appointmentId}", "/getOfferedServiceByAppointment/{appointmentId}/"})
	public OfferedServiceDto getOfferedServiceByAppointment(@PathVariable("appointmentId") int appointmentId) throws InvalidInputException{
		OfferedServiceDto foundedServiceDtos = new OfferedServiceDto();
		Appointment apt = appointmentService.getAppointmentById(appointmentId);
		OfferedService foundedService = offeredServiceService.getOfferedServiceByAppointment(apt);
		foundedServiceDtos = convertToDto(foundedService);
		return foundedServiceDtos;
	}

	/**
	 * get offered service by offeredServiceId 
	 * @param offeredServiceId
	 * @return
	 */
	@GetMapping(value = {"/getOfferedServiceById/{offeredServiceId}", "/getOfferedServiceById/{offeredServiceId}/"})
	public OfferedServiceDto getOfferedServiceById(@PathVariable("offeredServiceId") String offeredServiceId) throws InvalidInputException{
		OfferedServiceDto foundedServiceDtos = new OfferedServiceDto();
		OfferedService foundedService = offeredServiceService.getOfferedServiceByOfferedServiceId(offeredServiceId);
		foundedServiceDtos = convertToDto(foundedService);
		return foundedServiceDtos;
	}

	/**
	 * update offered service 
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
	@PutMapping(value = {"/updateOfferedService/{offeredServiceId}/{price}/{name}/{duration}/{reminderTime}/{reminderDate}/{description}", 
	"/updateOfferedService/{offeredServiceId}/{price}/{name}/{duration}/{reminderTime}/{reminderDate}/{description}/"})
	public OfferedServiceDto updateOfferedService(@PathVariable("offeredServiceId")String offeredServiceId, 
			@PathVariable("price")double price, 
			@PathVariable("name")String name, 
			@PathVariable("duration")int duration, 
			@PathVariable("reminderTime")String reminderTime, 
			@PathVariable("reminderDate")int reminderDate,
			@PathVariable("description")String description) throws InvalidInputException {
		OfferedServiceDto updatedServiceDtos = new OfferedServiceDto();
		OfferedService updatedService;
		try {
			updatedService = offeredServiceService.updateService(offeredServiceId,price, name, duration, Time.valueOf(reminderTime), reminderDate, description);
		} catch (InvalidInputException e) {
			throw new InvalidInputException(e.getMessage());
		}
		updatedServiceDtos = convertToDto(updatedService);
		return updatedServiceDtos; 
	}


	/**
	 * create an OfferedServiceDto
	 * @param offeredServiceId
	 * @param price
	 * @param name
	 * @param duration
	 * @param reminderTime
	 * @param reminderDate
	 * @param description
	 * @return
	 */
	@PostMapping(value = {"/createOfferedService/{offeredServiceId}/{price}/{name}/{duration}/{reminderTime}/{reminderDate}/{description}", 
	"/createOfferedService/{offeredServiceId}/{price}/{name}/{duration}/{reminderTime}/{reminderDate}/{description}/"})
	public OfferedServiceDto createOfferedService(@PathVariable("offeredServiceId")String offeredServiceId, 
			@PathVariable("price")double price, 
			@PathVariable("name")String name, 
			@PathVariable("duration")int duration, 
			@PathVariable("reminderTime")String reminderTime, 
			@PathVariable("reminderDate")int reminderDate,
			@PathVariable("description")String description) throws InvalidInputException{
		OfferedService createdOfferedService = offeredServiceService.createOfferedService(offeredServiceId,price, name, duration, Time.valueOf(reminderTime), reminderDate, description);
		//OfferedServiceDto createdOfferedServiceDto = new OfferedServiceDto(offeredServiceId,price, name, duration, reminderTime, reminderDate, description);
		OfferedServiceDto createdOfferedServiceDto = convertToDto(createdOfferedService);
		return createdOfferedServiceDto;
	}





	/**
	 * delete a offered service 
	 * @param offeredServiceId
	 * @return
	 * @throws InvalidInputException
	 */
	@DeleteMapping(value = {"/deleteOfferedServiceById/{offeredServiceId}", "/deleteOfferedServiceById/{offeredServiceId}/"})
	public OfferedServiceDto deleteOfferedServiceById(@PathVariable("offeredServiceId") String offeredServiceId) throws InvalidInputException{
		OfferedService offeredService = new OfferedService();
		try {
			offeredService = offeredServiceService.deleteOfferedService(offeredServiceId);
		}catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

		return convertToDto(offeredService);
	}






	// ---------------------------- Helper method ---------------------------
	/**
	 * converting OfferedService to type OfferedServiceDto
	 * @param s
	 * @return
	 */
	private OfferedServiceDto convertToDto(OfferedService s) {
		if (s == null) {
			throw new InvalidInputException("There is no such OfferedService!");
		}
		OfferedServiceDto offerServiceDto = new OfferedServiceDto(s.getOfferedServiceId(), s.getPrice(), 
				s.getName(),s.getDuration(), s.getReminderTime(),s.getReminderDate(), s.getDescription());
		return offerServiceDto;

	}






	//helper method
	/**
	 * Covert appointment to AppointmentDto
	 * @param appointment
	 * @return
	 */
	private AppointmentDto convertToAppointmentDto(Appointment appointment) {
		if (appointment == null) {
			throw new InvalidInputException("There is no such appointment!");
		}
		AppointmentDto appointmentDto = new AppointmentDto(convertToTimeSlotDto(appointment.getTimeSlot()),
				convertToCarDto(appointment.getCar()),
				appointment.getComment(), 
				convertToGarageDto(appointment.getGarage()),
				convertToTechnicianAccountListDtos(appointment.getWorker()), 
				convertToOfferedServiceDto(appointment.getOfferedService()),
				appointment.getAppointmentId());

		return appointmentDto;
	}



	/**
	 * convert TimeSlot to timeslotDto
	 * @param timeSlot
	 * @return
	 */
	private TimeSlotDto convertToTimeSlotDto(TimeSlot timeSlot) {
		if (timeSlot == null) {
			throw new InvalidInputException("There is no such timeslot!");
		}
		TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getStartTime(),timeSlot.getEndTime(),timeSlot.getStartDate(),timeSlot.getEndDate());
		return timeSlotDto;
	}







	/**
	 * Convert Car to carDto
	 * @param car
	 * @return
	 */
	private CarDto convertToCarDto(Car car)  {
		if (car == null) {
			return null;
		} else {
			CarDto carDto = new CarDto(car.getLicensePlate(), car.getModel(), car.getYear(), car.getMotorType());
			return carDto;
		}
	}



	/**
	 * convert garage to garageDto
	 * @param garage
	 * @return
	 */
	private GarageDto convertToGarageDto(Garage garage) {
		if (garage == null) {
			throw new InvalidInputException("There is no such Garage");
		}

		GarageDto garageDto = new GarageDto(garage.getGarageId());
		return garageDto;
	}


	/**
	 * Convert a list of TechinicianAccounts to a list of TechnicianAccountDtos
	 * @param users
	 * @return
	 * @throws InvalidInputException
	 */
	private List<TechnicianAccountDto> convertToTechnicianAccountListDtos(List<TechnicianAccount> users) throws InvalidInputException{
		List<TechnicianAccountDto> technicianAccountDtos = new ArrayList<TechnicianAccountDto>();
		if (users != null) {
			for (TechnicianAccount user:users) {
				TechnicianAccountDto technicianAccountDto = new TechnicianAccountDto(user.getUsername(), user.getPassword(), user.getName());
				technicianAccountDtos.add(technicianAccountDto);
			}	
		}
		return technicianAccountDtos;
	}




	/**
	 * Convert OfferedService to offeredServiceDto
	 * @param s
	 * @return
	 */
	private OfferedServiceDto convertToOfferedServiceDto(OfferedService s) {
		if (s == null) {
			throw new InvalidInputException("There is no such OfferedService!");
		}

		OfferedServiceDto offerServiceDto = new OfferedServiceDto(s.getOfferedServiceId(), s.getPrice(), 
				s.getName(),s.getDuration(), s.getReminderTime(),s.getReminderDate(), s.getDescription());
		return offerServiceDto;
	}

}
