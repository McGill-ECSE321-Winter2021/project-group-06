package vehiclerepairshop.controller;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import vehiclerepairshop.dto.AppointmentDto;
import vehiclerepairshop.dto.OfferedServiceDto;
import vehiclerepairshop.dto.TechnicianAccountDto;
import vehiclerepairshop.service.AppointmentService;
import vehiclerepairshop.service.InvalidInputException;
import vehiclerepairshop.service.OfferedServiceService;


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
	public List<OfferedServiceDto> getAllOfferedServices(){
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
	@GetMapping(value = {"/getOfferedServiceByAppointment/getAppointmentByAppointmentId/{appointmentId}", "/getOfferedServiceByAppointment/{appointmentId}/"})
	public OfferedServiceDto getOfferedServiceByAppointment(@PathVariable("appointmentId") AppointmentDto appointmentDto){
		OfferedServiceDto foundedServiceDtos = new OfferedServiceDto();
		OfferedService foundedService = offeredServiceService.getOfferedServiceByAppointment(convertToDomainObject(appointmentDto));
		foundedServiceDtos = convertToDto(foundedService);
		return foundedServiceDtos;
	}
	
	/**
	 * get offered service by offeredServiceId 
	 * @param offeredServiceId
	 * @return
	 */
	@GetMapping(value = {"/getOfferedServiceById/{offeredServiceId}", "/getOfferedServiceById/{offeredServiceId}/"})
	public OfferedServiceDto getOfferedServiceById(@PathVariable("offeredServiceId") String offeredServiceId){
		OfferedServiceDto foundedServiceDtos = new OfferedServiceDto();
		OfferedService foundedService = offeredServiceService.getOfferedServiceByOfferedSeriveId(offeredServiceId);
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
	 */
	@GetMapping(value = {"/updateOfferedService/{offeredServiceId}{offeredServiceId}/{price}/{name}/{duration}/{reminderTime}/{reminderDate}/{description}", 
			"/updateOfferedService/{offeredServiceId}/{price}/{name}/{duration}/{reminderTime}/{reminderDate}/{description}/"})
	public OfferedServiceDto updateOfferedService(@PathVariable("offeredServiceId")String offeredServiceId, 
												@PathVariable("price")String price, 
												@PathVariable("name")String name, 
												@PathVariable("duration")String duration, 
												@PathVariable("reminderTime")Time reminderTime, 
												@PathVariable("reminderDate")Date reminderDate,
												@PathVariable("description")String description) {
		OfferedServiceDto updatedServiceDtos = new OfferedServiceDto();
		OfferedService updatedService = offeredServiceService.updateService(offeredServiceId,price, name, duration, reminderTime, reminderDate, description);
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
	@GetMapping(value = {"/createOfferedService/{offeredServiceId}/{price}/{name}/{duration}/{reminderTime}/{reminderDate}/{description}", 
			"/createOfferedService/{offeredServiceId}/{price}/{name}/{duration}/{reminderTime}/{reminderDate}/{description}/"})
	public OfferedServiceDto createOfferedService(@PathVariable("offeredServiceId")String offeredServiceId, 
													@PathVariable("price")String price, 
													@PathVariable("name")String name, 
													@PathVariable("duration")String duration, 
													@PathVariable("reminderTime")Time reminderTime, 
													@PathVariable("reminderDate")Date reminderDate,
													@PathVariable("description")String description) {
		OfferedService createdOfferedService = offeredServiceService.createOfferedService(offeredServiceId,price, name, duration, reminderTime, reminderDate, description);
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
	@GetMapping(value = {"/deleteOfferedServiceById/{offeredServiceId}", "/deleteOfferedServiceById/{offeredServiceId}/"})
	public Boolean deleteOfferedServiceById(@PathVariable("offeredServiceId") String offeredServiceId) throws InvalidInputException{
		Boolean isDeleted = false;  
		 try {
			 offeredServiceService.deleteOfferedService(offeredServiceId);
		 }catch (RuntimeException e) {
			 throw new InvalidInputException(e.getMessage());
		 }
		 isDeleted = true;
		return isDeleted;
	}
	
	
	
	
	
	
	
	// ---------------------------- Helper method ---------------------------
	/**
	 * converting OfferedService to type OfferedServiceDto
	 * @param s
	 * @return
	 */
	private OfferedServiceDto convertToDto(OfferedService s) {
		if (s == null) {
			throw new IllegalArgumentException("There is no such OfferedService!");
		}
		
		OfferedServiceDto offerServiceDto = new OfferedServiceDto(s.getOfferedServiceId(), s.getPrice(), 
				s.getName(),s.getDuration(), s.getReminderTime(),s.getReminderDate(), s.getDescription());
		return offerServiceDto;
	}
	
	
	/**
	 *  convert dto to domain objects 
	 * @param a
	 * @return
	 */
	private Appointment convertToDomainObject(AppointmentDto a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such appointmentDto!");
		}
		
		// Appointment appointment = AppointmentService.getAppointmentById(a.getId());
		return null;
	}
	
}
