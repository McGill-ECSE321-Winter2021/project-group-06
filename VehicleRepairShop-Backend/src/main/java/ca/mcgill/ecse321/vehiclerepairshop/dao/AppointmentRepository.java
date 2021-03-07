package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.Service;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;

// we need to add an Appointment ID in the domain model
public interface AppointmentRepository extends CrudRepository<Appointment, Integer>{
	List<Appointment> findByCar(Car carLicience);
	// we need to put grageId as our primary key in the domain model
	List<Appointment> findByGarage(Garage grageID);
	// we need to put service name as our primary key in the domain model
	boolean existsByCarAndTimeSlot(Car carLicience, TimeSlot timeSlot);
	Appointment findByCarAndTimeSlot(Car car, TimeSlot timeSlot);
}
