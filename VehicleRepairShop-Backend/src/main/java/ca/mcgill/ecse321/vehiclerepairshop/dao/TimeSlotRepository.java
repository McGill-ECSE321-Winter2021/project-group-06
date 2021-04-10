package ca.mcgill.ecse321.vehiclerepairshop.dao;


import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;


import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "TimeSlot_data", path = "TimeSlot_data")
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Integer>{
	TimeSlot findByTimeSlotId(int Id);
	//empty cannot see any other classes
}
