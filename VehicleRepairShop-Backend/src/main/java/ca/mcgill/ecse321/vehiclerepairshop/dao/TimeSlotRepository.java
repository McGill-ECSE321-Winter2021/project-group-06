package ca.mcgill.ecse321.vehiclerepairshop.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, String>{
	TimeSlot findByTimeSlotId(String Id);
	//empty cannot see any other classes
}
