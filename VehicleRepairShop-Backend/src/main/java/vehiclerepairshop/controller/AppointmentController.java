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
import vehiclerepairshop.service.CarService;
import vehiclerepairshop.service.GarageService;


/**
 * 
 * @author chengchen
 * @author mikewang
 *
 */

@CrossOrigin(origins = "*")
@RestController
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private GarageService garageService;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	/**
	 * get all appointment 
	 * @return
	 * @throws IllegalArgumentException
	 */
	@GetMapping(value = { "/getAllAppointment", "/getAllAppointment/" })
	public List<AppointmentDto> getAllAppointment() throws IllegalArgumentException {
		return appointmentService.getAllAppointments().stream().map(app->convertToDto(app)).collect(Collectors.toList());
	}
	
	/**
	 * get appointment by appointment id 
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	@GetMapping(value = {"/getAppointmentById/{id}","/getAppointmentById/{id}/"})
	public AppointmentDto getAppointmentById(@PathVariable("id") int id) throws IllegalArgumentException {
		return convertToDto(appointmentService.getAppointmentById(id));
	}
	
	/**
	 * get appointments by car
	 * @param carDto
	 * @return
	 * @throws IllegalArgumentException
	 */
	@GetMapping(value = { "/getAppointmentByCar/getCarByLicensePlate/{licensePlate}", "/getAppointmentByCar/{carId}/" })
	public List<AppointmentDto> getAppointmentByCar(@PathVariable("carId") CarDto carDto) throws IllegalArgumentException {
		return appointmentService.getAppointmentByCar(converToCarDomainObject(carDto)).stream().map(app->convertToDto(app)).collect(Collectors.toList());
	}
	
	
	@GetMapping(value = { "/getAppointmentByGarage/getGarageByGarageId/{garageId}", "/getAppointmentByGarage/getGarageByGarageId/{garageId}/" })
	public List<AppointmentDto> getAppointmentByGarage(@PathVariable("garageId") GarageDto garageDto)  throws IllegalArgumentException{
		return appointmentService.getAppointmentByGarage(convertToGarageDomainObject(garageDto)).stream().map(app->convertToDto(app)).collect(Collectors.toList());
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
	
	
	/**
	 * Helper method which can turn a carDto to car 
	 * @param carDto
	 * @return
	 * @throws IllegalArgumentException
	 */
	private Car converToCarDomainObject(CarDto carDto) throws IllegalArgumentException{
		if (carDto == null) {
			throw new IllegalArgumentException("There is no such carDto!");
		}
		
		Car car = carService.getCarByLicensePlate(carDto.getLicensePlate());
		return car;
		
	}
	
	
	private Garage convertToGarageDomainObject(GarageDto garageDto) throws IllegalArgumentException{
		if (garageDto == null) {
			throw new IllegalArgumentException("There is no such garageDto!");
		}
		Garage garage = garageService.
	}
	


}
