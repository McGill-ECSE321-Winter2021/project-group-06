package ca.mcgill.ecse321.vehiclerepairshop.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Service;

public interface ServiceRepository  extends CrudRepository<Service, String>{
	Service findByAppointment(Appointment appointment);
}
