
package ca.mcgill.ecse321.vehiclerepairshop.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/



// line 69 "model.ump"
// line 124 "model.ump"
public class BusinessInformation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BusinessInformation Attributes
  private String name;
  private String address;
  private String phoneNumber;
  private String emailAddress;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BusinessInformation(String aName, String aAddress, String aPhoneNumber, String aEmailAddress)
  {
    name = aName;
    address = aAddress;
    phoneNumber = aPhoneNumber;
    emailAddress = aEmailAddress;
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

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmailAddress(String aEmailAddress)
  {
    boolean wasSet = false;
    emailAddress = aEmailAddress;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getAddress()
  {
    return address;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "emailAddress" + ":" + getEmailAddress()+ "]";
  }
}

//import javax.persistence.Entity;
//import javax.persistence.Id;
//
//@Entity
//public class BusinessInformation
//{
//
//  private String name;
//  private String address;
//  private String phoneNumber;
//  private String emailAddress;
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
//  public boolean setAddress(String aAddress)
//  {
//    boolean wasSet = false;
//    address = aAddress;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setPhoneNumber(String aPhoneNumber)
//  {
//    boolean wasSet = false;
//    phoneNumber = aPhoneNumber;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setEmailAddress(String aEmailAddress)
//  {
//    boolean wasSet = false;
//    emailAddress = aEmailAddress;
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
//  public String getAddress()
//  {
//    return address;
//  }
//
//  public String getPhoneNumber()
//  {
//    return phoneNumber;
//  }
//
//  public String getEmailAddress()
//  {
//    return emailAddress;
//  }
//
//
//}