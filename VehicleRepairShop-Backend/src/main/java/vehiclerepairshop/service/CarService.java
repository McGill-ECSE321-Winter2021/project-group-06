package vehiclerepairshop.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.CarRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car.MotorType;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TimeSlot;

public class CarService {
	@Autowired
	private CarRepository carRepository;
	
	/**
	 * @author James Darby
	 *
	 */
	
	/*
	 * Create a Car with given parameters
	 * @param licensePlate
	 * @param model
	 * @param year
	 * @param motorType
	 * @return
	 */
	@Transactional
	public Car createCar(String licensePlate, String model, int year, MotorType motorType) {
		Car car = new Car();
		car.setLicensePlate(licensePlate);
		car.setModel(model);
		car.setYear(year);
		car.setMotorType(motorType);
		carRepository.save(car);
		return car;
	}
	
	/*
	 * gets all cars belonging to one owner
	 * @param owner
	 * @return
	 */
	@Transactional
	public List<Car> getCarsByOwner(CustomerAccount owner) {
		List<Car> cars = carRepository.findByOwner(owner);
		return cars;	
	}
	
	/*
	 * gets car with the specified license plate
	 * @param licensePlate
	 * @return
	 */
	@Transactional
	public Car getCarByLicensePlate(String licensePlate) {
		Car car = carRepository.findByLicensePlate(licensePlate);
		return car;	
	}
	
	/*
	 * gets all cars in the database
	 * @return
	 */
	@Transactional
	public List<Car> getAllCars() {
		Iterable<Car> cars = carRepository.findAll();
		return toList(cars);	
	}
	
	/**
	 * 
	 * @param licensePlate
	 * @return
	 */
	@Transactional
	public Car deleteCar(String licensePlate) { 
		String error = "";
		if (carRepository.findByLicensePlate(licensePlate)==null) {
			error = error + "car not found";
		}
		error = error.trim();
	    if (error.length() > 0) {
	        throw new IllegalArgumentException(error);
	    }
		Car car = carRepository.findByLicensePlate(licensePlate);
		carRepository.delete(car);
		return car;
	}
	
	// Helper method that converts iterable to list
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
