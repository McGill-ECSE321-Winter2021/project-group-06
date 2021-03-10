package ca.mcgill.ecse321.vehiclerepairshop.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;

public interface GarageRepository extends CrudRepository<Garage, String>{
	Garage findByAppointment(Appointment appointment);
	boolean existsByAppointment(Appointment appointment);
}
