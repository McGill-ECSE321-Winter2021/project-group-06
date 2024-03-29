package ca.mcgill.ecse321.vehiclerepairshop.model;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class BusinessInformation
{

  private String name;
  private String address;
  private String phoneNumber;
  private String emailAddress;


  public void setName(String aName)
  {
    this.name = aName;
  }

  public void setAddress(String aAddress)
  {
    this.address = aAddress;
  }

  public void setPhoneNumber(String aPhoneNumber)
  {
    this.phoneNumber = aPhoneNumber;
  }

  public void setEmailAddress(String aEmailAddress)
  {
    this.emailAddress = aEmailAddress;
    
  }

  @Id
  public String getName()
  {
    return this.name;
  }

  public String getAddress()
  {
    return this.address;
  }

  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }

  public String getEmailAddress()
  {
    return this.emailAddress;
  }


}