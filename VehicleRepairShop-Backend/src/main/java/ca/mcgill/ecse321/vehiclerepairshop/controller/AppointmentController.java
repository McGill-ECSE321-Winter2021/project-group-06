package ca.mcgill.ecse321.vehiclerepairshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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



	/**
	 * get all appointment 
	 * @return
	 * @throws InvalidInputException
	 */
	@GetMapping(value = { "/getAllAppointment", "/getAllAppointment/" })
	public List<AppointmentDto> getAllAppointment() throws InvalidInputException {
		return appointmentService.getAllAppointments().stream().map(app->convertToDto(app)).collect(Collectors.toList());
	}

	/**
	 * get appointment by appointment id 
	 * @param id
	 * @return
	 * @throws InvalidInputException
	 */
	@GetMapping(value = {"/getAppointmentById/{appointmentId}","/getAppointmentById/{appointmentId}/"})
	public AppointmentDto getAppointmentById(@PathVariable("appointmentId") int id) throws InvalidInputException {
		return convertToDto(appointmentService.getAppointmentById(id));
	}

	/**
	 * get appointments by car
	 * @param carDto
	 * @return
	 * @throws InvalidInputException
	 */
	@GetMapping(value = { "/getAppointmentByCar/{licensePlate}", "/getAppointmentByCar/{licensePlate}/" })
	public List<AppointmentDto> getAppointmentByCar(@PathVariable("licensePlate") String licensePlate) throws InvalidInputException {
		Car car = carService.getCarByLicensePlate(licensePlate);
		CarDto carDto = convertToCarDto(car);
		return appointmentService.getAppointmentByCar(converToCarDomainObject(carDto)).stream().map(app->convertToDto(app)).collect(Collectors.toList());
	}

	/**
	 * get appointment by garage 
	 * @param garageDto
	 * @return
	 * @throws InvalidInputException
	 */
	@GetMapping(value = { "/getAppointmentByGarage/{garageId}", "/getAppointmentByGarage/{garageId}/" })
	public List<AppointmentDto> getAppointmentByGarage(@PathVariable("garageId") String garageId)  throws InvalidInputException{
		Garage garage = garageService.getGarageByGarageId(garageId);
		GarageDto garageDto = convertToGarageDto(garage);
		return appointmentService.getAppointmentByGarage(convertToGarageDomainObject(garageDto)).stream().map(app->convertToDto(app)).collect(Collectors.toList());
	}

	/**
	 * get appointments by worker
	 * @param technicianAccountDto
	 * @return
	 * @throws InvalidInputException
	 */
	@GetMapping(value = { "/getAppointmentByWorker/{username}", "/getAppointmentByWorker/{username}/" })
	public List<AppointmentDto> getAppointmentByWorker(@PathVariable("username") String username)  throws InvalidInputException{
		TechnicianAccount technicianAccount = technicianAccountService.getTechnicianAccountByUsername(username);
		TechnicianAccountDto technicianAccountDto = convertToTechnicianAccountDto(technicianAccount);
		return appointmentService.getAppointmentByWorker(convertToTechnicianAccountDomainObject(technicianAccountDto)).stream().map(app->convertToDto(app)).collect(Collectors.toList());
	}

	/**
	 * create an appointment 
	 * @param appointment
	 * @return
	 */
	@PostMapping(value = {"/createAppointment/{comment}/{timeslotId}/{licensePlate}/{garageId}/{offeredServiceId}/{username}",
	"/createAppointment/{comment}/{timeslotId}/{licensePlate}/{garageId}/{offeredServiceId}/{username}/"})
	public AppointmentDto createAppointment(@PathVariable("comment")String comment,
			@PathVariable("timeslotId")int timeslotId,
			@PathVariable("licensePlate")String licensePlate,
			@PathVariable("garageId")String garageId,
			@PathVariable("offeredServiceId")String offeredServiceId,
			@PathVariable("username")String username) {
		TimeSlot timeSlot = timeSlotService.getTimeSlot(timeslotId);
		OfferedService offeredService = offeredServiceService.getOfferedServiceByOfferedServiceId(offeredServiceId);
		Car car = carService.getCarByLicensePlate(licensePlate);
		Garage garage = garageService.getGarageByGarageId(garageId);
		TechnicianAccount worker = technicianAccountService.getTechnicianAccountByUsername(username);
		Appointment appointment = appointmentService.createAppointment(timeSlot, 
				offeredService, 
				car, 
				garage, comment, worker)  ;
		
		return convertToDto(appointment);
	}

	/**
	 * update an appointment garage
	 * @param appointment
	 * @return
	 */
	@PutMapping(value = {"/updateAppointmentGarage/{appointmentId}/{garageId}","/updateAppointmentGarage/{appointmentId}/{garageId}/"})
	public AppointmentDto updateAppointmentGarage(@PathVariable("appointmentId") int appointmentId,
			@PathVariable("garageId") String garageId) {
		Garage garage = garageService.getGarageByGarageId(garageId);
		Appointment appointment = appointmentService.updateAppointmentGarage(appointmentId, garage);
		return convertToDto(appointment); 
	}




	/**
	 * update an appointment car
	 * @param appointment
	 * @return
	 */
	@PutMapping(value = {"/updateAppointmentCar/{appointmentId}/{licensePlate}","/updateAppointmentCar/{appointmentId}/{licensePlate}/"})
	public AppointmentDto updateAppointmentCar(@PathVariable("appointmentId") int appointmentId,
			@PathVariable("licensePlate") String licensePlate) {
		Car car = carService.getCarByLicensePlate(licensePlate);
		Appointment appointment = appointmentService.updateAppointmentCar(appointmentId, car);
		return convertToDto(appointment); 
	}


	/**
	 * update an appointment offeredservice 
	 * @param appointment
	 * @return
	 */
	@PutMapping(value = {"/updateAppointmentOfferedService/{appointmentId}/{offeredServiceId}","/updateAppointmentOfferedService/{appointmentId}/{offeredServiceId}/"})
	public AppointmentDto updateAppointmentOfferedService(@PathVariable("appointmentId") int appointmentId,
			@PathVariable("offeredServiceId") String offeredServiceId) {
		OfferedService offeredService = offeredServiceService.getOfferedServiceByOfferedServiceId(offeredServiceId);
		Appointment appointment = appointmentService.updateAppointmentOfferedService(appointmentId, offeredService);
		return convertToDto(appointment); 
	}


	/**
	 * update an appointment timeslot  
	 * @param appointment
	 * @return
	 */

	@PutMapping(value = {"/updateAppointmentTimeSlot/{appointmentId}/{id}","/updateAppointmentTimeSlot/{appointmentId}/{id}/"})
	public AppointmentDto updateAppointmentTimeSlot(@PathVariable("appointmentId") int appointmentId,
			@PathVariable("id") int id) {
		TimeSlot timeSlot = timeSlotService.getTimeSlot(id);
		Appointment appointment = appointmentService.updateAppointmentTimeSlot(appointmentId, timeSlot);
		return convertToDto(appointment); 
	}

	/**
	 * update an appointment Comment
	 * @param appointment
	 * @return
	 */
	@PutMapping(value = {"/updateAppointmentTimeSlot/{appointmentId}/{comment}","/updateAppointmentTimeSlot/{appointmentId}/{comment}/"})
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




	//helper method
	/**
	 * Covert appointment to AppointmentDto
	 * @param appointment
	 * @return
	 */
	private AppointmentDto convertToDto(Appointment appointment) {
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
				technicianAccountDto.setAppointments(user.getAppointment().stream().map(c -> convertToDto(c)).collect(Collectors.toList()));
				technicianAccountDtos.add(technicianAccountDto);
			}
		}
		return technicianAccountDtos;
	}

	
	private TechnicianAccountDto convertToTechnicianAccountDto(TechnicianAccount user) throws InvalidInputException{
		TechnicianAccountDto technicianAccountDto = new TechnicianAccountDto();
		if (user != null) {
				technicianAccountDto = new TechnicianAccountDto(user.getUsername(), user.getPassword(), user.getName());
				technicianAccountDto.setAppointments(user.getAppointment().stream().map(c -> convertToDto(c)).collect(Collectors.toList()));
			}
		return technicianAccountDto;
	}

	/**
	 * Helper method which can turn a carDto to car 
	 * @param carDto
	 * @return
	 * @throws InvalidInputException
	 */
	private Car converToCarDomainObject(CarDto carDto) throws InvalidInputException{
		if (carDto == null) {
			throw new InvalidInputException("There is no such carDto!");
		}

		Car car = carService.getCarByLicensePlate(carDto.getLicensePlate());
		return car;

	}

	/**
	 * Helper method which can turn a garageDto to garage
	 * @param garageDto
	 * @return
	 * @throws InvalidInputException
	 */
	private Garage convertToGarageDomainObject(GarageDto garageDto) throws InvalidInputException{
		if (garageDto == null) {
			throw new InvalidInputException("There is no such garageDto!");
		}
		Garage garage = garageService.getGarageByGarageId(garageDto.getGarageId());
		return garage;
	}

	/**
	 * Helper method which can turn a technicianAccountDto to technicianAccount
	 * @param technicianAccountDto
	 * @return
	 * @throws InvalidInputException
	 */
	private TechnicianAccount convertToTechnicianAccountDomainObject(TechnicianAccountDto technicianAccountDto) throws InvalidInputException{
		if (technicianAccountDto == null) {
			throw new InvalidInputException("There is no such technicianAccountDto!");
		}
		TechnicianAccount technicianAccount = technicianAccountService.getTechnicianAccountByUsername(technicianAccountDto.getUsername());
		return technicianAccount;
	}


	/**
	 * Helper method which can turn a CarDto to Car
	 * @param carDto
	 * @return
	 */
//	private Car convertToCarDomainObject(CarDto carDto) {
//		if (carDto == null) {
//			throw new InvalidInputException("There is no such carDto!");
//		}
//		Car car = carService.getCarByLicensePlate(carDto.getLicensePlate());
//		return car;
//	}

	/**
	 * Helper method which can turn a timeSlotDto to timeSlot
	 * @param timeSlotDto
	 * @return
	 */
//	private TimeSlot convertToTimeSlotDomainObject(TimeSlotDto timeSlotDto) {
//		if (timeSlotDto == null) {
//			throw new InvalidInputException("There is no such timeSlotDto!");
//		}
//		TimeSlot timeSlot = timeSlotService.getTimeSlot(timeSlotDto.getTimeslotId());
//		return timeSlot;
//	}

//	/**
//	 * Helper method which can turn a OfferedServiceDto to OfferedService
//	 * @param offeredServiceDto
//	 * @return
//	 */
//	private OfferedService convertToOfferedServiceDomainObject(OfferedServiceDto offeredServiceDto) {
//		if (offeredServiceDto == null) {
//			throw new InvalidInputException("There is no such offeredServiceDto!");
//		}
//		OfferedService offeredService = offeredServiceService.getOfferedServiceByOfferedServiceId(offeredServiceDto.getOfferedServiceId());
//		return offeredService;
//	}


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
			throw new InvalidInputException("There is no such Garage");
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
			throw new InvalidInputException("There is no such Appointment List!");
		}
		for (Appointment apt: appointments) {
			AppointmentDto aptDto = new AppointmentDto( convertToTimeSlotDto(apt.getTimeSlot()),
					convertToCarDto(apt.getCar()), 
					apt.getComment(), 
					convertToGarageDto(apt.getGarage()),
					convertToTechnicianAccountListDtos(apt.getWorker()), 
					convertToOfferedServiceDto(apt.getOfferedService()),
					apt.getAppointmentId());
			aptDtos.add(aptDto);
		}
		return aptDtos; 

	}

}
