package ca.mcgill.ecse321.vehiclerepairshop.dto;

public class GarageDto {

	private String garageId;

	public GarageDto() {	
	}

	public GarageDto(String garageId) {
		this.garageId = garageId;
	}


	public String getGarageId() {
		return garageId;
	}


	public void setGarageId(String aGarageId) {
		this.garageId = aGarageId;
	}



}
