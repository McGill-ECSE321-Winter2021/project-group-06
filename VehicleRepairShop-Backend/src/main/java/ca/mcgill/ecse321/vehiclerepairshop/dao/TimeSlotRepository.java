package ca.mcgill.ecse321.vehiclerepairshop.dao;


import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, Integer>{
	TimeSlot findByTimeSlotId(int Id);
	//empty cannot see any other classes
}
