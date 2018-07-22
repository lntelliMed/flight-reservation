package com.lntellimed.flightreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lntellimed.flightreservation.dto.ReservationRequest;
import com.lntellimed.flightreservation.entities.Flight;
import com.lntellimed.flightreservation.entities.Passenger;
import com.lntellimed.flightreservation.entities.Reservation;
import com.lntellimed.flightreservation.repos.FlightRepository;
import com.lntellimed.flightreservation.repos.PassengerRepository;
import com.lntellimed.flightreservation.repos.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		//Make Payment: Contact a third party payment gateway, etc.
		//request.getCardNumber();
		
		Long flightId = request.getFlightId();
		Flight flight = flightRepository.findById(flightId).get();
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		Reservation savedReservation = reservationRepository.save(reservation);
		
		return savedReservation;
	}

}
