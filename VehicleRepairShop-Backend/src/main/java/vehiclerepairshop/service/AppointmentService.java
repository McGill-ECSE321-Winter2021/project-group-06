package vehiclerepairshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;

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
		appointment.setAppointmentId(timeSlot.getStartTime().hashCode()*service.getName().hashCode());
		appointment.setCar(car);
		appointment.setComment(comment);
		appointment.setGarage(garage);
		appointment.setOfferedService(service);
		appointment.setTimeSlot(timeSlot);
		appointment.setWorker(worker);
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
	public void deleteAppointment(int appointmentId) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		appointmentRepository.delete(appointment.get());
	}
	
	/**
	 * @author chengchen
	 */
	@Transactional
	public void deleteAllAppointment() {
		appointmentRepository.deleteAll();
	}
	
	/**
	 * @author chengchen
	 * @param appointmentId
	 * @param car
	 */
	@Transactional
	public void updateAppointmentCar(int appointmentId, Car car) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		appointment.get().setCar(car);
		appointmentRepository.save(appointment.get());
	}
	
	/**
	 * @author chengchen
	 * @param appointmentId
	 * @param garage
	 */
	@Transactional
	public void updateAppointmentGarage(int appointmentId, Garage garage) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		appointment.get().setGarage(garage);
		appointmentRepository.save(appointment.get());
	}
	
	/**
	 * @author chengchen
	 * @param appointmentId
	 * @param worker
	 */
	@Transactional
	public void updateAppointmentWorker(int appointmentId, TechnicianAccount worker) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		List<TechnicianAccount> technicianAccounts = new ArrayList<TechnicianAccount>();
		technicianAccounts.add(worker);
		appointment.get().setWorker(technicianAccounts);
		appointmentRepository.save(appointment.get());
	}
	
	/**
	 * @author chengchen
	 * @param appointmentId
	 * @param timeSlot
	 */
	@Transactional
	public void updateAppointmentTimeSlot(int appointmentId, TimeSlot timeSlot) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		appointment.get().setTimeSlot(timeSlot);
		appointmentRepository.save(appointment.get());
	
	}
	
	/**
	 * @author chengchen
	 * @param appointmentId
	 * @param comment
	 */
	@Transactional
	public void updateAppointmentComment(int appointmentId, String comment) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		appointment.get().setComment(comment);
		appointmentRepository.save(appointment.get());
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
