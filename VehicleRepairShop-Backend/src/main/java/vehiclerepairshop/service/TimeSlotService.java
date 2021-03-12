package vehiclerepairshop.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.TimeSlotRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;


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
		public TimeSlot createTimeSlot(Time startTime, Time endTime, Date StartDate, Date endDate) {
			TimeSlot timeSlot = new TimeSlot();
			timeSlot.setEndDate(endDate);
			timeSlot.setEndTime(endTime);
			timeSlot.setStartDate(StartDate);
			timeSlot.setStartTime(startTime);
			timeSlot.setTimeSlotId(startTime.hashCode());
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
		
		// helper method that converts iterable to list
		private <T> List<T> toList(Iterable<T> iterable){
			List<T> resultList = new ArrayList<T>();
			for (T t : iterable) {
				resultList.add(t);
			}
			return resultList;
		}

}