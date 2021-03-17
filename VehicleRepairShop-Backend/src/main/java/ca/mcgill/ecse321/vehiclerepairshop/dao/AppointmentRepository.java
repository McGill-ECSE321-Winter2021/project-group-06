package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Appointment_data", path = "Appointment_data")
public interface AppointmentRepository extends CrudRepository<Appointment, Integer>{
	List<Appointment> findByCar(Car carLicense);
	List<Appointment> findByGarage(Garage garageID);
	List<Appointment> findByWorker(TechnicianAccount worker);
	Appointment findByCarAndTimeSlot(Car car, TimeSlot timeSlot);
	
}
