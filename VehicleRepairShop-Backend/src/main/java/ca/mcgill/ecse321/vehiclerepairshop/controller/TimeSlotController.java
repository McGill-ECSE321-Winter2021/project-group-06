package ca.mcgill.ecse321.vehiclerepairshop.controller;


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
	public List<TimeSlotDto> getAllTimeSlots() {
		return timeSlotService.getAllTimeSlots().stream().map(timeslot->convertToDto(timeslot)).collect(Collectors.toList());
	}

	/**
	 * get a time slot by id
	 * @author mikewang
	 * @param id
	 * @return
	 */
	@GetMapping(value = {"/getTimeSlot/{timeSlotId}", "/getTimeSlot/{timeSlotId}/" })
	public TimeSlotDto getTimeSlot(@PathVariable("timeSlotId") int timeSlotId) {
		TimeSlot timeSlot = timeSlotService.getTimeSlot(timeSlotId);
		return convertToDto(timeSlot);
	}

	/** 
	 * create a time slot
	 * @author mikewang
	 * @param startTime
	 * @param endTime
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@PostMapping(value = { "/createTimeSlot/{startTime}/{endTime}/{startDate}/{endDate}","/createTimeSlot/{startTime}/{endTime}/{startDate}/{endDate}/"})
	public TimeSlotDto createTimeSlot(@PathVariable("startTime") String startTime,
			@PathVariable("endTime") String endTime,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {
		
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf(startTime),Time.valueOf(endTime),Date.valueOf(startDate),Date.valueOf(endDate));
		return convertToDto(timeSlot);
	}

	/** 
	 * delete a time slot by id
	 * @author mikewang
	 * @param timeslotId
	 * @return
	 */
	@DeleteMapping(value = {"/deleteTimeSlot/{timeslotId}","/deleteTimeSlot/{timeslotId}/"})
	public TimeSlotDto deleteTimeSlotDto(@PathVariable("timeslotId") int timeslotId) {
		TimeSlot timeSlot = timeSlotService.getTimeSlot(timeslotId);
		timeslotRepository.delete(timeSlot);
		return convertToDto(timeSlot);

	}

	/**
	 * delete all time slots
	 * @author mikewang
	 * @return
	 */
	@DeleteMapping(value = {"/deleteAllTimeSlot","/deleteAllTimeSlot/"})
	public List<TimeSlotDto> deleteAllTimeSlotDto() {
		List<TimeSlot >timeSlot = timeSlotService.deleteAllTimeSlot();
		List<TimeSlotDto> timeSlotDtos = new ArrayList<TimeSlotDto>();
		for (TimeSlot timeSlot2:timeSlot) {
			timeSlotDtos.add(convertToDto(timeSlot2));
		}
		return timeSlotDtos;

	}



	//helper method
	/**
	 * convert to dto
	 * @author mikewang
	 * @param timeSlot
	 * @return
	 */
	private TimeSlotDto convertToDto(TimeSlot timeSlot) {
		if (timeSlot == null) {
			throw new InvalidInputException("There is no such timeslot!");
		}
		TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getStartTime(),timeSlot.getEndTime(),timeSlot.getStartDate(),timeSlot.getEndDate());
		return timeSlotDto;
	}


}
