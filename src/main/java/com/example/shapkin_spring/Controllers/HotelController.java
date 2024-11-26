package com.example.shapkin_spring.Controllers;

import com.example.shapkin_spring.Models.DestinationModel;
import com.example.shapkin_spring.Models.HotelModel;
import com.example.shapkin_spring.Models.PaymentModel;
import com.example.shapkin_spring.Services.DestinationService;
import com.example.shapkin_spring.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;
    private final DestinationService destinationService;

    @Autowired
    public HotelController(HotelService hotelService, DestinationService destinationService) {
        this.hotelService = hotelService;
        this.destinationService = destinationService;
    }

    @GetMapping
    public String getAllHotel(Model model) {
        model.addAttribute("hotels", hotelService.findAll());
        model.addAttribute("newHotel", new HotelModel());
        model.addAttribute("destinations", destinationService.findAll());
        return "hotelPage";
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelModel> getHotelById(@PathVariable Long id) {
        return hotelService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HotelModel> createHotel(@RequestParam String name,
                                                  @RequestParam Double rating,
                                                  @RequestParam Double pricePerNight,
                                                  @RequestParam Long destinationId) {
        DestinationModel destination = destinationService.findById(destinationId)
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        HotelModel hotel = new HotelModel();
        hotel.setName(name);
        hotel.setRating(rating);
        hotel.setPricePerNight(pricePerNight);
        hotel.setDestination(destination);

        HotelModel savedHotel = hotelService.save(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel);
    }


    @PutMapping("/{id}")
    public ResponseEntity<HotelModel> updateHotel(@PathVariable Long id, @RequestBody HotelModel hotel) {
        if (!hotelService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        hotel.setHotelId(id);
        return ResponseEntity.ok(hotelService.save(hotel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        if (!hotelService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        hotelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
