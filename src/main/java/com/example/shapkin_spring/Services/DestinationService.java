package com.example.shapkin_spring.Services;

import com.example.shapkin_spring.Models.DestinationModel;
import com.example.shapkin_spring.Repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<DestinationModel> findAll() {
        return destinationRepository.findAll();
    }

    public Optional<DestinationModel> findById(Long id) {
        return destinationRepository.findById(id);
    }

    public DestinationModel save(DestinationModel destination) {
        return destinationRepository.save(destination);
    }

    public void deleteById(Long id) {
        destinationRepository.deleteById(id);
    }
}
