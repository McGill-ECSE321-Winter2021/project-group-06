package ca.mcgill.ecse321.vehiclerepairshop.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.vehiclerepairshop.dao.AppointmentRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.CarRepository;
import ca.mcgill.ecse321.vehiclerepairshop.dao.CustomerAccountRepository;
import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car.MotorType;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;


@Service
public class CarService {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private CustomerAccountRepository customerAccountRepository;

	/**
	 * @author James Darby
	 *
	 */

	/**
	 * Create a Car with given parameters
	 * @param licensePlate
	 * @param model
	 * @param year
	 * @param motorType
	 * @return
	 */
	@Transactional
	public Car createCar(String licensePlate, String model, int year, MotorType motorType, CustomerAccount owner) {
		String error = "";
		if (licensePlate == null || licensePlate.trim().length() == 0) {
			error = error + "licensePlate can not be null or empty!";
		}
		if (model == null || model.trim().length() == 0) {
			error = error + "model can not be null or empty!";
		}
		if (year < 1886) {
			error = error + "Theres not car have been invented until 1886!";
		}else if (year > 2021) {
			error = error + "you can't add a car which is invented in the future!";
		}
		if (motorType == null) {
			error = error + "motorType can't be null!";
		}
		if (error.length() >0 ) {
			throw new InvalidInputException(error);
		}
		else {
			Car car = new Car();
			car.setLicensePlate(licensePlate);
			car.setModel(model);
			car.setYear(year);
			car.setMotorType(motorType);
			carRepository.save(car);
			return car;
		}

	}

	/**
	 * gets car with the specified license plate
	 * @param licensePlate
	 * @return
	 */
	@Transactional
	public Car getCarByLicensePlate(String licensePlate) {
		String error = "";
		if (licensePlate == null || licensePlate.trim().length() == 0) {
			error = error + "licensePlate can not be null or empty!";
		}
		if (carRepository.findByLicensePlate(licensePlate) == null) {
			error = error + "can not find this car in the car repository!";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		Car car = carRepository.findByLicensePlate(licensePlate);
		return car;

	}

	/**
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
			throw new InvalidInputException(error);
		}
		Car car = carRepository.findByLicensePlate(licensePlate);
		carRepository.delete(car);
		return car;
	}

	// Helper method that converts iterable to list
	/**
	 * @param <T>
	 * @param iterable
	 * @return
	 */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

	/**
	 * @param appId
	 * @return
	 */
	@Transactional
	public List<Car> findCarByAppointment(int appId){
		List<Car> cars = toList(carRepository.findAll());
		List<Appointment> appointments = toList(appointmentRepository.findAll());
		List<Car> result = new ArrayList<Car>();
		if (cars.isEmpty()) {
			return result;
		}
		else {
			for (Appointment appointment:appointments) {
				if (appointment.getAppointmentId() == appId) {
					result.add(appointment.getCar());
					break;
				}
			}
			return result;
		}

	}

	/**
	 * @param customerUsername
	 * @return
	 */
	@Transactional
	public List<Car> findCarByCustomerAccount(String customerUsername){
		List<CustomerAccount> customers = toList(customerAccountRepository.findAll());
		for (CustomerAccount customer:customers) {
			if (customer.getUsername().equals(customerUsername)) {
				return customer.getCar();
			}
		}
		throw new InvalidInputException("Username not found!");

	}

}
