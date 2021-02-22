package ca.mcgill.ecse321.vehiclerepairshop.model;
import javax.persistence.Entity;
import javax.persistence.Id;



import java.util.*;


import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;

@Entity
public class CustomerAccount extends UserAccount
{

  private List<Car> car;

  
  @OneToMany(cascade = {CascadeType.ALL})
  public List<Car> getCar()
  {
    return this.car;
  }
  
  public void setCar(List<Car> aCar) {
	  this.car = aCar;
  }
  

}