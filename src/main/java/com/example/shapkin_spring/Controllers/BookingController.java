package com.example.shapkin_spring.Controllers;

import com.example.shapkin_spring.Models.BookingModel;
import com.example.shapkin_spring.Services.BookingService;
import com.example.shapkin_spring.Services.FlightService;
import com.example.shapkin_spring.Services.HotelService;
import com.example.shapkin_spring.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;
    private final HotelService hotelService;
    private final FlightService flightService;

    @Autowired
    public BookingController(BookingService bookingService, UserService userService, HotelService hotelService, FlightService flightService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.hotelService = hotelService;
        this.flightService = flightService;
    }

    @GetMapping
    public String getAllBookings(Model model) {
        model.addAttribute("bookings", bookingService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("hotels", hotelService.findAll());
        model.addAttribute("flights", flightService.findAll());
        return "bookingPage";
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingModel> getBookingById(@PathVariable Long id) {
        return bookingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public String createBooking(@RequestParam Long userId,
                                @RequestParam Long hotelId,
                                @RequestParam Long flightId,
                                @RequestParam LocalDateTime bookingDate,
                                @RequestParam Double totalPrice) {

        BookingModel booking = new BookingModel();
        booking.setUser(userService.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID")));
        booking.setHotel(hotelService.findById(hotelId).orElseThrow(() -> new IllegalArgumentException("Invalid hotel ID")));
        booking.setFlight(flightService.findById(flightId).orElseThrow(() -> new IllegalArgumentException("Invalid flight ID")));
        booking.setBookingDate(bookingDate);
        booking.setTotalPrice(totalPrice);

        bookingService.save(booking);
        return "redirect:/api/bookings";
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingModel> updateBooking(@PathVariable Long id, @RequestBody BookingModel booking) {
        if (!bookingService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        booking.setBookingId(id);
        return ResponseEntity.ok(bookingService.save(booking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        if (!bookingService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        bookingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
