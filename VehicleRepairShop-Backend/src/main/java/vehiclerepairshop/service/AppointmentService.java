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
		String error = "";
		if (car == null) {
			error = error + "car cannot be empty! ";
		}
		if (garage == null) {
			error = error + "garage cannot be empty! ";
		}
		if (comment == null) {
			error = error + "comment cannot be empty! ";
		}
		if (service == null) {
			error = error + "service cannot be empty! ";
		}
		if (timeSlot == null) {
			error = error + "timeslot cannot be empty! ";
		}
		if (worker == null || worker.isEmpty()) {
			error = error + "technicians cannot be empty! ";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

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
		String error = "";
		if (appointmentRepository.findById(id)==null) {
			error = error + "appointment not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
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
		String error = "";
		if (appointmentRepository.findByCar(car) == null) {
			error = error + "appointment not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(appointmentRepository.findByCar(car).get(0));
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
		String error = "";
		if (appointmentRepository.findByGarage(garage)==null) {
			error = error + "appointment not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		List<Appointment> appointments = appointmentRepository.findByGarage(garage);
		return appointments;
	}


	@Transactional
	public List<Appointment> getAppointmentByWorker(TechnicianAccount worker){
		String error = "";
		if (appointmentRepository.findByWorker(worker)==null) {
			error = error + "appointment not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		List<Appointment> appointments = appointmentRepository.findByWorker(worker);
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
		String error = "";
		if (appointmentRepository.findById(appointmentId)==null) {
			error = error + "appointment not found";
		}
		error = error.trim();
	    if (error.length() > 0) {
	        throw new IllegalArgumentException(error);
	    }
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
		String error = "";
		if (appointmentRepository.findById(appointmentId)==null) {
			error = error + "appointment not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
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
		String error = "";
		if (appointmentRepository.findById(appointmentId)==null) {
			error = error + "appointment not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
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
	public Appointment updateAppointmentWorker(int appointmentId, List<TechnicianAccount> workers) {
		String error = "";
		if (appointmentRepository.findById(appointmentId)==null) {
			error = error + "appointment not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		appointment.get().setWorker(workers);
		appointmentRepository.save(appointment.get());
		return appointment.get();
	}

	/**
	 * @author chengchen
	 * @param appointmentId
	 * @param timeSlot
	 */
	@Transactional
	public Appointment updateAppointmentTimeSlot(int appointmentId, TimeSlot updatedTimeSlot) {
		String error = "";
		if (appointmentRepository.findById(appointmentId)==null) {
			error = error + "appointment not found";
		}

		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		if (appointment != null) {
			TimeSlot existingTimeSlot = appointment.get().getTimeSlot();
			updatedTimeSlot.setTimeSlotId(existingTimeSlot.getTimeSlotId());
			List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
			for (Appointment app:getAllAppointments()) {
				timeSlots.add(app.getTimeSlot());
			}

			for (TimeSlot timeSlot:timeSlots) {
				if (updatedTimeSlot.getTimeSlotId() != timeSlot.getTimeSlotId()) {
					if (isTimeslotOverlapped(updatedTimeSlot, timeSlot)) {
						error = error + "this new timeslot conflicts with another existing timeslot";
					}
				}

			}
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		appointment.get().setTimeSlot(updatedTimeSlot);


		appointmentRepository.save(appointment.get());
		return appointment.get();

	}

	@Transactional
	public Appointment updateAppointmentOfferedService(int appointmentId, OfferedService offeredService) {
		String error = "";
		TimeSlot newTimeSlot = null;
		if (appointmentRepository.findById(appointmentId)==null) {
			error = error + "appointment not found";
		}
		
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		if (appointment != null) {
			TimeSlot existingTimeSlot = appointment.get().getTimeSlot();
			LocalTime endLocalTime = existingTimeSlot.getStartTime().toLocalTime();
			newTimeSlot = new TimeSlot();
			Time endTime = Time.valueOf(endLocalTime.plusMinutes(offeredService.getDuration()));
			newTimeSlot.setEndTime(endTime);
			newTimeSlot.setStartTime(existingTimeSlot.getStartTime());
			newTimeSlot.setStartDate(existingTimeSlot.getStartDate());
			newTimeSlot.setEndDate(existingTimeSlot.getEndDate());
			newTimeSlot.setTimeSlotId(existingTimeSlot.getTimeSlotId());
			List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
			for (Appointment app:getAllAppointments()) {
				timeSlots.add(app.getTimeSlot());
			}

			for (TimeSlot timeSlot:timeSlots) {
				if (newTimeSlot.getTimeSlotId() != timeSlot.getTimeSlotId()) {
					if (isTimeslotOverlapped(newTimeSlot, timeSlot)) {
						error = error + "this new timeslot conflicts with another existing timeslot";
					}
				}

			}
			
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		appointment.get().setTimeSlot(newTimeSlot);
		appointment.get().setOfferedService(offeredService);
		appointmentRepository.save(appointment.get());
		return appointment.get();	

	}



	/**
	 * @author chengchen
	 * @param appointmentId
	 * @param comment
	 */
	@Transactional
	public Appointment updateAppointmentComment(int appointmentId, String comment) {
		String error = "";
		if (appointmentRepository.findById(appointmentId)==null) {
			error = error + "appointment not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		appointment.get().setComment(comment);
		appointmentRepository.save(appointment.get());
		return appointment.get();
	}
	/**
	 * @author chengchen
	 * @return
	 */
	private boolean isTimeslotOverlapped(TimeSlot timeSlot1, TimeSlot timeSlot2){
		boolean isConflict = false;
		if ((timeSlot1.getStartDate().before(timeSlot2.getEndDate())&&timeSlot1.getEndDate().before(timeSlot2.getStartDate())) || (timeSlot2.getStartDate().before(timeSlot1.getEndDate())&&timeSlot2.getEndDate().before(timeSlot1.getStartDate()))) {
			return isConflict;
		}
		if ((timeSlot1.getStartTime().before(timeSlot2.getEndTime())&&timeSlot1.getEndTime().before(timeSlot2.getStartTime())) || (timeSlot2.getStartTime().before(timeSlot1.getEndTime())&&timeSlot2.getEndTime().before(timeSlot1.getStartTime()))) {
			return isConflict;
		}
		else {
			isConflict = true;
			return isConflict;
		}
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
