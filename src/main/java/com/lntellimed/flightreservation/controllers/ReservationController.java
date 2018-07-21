package com.lntellimed.flightreservation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lntellimed.flightreservation.entities.Flight;
import com.lntellimed.flightreservation.repos.FlightRepository;

@Controller
public class ReservationController {

	@Autowired
	FlightRepository flightRepository;
	
	@RequestMapping("showCompleteResvervation")
	public String showCompleteResvervation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
		Flight flight = flightRepository.findById(flightId).get();
		modelMap.addAttribute("flight", flight);
		return "completeReservation";
	}
}
