package com.example.shapkin_spring.Controllers;

import com.example.shapkin_spring.Models.DestinationModel;
import com.example.shapkin_spring.Services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping
    public String getAllDestination(Model model) {
        model.addAttribute("destinations", destinationService.findAll());
        return "destinationPage"; // Возвращает шаблон с отображением всех назначений
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationModel> getDestinationById(@PathVariable Long id) {
        return destinationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public String createDestination(@ModelAttribute DestinationModel destination) {
        destinationService.save(destination);
        return "redirect:/api/destinations";
    }


    @PutMapping("/{id}")
    public ResponseEntity<DestinationModel> updateDestination(@PathVariable Long id, @RequestBody DestinationModel destination) {
        if (!destinationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        destination.setDestinationId(id);
        return ResponseEntity.ok(destinationService.save(destination));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable Long id) {
        if (!destinationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        destinationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
