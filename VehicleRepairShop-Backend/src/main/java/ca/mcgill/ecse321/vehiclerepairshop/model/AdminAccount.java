package ca.mcgill.ecse321.vehiclerepairshop.model;
import javax.persistence.Entity;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/



// line 15 "model.ump"
// line 131 "model.ump"
@Entity
public class AdminAccount extends UserAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AdminAccount(String aName, String aPassword, String aUsername)
  {
    super(aName, aPassword, aUsername);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}

//
//@Entity
//public class Admin extends Account
//{
//
//
//}