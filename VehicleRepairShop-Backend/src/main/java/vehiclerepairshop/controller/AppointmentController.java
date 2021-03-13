package vehiclerepairshop.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car.MotorType;
import vehiclerepairshop.dto.AppointmentDto;
import vehiclerepairshop.dto.CarDto;
import vehiclerepairshop.dto.GarageDto;
import vehiclerepairshop.dto.OfferedServiceDto;
import vehiclerepairshop.dto.TechnicianAccountDto;
import vehiclerepairshop.dto.TimeSlotDto;
import vehiclerepairshop.service.AppointmentService;

/**
 * 
 * @author chengchen
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@GetMapping(value = { "/getAllAppointment", "/getAllAppointment/" })
	public List<AppointmentDto> getAllAppointment() throws IllegalArgumentException {
		return appointmentService.getAllAppointments().stream().map(app->convertToDto(app)).collect(Collectors.toList());
	}
	
//	@PostMapping(value = { "/createTimeSlot/{startTime}/{endTime}/{startDate}/{endDate}","/createTimeSlot/{startTime}/{endTime}/{startDate}/{endDate}/"})
//	public TimeSlotDto createAppointment(@PathVariable("startTime") Time startTime,
//			@PathVariable("endTime") Time endTime,
//			@PathVariable("startDate") Date startDate,
//			@PathVariable("endDate") Date endDate) throws IllegalArgumentException {
//		TimeSlot timeSlot = timeSlotService.createTimeSlot(startTime,endTime,startDate,endDate);
//		return convertToDto(timeSlot);
//	}
//	
//	
	
	//helper method
	private AppointmentDto convertToDto(Appointment appointment) {
		if (appointment == null) {
			throw new IllegalArgumentException("There is no such appointment!");
		}
		AppointmentDto appointmentDto = new AppointmentDto(convertToDto(appointment.getTimeSlot()),convertToDto(appointment.getCar()),appointment.getComment(), 
										convertToDto(appointment.getGarage()),convertToDtos(appointment.getWorker()), convertToDto(appointment.getOfferedService()));
		return appointmentDto;
	}
	
	private OfferedServiceDto convertToDto(OfferedService s) {
		if (s == null) {
			throw new IllegalArgumentException("There is no such OfferedService!");
		}
		
		OfferedServiceDto offerServiceDto = new OfferedServiceDto(s.getOfferedServiceId(), s.getPrice(), 
				s.getName(),s.getDuration(), s.getReminderTime(),s.getReminderDate(), s.getDescription());
		return offerServiceDto;
	}
	
	private TimeSlotDto convertToDto(TimeSlot timeSlot) {
		if (timeSlot == null) {
			throw new IllegalArgumentException("There is no such timeslot!");
		}
		TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getStartTime(),timeSlot.getEndTime(),timeSlot.getStartDate(),timeSlot.getEndDate());
		return timeSlotDto;
	}
	
	
	private CarDto convertToDto(Car car)  {
		if (car == null) {
			return null;
		}
		else {
			CarDto carDto = new CarDto(car.getLicensePlate(), car.getModel(), car.getYear(), 
						   			car.getMotorType(), car.getOwner());
			return carDto;
		}
	}
	
	
	private GarageDto convertToDto(Garage garage)  {
		if (garage == null) {
			return null;
		}
		else {
			GarageDto garageDto = new GarageDto(garage.getGarageId());
			garageDto.setAvailability(garage.getIsAvailable());
			return garageDto;
		}
	}
	
	private List<TechnicianAccountDto> convertToDtos(List<TechnicianAccount> users) throws IllegalArgumentException{
		List<TechnicianAccountDto> technicianAccountDtos = new ArrayList<TechnicianAccountDto>();
		if (users == null) {
			throw new IllegalArgumentException("This user does not exist");
		}
		for (TechnicianAccount user:users) {
			TechnicianAccountDto technicianAccountDto = new TechnicianAccountDto(user.getUsername(), user.getPassword(), user.getName());
			technicianAccountDto.setAppointments(user.getAppointment().stream().map(c -> convertToDto(c)).collect(Collectors.toList()));
			technicianAccountDtos.add(technicianAccountDto);
		}
		
		
		return technicianAccountDtos;
	}
	
	
	
	


}
