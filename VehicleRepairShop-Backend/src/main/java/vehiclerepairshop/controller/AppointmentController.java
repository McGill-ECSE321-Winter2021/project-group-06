package vehiclerepairshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import vehiclerepairshop.service.AppointmentService;

/**
 * 
 * @author chengchen
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	
	
	


}
