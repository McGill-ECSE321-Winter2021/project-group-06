
package ca.mcgill.ecse321.vehiclerepairshop.model;


import javax.persistence.Id;

import javax.persistence.Entity;


@Entity
public class Garage
{
  private String garageId;

  public void setGarageId(String aGarageId)
  {
    this.garageId = aGarageId;
  }

  @Id
  public String getGarageId()
  {
    return this.garageId;
  }
  


}
