package ca.mcgill.ecse321.vehiclerepairshop.dto;

import java.util.Collections;
import java.util.List;

public class GarageDto {

	private boolean isAvailable;
	private String garageId;
	private List<AppointmentDto> appointmentsDTO;

	public GarageDto() {	
	}

	@SuppressWarnings("unchecked")
	public GarageDto(String garageId) {
		this(false, garageId, Collections.EMPTY_LIST);
	}

	public GarageDto(boolean isAvailable, String garageId,  List<AppointmentDto> arrayList) {
		this.isAvailable = isAvailable;
		this.garageId = garageId;
		this.appointmentsDTO = arrayList;
	}

	public boolean getAvailability() {
		return isAvailable;
	}

	public String getGarageId() {
		return garageId;
	}

	public List<AppointmentDto> getAppointments() {
		return appointmentsDTO;
	}

	public void setAvailability(boolean aIsAvailable) {
		this.isAvailable = aIsAvailable;
	}

	public void setGarageId(String aGarageId) {
		this.garageId = aGarageId;
	}

	public void setAppointments(List<AppointmentDto> arrayList) {
		this.appointmentsDTO = arrayList;
	}



}
