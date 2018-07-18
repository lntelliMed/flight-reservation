package com.lntellimed.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lntellimed.flightreservation.entities.Flight;
import com.lntellimed.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
