package ca.mcgill.ecse321.vehiclerepairshop.model;
import javax.persistence.Entity;
import javax.persistence.Id;


import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class UserAccount
{
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
  
  @Id
  public String getName()
  {
    return name;
  }

  public String getPassword()
  {
    return password;
  }

  public String getUsername()
  {
    return username;
  }

}