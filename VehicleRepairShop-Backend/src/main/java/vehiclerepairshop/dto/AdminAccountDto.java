package vehiclerepairshop.dto;



public class AdminAccountDto {

	private String username;
	private String password;
	private String name;
	private int token;
	private BusinessInformationDto businessInformationDTO; 
	

	public AdminAccountDto() {
	}
	
	public AdminAccountDto(String username, String password, String name) {
		this(username, password, name, null);
	}
		
	public AdminAccountDto(String username, String password, String name, BusinessInformationDto businessInfo) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.businessInformationDTO = businessInfo;
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
	
	public BusinessInformationDto getBusinessInformation() {
		return businessInformationDTO;
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
	
	public void setBusinessInformation(BusinessInformationDto businessInfo) {
		this.businessInformationDTO = businessInfo;
	}
	
	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}
	
}