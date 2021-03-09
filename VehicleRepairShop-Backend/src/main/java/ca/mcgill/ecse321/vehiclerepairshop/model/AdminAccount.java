package ca.mcgill.ecse321.vehiclerepairshop.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AdminAccount
{

	private String name;
	private String password;
	private String username;
	private BusinessInformation businessInformation;

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
	
	public void setBusinessInformation(BusinessInformation aBusinessInformation)
	{

		this.businessInformation = aBusinessInformation;
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
	
	@ManyToOne
	public BusinessInformation getBusinessInformation()
	{

		return this.businessInformation;
	}
}