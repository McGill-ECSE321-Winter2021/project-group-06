
package ca.mcgill.ecse321.vehiclerepairshop.model;
import java.util.*;
import javax.persistence.*;

@Entity
public class TechnicianAccount
{

	private String name;
	private String password;
	private String username;
	private int token;


	public void setName(String aName)
	{
		this.name = aName;
	}

	public void setPassword(String aPassword)
	{
		this.password = aPassword;
	}

	public void setUsername(String aUsername)
	{
		this.username = aUsername;
	}


	public String getName()
	{
		return this.name;
	}

	public String getPassword()
	{
		return this.password;
	}
	@Id
	public String getUsername()
	{
		return this.username;
	}

	private List<TimeSlot> availability;

	@ManyToMany
	public List<TimeSlot> getAvailability()
	{
		return this.availability;
	}

	public void setAvailability(List<TimeSlot> availability) {
		this.availability = availability;	  
	}


	public void setToken(int token) {
		this.token = token;
	}

	public int getToken() {
		return this.token;
	}
}