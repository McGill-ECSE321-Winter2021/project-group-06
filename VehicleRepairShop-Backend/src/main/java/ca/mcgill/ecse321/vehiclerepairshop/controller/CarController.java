package ca.mcgill.ecse321.vehiclerepairshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car.MotorType;
import ca.mcgill.ecse321.vehiclerepairshop.dto.*;
import ca.mcgill.ecse321.vehiclerepairshop.service.*;

@CrossOrigin(origins = "*")
@RestController
public class CarController {

	@Autowired
	private CarService carService;

	/**
	 * Return a list of all Car Dtos 
	 * @author James
	 * @return list of Car Dtos
	 */
	@GetMapping(value = { "/getAllCars", "/getAllCars/" })
	public List<CarDto> getAllCars() {
		return carService.getAllCars().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}

	/**
	 * Returns a Car Dto by license plate 
	 * @author James
	 * @return CarDto
	 */
	@GetMapping(value = { "/getCarByLicensePlate/{licensePlate}", "/getCarByLicensePlate/{licensePlate}/" })
	public CarDto getCarByLicensePlate(@PathVariable("licensePlate") String licensePlate) {
		return convertToDto(carService.getCarByLicensePlate(licensePlate));
	}

	/**
	 * Return a list of all Car Dtos belonging to one owner
	 * @author James
	 * @return list of Car Dtos
	 */
	@GetMapping(value = { "/getCarsByOwner/{username}", "/getCarsByOwner/{username}/" })
	public List<CarDto> getCarsByOwner(@PathVariable("username") String username) {	
		return carService.findCarByCustomerAccount(username).stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}
	
	@GetMapping(value = { "/getCarByAppointment/{appointmentId}", "/getCarByAppointment/{appointmentId}/" })
	public CarDto getCarsByAppointment(@PathVariable("appointmentId") int appointmentId) {	
		Car car = carService.findCarByAppointment(appointmentId);
		return convertToDto(car);
		
	}

	/**
	 * Create a Car Dto with provided parameters
	 * @author James
	 * @param licensePlate
	 * @param model
	 * @param year
	 * @param motorType
	 * @return Car Dto
	 */
	@PostMapping(value = { "/createCar/{licensePlate}/{model}/{year}/{motorType}/{username}", "/createAdminAccount/{licensePlate}/{model}/{year}/{motorType}/{username}/" })
	public CarDto createCar(@PathVariable("licensePlate") String licensePlate, @PathVariable("model") String model, @PathVariable("year") int year, 
			@PathVariable("motorType") MotorType motorType,@PathVariable("username") String username) {
		Car car = carService.createCar(licensePlate, model, year, motorType,username);
		return convertToDto(car);
	}
	



	@DeleteMapping(value = {"/deleteCar/{licensePlate}"})
	public CarDto deleteCar(@PathVariable("licensePlate") String licensePlate) {
		Car car = carService.deleteCar(licensePlate);
		return convertToDto(car);
	}


	//-------------------------- Helper Methods -----------------------------

	/**
	 * Helper Method to convert a car to a Dto
	 * Will return null if you pass null
	 * @author Catherine
	 * @param car
	 * @return CarDto
	 */
	private CarDto convertToDto(Car car)  {
		if (car == null) {
			return null;
		} else {
			CarDto carDto = new CarDto(car.getLicensePlate(), car.getModel(), car.getYear(), car.getMotorType());
			return carDto;
		}
	}
}
