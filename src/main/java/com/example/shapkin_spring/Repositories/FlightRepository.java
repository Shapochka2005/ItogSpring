package com.example.shapkin_spring.Repositories;

import com.example.shapkin_spring.Models.FlightModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<FlightModel, Long> {
}
