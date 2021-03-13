package vehiclerepairshop.controller;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.vehiclerepairshop.model.OfferedService;
import vehiclerepairshop.dto.AppointmentDto;
import vehiclerepairshop.dto.OfferedServiceDto;
import vehiclerepairshop.service.OfferedServiceService;


@CrossOrigin(origins = "*")
@RestController
public class OfferedServiceController {

	@Autowired
	private OfferedServiceService offeredServiceService;
	
	/**
	 * @TODO: check why OfferedServiceService.getAllOfferedServices() returns type OfferedServiceDto
	 */
	@GetMapping(value = {"/getAllOfferedServices", "/getAllOfferedServices/"})
	public List<OfferedServiceDto> getAllOfferedServices(){
		List<OfferedServiceDto> offeredServiceDtos = new ArrayList<>();
		for (OfferedService offeredService: offeredServiceService.getAllOfferedServices()) {
			offeredServiceDtos.add(convertToDto(offeredService));
		}
		return offeredServiceDtos;
	}
	
	
	private OfferedServiceDto convertToDto(OfferedService s) {
		if (s == null) {
			throw new IllegalArgumentException("There is no such OfferedService!");
		}
		
		OfferedServiceDto offerServiceDto = new OfferedServiceDto(s.getOfferedServiceId(), s.getPrice(), 
				s.getName(),s.getDuration(), s.getReminderTime(),s.getReminderDate(), s.getDescription());
		return offerServiceDto;
	}
	
}
