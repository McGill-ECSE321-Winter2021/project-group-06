package vehiclerepairshop.controller;

import vehiclerepairshop.dto.*;
import vehiclerepairshop.service.TimeSlotService;
import java.sql.Date;
import java.sql.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * @author chengchen
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class TimeSlotController {
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	@GetMapping(value = { "/getAllTimeSlots", "/getAllTimeSlots/" })
	public List<TimeSlotDto> getAllTimeSlots() throws IllegalArgumentException {
		return timeSlotService.getAllTimeSlots().stream().map(timeslot->convertToDto(timeslot)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/createTimeSlot/{startTime}/{endTime}/{startDate}/{endDate}","/createTimeSlot/{startTime}/{endTime}/{startDate}/{endDate}/"})
	public TimeSlotDto createTimeSlot(@PathVariable("startTime") Time startTime,
			@PathVariable("endTime") Time endTime,
			@PathVariable("startDate") Date startDate,
			@PathVariable("endDate") Date endDate) throws IllegalArgumentException {
		TimeSlot timeSlot = timeSlotService.createTimeSlot(startTime,endTime,startDate,endDate);
		return convertToDto(timeSlot);
	}
	
	
	
	
	
	//helper method
	private TimeSlotDto convertToDto(TimeSlot timeSlot) {
		if (timeSlot == null) {
			throw new IllegalArgumentException("There is no such timeslot!");
		}
		TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getStartTime(),timeSlot.getEndTime(),timeSlot.getStartDate(),timeSlot.getEndDate());
		return timeSlotDto;
	}
	

}
