package com.example.shapkin_spring.Services;

import com.example.shapkin_spring.Models.HotelModel;
import com.example.shapkin_spring.Repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HotelModel findFirstHotel() {
        return hotelRepository.findAll().stream().findFirst().orElse(null);
    }

    public List<HotelModel> findAll() {
        return hotelRepository.findAll();
    }

    public Optional<HotelModel> findById(Long id) {
        return hotelRepository.findById(id);
    }

    public HotelModel save(HotelModel hotel) {
        return hotelRepository.save(hotel);
    }

    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }
}
