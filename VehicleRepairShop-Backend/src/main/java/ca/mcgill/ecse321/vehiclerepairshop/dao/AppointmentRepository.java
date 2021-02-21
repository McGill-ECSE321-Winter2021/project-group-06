package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.Service;
import ca.mcgill.ecse321.vehiclerepairshop.model.Technician;

// we need to add an Appointment ID in the domain model
public interface AppointmentRepository extends CrudRepository<Appointment, Integer>{
	List<Appointment> findByCar(Car carLicience);
	List<Appointment> findByTechnician(Technician name);
	// we need to put grageId as our primary key in the domain model
	List<Appointment> findByGarage(Garage grageID);
	List<Appointment> findByTimeSlot(TimeSlot timeSlot);
	// we need to put service name as our primary key in the domain model
	List<Appointment> findByCarAndService(Car carLicience, Service name);
	boolean existsByCar(Car carLicience);
	boolean existsByCarAndTimeSlot(Car carLicience, TimeSlot timeSlot);
	boolean existsByGrageAndTimeSlot(Garage grageID, TimeSlot timeSlot);
	Appointment findByCarAndTimeSlot(Car car, TimeSlot timeSlot);
}
