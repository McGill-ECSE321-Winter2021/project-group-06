

package ca.mcgill.ecse321.vehiclerepairshop.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/



/**
 * Auto Repair Shop System
 */
// line 3 "model.ump"
// line 86 "model.ump"
public class Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Account Attributes
  private String name;
  private String password;
  private String uniqueId;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Account(String aName, String aPassword, String aUniqueId)
  {
    name = aName;
    password = aPassword;
    uniqueId = aUniqueId;
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "password" + ":" + getPassword()+ "," +
            "uniqueId" + ":" + getUniqueId()+ "]";
  }
}

//import javax.persistence.Id;
//import javax.persistence.Entity;
//
//@Entity
//public class Account
//{
//  private String name;
//  private String password;
//  private String uniqueId;
//
//  
//  public boolean setName(String aName)
//  {
//    boolean wasSet = false;
//    name = aName;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setPassword(String aPassword)
//  {
//    boolean wasSet = false;
//    password = aPassword;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setUniqueId(String aUniqueId)
//  {
//    boolean wasSet = false;
//    uniqueId = aUniqueId;
//    wasSet = true;
//    return wasSet;
//  }
//  
//  @Id
//  public String getName()
//  {
//    return name;
//  }
//
//  public String getPassword()
//  {
//    return password;
//  }
//
//  public String getUniqueId()
//  {
//    return uniqueId;
//  }
//
//}