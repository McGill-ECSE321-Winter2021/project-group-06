package vehiclerepairshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import java.sql.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import java.time.LocalTime;

public class AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;
	/**
	 *  
	 * @param worker
	 * @param timeSlot
	 * @param service
	 * @param car
	 * @param garage
	 * @param comment
	 * @param appointmentId
	 * @return appointment
	 * @author chengchen
	 */
	@Transactional
	public Appointment createAppointment(List<TechnicianAccount> worker,TimeSlot timeSlot, OfferedService service, Car car, Garage garage, String comment) {
		Appointment appointment = new Appointment();
		
		appointment.setCar(car);
		appointment.setComment(comment);
		appointment.setGarage(garage);
		appointment.setOfferedService(service);
		appointment.setTimeSlot(timeSlot);
		appointment.setWorker(worker);
		appointment.setAppointmentId(timeSlot.getStartTime().hashCode()*service.getOfferedServiceId().hashCode());
		appointmentRepository.save(appointment);
		
		return appointment;
	}
	
	/**
	 * 
	 * @param id
	 * @return appointment
	 * @author chengchen
	 */
	@Transactional
	public Appointment getAppointmentById(int id) {
		Optional<Appointment> appointment = appointmentRepository.findById(id);
		return appointment.get();
		
	}
	
	/**
	 * 
	 * @param car
	 * @return appointments
	 * @author chengchen
	 */
	@Transactional
	public List<Appointment> getAppointmentByCar(Car car){
		List<Appointment> appointments = appointmentRepository.findByCar(car);
		return appointments;
	}
	
	/**
	 * 
	 * @param garage
	 * @return appointments
	 * @author chengchen
	 */
	@Transactional
	public List<Appointment> getAppointmentByGarage(Garage garage){
		List<Appointment> appointments = appointmentRepository.findByGarage(garage);
		return appointments;
	}
	
	/**
	 * 
	 * @return appointments
	 * @author chengchen
	 */
	@Transactional
	public List<Appointment> getAllAppointments(){
		Iterable<Appointment> appointments = appointmentRepository.findAll();
		return toList(appointments);
	}
	
	/**
	 * 
	 * @param appointmentId
	 * @author chengchen
	 */
	@Transactional
	public Appointment deleteAppointment(int appointmentId) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		appointmentRepository.delete(appointment.get());
		return appointment.get();
	}
	
	/**
	 * @author chengchen
	 */
	@Transactional
	public List<Appointment> deleteAllAppointment() {
		Iterable<Appointment> appointment = appointmentRepository.findAll();
		appointmentRepository.deleteAll();
		return toList(appointment);
	}
	
	/**
	 * @author chengchen
	 * @param appointmentId
	 * @param car
	 */
	@Transactional
	public Appointment updateAppointmentCar(int appointmentId, Car car) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		appointment.get().setCar(car);
		appointmentRepository.save(appointment.get());
		return appointment.get();
	}
	
	/**
	 * @author chengchen
	 * @param appointmentId
	 * @param garage
	 */
	@Transactional
	public Appointment updateAppointmentGarage(int appointmentId, Garage garage) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		appointment.get().setGarage(garage);
		appointmentRepository.save(appointment.get());
		return appointment.get();
	}
	
	/**
	 * @author chengchen
	 * @param appointmentId
	 * @param worker
	 */
	@Transactional
	public Appointment updateAppointmentWorker(int appointmentId, TechnicianAccount worker) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		List<TechnicianAccount> technicianAccounts = new ArrayList<TechnicianAccount>();
		technicianAccounts.add(worker);
		appointment.get().setWorker(technicianAccounts);
		appointmentRepository.save(appointment.get());
		return appointment.get();
	}
	
	/**
	 * @author chengchen
	 * @param appointmentId
	 * @param timeSlot
	 */
	@Transactional
	public void updateAppointmentStartTime(int appointmentId, Time startTime) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		TimeSlot existingTimeSlot = appointment.get().getTimeSlot();
		TimeSlot newTimeSlot = new TimeSlot();
		newTimeSlot.setStartTime(startTime);
		LocalTime endLocalTime = startTime.toLocalTime();
		Time endTime = Time.valueOf(endLocalTime.plusMinutes(appointment.get().getOfferedService().getDuration()));
		newTimeSlot.setEndTime(endTime);
		newTimeSlot.setStartDate(existingTimeSlot.getStartDate());
		newTimeSlot.setEndDate(existingTimeSlot.getEndDate());
		appointment.get().setTimeSlot(newTimeSlot);
		appointmentRepository.save(appointment.get());
	
	}
	
	/**
	 * @author chengchen
	 * @param appointmentId
	 * @param comment
	 */
	@Transactional
	public Appointment updateAppointmentComment(int appointmentId, String comment) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		appointment.get().setComment(comment);
		appointmentRepository.save(appointment.get());
		return appointment.get();
	}
	
	
	// helper method that converts iterable to list
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	

}
