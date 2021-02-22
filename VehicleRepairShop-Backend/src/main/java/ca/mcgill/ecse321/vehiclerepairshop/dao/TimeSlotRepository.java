package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, String>{
	List<TimeSlot> findByTechnician(TechnicianAccount technician);
	
	//assumes an appointment can only have 1 timeslot as in domain model
	TimeSlot findByAppointment (Appointment appointmentID);
	
}
