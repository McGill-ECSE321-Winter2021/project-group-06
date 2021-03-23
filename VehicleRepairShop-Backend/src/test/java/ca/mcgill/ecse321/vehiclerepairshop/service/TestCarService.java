package ca.mcgill.ecse321.vehiclerepairshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

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
import ca.mcgill.ecse321.vehiclerepairshop.dao.CustomerAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car.MotorType;

import static org.mockito.ArgumentMatchers.anyString;

//@ExtendWith(MockitoExtension.class)
public class TestCarService {

	@Mock
	private CarRepository carRepository;
	@Mock
	private CustomerAccountRepository customerAccountRepository;

	@InjectMocks
	private CarService carService;

	private static final String OWNER_USERNAME = "owner1";
	private static final String LICENSEPLATE = "ENL 432";
	private static final String MODEL = "Honda Civic";
	private static final int YEAR = 2006;
	private static final MotorType MOTORTYPE = MotorType.Gas;
	CustomerAccount owner = new CustomerAccount();

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

		lenient().when(customerAccountRepository.findByUsername(anyString())).thenAnswer((InvocationOnMock invocation) ->{

			if(invocation.getArgument(0).equals(OWNER_USERNAME)) {
				owner.setUsername(OWNER_USERNAME);
				return owner;
			}else {
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
		lenient().when(customerAccountRepository.save(any(CustomerAccount.class))).thenAnswer(returnParameterAsAnswer);
	}



	@Test
	public void testCreateCar() {

		Car car = null;
		try {
			car = carService.createCar(LICENSEPLATE,MODEL,YEAR,MOTORTYPE,null);
		} catch (InvalidInputException e) {
			fail();
		}
		Car car2 = carService.getCarByLicensePlate(LICENSEPLATE);
		assertNotNull(car);
		assertEquals(car2.getLicensePlate(), car.getLicensePlate());
		assertEquals(car2.getModel(), car.getModel());
		assertEquals(car2.getYear(), car.getYear());
		assertEquals(car2.getMotorType(), car.getMotorType());
	}



	@Test 
	public void testCreateCarWithAllNullInfo() {
		Car car = null;
		String error = "";
		try {
			car = carService.createCar(null,null,0,null,null);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertNull(car);
		assertEquals("licensePlate can not be null or empty!model can not be null or empty!Theres not car have been invented until 1886!motorType can't be null!",error);
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
			car = carService.createCar(nullLicense,MODEL,YEAR,MOTORTYPE,null);
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
			car = carService.createCar(nullLicense,MODEL,YEAR,MOTORTYPE,null);
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
			car = carService.createCar(nullLicense,MODEL,YEAR,MOTORTYPE,null);
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
			car = carService.createCar(LICENSEPLATE,nullModel,YEAR,MOTORTYPE,null);
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
			car = carService.createCar(LICENSEPLATE,nullModel,YEAR,MOTORTYPE,null);
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
			car = carService.createCar(LICENSEPLATE,nullModel,YEAR,MOTORTYPE,null);
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
		try {
			car = carService.getCarByLicensePlate(LICENSEPLATE);
		}catch (InvalidInputException e) {
			fail();
		}
		assertNotNull(car);
		assertEquals(LICENSEPLATE,car.getLicensePlate());
	}


	/**
	 * testing get car by null license plate 
	 */
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


	/**
	 * testing get car by empty license plate 
	 */
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

	/**
	 * testing get car by space lincense plate 
	 */
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



	/**
	 * testing get all cars 
	 */
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

  	 	
  	/**
  	 * testing get cars by valid owner info 
  	 */
  	@Test
  	public void testGetCarsByOwner() {
  		List<Car> cars = new ArrayList<Car>();
  		owner.setUsername(OWNER_USERNAME);
  		String error = "";
  		try {
  			cars = carService.getCarsByOwner(owner);
  		}catch(InvalidInputException e) {
  			error = fail();
  		}
  		assertNotNull(cars);
  		for(Car c : cars) {
  			testCarAttrribute(c);
  		}
  	}
  	
  	
  	
  	/**
  	 * testing get cars by null owner 
  	 */
  	@Test
  	public void testGetCarsByOwnerWithNullOwner() {
  		List<Car> cars = new ArrayList<Car>();
  		CustomerAccount owner = null;
  		String error = "";
  		try {
  			cars = carService.getCarsByOwner(owner);
  		}catch(InvalidInputException e) {
  			error = e.getMessage();
  		}
  		assertEquals(cars.size(), 0);
  		assertEquals("Car owner cannot be null!",error);
  		
  	}
  	
  	
  	/**
  	 * testing get car by owner but with an empty owner username 
  	 */
  	@Test
  	public void testGetCarsByOwnerWithEmptyOwnerUsername() {
  		List<Car> cars = new ArrayList<Car>();
  		CustomerAccount owner = new CustomerAccount();
  		owner.setUsername("");
  		String error = "";
  		try {
  			cars = carService.getCarsByOwner(owner);
  		}catch(InvalidInputException e) {
  			error = e.getMessage();
  		}
  		assertEquals(cars.size(), 0);
  		assertEquals("This owner's username cannot be empty or null!",error);
  		
  	}
	
  	/**
  	 * testing get cars by using null owner username 
  	 */
  	@Test
  	public void testGetCarsByOwnerWithNullOwnerUsername() {
  		List<Car> cars = new ArrayList<Car>();
  		CustomerAccount owner = new CustomerAccount();
  		owner.setUsername(null);
  		String error = "";
  		try {
  			cars = carService.getCarsByOwner(owner);
  		}catch(InvalidInputException e) {
  			error = e.getMessage();
  		}
  		assertEquals(cars.size(), 0);
  		assertEquals("This owner's username cannot be empty or null!",error);
  		
  	}
  	
  	
  	/**
  	 * testing get cars by owner with space owner username 
  	 */
  	@Test
  	public void testGetCarsByOwnerWithSpaceOwnerUsername() {
  		List<Car> cars = new ArrayList<Car>();
  		CustomerAccount owner = new CustomerAccount();
  		owner.setUsername(" ");
  		String error = "";
  		try {
  			cars = carService.getCarsByOwner(owner);
  		}catch(InvalidInputException e) {
  			error = e.getMessage();
  		}
  		assertEquals(cars.size(), 0);
  		assertEquals("This owner's username cannot be empty or null!",error);
  		
  	}
  	
  	
  	/**
  	 * testing get cars by owner with not saved owner object
  	 */
  	@Test
  	public void testGetCarsByOwnerWithOwnerNotFoundInCustomerRepo() {
  		List<Car> cars = new ArrayList<Car>();
  		CustomerAccount owner = new CustomerAccount();
  		owner.setUsername("owner2");
  		String error = "";
  		try {
  			cars = carService.getCarsByOwner(owner);
  		}catch(InvalidInputException e) {
  			error = e.getMessage();
  		}
  		assertEquals(cars.size(), 0);
  		assertEquals("cannot find this car owner in customerAccountRepository!",error);
  		
  	}
  	
  	
  	/**
  	 * testing deleting car 
  	 */
  	
	@Test
	public void testDeleteCar() {	

		Car car = null;
		try {
			car = carService.deleteCar(LICENSEPLATE);
		} catch (InvalidInputException e) {
			fail(e.getMessage());
		}
		Car car2 = carService.getCarByLicensePlate(LICENSEPLATE);
		assertNotNull(car);
		assertEquals(car2.getLicensePlate(), car.getLicensePlate());
		assertEquals(car2.getModel(), car.getModel());
		assertEquals(car2.getYear(), car.getYear());
		assertEquals(car2.getMotorType(), car.getMotorType());
	}


	//------------------ helper method -----------------------

	/**
	 * helper method
	 * @param car
	 */
	private void testCarAttrribute(Car car) {
		assertNotNull(car);
		assertEquals(LICENSEPLATE, car.getLicensePlate());
		assertEquals(MODEL, car.getModel());
		assertEquals(YEAR, car.getYear());
		assertEquals(MOTORTYPE, car.getMotorType());
	}

}
