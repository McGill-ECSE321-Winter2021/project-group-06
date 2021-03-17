package ca.mcgill.ecse321.vehiclerepairshop.dto;
import java.util.Collections;
import java.util.List;



public class CustomerAccountDto {

	private String username;
	private String password;
	private String name;
	private int token;
	private List<CarDto> carsDTO;

	public CustomerAccountDto() {
	}
	
	@SuppressWarnings("unchecked")
	public CustomerAccountDto(String username, String password, String name) {
		this(username, password, name, Collections.EMPTY_LIST);
	}
		
	public CustomerAccountDto(String username, String password, String name, List<CarDto> arrayList) {
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
	
	public List<CarDto> getCars() {
		return carsDTO;
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
	
	public void setCars(List<CarDto> arrayList) {
		this.carsDTO = arrayList;
	}
	
}