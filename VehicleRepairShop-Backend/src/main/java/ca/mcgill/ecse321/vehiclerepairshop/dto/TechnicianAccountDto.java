package ca.mcgill.ecse321.vehiclerepairshop.dto;
import java.util.Collections;
import java.util.List;


public class TechnicianAccountDto {

	private String username;
	private String password;
	private String name;
	private int token;
	private List<AppointmentDto> appointmentsDTO;
	private List<TimeSlotDto> timeSlotsDTO;

	public TechnicianAccountDto() {
	}
	
	@SuppressWarnings("unchecked")
	public TechnicianAccountDto(String username, String password, String name) {
		this(username, password, name, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
	}
		
	public TechnicianAccountDto(String username, String password, String name, List<AppointmentDto> appointments, List<TimeSlotDto> timeSlots) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.appointmentsDTO = appointments;
		this.timeSlotsDTO = timeSlots;
	}
		
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public List<AppointmentDto> getAppointments() {
		return appointmentsDTO;
	}
	
	public List<TimeSlotDto> getTimeSlots() {
		return timeSlotsDTO;
	}
	
	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAppointments(List<AppointmentDto> arrayList) {
		this.appointmentsDTO = arrayList;
	}
	
	public void setTimeSlots(List<TimeSlotDto> arrayList) {
		this.timeSlotsDTO = arrayList;
	}
	
}