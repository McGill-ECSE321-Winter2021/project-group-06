package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;

public interface TechnicianAccountRepository extends CrudRepository<TechnicianAccount, String>{
	List<TechnicianAccount> findTechnicianAccountByName(String name); //name is not unique
	List<TechnicianAccount> findTechnicianAccountByAppointment(Appointment appointmentId);
	//List<TechnicianAccount> findByTimeSlot(TimeSlot timeSlotId); //can't be done because unidirectional
	//List<TechnicianAccount> findByAppointmentandTimeSlot(Appointment appointmentId, TimeSlot timeSlotid);
	TechnicianAccount findByUsername(String username); 
}