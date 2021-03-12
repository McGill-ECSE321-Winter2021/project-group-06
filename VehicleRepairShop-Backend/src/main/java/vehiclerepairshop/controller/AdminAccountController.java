package vehiclerepairshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import vehiclerepairshop.service.AdminAccountService;

@CrossOrigin(origins = "*")
@RestController
public class AdminAccountController {

	@Autowired
	private AdminAccountController adminAccountService;


}