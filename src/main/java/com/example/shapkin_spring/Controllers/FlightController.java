package com.example.shapkin_spring.Controllers;

import com.example.shapkin_spring.Models.DestinationModel;
import com.example.shapkin_spring.Models.FlightModel;
import com.example.shapkin_spring.Services.DestinationService;
import com.example.shapkin_spring.Services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;
    private final DestinationService destinationService;

    @Autowired
    public FlightController(FlightService flightService, DestinationService destinationService) {
        this.flightService = flightService;
        this.destinationService = destinationService;
    }

    @GetMapping
    public String getAllFlight(Model model) {
        model.addAttribute("flights", flightService.findAll());
        model.addAttribute("newFlight", new FlightModel());
        model.addAttribute("destinations", destinationService.findAll());
        return "flightPage";
    }

    @PostMapping
    public String createFlight(@RequestParam String airline,
                               @RequestParam Long fromDestinationId,
                               @RequestParam Long toDestinationId,
                               @RequestParam LocalDateTime departureTime,
                               @RequestParam LocalDateTime arrivalTime,
                               @RequestParam Double price) {

        DestinationModel fromDestination = destinationService.findById(fromDestinationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid fromDestinationId: " + fromDestinationId));
        DestinationModel toDestination = destinationService.findById(toDestinationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid toDestinationId: " + toDestinationId));

        FlightModel flight = new FlightModel();
        flight.setAirline(airline);
        flight.setFromDestination(fromDestination);
        flight.setToDestination(toDestination);
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        flight.setPrice(price);

        flightService.save(flight);
        return "redirect:/api/flights";
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightModel> getFlightById(@PathVariable Long id) {
        Optional<FlightModel> flight = flightService.findById(id);
        return flight.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightModel> updateFlight(@PathVariable Long id, @RequestBody FlightModel flight) {
        if (!flightService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        flight.setFlightId(id);
        return ResponseEntity.ok(flightService.save(flight));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        if (!flightService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        flightService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
