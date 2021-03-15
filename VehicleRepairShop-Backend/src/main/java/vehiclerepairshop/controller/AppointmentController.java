package vehiclerepairshop.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import vehiclerepairshop.service.TechnicianAccountService;
import vehiclerepairshop.service.TimeSlotService;
import vehiclerepairshop.service.OfferedServiceService;


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
	private TechnicianAccountService technicianAccountService;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	@Autowired
	private OfferedServiceService offeredServiceService;
	
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
	
	/**
	 * get appointment by garage 
	 * @param garageDto
	 * @return
	 * @throws IllegalArgumentException
	 */
	@GetMapping(value = { "/getAppointmentByGarage/getGarageByGarageId/{garageId}", "/getAppointmentByGarage/getGarageByGarageId/{garageId}/" })
	public List<AppointmentDto> getAppointmentByGarage(@PathVariable("garageId") GarageDto garageDto)  throws IllegalArgumentException{
		return appointmentService.getAppointmentByGarage(convertToGarageDomainObject(garageDto)).stream().map(app->convertToDto(app)).collect(Collectors.toList());
	}
	
	/**
	 * get appointments by worker
	 * @param technicianAccountDto
	 * @return
	 * @throws IllegalArgumentException
	 */
	@GetMapping(value = { "/getAppointmentByWorker/getTechnicianAccountByUsername/{username}", "/getAppointmentByGarage/getTechnicianAccountByUsername/{username}/" })
	public List<AppointmentDto> getAppointmentByWorker(@PathVariable("username") TechnicianAccountDto technicianAccountDto)  throws IllegalArgumentException{
		return appointmentService.getAppointmentByWorker(convertToTechnicianAccountDomainObject(technicianAccountDto)).stream().map(app->convertToDto(app)).collect(Collectors.toList());
	}
	
//	TimeSlotDto timeSlotDto,
//	CarDto carDto, 
//	String comment, 
//	GarageDto garageDto, 
//	List<TechnicianAccountDto> workerDto, 
//	OfferedServiceDto serviceDto
//	/**
//	 * create an appointment 
//	 * @param appointment
//	 * @return
//	 */
//	@PostMapping(value = {"/createAppointment/{comment}/getTimeSlot/{id}/getCarByLicensePlate/{licensePlate}/getGarageByGarageId/{garageId}", 
//	"/createAppointment/{offeredServiceId}/{price}/{name}/{duration}/{reminderTime}/{reminderDate}/{description}/"})
//	public OfferedServiceDto createAppointment(@PathVariable("offeredServiceId")String offeredServiceId, 
//												@PathVariable("price")double price, 
//												@PathVariable("name")String name, 
//												@PathVariable("duration")int duration, 
//												@PathVariable("reminderTime")@DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm")Time reminderTime, 
//												@PathVariable("reminderDate")int reminderDate,
//												@PathVariable("description")String description) {
//		OfferedService createdOfferedService = offeredServiceService.createOfferedService(offeredServiceId,price, name, duration, reminderTime, reminderDate, description);
//		//OfferedServiceDto createdOfferedServiceDto = new OfferedServiceDto(offeredServiceId,price, name, duration, reminderTime, reminderDate, description);
//		OfferedServiceDto createdOfferedServiceDto = convertToDto(createdOfferedService);
//		return createdOfferedServiceDto;
//	}
	
	
	/**
	 * update an appointment garage
	 * @param appointment
	 * @return
	 */
	@PostMapping(value = {"/updateAppointmentGarage/{appointmentId}/getGarageByGarageId/{garageId}","/updateAppointmentGarage/{appointmentId}/getGarageByGarageId/{garageId}/"})
	public AppointmentDto updateAppointmentGarage(@PathVariable("appointmentId") int appointmentId,
												@PathVariable("garageId") GarageDto garageDto) {
		Appointment appointment = appointmentService.updateAppointmentGarage(appointmentId, convertToGarageDomainObject(garageDto));
		return convertToDto(appointment); 
	}
	
	
//	/**
//	 * update an appointment Worker 
//	 * @param appointment
//	 * @return
//	 */
//	@PostMapping(value = {"/updateAppointmentByCar/{appointmentId}/getTechnicianAccountByUsername/{username}"})
//	public AppointmentDto updateAppointmentWorker(@PathVariable("appointmentId") int appointmentId,
//												@PathVariable("licensePlate") CarDto carDto) {
//		Appointment appointment = appointmentService.updateAppointmentCar(appointmentId, convertToCarDomainObject(carDto));
//		return convertToDto(appointment); 
//	}
	
	
	/**
	 * update an appointment car
	 * @param appointment
	 * @return
	 */
	@PostMapping(value = {"/updateAppointmentCar/{appointmentId}/getCarByLicensePlate/{licensePlate}","/updateAppointmentCar/{appointmentId}/getCarByLicensePlate/{licensePlate}/"})
	public AppointmentDto updateAppointmentCar(@PathVariable("appointmentId") int appointmentId,
												@PathVariable("licensePlate") CarDto carDto) {
		Appointment appointment = appointmentService.updateAppointmentCar(appointmentId, convertToCarDomainObject(carDto));
		return convertToDto(appointment); 
	}
	
	
	/**
	 * update an appointment TimeSlot 
	 * @param appointment
	 * @return
	 */
	@PostMapping(value = {"/updateAppointmentOfferedService/{appointmentId}/getOfferedServiceById/{offeredServiceId}","/updateAppointmentOfferedService/{appointmentId}/getOfferedServiceById/{offeredServiceId}/"})
	public AppointmentDto updateAppointmentOfferedService(@PathVariable("appointmentId") int appointmentId,
															@PathVariable("offeredServiceId") OfferedServiceDto offeredServiceDto) {
		Appointment appointment = appointmentService.updateAppointmentOfferedService(appointmentId, convertToOfferedServiceDomainObject(offeredServiceDto));
		return convertToDto(appointment); 
	}
	
	
	/**
	 * update an appointment offeredService  
	 * @param appointment
	 * @return
	 */
	
	@PostMapping(value = {"/updateAppointmentTimeSlot/{appointmentId}/getTimeSlot/{id}","/updateAppointmentTimeSlot/{appointmentId}/getTimeSlot/{id}/"})
	public AppointmentDto updateAppointmentTimeSlot(@PathVariable("appointmentId") int appointmentId,
												@PathVariable("id") TimeSlotDto timeSlotDto) {
		Appointment appointment = appointmentService.updateAppointmentTimeSlot(appointmentId, convertToTimeSlotDomainObject(timeSlotDto));
		return convertToDto(appointment); 
	}
	
	/**
	 * update an appointment Comment
	 * @param appointment
	 * @return
	 */
	@PostMapping(value = {"/updateAppointmentTimeSlot/{appointmentId}/{comment}","/updateAppointmentTimeSlot/{appointmentId}/{comment}/"})
	public AppointmentDto updateAppointmentTimeSlot(@PathVariable("appointmentId") int appointmentId,
												@PathVariable("comment") String comment) {
		Appointment appointment = appointmentService.updateAppointmentComment(appointmentId, comment);
		return convertToDto(appointment);
	}
	
	
	/**
	 * delete an appointment by id 
	 * @param appointment
	 * @return
	 */
	
	@DeleteMapping(value = {"/deleteAppointmentById/{appointmentId}"})
	public AppointmentDto deleteAppointmentByid(@PathVariable("appointmentId") int appointmentId) {
		Appointment appointment = appointmentService.deleteAppointment(appointmentId);
		return convertToDto(appointment);
	}
	
	/**
	 * delete all appointment 
	 * @param appointment
	 * @return
	 */
	@DeleteMapping(value = {"/deleteAllAppointment", "/deleteAllAppointment/"})
	public List<AppointmentDto> deleteAllAppointment() {
		List<Appointment> appointments = appointmentService.deleteAllAppointment();
		return convertToDtoApplointmentList(appointments);
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
	/**
	 * Covert appointment to AppointmentDto
	 * @param appointment
	 * @return
	 */
	private AppointmentDto convertToDto(Appointment appointment) {
		if (appointment == null) {
			throw new IllegalArgumentException("There is no such appointment!");
		}
		AppointmentDto appointmentDto = new AppointmentDto(convertToTimeSlotDto(appointment.getTimeSlot()),
															convertToCarDto(appointment.getCar()),
															appointment.getComment(), 
															convertToGarageDto(appointment.getGarage()),
															convertToTechnicianAccountListDtos(appointment.getWorker()), 
															convertToOfferedServiceDto(appointment.getOfferedService()));
		return appointmentDto;
	}
	
	
	
	/**
	 * Convert OfferedService to offeredServiceDto
	 * @param s
	 * @return
	 */
	private OfferedServiceDto convertToOfferedServiceDto(OfferedService s) {
		if (s == null) {
			throw new IllegalArgumentException("There is no such OfferedService!");
		}
		
		OfferedServiceDto offerServiceDto = new OfferedServiceDto(s.getOfferedServiceId(), s.getPrice(), 
				s.getName(),s.getDuration(), s.getReminderTime(),s.getReminderDate(), s.getDescription());
		return offerServiceDto;
	}
	
	
	
	/**
	 * Convert a list of TechinicianAccounts to a list of TechnicianAccountDtos
	 * @param users
	 * @return
	 * @throws IllegalArgumentException
	 */
	private List<TechnicianAccountDto> convertToTechnicianAccountListDtos(List<TechnicianAccount> users) throws IllegalArgumentException{
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
	
	/**
	 * Helper method which can turn a garageDto to garage
	 * @param garageDto
	 * @return
	 * @throws IllegalArgumentException
	 */
	private Garage convertToGarageDomainObject(GarageDto garageDto) throws IllegalArgumentException{
		if (garageDto == null) {
			throw new IllegalArgumentException("There is no such garageDto!");
		}
		Garage garage = garageService.getGarageByGarageId(garageDto.getGarageId());
		return garage;
	}
	
	/**
	 * Helper method which can turn a technicianAccountDto to technicianAccount
	 * @param technicianAccountDto
	 * @return
	 * @throws IllegalArgumentException
	 */
	private TechnicianAccount convertToTechnicianAccountDomainObject(TechnicianAccountDto technicianAccountDto) throws IllegalArgumentException{
		if (technicianAccountDto == null) {
			throw new IllegalArgumentException("There is no such technicianAccountDto!");
		}
		TechnicianAccount technicianAccount = technicianAccountService.getTechnicianAccountByUsername(technicianAccountDto.getUsername());
		return technicianAccount;
	}
	
	
	/**
	 * Helper method which can turn a CarDto to Car
	 * @param carDto
	 * @return
	 */
	private Car convertToCarDomainObject(CarDto carDto) {
		if (carDto == null) {
			throw new IllegalArgumentException("There is no such carDto!");
		}
		Car car = carService.getCarByLicensePlate(carDto.getLicensePlate());
		return car;
	}
	
	/**
	 * Helper method which can turn a timeSlotDto to timeSlot
	 * @param timeSlotDto
	 * @return
	 */
	private TimeSlot convertToTimeSlotDomainObject(TimeSlotDto timeSlotDto) {
		if (timeSlotDto == null) {
			throw new IllegalArgumentException("There is no such timeSlotDto!");
		}
		TimeSlot timeSlot = timeSlotService.getTimeSlot(timeSlotDto.getTimeslotId());
		return timeSlot;
	}

	/**
	 * Helper method which can turn a OfferedServiceDto to OfferedService
	 * @param offeredServiceDto
	 * @return
	 */
	private OfferedService convertToOfferedServiceDomainObject(OfferedServiceDto offeredServiceDto) {
		if (offeredServiceDto == null) {
			throw new IllegalArgumentException("There is no such offeredServiceDto!");
		}
		OfferedService offeredService = offeredServiceService.getOfferedServiceByOfferedServiceId(offeredServiceDto.getOfferedServiceId());
		return offeredService;
	}
	
	
	/**
	 * convert TimeSlot to timeslotDto
	 * @param timeSlot
	 * @return
	 */
	private TimeSlotDto convertToTimeSlotDto(TimeSlot timeSlot) {
		if (timeSlot == null) {
			throw new IllegalArgumentException("There is no such timeslot!");
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
			CarDto carDto = new CarDto(car.getLicensePlate(), car.getModel(), car.getYear(), car.getMotorType(), car.getOwner(), 
					car.getAppointment().stream().map(a -> convertToDto(a)).collect(Collectors.toList()));
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
			throw new IllegalArgumentException("There is no such Garage");
		}

		GarageDto garageDto = new GarageDto(garage.getGarageId());
		return garageDto;
	}

	/**
	 * convert list of appointment to list of appointmentDto
	 * @param appointments
	 * @return
	 */
	// TimeSlotDto timeSlotDto,CarDto carDto, String comment, GarageDto garageDto, List<TechnicianAccountDto> workerDto, OfferedServiceDto serviceDto
	private List<AppointmentDto> convertToDtoApplointmentList(List<Appointment> appointments){
		List<AppointmentDto> aptDtos = new ArrayList<AppointmentDto>();
		if (appointments.size() == 0) {
			throw new IllegalArgumentException("There is no such Appointment List!");
		}
		for (Appointment apt: appointments) {
			AppointmentDto aptDto = new AppointmentDto( convertToTimeSlotDto(apt.getTimeSlot()),
														convertToCarDto(apt.getCar()), 
														apt.getComment(), 
														convertToGarageDto(apt.getGarage()),
														convertToTechnicianAccountListDtos(apt.getWorker()), 
														convertToOfferedServiceDto(apt.getOfferedService()));
			aptDtos.add(aptDto);
		}
		return aptDtos; 
		
	}

}
