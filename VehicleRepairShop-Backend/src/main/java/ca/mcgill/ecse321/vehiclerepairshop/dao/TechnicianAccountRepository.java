package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.model.UserAccount;

public interface TechnicianAccountRepository extends CrudRepository<TechnicianAccount, Integer>{
	List<TechnicianAccount> findByAppointment(Appointment appointmentId);
	List<TechnicianAccount> findByTimeSlot(TimeSlot timeSlotId);
	List<TechnicianAccount> findByAppointmentandTimeSlot(Appointment appointmentId, TimeSlot timeSlotid);
	TechnicianAccount findByUserAccount(UserAccount username); 
}