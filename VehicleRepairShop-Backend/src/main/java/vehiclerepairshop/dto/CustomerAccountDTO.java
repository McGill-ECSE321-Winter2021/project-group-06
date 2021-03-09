package vehiclerepairshop.dto;
import java.util.Collections;
import java.util.List;



public class CustomerAccountDTO {

	private String username;
	private String password;
	private String name;
	private List<CarDTO> carsDTO;

	public CustomerAccountDTO() {
	}
	
	@SuppressWarnings("unchecked")
	public CustomerAccountDTO(String username, String password, String name) {
		this(username, password, name, Collections.EMPTY_LIST);
	}
		
	public CustomerAccountDTO(String username, String password, String name, List<CarDTO> arrayList) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.carsDTO = arrayList;
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
	
	public List<CarDTO> getCars() {
		return carsDTO;
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
	
	public void setCars(List<CarDTO> arrayList) {
		this.carsDTO = arrayList;
	}
	
}