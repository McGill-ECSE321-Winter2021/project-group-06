package vehiclerepairshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.mcgill.ecse321.vehiclerepairshop.model.Appointment;
import ca.mcgill.ecse321.vehiclerepairshop.model.Car;
import ca.mcgill.ecse321.vehiclerepairshop.model.CustomerAccount;
import ca.mcgill.ecse321.vehiclerepairshop.model.TechnicianAccount;
import vehiclerepairshop.dto.AppointmentDto;
import vehiclerepairshop.dto.CarDto;
import vehiclerepairshop.dto.TechnicianAccountDto;
import vehiclerepairshop.service.TechnicianAccountService;
import vehiclerepairshop.service.AppointmentService;
import vehiclerepairshop.service.InvalidInputException;

@CrossOrigin(origins = "*")
@RestController
public class TechnicianAccountController {

	@Autowired
	private TechnicianAccountService technicianAccountService;
	@Autowired
	private AppointmentService appointmentService;

	/**
	 * Return a list of all Technician Account Dtos 
	 * @author Catherine
	 * @return list of Technician Dtos
	 */
	@GetMapping(value = { "/getAllTechnicianAccounts", "/getAllTechnicianAccounts/" })
	public List<TechnicianAccountDto> getAllTechnicianAccounts() {
		return technicianAccountService.getAllTechnicianAccounts().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}

	/**
	 * Return the technician account with specified username
	 * @author Catherine
	 * @param username
	 * @return Technician Dto
	 */
	@GetMapping(value = { "/getTechnicianAccountByUsername/{username}", "/getTechnicianAccountByUsername/{username}/" })
	public TechnicianAccountDto getTechnicianAccountByUsername(@PathVariable("username") String username) {
		return convertToDto(technicianAccountService.getTechnicianAccountByUsername(username));
	}
	
	/**
	 * Return a list of all Technician Accounts with specified name
	 * @author Catherine
	 * @param name
	 * @return list of Technician Dtos
	 */
	@GetMapping(value = { "/getTechnicianAccountsByName/{name}", "/getTechnicianAccountsByName/{name}" })
	public List<TechnicianAccountDto> getTechnicianAccountsByName(@PathVariable("name") String name) {
		return technicianAccountService.getTechnicianAccountsByName(name).stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}
	
	/**
	 * Create an Technician Account Dto with provided parameters
	 * @author Catherine
	 * @param username
	 * @param password
	 * @param name
	 * @return Technician Account Dto
	 * @throws InvalidInputException
	 */
	@PostMapping(value = { "/createTechnicianAccount/{username}/{password}/{name}", "/createTechnicianAccount/{username}/{password}/{name}/" })
	public TechnicianAccountDto createTechnicianAccount(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("name") String name) throws InvalidInputException {
		TechnicianAccount user = technicianAccountService.createTechnicianAccount(username, password, name);
		return convertToDto(user);
	}

	
	/**
	 * Update an Technician Account Dto username, password, and name
	 * If not changing something, pass old value
	 * @author Catherine
	 * @param currentUsername
	 * @param newUsername
	 * @param newPassword
	 * @param newName
	 * @return Technician Account Dto
	 * @throws InvalidInputException
	 */
	@PostMapping(value = {"/updateTechnicianAccount/{currentUsername}/{newUsername}/{newPassword}/{newName}", "/technicianAccount/updateTechnicianAccount/{currentUsername}/{newUsername}/{newPassword}/{newName}/" })
	public TechnicianAccountDto updateTechnicianAccount(@PathVariable("currentUsername") String currentUsername, @PathVariable("newUsername") String newUsername, @PathVariable("newPassword") String newPassword, @PathVariable("newName") String newName) throws InvalidInputException {
		TechnicianAccount user = technicianAccountService.updateTechnicianAccount(currentUsername, newUsername, newPassword, newName);
		return convertToDto(user);
	}

	/**
	 * Delete technician account
	 * @author Catherine
	 * @param username
	 * @return boolean if successful
	 * @throws InvalidInputException
	 */
	@DeleteMapping(value = { "/deleteTechnicianAccount/{username}", "/technicianAccount/deleteTechnicianAccount/{username}/" })
	public boolean deleteTechnicianAccount(@PathVariable("username") String username) throws InvalidInputException {
		boolean successful = technicianAccountService.deleteTechnicianAccount(username);
		return successful;
	}
	
	/**
	 * Login and generate token
	 * @author Catherine
	 * @param username
	 * @param password
	 * @return boolean if successful
	 * @throws InvalidInputException
	 */
	@PostMapping(value = {"/loginTechnicianAccount/{username}/{password}", "/loginTechnicianAccount/{username}/{password}/" })
	public boolean loginTechnicianAccount(@PathVariable("username") String username, @PathVariable("password") String password) throws InvalidInputException {
		boolean successful = technicianAccountService.loginTechnicianAccount(username, password);
		return successful;
	}
	
	/**
	 * Logout and delete token
	 * @author Catherine
	 * @param username
	 * @return boolean if successful
	 * @throws InvalidInputException
	 */
	@PostMapping(value = {"/logoutTechnicianAccount/{username}", "/logoutTechnicianAccount/{username}/" })
	public boolean logoutTechnicianAccount(@PathVariable("username") String username) throws InvalidInputException {
		boolean successful = technicianAccountService.logoutTechnicianAccount(username);
		return successful;
	}
	
	/**
	 * Authenticate token
	 * @author Catherine
	 * @param username
	 * @return boolean authenticity
	 * @throws InvalidInputException
	 */
	@PostMapping(value = {"/authenticateTechnicianAccount/{username}", "/authenticateTechnicianAccount/{username}/" })
	public boolean authenticateTechnicianAccount(@PathVariable("username") String username) throws InvalidInputException{
		boolean authentic = technicianAccountService.authenticateTechnicianAccount(username);
		return authentic;
	}
	/**
	 * Get all technicians associated with specified appointment
	 * @param appointmentDto
	 * @return List of technician account Dtos
	 */
	// TODO fix url once AppointmentDto and Controller is done
	@GetMapping(value = { "/getTechniciansAccountByAppointment/getAppointmentByAppointmentId/{appointmentId}", "/getTechniciansAccountByAppointment/getAppointmentByAppointmentId/{appointmentId}/" })
	public List<TechnicianAccountDto> getTechnicianAccountsByAppointment(@PathVariable("appointmentId") AppointmentDto appointmentDto) {
		return technicianAccountService.getTechnicianAccountsForAppointment(convertToDomainObject(appointmentDto)).stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}
	

	//-------------------------- Helper Methods -----------------------------

	/**
	 * Helper Method to convert an technician account to a Dto
	 * @author Catherine
	 * @param user
	 * @return
	 */
	private TechnicianAccountDto convertToDto(TechnicianAccount user) throws IllegalArgumentException{
		if (user == null) {
			throw new IllegalArgumentException("This user does not exist");
		}
		TechnicianAccountDto technicianAccountDto = new TechnicianAccountDto(user.getUsername(), user.getPassword(), user.getName());
		technicianAccountDto.setAppointments(user.getAppointment().stream().map(c -> convertToDto(c)).collect(Collectors.toList()));
		return technicianAccountDto;
	}
	
	/**
	 * Helper Method to convert an appointment to a Dto
	 * Will return null if you pass null
	 * @author Catherine
	 * @author Catherine
	 * @param car
	 * @return AppointmentDto
	 */
	private AppointmentDto convertToDto(Appointment apt)  {
		if (apt == null) {
			return null;
		}
		else {
			// TODO add appointment attributes once AppointmentDto is finished

			AppointmentDto aptDto = new AppointmentDto();

			return aptDto;
		}
	}
	
	/**
	 * Helper method to get the appointment for the appointmentDto
	 * @author Catherine
	 * @param appointmentDto
	 * @return Appointment
	 */
	private Appointment convertToDomainObject(AppointmentDto appointmentDto)  {
		if (appointmentDto == null) {
			return null;
		}
		else {
			// TODO
			//return appointmentService.getAppointmentById(appointmentDto.getId());
			return null;
		}
	}




}