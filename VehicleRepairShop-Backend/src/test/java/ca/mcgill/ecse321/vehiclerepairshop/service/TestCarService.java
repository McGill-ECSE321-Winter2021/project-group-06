package ca.mcgill.ecse321.vehiclerepairshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.vehiclerepairshop.dao.CarRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car.MotorType;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

//@ExtendWith(MockitoExtension.class)
public class TestCarService {
	
	@Mock
	private CarRepository carRepository;

	@InjectMocks
	private CarService carService;
	

  private static final String LICENSEPLATE = "ENL 432";
  private static final String MODEL = "Honda Civic";
  private static final int YEAR = 2006;
  private static final MotorType MOTORTYPE = MotorType.Gas;

  //private static final CustomerAccount owner;
  //private static final List<Appointment> appointment;
  
  	@SuppressWarnings("deprecation")
  	@BeforeEach
	public void setMockOutput() {
		MockitoAnnotations.initMocks(this);
	    lenient().when(carRepository.findByLicensePlate(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(LICENSEPLATE)) {
	            Car car = new Car();
	            car.setLicensePlate(LICENSEPLATE);
	    		car.setModel(MODEL);
	    		car.setYear(YEAR);
	    		car.setMotorType(MOTORTYPE);
	            return car;
	        } else {
	            return null;
	        }
	    });
	    
	    lenient().when(carRepository.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
	    	 Car car = new Car();
            car.setLicensePlate(LICENSEPLATE);
    		car.setModel(MODEL);
    		car.setYear(YEAR);
    		car.setMotorType(MOTORTYPE);
			List<Car> cars = new ArrayList<Car>();
			cars.add(car);
            return cars;
	    });
	    
	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
		lenient().when(carRepository.save(any(Car.class))).thenAnswer(returnParameterAsAnswer);
	}
  	
  	@Test
	public void testCreateCar() {
		
		Car car = null;
		try {
			car = carService.createCar(LICENSEPLATE,MODEL,YEAR,MOTORTYPE);
		} catch (IllegalArgumentException e) {
			fail();
		}
		Car car2 = carService.getCarByLicensePlate(LICENSEPLATE);
		assertNotNull(car);
		assertEquals(car2.getLicensePlate(), car.getLicensePlate());
		assertEquals(car2.getModel(), car.getModel());
		assertEquals(car2.getYear(), car.getYear());
		assertEquals(car2.getMotorType(), car.getMotorType());
	}
  	
  	/**
  	 * testing create a car with null liscense Plate 
  	 */
  	@Test
  	public void testCreateCarwithNullLicensePlate() {
  		Car car = null; 
  		String error = "";
  		String nullLicense = null;
  		try {
  			car = carService.createCar(nullLicense,MODEL,YEAR,MOTORTYPE);
  		}catch(InvalidInputException e) {
  			error = e.getMessage();
  		}
  		assertNull(car);
  		assertEquals("licensePlate can not be null or empty!", error);
  	}
  	
  	/**
  	 * testing create a car with empty liscense Plate 
  	 */
	@Test
  	public void testCreateCarwithEmptyLicensePlate() {
  		Car car = null; 
  		String error = "";
  		String nullLicense = "";
  		try {
  			car = carService.createCar(nullLicense,MODEL,YEAR,MOTORTYPE);
  		}catch(InvalidInputException e) {
  			error = e.getMessage();
  		}
  		assertNull(car);
  		assertEquals("licensePlate can not be null or empty!", error);
  	}
	
	/**
  	 * testing create a car with Space liscense Plate 
  	 */
	@Test
  	public void testCreateCarwithSpaceLicensePlate() {
  		Car car = null; 
  		String error = "";
  		String nullLicense = " ";
  		try {
  			car = carService.createCar(nullLicense,MODEL,YEAR,MOTORTYPE);
  		}catch(InvalidInputException e) {
  			error = e.getMessage();
  		}
  		assertNull(car);
  		assertEquals("licensePlate can not be null or empty!", error);
  	}
  	
	
	/**
	 * testing create a car with Space model 
	 */
	@Test
  	public void testCreateCarwithSpaceModel() {
  		Car car = null; 
  		String error = "";
  		String nullModel = " ";
  		try {
  			car = carService.createCar(LICENSEPLATE,nullModel,YEAR,MOTORTYPE);
  		}catch(InvalidInputException e) {
  			error = e.getMessage();
  		}
  		assertNull(car);
  		assertEquals("model can not be null or empty!", error);
  		
  	}
	
	
	/**
	 * testing create a car with empty model 
	 */
	@Test
  	public void testCreateCarwithEmptyModel() {
  		Car car = null; 
  		String error = "";
  		String nullModel = "";
  		try {
  			car = carService.createCar(LICENSEPLATE,nullModel,YEAR,MOTORTYPE);
  		}catch(InvalidInputException e) {
  			error = e.getMessage();
  		}
  		assertNull(car);
  		assertEquals("model can not be null or empty!", error);
  		
  	}
	
	/**
	 * testing create a car with null model 
	 */
	@Test
  	public void testCreateCarwithNullModel() {
  		Car car = null; 
  		String error = "";
  		String nullModel = null;
  		try {
  			car = carService.createCar(LICENSEPLATE,nullModel,YEAR,MOTORTYPE);
  		}catch(InvalidInputException e) {
  			error = e.getMessage();
  		}
  		assertNull(car);
  		assertEquals("model can not be null or empty!", error);
  		
  	}
	
	/**
	 * testing get a car by valid licence plate
	 */
	@Test 
	public void testGetCarByValidLicensePlate() {
		Car car = null;
		String error = "";
		try {
			car = carService.getCarByLicensePlate(LICENSEPLATE);
		}catch (InvalidInputException e) {
			error = fail();
		}
		assertNotNull(car);
		assertEquals(LICENSEPLATE,car.getLicensePlate());
	}
	
	@Test 
	public void testGetCarByNullLicensePlate() {
		Car car = null;
		String nullLicensePlate = null;
		String error = "";
		try {
			car = carService.getCarByLicensePlate(nullLicensePlate);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(car);
		assertEquals("licensePlate can not be null or empty!can not find this car in the car repository!",error);
	}
	
	@Test 
	public void testGetCarByEmptyLicensePlate() {
		Car car = null;
		String nullLicensePlate = "";
		String error = "";
		try {
			car = carService.getCarByLicensePlate(nullLicensePlate);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(car);
		assertEquals("licensePlate can not be null or empty!can not find this car in the car repository!",error);
	}
	
	
	@Test 
	public void testGetCarBySpaceLicensePlate() {
		Car car = null;
		String nullLicensePlate = " ";
		String error = "";
		try {
			car = carService.getCarByLicensePlate(nullLicensePlate);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(car);
		assertEquals("licensePlate can not be null or empty!can not find this car in the car repository!",error);
	}
	
	
  	@Test
	public void testGetAllCars() {
		List<Car> cars = new ArrayList<Car>();
		cars = carService.getAllCars();
		Car car = cars.get(0);
		assertNotNull(car);
		assertEquals(LICENSEPLATE, car.getLicensePlate());
		assertEquals(MODEL, car.getModel());
		assertEquals(YEAR, car.getYear());
		assertEquals(MOTORTYPE, car.getMotorType());
	}
  	
//  	@Test
//  	public void testGetCarsByOwner() {
//  		List<Car> cars = new ArrayList<Car>();
//  		cars = carService.getCarsByOwner();
//  		
//  	}
	
	@Test
	public void testDeleteCar() {	
		
		Car car = null;
		try {
			car = carService.deleteCar(LICENSEPLATE);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		Car car2 = carService.getCarByLicensePlate(LICENSEPLATE);
		assertNotNull(car);
		assertEquals(car2.getLicensePlate(), car.getLicensePlate());
		assertEquals(car2.getModel(), car.getModel());
		assertEquals(car2.getYear(), car.getYear());
		assertEquals(car2.getMotorType(), car.getMotorType());
	}

}
