package vehiclerepairshop.dto;



public class AdminAccountDTO {

	private String username;
	private String password;
	private String name;
	private BusinessInformationDTO businessInformationDTO; 

	public AdminAccountDTO() {
	}
	
	public AdminAccountDTO(String username, String password, String name) {
		this(username, password, name, null);
	}
		
	public AdminAccountDTO(String username, String password, String name, BusinessInformationDTO businessInfo) {
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
	
	public BusinessInformationDTO getBusinessInformation() {
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
	
	public void setBusinessInformation(BusinessInformationDTO businessInfo) {
		this.businessInformationDTO = businessInfo;
	}
	
}