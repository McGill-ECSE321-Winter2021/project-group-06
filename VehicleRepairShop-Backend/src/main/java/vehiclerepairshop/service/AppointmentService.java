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
	
	// helper method that converts iterable to list
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	

}
