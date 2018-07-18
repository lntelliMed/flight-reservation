package com.lntellimed.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lntellimed.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
