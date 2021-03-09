package vehiclerepairshop.dto;
import java.util.Collections;
import java.util.List;


public class TechnicianAccountDTO {

	private String username;
	private String password;
	private String name;
	private List<AppointmentDTO> appointmentsDTO;

	public TechnicianAccountDTO() {
	}
	
	@SuppressWarnings("unchecked")
	public TechnicianAccountDTO(String username, String password, String name) {
		this(username, password, name, Collections.EMPTY_LIST);
	}
		
	public TechnicianAccountDTO(String username, String password, String name, List<AppointmentDTO> arrayList) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.appointmentsDTO = arrayList;
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
	
	public List<AppointmentDTO> getAppointments() {
		return appointmentsDTO;
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
	
	public void setAppointments(List<AppointmentDTO> arrayList) {
		this.appointmentsDTO = arrayList;
	}
	
}