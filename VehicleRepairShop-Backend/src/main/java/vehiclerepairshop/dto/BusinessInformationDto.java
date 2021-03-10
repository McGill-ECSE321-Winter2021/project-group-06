package vehiclerepairshop.dto;

import java.util.Collections;
import java.util.List;

public class BusinessInformationDto {

	private String name;
	private String address;
	private String phoneNumber;
	private String emailAddress;
	private List<AdminAccountDto> adminAccountsDTO;
	
	public BusinessInformationDto () {
	}
	
	@SuppressWarnings("unchecked")
	public BusinessInformationDto(String name, String address, String phoneNumber, String emailAddress) {
		this(name, address, phoneNumber, emailAddress, Collections.EMPTY_LIST);
	}
	
	public BusinessInformationDto (String name, String address, String phoneNumber, String emailAddress, List<AdminAccountDto> arrayList) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.adminAccountsDTO = arrayList;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAdress() {
		return address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public List<AdminAccountDto> getAppointments() {
		return adminAccountsDTO;
	}
	
	public void setAdminAccounts(List<AdminAccountDto> arrayList) {
		this.adminAccountsDTO = arrayList;
	}
	
}
