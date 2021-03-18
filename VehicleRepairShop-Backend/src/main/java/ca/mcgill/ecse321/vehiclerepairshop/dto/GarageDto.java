package ca.mcgill.ecse321.vehiclerepairshop.dto;

import java.util.Collections;
import java.util.List;

public class GarageDto {

	private String garageId;
	private List<AppointmentDto> appointmentsDTO;

	public GarageDto() {	
	}

	@SuppressWarnings("unchecked")
	public GarageDto(String garageId) {
		this(garageId, Collections.EMPTY_LIST);
	}

	public GarageDto(String garageId,  List<AppointmentDto> arrayList) {
		this.garageId = garageId;
		this.appointmentsDTO = arrayList;
	}


	public String getGarageId() {
		return garageId;
	}

	public List<AppointmentDto> getAppointments() {
		return appointmentsDTO;
	}

	public void setGarageId(String aGarageId) {
		this.garageId = aGarageId;
	}

	public void setAppointments(List<AppointmentDto> arrayList) {
		this.appointmentsDTO = arrayList;
	}



}
