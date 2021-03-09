package vehiclerepairshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import vehiclerepairshop.service.VehicleRepairShopService;

@CrossOrigin(origins = "*")
@RestController
public class VehicleRepairShopController {

	@Autowired
	private VehicleRepairShopService service;


}