package com.example.shapkin_spring.Services;

import com.example.shapkin_spring.Models.FlightModel;
import com.example.shapkin_spring.Repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<FlightModel> findAll() {
        return flightRepository.findAll();
    }

    public Optional<FlightModel> findById(Long id) {
        return flightRepository.findById(id);
    }

    public FlightModel save(FlightModel flight) {
        return flightRepository.save(flight);
    }

    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }
}
