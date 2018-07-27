package com.lntellimed.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lntellimed.flightreservation.controllers.ReservationController;
import com.lntellimed.flightreservation.dto.ReservationRequest;
import com.lntellimed.flightreservation.entities.Flight;
import com.lntellimed.flightreservation.entities.Passenger;
import com.lntellimed.flightreservation.entities.Reservation;
import com.lntellimed.flightreservation.repos.FlightRepository;
import com.lntellimed.flightreservation.repos.PassengerRepository;
import com.lntellimed.flightreservation.repos.ReservationRepository;
import com.lntellimed.flightreservation.util.EmailUtil;
import com.lntellimed.flightreservation.util.PdfGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PdfGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		LOGGER.info("Inside bookFlight()");
		//Make Payment: Contact a third party payment gateway, etc.
		//request.getCardNumber();
		
		Long flightId = request.getFlightId();
		LOGGER.info("Fetching flight for Flight ID: " + flightId);
		Flight flight = flightRepository.findById(flightId).get();
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Saving the passenger: " + passenger);
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		LOGGER.info("Saving the reservatin: " + reservation);
		Reservation savedReservation = reservationRepository.save(reservation);
		String filePath = "/Users/sam/Documents/reservations/reservation" + savedReservation.getId() + ".pdf";
		
		LOGGER.info("Generating the itinerary");
		pdfGenerator.generateItinerary(savedReservation,
				filePath);
//		LOGGER.info("Emailing the itinerary");
//		emailUtil.sendItinerery(passenger.getEmail(), filePath);
		return savedReservation;
	}

}
