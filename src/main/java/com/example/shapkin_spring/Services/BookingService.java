package com.example.shapkin_spring.Services;

import com.example.shapkin_spring.Models.BookingModel;
import com.example.shapkin_spring.Repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<BookingModel> findAll() {
        return bookingRepository.findAll();
    }

    public Optional<BookingModel> findById(Long id) {
        return bookingRepository.findById(id);
    }

    public BookingModel save(BookingModel booking) {
        return bookingRepository.save(booking);
    }

    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }
}
