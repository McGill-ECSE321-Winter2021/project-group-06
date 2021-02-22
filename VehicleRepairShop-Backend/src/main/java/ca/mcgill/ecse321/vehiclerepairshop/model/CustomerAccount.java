
package ca.mcgill.ecse321.vehiclerepairshop.model;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;

@Entity
public class CustomerAccount extends UserAccount
{

  private List<Car> car;

  
  @OneToMany(cascade = {CascadeType.ALL})
  public List<Car> getCars()
  {
    return this.car;
  }
  

}