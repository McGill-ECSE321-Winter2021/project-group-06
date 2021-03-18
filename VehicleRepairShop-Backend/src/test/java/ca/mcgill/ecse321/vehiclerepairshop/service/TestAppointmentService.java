package ca.mcgill.ecse321.vehiclerepairshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.aspectj.weaver.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.TimeSlotRepository;
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
	@Mock
	private TimeSlotRepository timeSlotRepository;
	@InjectMocks
	private AppointmentService appointmentService;

	private static final int APPOINTMENTID = 21;




	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setMockOutput() {
		MockitoAnnotations.initMocks(this);
		lenient().when(appointmentRepository.findByAppointmentId(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
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
				appointment.setAppointmentId(APPOINTMENTID);
				List<Appointment> appointments = new ArrayList<Appointment>();
				appointments.add(appointment);
				offeredService.setAppointment(appointments);
				car.setAppointment(appointments);
				garage.setAppointment(appointments);
				workers.get(0).setAppointment(appointments);



				return appointment;
			} else {
				return null;
			}
		});
		
		
		lenient().when(appointmentRepository.findByCar(any(Car.class))).thenAnswer( (InvocationOnMock invocation) -> {
			//Car
			Car car1 = new Car();
			car1.setLicensePlate("1");
			Car car2 = invocation.getArgument(0);
			
			if(car2.getLicensePlate().equals(car1.getLicensePlate())) {
				//offered service 
				OfferedService offeredService = new OfferedService();
				offeredService.setOfferedServiceId("1");
				offeredService.setDuration(60);

				
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
				appointment.setCar(car1);
				appointment.setComment(comment);
				appointment.setGarage(garage);
				appointment.setOfferedService(offeredService);
				appointment.setTimeSlot(timeSlot);
				appointment.setWorker(workers);
				appointment.setAppointmentId(APPOINTMENTID);
				List<Appointment> appointments = new ArrayList<Appointment>();
				appointments.add(appointment);
				offeredService.setAppointment(appointments);
				car1.setAppointment(appointments);
				garage.setAppointment(appointments);
				workers.get(0).setAppointment(appointments);



				return appointments;
			} else {
				return null;
			}
		});
		
		
		lenient().when(appointmentRepository.findByGarage(any(Garage.class))).thenAnswer( (InvocationOnMock invocation) -> {

			//garage
			Garage garage = new Garage();
			garage.setGarageId("1");
			
			Garage garage2 = invocation.getArgument(0);

			if(garage2.getGarageId().equals(garage.getGarageId())) {
				//offered service 
				OfferedService offeredService = new OfferedService();
				offeredService.setOfferedServiceId("1");
				offeredService.setDuration(60);

				//Car
				Car car = new Car();
				car.setLicensePlate("1");


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
				appointment.setAppointmentId(APPOINTMENTID);
				List<Appointment> appointments = new ArrayList<Appointment>();
				appointments.add(appointment);
				offeredService.setAppointment(appointments);
				car.setAppointment(appointments);
				garage.setAppointment(appointments);
				workers.get(0).setAppointment(appointments);



				return appointments;
			} else {
				return null;
			}
		});
		
		lenient().when(appointmentRepository.findByWorker(any(TechnicianAccount.class))).thenAnswer( (InvocationOnMock invocation) -> {
			//Car
			TechnicianAccount worker = new TechnicianAccount();
			worker.setUsername("1");
			TechnicianAccount worker2 = invocation.getArgument(0);
			
			if(worker2.getUsername().equals(worker.getUsername())) {
				//offered service 
				OfferedService offeredService = new OfferedService();
				offeredService.setOfferedServiceId("1");
				offeredService.setDuration(60);

				//Car
				Car car1 = new Car();
				car1.setLicensePlate("1");
				
				//garage
				Garage garage = new Garage();
				garage.setGarageId("1");

			
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
				appointment.setCar(car1);
				appointment.setComment(comment);
				appointment.setGarage(garage);
				appointment.setOfferedService(offeredService);
				appointment.setTimeSlot(timeSlot);
				appointment.setWorker(workers);
				appointment.setAppointmentId(APPOINTMENTID);
				List<Appointment> appointments = new ArrayList<Appointment>();
				appointments.add(appointment);
				offeredService.setAppointment(appointments);
				car1.setAppointment(appointments);
				garage.setAppointment(appointments);
				workers.get(0).setAppointment(appointments);



				return appointments;
			} else {
				return null;
			}
		});

		
		
		
		
		lenient().when(appointmentRepository.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
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
				appointment.setAppointmentId(APPOINTMENTID);
				
				
				
				
				
				//offered service 
				OfferedService offeredService1 = new OfferedService();
				offeredService.setOfferedServiceId("2");
				offeredService.setDuration(40);

				//Car
				Car car1 = new Car();
				car.setLicensePlate("2");

				//garage
				Garage garage1 = new Garage();
				garage.setGarageId("2");

				//technician
				TechnicianAccount worker1 = new TechnicianAccount();
				List<TechnicianAccount> workers1 = new ArrayList<TechnicianAccount>();
				worker1.setUsername("2");
				workers1.add(worker1);

				//timeslot
				String startTime1 = "11:10:00";
				String endTime1 = "11:50:00";
				String startDate1 = "2021-03-01";
				String endDate1 = "2021-03-01";
				TimeSlot timeSlot1 = new TimeSlot();
				timeSlot1.setTimeSlotId(2);
				timeSlot1.setEndDate(Date.valueOf(endDate1));
				timeSlot1.setEndTime(Time.valueOf(endTime1));
				timeSlot1.setStartDate(Date.valueOf(startDate1));
				timeSlot1.setStartTime(Time.valueOf(startTime1));

				String comment1 = "comment1";

				Appointment appointment1 = new Appointment();
				appointment1.setCar(car1);
				appointment1.setComment(comment1);
				appointment1.setGarage(garage1);
				appointment1.setOfferedService(offeredService1);
				appointment1.setTimeSlot(timeSlot1);
				appointment1.setWorker(workers1);
				appointment1.setAppointmentId(APPOINTMENTID+1);
				List<Appointment> appointments = new ArrayList<Appointment>();
				appointments.add(appointment);
				appointments.add(appointment1);
				offeredService.setAppointment(appointments);
				car.setAppointment(appointments);
				garage.setAppointment(appointments);
				workers.get(0).setAppointment(appointments);
				offeredService.setAppointment(appointments);
				car1.setAppointment(appointments);
				garage1.setAppointment(appointments);
				workers1.get(0).setAppointment(appointments);
				offeredService1.setAppointment(appointments);



				return (Iterable<Appointment>) appointments;
		});


		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
		lenient().when(appointmentRepository.save(any(Appointment.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(timeSlotRepository.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);




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
		String startTime = "14:00:00";
		String endTime = "15:00:00";
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
		Appointment appointment2 = new Appointment();

		try {
			appointment = appointmentService.createAppointment(timeSlot, offeredService, car, garage, comment,worker);
			appointment.setAppointmentId(APPOINTMENTID);

		} catch (InvalidInputException e) {
			fail(e.getMessage());
		}
		System.out.println(appointment.getAppointmentId());
		appointment2 = appointmentService.getAppointmentById(APPOINTMENTID);
		assertNotNull(appointment);
		assertEquals(appointment2.getComment(), appointment.getComment());
		assertEquals(appointment2.getAppointmentId(), appointment.getAppointmentId());
		assertEquals(appointment2.getCar().getLicensePlate(), appointment.getCar().getLicensePlate());
		assertEquals(appointment2.getGarage().getGarageId(), appointment.getGarage().getGarageId());
		assertEquals(appointment2.getTimeSlot().getTimeSlotId(), appointment.getTimeSlot().getTimeSlotId());
		assertEquals(appointment2.getOfferedService().getOfferedServiceId(), appointment.getOfferedService().getOfferedServiceId());
	}
	
	
	@Test
	public void testCreateAppointmentConflict() {
		//offered service 
		OfferedService offeredService = new OfferedService();
		offeredService.setOfferedServiceId("1");
		offeredService.setDuration(60);
		String error = "";
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
		String startTime = "11:00:00";
		String endTime = "12:00:00";
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
			appointment = appointmentService.createAppointment(timeSlot, offeredService, car, garage, comment,worker);
			appointment.setAppointmentId(APPOINTMENTID);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals( error, "this new timeslot conflicts with another existing timeslot");

	}


	@Test
	public void testMissingCarCreateAppointment(){
		String error = "";	
		//offered service 
		OfferedService offeredService = new OfferedService();
		offeredService.setOfferedServiceId("1");
		offeredService.setDuration(60);

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
			appointment = appointmentService.createAppointment(timeSlot, offeredService, null, garage, comment, worker);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(error, "car cannot be empty!");
	}

	@Test
	public void testMissingGarageCreateAppointment(){
		String error = "";	
		//offered service 
		OfferedService offeredService = new OfferedService();
		offeredService.setOfferedServiceId("1");
		offeredService.setDuration(60);

		//Car
		Car car = new Car();
		car.setLicensePlate("1");

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
			appointment = appointmentService.createAppointment(timeSlot, offeredService, car, null, comment, worker);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(error, "garage cannot be empty!");

	}


	@Test
	public void testMissingTimeSlotCreateAppointment(){
		String error = "";	
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

		String comment = "comment";

		Appointment appointment = new Appointment();

		try {
			appointment = appointmentService.createAppointment(null, offeredService, car, garage, comment, worker);
		} catch (InvalidInputException e) {
			error = e.getMessage();

			assertEquals(error, "timeslot cannot be empty!");

		}
	}



	@Test
	public void testMissingCommentCreateAppointment(){
		String error = "";	
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

		Appointment appointment = new Appointment();

		try {
			appointment = appointmentService.createAppointment(timeSlot, offeredService, car, garage, null,worker);
		} catch (InvalidInputException e) {
			error = e.getMessage();

			assertEquals(error, "comment cannot be empty!");

		}
	}




	@Test
	public void testMissingServiceCreateAppointment(){
		String error = "";	
		String comment = "comment";

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

		Appointment appointment = new Appointment();

		try {
			appointment = appointmentService.createAppointment(timeSlot, null, car, garage, comment,worker);
		} catch (InvalidInputException e) {
			error = e.getMessage();

			assertEquals(error, "service cannot be empty!");

		}
	}


	@Test
	public void testUpdateAppointmentGarage() {
		Appointment appointment = null;
		Garage garage = new Garage();
		garage.setGarageId("3");
		try {
			appointment = appointmentService.updateAppointmentGarage(APPOINTMENTID,garage);
		} catch (InvalidInputException e) {
			fail(e.getMessage());
		}

		assertNotNull(appointment);
		assertEquals(garage.getGarageId(), appointment.getGarage().getGarageId());
	
	}

	
	@Test
	public void testUpdateAppointmentCar() {
		Appointment appointment = null;
		Car car = new Car();
		car.setLicensePlate("3");
		try {
			appointment = appointmentService.updateAppointmentCar(APPOINTMENTID,car);
		} catch (InvalidInputException e) {
			fail(e.getMessage());
		}

		assertNotNull(appointment);
		assertEquals(car.getLicensePlate(), appointment.getCar().getLicensePlate());
	
	}
	
	
	@Test
	public void testUpdateAppointmentOffereredService() {
		Appointment appointment = null;
		OfferedService offeredService = new OfferedService();
		offeredService.setOfferedServiceId("3");
		offeredService.setDuration(40);
		try {
			appointment = appointmentService.updateAppointmentOfferedService(APPOINTMENTID,offeredService);
		} catch (InvalidInputException e) {
			fail(e.getMessage());
		}

		assertNotNull(appointment);
		assertEquals(offeredService.getDuration(),appointment.getOfferedService().getDuration());
		assertEquals(offeredService.getOfferedServiceId(), appointment.getOfferedService().getOfferedServiceId());
		assertEquals(Time.valueOf("10:40:00"), appointment.getTimeSlot().getEndTime());
	
	}
	
	
	@Test
	public void testUpdateAppointmentComment() {
		Appointment appointment = null;
		String comment = "new comment";
		try {
			appointment = appointmentService.updateAppointmentComment(APPOINTMENTID,comment);
		} catch (InvalidInputException e) {
			fail(e.getMessage());
		}
		assertNotNull(appointment);
		assertEquals(comment,appointment.getComment());
	
	}
	
	
	
	@Test
	public void testUpdateAppointmentTimeSlot() {
		Appointment appointment = null;
		String startTime = "10:05:00";
		String endTime = "11:05:00";
		String startDate = "2021-03-01";
		String endDate = "2021-03-01";
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setEndDate(Date.valueOf(endDate));
		timeSlot.setEndTime(Time.valueOf(endTime));
		timeSlot.setStartDate(Date.valueOf(startDate));
		timeSlot.setStartTime(Time.valueOf(startTime));
		timeSlot.setTimeSlotId(APPOINTMENTID);
		try {
			appointment = appointmentService.updateAppointmentTimeSlot(APPOINTMENTID,timeSlot);
		} catch (InvalidInputException e) {
			fail(e.getMessage());
		}
		assertNotNull(appointment);
		assertEquals(Time.valueOf(startTime),appointment.getTimeSlot().getStartTime());
		assertEquals(Time.valueOf("11:05:00"),appointment.getTimeSlot().getEndTime());
	
	}
	
	
	
	@Test
	public void testUpdateNonexistingAppointmentStartTime() {
		Appointment appointment = null;
		String startTime = "10:10:00";
		String endTime = "11:10:00";
		String startDate = "2021-03-01";
		String endDate = "2021-03-01";
		TimeSlot timeSlot = new TimeSlot();
		String error = "";
		timeSlot.setEndDate(Date.valueOf(endDate));
		timeSlot.setEndTime(Time.valueOf(endTime));
		timeSlot.setStartDate(Date.valueOf(startDate));
		timeSlot.setStartTime(Time.valueOf(startTime));
		timeSlot.setTimeSlotId(APPOINTMENTID);
		try {
			appointment = appointmentService.updateAppointmentTimeSlot(56,timeSlot);
		} catch (InvalidInputException e) {
			error = error + e.getMessage();
		}
		assertEquals(error,"appointment not found");
	
	}
	
	
	@Test
	public void testUpdateNonexistingAppointmentComment() {
		Appointment appointment = null;
		String comment = "new comment";
		String error = "";
		try {
			appointment = appointmentService.updateAppointmentComment(56,comment);
		} catch (InvalidInputException e) {
			error = error + e.getMessage();
		}
		assertEquals(error,"appointment not found");
	
	}
	
	
	
	
	@Test
	public void testUpdateNonexistingAppointmentOfferedService() {
		Appointment appointment = null;
		String error = "";
		OfferedService offeredService = new OfferedService();
		offeredService.setOfferedServiceId("3");
		offeredService.setDuration(40);
		try {
			appointment = appointmentService.updateAppointmentOfferedService(56,offeredService);
		} catch (InvalidInputException e) {
			error = error + e.getMessage();
		}

		assertEquals(error,"appointment not found");
	
	}
	
	

	@Test
	public void testUpdateNonexistingAppointmentGarage() {
		Appointment appointment = null;
		Garage garage = new Garage();
		String error = "";
		garage.setGarageId("3");
		try {
			appointment = appointmentService.updateAppointmentGarage(56,garage);
		} catch (InvalidInputException e) {
			error = error + e.getMessage();
		}

		assertEquals(error,"appointment not found");
	
	}

	
	@Test
	public void testUpdateNonexistingAppointmentCar() {
		Appointment appointment = null;
		String error = "";
		Car car = new Car();
		car.setLicensePlate("3");
		try {
			appointment = appointmentService.updateAppointmentCar(56,car);
		} catch (InvalidInputException e) {
			error = error + e.getMessage();
		}
		assertEquals(error,"appointment not found");
	}
	

	
	@Test
	public void testUpdateConflictAppointmentTimeSlot() {
		Appointment appointment = null;
		String startTime = "11:00:00";
		String endTime = "12:00:00";
		String startDate = "2021-03-01";
		String endDate = "2021-03-01";
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setEndDate(Date.valueOf(endDate));
		timeSlot.setEndTime(Time.valueOf(endTime));
		timeSlot.setStartDate(Date.valueOf(startDate));
		timeSlot.setStartTime(Time.valueOf(startTime));
		timeSlot.setTimeSlotId(APPOINTMENTID);
	
		String error = "";
		try {
			appointment = appointmentService.updateAppointmentTimeSlot(APPOINTMENTID,timeSlot);
		} catch (InvalidInputException e) {
			error = error + e.getMessage();
		}
		assertEquals(error,"this new timeslot conflicts with another existing timeslot");
	
	}
	
	
	
	@Test
	public void testUpdateConflictAppointmentService() {
		Appointment appointment = null;
		OfferedService offeredService = new OfferedService();
		offeredService.setOfferedServiceId("3");
		offeredService.setDuration(80);
		String error = "";
		try {
			appointment = appointmentService.updateAppointmentOfferedService(APPOINTMENTID,offeredService);
		} catch (InvalidInputException e) {
			error = error + e.getMessage();
		}
		assertEquals(error,"this new timeslot conflicts with another existing timeslot");
	
	}
	
	@Test
	public void testDeleteAppointment() {
		
		Appointment appointment = null;
		try {
			appointment = appointmentService.deleteAppointment(APPOINTMENTID);
		} catch (InvalidInputException e) {
			fail(e.getMessage());
		};
		assertNotNull(appointment);
		assertEquals(APPOINTMENTID, appointment.getAppointmentId());

	}
	
	
	@Test 
	public void testInvalidDeleteAppointment() {
		String error = "";
		try {
			appointmentService.deleteAppointment(2);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(error,"appointment not found");
	}
	
	
	
	@Test 
	public void testInvalidGetAppointment() {
		String error = "";
		try {
			appointmentService.getAppointmentById(1000);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(error,"appointment not found");
	}
	
	
	
	@Test
	public void testDeleteAllAppointment() {
		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments = appointmentService.deleteAllAppointment();
		Appointment appointment = appointments.get(0);
		Appointment appointment1 = appointments.get(1);
		assertNotNull(appointment);
		assertNotNull(appointment1);
		assertEquals(APPOINTMENTID, appointment.getAppointmentId());
		assertEquals(APPOINTMENTID+1, appointment1.getAppointmentId());
	
	}
	
	
	@Test
	public void testgetAllAppointment() {
		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments = appointmentService.getAllAppointments();
		Appointment appointment = appointments.get(0);
		Appointment appointment1 = appointments.get(1);
		assertNotNull(appointment);
		assertNotNull(appointment1);
		assertEquals(APPOINTMENTID, appointment.getAppointmentId());
		assertEquals(APPOINTMENTID+1, appointment1.getAppointmentId());
	}
	
	
	@Test
	public void testGetAppointmentByCar() {
		Appointment appointment = new Appointment();
		Car car = new Car();
		car.setLicensePlate("1");
		appointment = appointmentService.getAppointmentByCar(car).get(0);
		assertEquals(APPOINTMENTID, appointment.getAppointmentId());
	}
	
	
	
	
	@Test
	public void testGetAppointmentByGarage() {
		Appointment appointment = new Appointment();
		Garage garage = new Garage();
		garage.setGarageId("1");
		appointment = appointmentService.getAppointmentByGarage(garage).get(0);
		assertEquals(APPOINTMENTID, appointment.getAppointmentId());
	}
	
	
	@Test
	public void testGetAppointmentByWorker() {
		Appointment appointment = new Appointment();
		TechnicianAccount worker = new TechnicianAccount();
		worker.setUsername("1");
		appointment = appointmentService.getAppointmentByWorker(worker).get(0);
		assertEquals(APPOINTMENTID, appointment.getAppointmentId());
	}
	
	
	@Test 
	public void testInvalidGetAppointmentByGarage() {
		Appointment appointment = new Appointment();
		Garage garage = new Garage();
		garage.setGarageId("2");
		String error = "";
		try {
			appointmentService.getAppointmentByGarage(garage);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(error,"appointment not found");
	}
	
	
	@Test 
	public void testInvalidGetAppointmentByCar() {
		Appointment appointment = new Appointment();
		Car car = new Car();
		car.setLicensePlate("2");
		String error = "";
		try {
			appointmentService.getAppointmentByCar(car);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(error,"appointment not found");
	}
	
	
	@Test 
	public void testInvalidGetAppointmentByWorker() {
		Appointment appointment = new Appointment();
		TechnicianAccount worker = new TechnicianAccount();
		worker.setUsername("2");
		String error = "";
		try {
			appointmentService.getAppointmentByWorker(worker);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(error,"appointment not found");
	}
	
	
	
		



}