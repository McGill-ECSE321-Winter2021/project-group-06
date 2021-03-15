package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer>{
	List<Appointment> findByCar(Car carLicense);
	List<Appointment> findByGarage(Garage garageID);
	boolean existsByCarAndTimeSlot(Car carLicense, TimeSlot timeSlot);
	Appointment findByCarAndTimeSlot(Car car, TimeSlot timeSlot);
//	// added by mike
//	Appointment findByAppointmentId(int Id);
}
