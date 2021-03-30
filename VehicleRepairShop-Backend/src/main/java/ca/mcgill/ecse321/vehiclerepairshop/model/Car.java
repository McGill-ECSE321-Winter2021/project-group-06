package ca.mcgill.ecse321.vehiclerepairshop.model;

import javax.persistence.Id;
import javax.persistence.Entity;


@Entity
public class Car
{
	public enum MotorType { Electric, Hybrid, Gas, Diesel }

	private String licensePlate;
	private String model;
	private int year;
	private MotorType motorType;

	public void setLicensePlate(String aLicensePlate)
	{
		this.licensePlate = aLicensePlate;
	}

	public void setModel(String aModel)
	{
		this.model = aModel;
	}

	public void setYear(int aYear)
	{
		this.year = aYear;
	}

	public void setMotorType(MotorType aMotorType)
	{
		this.motorType = aMotorType;
	}

	@Id
	public String getLicensePlate()
	{
		return licensePlate;
	}

	public String getModel()
	{
		return model;
	}

	public int getYear()
	{
		return year;
	}

	public MotorType getMotorType()
	{
		return motorType;
	}
}

