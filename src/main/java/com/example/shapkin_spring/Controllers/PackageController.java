package com.example.shapkin_spring.Controllers;

import com.example.shapkin_spring.Models.HotelModel;
import com.example.shapkin_spring.Models.PackageModel;
import com.example.shapkin_spring.Models.PaymentModel;
import com.example.shapkin_spring.Services.HotelService;
import com.example.shapkin_spring.Services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/api/packages")
public class PackageController {

    private final PackageService packageService;
    private final HotelService hotelService;

    @Autowired
    public PackageController(PackageService packageService, HotelService hotelService) {
        this.packageService = packageService;
        this.hotelService = hotelService;
    }

    @GetMapping
    public String getAllPackage(Model model) {
        model.addAttribute("packages", packageService.findAll());
        model.addAttribute("newPackage", new PaymentModel());
        return "packagePage";
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageModel> getPackageById(@PathVariable Long id) {
        return packageService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PackageModel> createPackage(@RequestParam String name,
                                                      @RequestParam String description,
                                                      @RequestParam Double price,
                                                      @RequestParam Integer durationDays,
                                                      @RequestParam Long hotel_id) {
        HotelModel hotel = hotelService.findById(hotel_id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        PackageModel packageModel = new PackageModel();
        packageModel.setName(name);
        packageModel.setDescription(description);
        packageModel.setPrice(price);
        packageModel.setDurationDays(durationDays);
        packageModel.setHotel(hotel);

        PackageModel savedPackage = packageService.save(packageModel);
        return ResponseEntity.ok(savedPackage);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PackageModel> updatePackage(@PathVariable Long id, @RequestBody PackageModel packageModel) {
        if (!packageService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        PackageModel existingPackage = packageService.findById(id).get();
        existingPackage.setName(packageModel.getName());
        existingPackage.setDescription(packageModel.getDescription());
        existingPackage.setPrice(packageModel.getPrice());
        existingPackage.setDurationDays(packageModel.getDurationDays());
        existingPackage.setHotel(packageModel.getHotel());

        PackageModel updatedPackage = packageService.save(existingPackage);
        return ResponseEntity.ok(updatedPackage);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackage(@PathVariable Long id) {
        if (!packageService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        packageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
