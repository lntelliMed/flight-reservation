package com.lntellimed.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lntellimed.flightreservation.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
