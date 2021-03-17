package ca.mcgill.ecse321.vehiclerepairshop.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.TimeSlotRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;

@Service
public class TimeSlotService {
	@Autowired
	private TimeSlotRepository timeslotRepository;
		/**
		 * 
		 * @param startTime
		 * @param endTime
		 * @param StartDate
		 * @param endDate
		 * @param timeSlotId
		 * @return timeSlot
		 * @author chengchen
		 */
		@Transactional
		public TimeSlot createTimeSlot(Time startTime, Time endTime, Date startDate, Date endDate) {
			String error = "";
			if (startDate == null) {
				error = error + "start date cannot be empty! ";
			}
			if (startTime == null) {
				error = error + "start time cannot be empty! ";
			}
			if (endTime == null) {
				error = error + "end time cannot be empty! ";
			}
			if (endDate == null) {
				error = error + "end date cannot be empty! ";
			}
			if (endTime != null && startTime != null && startTime.after(endTime)) {
				error = error + "start time can't be after end time";
			}
			if (endDate != null && startDate != null &&startDate.after(endDate)){
				error = error + "start date can't be after end date";
			}
			error = error.trim();
		    if (error.length() > 0) {
		        throw new InvalidInputException(error);
		    }
			TimeSlot timeSlot = new TimeSlot();
			timeSlot.setEndDate(endDate);
			timeSlot.setEndTime(endTime);
			timeSlot.setStartDate(startDate);
			timeSlot.setStartTime(startTime);
			timeSlot.setTimeSlotId(startTime.hashCode()*startDate.hashCode());
			timeslotRepository.save(timeSlot);
			
			return timeSlot;
		}
		/**
		 * 
		 * @return timeSlots
		 * @author chengchen
		 */
		@Transactional
		public List<TimeSlot> getAllTimeSlots(){
			Iterable<TimeSlot> timeSlots = timeslotRepository.findAll();
			return toList(timeSlots);
		}
		
		/**
		 * @author chengchen
		 */
		@Transactional
		public TimeSlot getTimeSlot(int id) {
			String error = "";
			if (timeslotRepository.findByTimeSlotId(id)==null) {
				error = error + "timeslot not found";
			}
			error = error.trim();
		    if (error.length() > 0) {
		        throw new InvalidInputException(error);
		    }
			return timeslotRepository.findByTimeSlotId(id);
		}
		
		/**
		 * 
		 * @param timeslotId
		 * @author chengchen
		 */
		@Transactional
		public TimeSlot deleteTimeSlot(int timeslotId) { 
			String error = "";
			if (timeslotRepository.findByTimeSlotId(timeslotId)==null) {
				error = error + "timeslot not found";
			}
			error = error.trim();
		    if (error.length() > 0) {
		        throw new InvalidInputException(error);
		    }
			TimeSlot timeSlot = timeslotRepository.findByTimeSlotId(timeslotId);
			timeslotRepository.delete(timeSlot);
			return timeSlot;
		}
		
		/**
		 * @author chengchen
		 * @return
		 */
		@Transactional
		public List<TimeSlot> deleteAllTimeSlot(){
			Iterable<TimeSlot> timeSlots = timeslotRepository.findAll();
			timeslotRepository.deleteAll();
			return toList(timeSlots);
		}
		
		@Transactional
		public TimeSlot updateTimeSlot(int id, Time startTime, Time endTime, Date startDate, Date endDate) {
			String error = "";
			TimeSlot timeSlot = timeslotRepository.findByTimeSlotId(id);
			if (startDate == null) {
				error = error + "start date cannot be empty! ";
			}
			if (startTime == null) {
				error = error + "start time cannot be empty! ";
			}
			if (endTime == null) {
				error = error + "end time cannot be empty! ";
			}
			if (endDate == null) {
				error = error + "end date cannot be empty! ";
			}
			if (endTime != null && startTime != null && startTime.after(endTime)) {
				error = error + "start time can't be after end time";
			}
			if (endDate != null && startDate != null &&startDate.after(endDate)){
				error = error + "start date can't be after end date";
			}
			if (timeSlot == null) {
				error = error + "non-existing time slot";
			}
			error = error.trim();
		    if (error.length() > 0) {
		        throw new InvalidInputException(error);
		    }

			timeSlot.setEndDate(endDate);
			timeSlot.setEndTime(endTime);
			timeSlot.setStartDate(startDate);
			timeSlot.setStartTime(startTime);
			timeSlot.setTimeSlotId(id);
			timeslotRepository.save(timeSlot);
			
			return timeSlot;
		}
		
		// helper method that converts iterable to list
		private <T> List<T> toList(Iterable<T> iterable){
			List<T> resultList = new ArrayList<T>();
			for (T t : iterable) {
				resultList.add(t);
			}
			return resultList;
		}

}
