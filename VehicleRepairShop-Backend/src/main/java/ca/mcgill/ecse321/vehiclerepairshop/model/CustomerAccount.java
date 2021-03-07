package ca.mcgill.ecse321.vehiclerepairshop.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class CustomerAccount
{

  private List<Car> car;
  private String name;
  private String password;
  private String username;

  
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

  
  @OneToMany(cascade = {CascadeType.ALL})
  public List<Car> getCar()
  {
    return this.car;
  }
  
  public void setCar(List<Car> aCar) {
	  this.car = aCar;
  }
  

}