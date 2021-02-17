/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/
package ca.mcgill.ecse321.vehiclerepairshop.model;

import java.util.*;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;


// line 10 "model.ump"
// line 90 "model.ump"
public class Customer extends Account
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum MotorType { Electric, Hybrid, Gas, Diesel }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Associations
  private List<Car> car;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aName, String aPassword, String aUniqueId)
  {
    super(aName, aPassword, aUniqueId);
    car = new ArrayList<Car>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Car getCar(int index)
  {
    Car aCar = car.get(index);
    return aCar;
  }

  public List<Car> getCar()
  {
    List<Car> newCar = Collections.unmodifiableList(car);
    return newCar;
  }

  public int numberOfCar()
  {
    int number = car.size();
    return number;
  }

  public boolean hasCar()
  {
    boolean has = car.size() > 0;
    return has;
  }

  public int indexOfCar(Car aCar)
  {
    int index = car.indexOf(aCar);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCar()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Car addCar(String aLicensePlate, String aModel, int aYear, Car.MotorType aMotorType)
  {
    return new Car(aLicensePlate, aModel, aYear, aMotorType, this);
  }

  public boolean addCar(Car aCar)
  {
    boolean wasAdded = false;
    if (car.contains(aCar)) { return false; }
    Customer existingOwner = aCar.getOwner();
    boolean isNewOwner = existingOwner != null && !this.equals(existingOwner);
    if (isNewOwner)
    {
      aCar.setOwner(this);
    }
    else
    {
      car.add(aCar);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCar(Car aCar)
  {
    boolean wasRemoved = false;
    //Unable to remove aCar, as it must always have a owner
    if (!this.equals(aCar.getOwner()))
    {
      car.remove(aCar);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCarAt(Car aCar, int index)
  {  
    boolean wasAdded = false;
    if(addCar(aCar))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCar()) { index = numberOfCar() - 1; }
      car.remove(aCar);
      car.add(index, aCar);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCarAt(Car aCar, int index)
  {
    boolean wasAdded = false;
    if(car.contains(aCar))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCar()) { index = numberOfCar() - 1; }
      car.remove(aCar);
      car.add(index, aCar);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCarAt(aCar, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (car.size() > 0)
    {
      Car aCar = car.get(car.size() - 1);
      aCar.delete();
      car.remove(aCar);
    }
    
    super.delete();
  }

}