package com.lntellimed.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lntellimed.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
