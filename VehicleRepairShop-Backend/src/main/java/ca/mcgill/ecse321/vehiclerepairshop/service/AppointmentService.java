package ca.mcgill.ecse321.vehiclerepairshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import java.sql.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import java.time.LocalTime;

@Service
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
	public Appointment createAppointment(TimeSlot timeSlot, OfferedService service, Car car, Garage garage, String comment, TechnicianAccount worker) {
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
		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		for (Appointment app:getAllAppointments()) {
			timeSlots.add(app.getTimeSlot());
		}

		for (TimeSlot timeSlot1:timeSlots) {
			if (isTimeslotOverlapped(timeSlot, timeSlot1)) {
				error = "this new timeslot conflicts with another existing timeslot";
			}

		}

		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}


		Appointment appointment = new Appointment();
		appointment.setCar(car);
		appointment.setComment(comment);
		appointment.setGarage(garage);
		appointment.setOfferedService(service);
		appointment.setTimeSlot(timeSlot);
		List<TechnicianAccount> technicianAccounts = new ArrayList<TechnicianAccount>();
		technicianAccounts.add(worker);
		appointment.setWorker(technicianAccounts);
		appointment.setAppointmentId(comment.hashCode());



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
		if (appointmentRepository.findByAppointmentId(id)==null) {
			error = error + "appointment not found";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		return appointmentRepository.findByAppointmentId(id);
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
			throw new InvalidInputException(error);
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
			throw new InvalidInputException(error);
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
			throw new InvalidInputException(error);
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
	public Appointment deleteAppointment(int appointmentId) throws InvalidInputException{
		String error = "";
		if (appointmentRepository.findByAppointmentId(appointmentId)==null) {
			error = error + "appointment not found";
			error = error.trim();
			throw new InvalidInputException(error);
				
		}else {
			
			Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);
			appointmentRepository.delete(appointment);
			return appointment;
		}
		
	
		
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
		if (appointmentRepository.findByAppointmentId(appointmentId)==null) {
			error = error + "appointment not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);
		appointment.setCar(car);
		appointmentRepository.save(appointment);
		return appointment;
	}

	/**
	 * @author chengchen
	 * @param appointmentId 
	 * @param garage
	 */
	@Transactional
	public Appointment updateAppointmentGarage(int appointmentId, Garage garage) {
		String error = "";
		if (appointmentRepository.findByAppointmentId(appointmentId)==null) {
			error = error + "appointment not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);
		appointment.setGarage(garage);
		appointmentRepository.save(appointment);
		return appointment;
	}


	/**
	 * @author chengchen
	 * @param appointmentId
	 * @param timeSlot
	 */
	@Transactional
	public Appointment updateAppointmentTimeSlot(int appointmentId, TimeSlot updatedTimeSlot) {
		String error = "";
		if (appointmentRepository.findByAppointmentId(appointmentId)==null) {
			error = error + "appointment not found";
		}

		Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);
		if (appointment != null) {
			//			TimeSlot existingTimeSlot = appointment.get().getTimeSlot();
			//			updatedTimeSlot.setTimeSlotId(existingTimeSlot.getTimeSlotId());
			List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
			for (Appointment app:getAllAppointments()) {
				timeSlots.add(app.getTimeSlot());
			}

			for (TimeSlot timeSlot:timeSlots) {
				if (appointment.getTimeSlot().getTimeSlotId() != timeSlot.getTimeSlotId()) {
					if (isTimeslotOverlapped(updatedTimeSlot, timeSlot)) {
						error = error + "this new timeslot conflicts with another existing timeslot";
					}
				}

			}
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		appointment.setTimeSlot(updatedTimeSlot);


		appointmentRepository.save(appointment);
		return appointment;

	}

	@Transactional
	public Appointment updateAppointmentOfferedService(int appointmentId, OfferedService offeredService) {
		String error = "";
		TimeSlot newTimeSlot = null;
		if (appointmentRepository.findByAppointmentId(appointmentId)==null || offeredService == null) {
			error = error + "appointment not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);
		if (appointment != null) {
			TimeSlot existingTimeSlot = appointment.getTimeSlot();
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
				if (appointment.getTimeSlot().getTimeSlotId() != timeSlot.getTimeSlotId()) {
					if (isTimeslotOverlapped(newTimeSlot, timeSlot)) {
						error = error + "this new timeslot conflicts with another existing timeslot";
					}
				}

			}

		}
		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		appointment.setTimeSlot(newTimeSlot);
		appointment.setOfferedService(offeredService);
		appointmentRepository.save(appointment);
		return appointment;	

	}



	/**
	 * @author chengchen
	 * @param appointmentId
	 * @param comment
	 */
	@Transactional
	public Appointment updateAppointmentComment(int appointmentId, String comment) {
		String error = "";
		if (appointmentRepository.findByAppointmentId(appointmentId)==null) {
			error = error + "appointment not found";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);
		appointment.setComment(comment);
		appointmentRepository.save(appointment);
		return appointment;
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
