

package ca.mcgill.ecse321.vehiclerepairshop.model;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Account
{
  private String name;
  private String password;
  private String uniqueId;

  
  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setUniqueId(String aUniqueId)
  {
    boolean wasSet = false;
    uniqueId = aUniqueId;
    wasSet = true;
    return wasSet;
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

  public String getUniqueId()
  {
    return uniqueId;
  }

}