package vehiclerepairshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Garage;
import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import java.util.ArrayList;
import java.util.Optional;

public class TestAppointmentService {

	@Mock
	private AppointmentRepository appointmentRepository;

	@InjectMocks
	private AppointmentService appointmentService;
	
//	private Car car;
//	private Garage garage;
//	private TimeSlot timeSlot;
//	private List<TechnicianAccount> workers;
//	private OfferedService offeredService;
//	private String comment;
	private static final int APPOINTMENTID = 21;


	
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setMockOutput() {
		MockitoAnnotations.initMocks(this);
		lenient().when(appointmentRepository.findById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(APPOINTMENTID)) {
				//offered service 
				OfferedService offeredService = new OfferedService();
				offeredService.setOfferedServiceId("1");
				offeredService.setDuration(60);
				
				//Car
				Car car = new Car();
				car.setLicensePlate("1");
				
				//garage
				Garage garage = new Garage();
				garage.setGarageId("1");
				
				//technician
				TechnicianAccount worker = new TechnicianAccount();
				List<TechnicianAccount> workers = new ArrayList<TechnicianAccount>();
				worker.setUsername("1");
				workers.add(worker);
				
				//timeslot
				String startTime = "10:00:00";
				String endTime = "11:00:00";
				String startDate = "2021-03-01";
				String endDate = "2021-03-01";
				TimeSlot timeSlot = new TimeSlot();
				timeSlot.setTimeSlotId(1);
				timeSlot.setEndDate(Date.valueOf(endDate));
				timeSlot.setEndTime(Time.valueOf(endTime));
				timeSlot.setStartDate(Date.valueOf(startDate));
				timeSlot.setStartTime(Time.valueOf(startTime));
				
				String comment = "comment";
				
				Appointment appointment = new Appointment();
				appointment.setCar(car);
				appointment.setComment(comment);
				appointment.setGarage(garage);
				appointment.setOfferedService(offeredService);
				appointment.setTimeSlot(timeSlot);
				appointment.setWorker(workers);
				List<Appointment> appointments = new ArrayList<Appointment>();
				appointments.add(appointment);
				offeredService.setAppointment(appointments);
				car.setAppointment(appointments);
				garage.setAppointment(appointments);
				workers.get(0).setAppointment(appointments);
				
				
				
				return Optional.of(appointment);
			} else {
				return null;
			}
		});
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
		lenient().when(appointmentRepository.save(any(Appointment.class))).thenAnswer(returnParameterAsAnswer);

		
		

	}
	
	@Test
	public void testCreateAppointment() {
		//offered service 
		OfferedService offeredService = new OfferedService();
		offeredService.setOfferedServiceId("1");
		offeredService.setDuration(60);
		
		//Car
		Car car = new Car();
		car.setLicensePlate("1");
		
		//garage
		Garage garage = new Garage();
		garage.setGarageId("1");
		
		//technician
		TechnicianAccount worker = new TechnicianAccount();
		List<TechnicianAccount> workers = new ArrayList<TechnicianAccount>();
		worker.setUsername("1");
		workers.add(worker);
		
		//timeslot
		String startTime = "10:00:00";
		String endTime = "11:00:00";
		String startDate = "2021-03-01";
		String endDate = "2021-03-01";
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setTimeSlotId(1);
		timeSlot.setEndDate(Date.valueOf(endDate));
		timeSlot.setEndTime(Time.valueOf(endTime));
		timeSlot.setStartDate(Date.valueOf(startDate));
		timeSlot.setStartTime(Time.valueOf(startTime));
		
		String comment = "comment";
		
		Appointment appointment = new Appointment();
		
		try {
			appointment = appointmentService.createAppointment(workers,timeSlot, offeredService, car, garage, comment);
			appointment.setAppointmentId(APPOINTMENTID);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		assertNotNull(appointment);
		assertEquals(comment, appointment.getComment());
		assertEquals(APPOINTMENTID, appointment.getAppointmentId());
		assertEquals(car.getLicensePlate(), appointment.getCar().getLicensePlate());
		assertEquals(garage.getGarageId(), appointment.getGarage().getGarageId());
		assertEquals(timeSlot.getTimeSlotId(), appointment.getTimeSlot().getTimeSlotId());
		assertEquals(worker.getUsername(), appointment.getWorker().get(0).getUsername());
		assertEquals(offeredService.getOfferedServiceId(), appointment.getOfferedService().getOfferedServiceId());
	}
	


}