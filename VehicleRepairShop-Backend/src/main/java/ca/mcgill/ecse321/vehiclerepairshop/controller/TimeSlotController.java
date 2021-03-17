package ca.mcgill.ecse321.vehiclerepairshop.controller;

import ca.mcgill.ecse321.vehiclerepairshop.service.InvalidInputException;
import ca.mcgill.ecse321.vehiclerepairshop.dto.*;
import ca.mcgill.ecse321.vehiclerepairshop.service.*;
import java.sql.Date;
import java.sql.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.vehiclerepairshop.dao.TimeSlotRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * @author chengchen
 * @author mikewang (some minor addtions)
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class TimeSlotController {
	
	@Autowired
	private TimeSlotService timeSlotService;
	@Autowired
	private TimeSlotRepository timeslotRepository;
	
	@GetMapping(value = { "/getAllTimeSlots", "/getAllTimeSlots/" })
	public List<TimeSlotDto> getAllTimeSlots() throws IllegalArgumentException {
		return timeSlotService.getAllTimeSlots().stream().map(timeslot->convertToDto(timeslot)).collect(Collectors.toList());
	}
	
	/**
	 * get a time slot by id
	 * @author mikewang
	 * @param id
	 * @return
	 */
	@GetMapping(value = {"/getTimeSlot/{id}", "/getTimeSlot/{id}/" })
	public TimeSlotDto getTimeSlot(@PathVariable("id") int id) {
		TimeSlot timeSlot = timeSlotService.getTimeSlot(id);
		return convertToDto(timeSlot);
	}
	
	@PostMapping(value = { "/createTimeSlot/{startTime}/{endTime}/{startDate}/{endDate}","/createTimeSlot/{startTime}/{endTime}/{startDate}/{endDate}/"})
	public TimeSlotDto createTimeSlot(@PathVariable("startTime") Time startTime,
			@PathVariable("endTime") Time endTime,
			@PathVariable("startDate") Date startDate,
			@PathVariable("endDate") Date endDate) throws IllegalArgumentException {
		TimeSlot timeSlot = timeSlotService.createTimeSlot(startTime,endTime,startDate,endDate);
		return convertToDto(timeSlot);
	}
	
	@DeleteMapping(value = {"/deleteTimeSlot/{timeslotId}","/deleteTimeSlot/{timeslotId}/"})
	public boolean deleteTimeSlotDto(@PathVariable("timeslotId") int timeslotId) {
		boolean isSuccess = false; 
		TimeSlot timeSlot = timeslotRepository.findByTimeSlotId(timeslotId);
		timeslotRepository.delete(timeSlot);
		isSuccess = true;
		return isSuccess;
	}
	
	@DeleteMapping(value = {"/deleteAllTimeSlot","/deleteAllTimeSlot/"})
	public boolean deleteTimeSlotDto() {
		boolean isSuccess = false;
		timeslotRepository.deleteAll();
		isSuccess = true;
		return isSuccess;
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
