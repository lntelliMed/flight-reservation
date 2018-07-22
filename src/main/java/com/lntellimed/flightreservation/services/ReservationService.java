package com.lntellimed.flightreservation.services;

import com.lntellimed.flightreservation.dto.ReservationRequest;
import com.lntellimed.flightreservation.entities.Reservation;

public interface ReservationService {
	public Reservation bookFlight(ReservationRequest request);
}
