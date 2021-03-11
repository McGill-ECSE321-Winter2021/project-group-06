package ca.mcgill.ecse321.vehiclerepairshop.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;

public interface OfferedServiceRepository  extends CrudRepository<OfferedService, String>{
	OfferedService findByAppointment(Appointment appointment);
	OfferedService findByOfferedServiceId (String Id);
}
