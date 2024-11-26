package com.example.shapkin_spring.Controllers;

import com.example.shapkin_spring.Models.HotelModel;
import com.example.shapkin_spring.Models.ReviewModel;
import com.example.shapkin_spring.Models.UserModel;
import com.example.shapkin_spring.Services.HotelService;
import com.example.shapkin_spring.Services.ReviewService;
import com.example.shapkin_spring.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getAllReview(Model model) {
        model.addAttribute("reviews", reviewService.findAll());
        model.addAttribute("newReview", new ReviewModel());
        return "reviewPage";
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReviewModel> getReviewById(@PathVariable Long id) {
        System.out.println("Received request for review ID: " + id); // Отладочное сообщение
        Optional<ReviewModel> review = reviewService.findById(id);
        if (review.isPresent()) {
            System.out.println("Review found: " + review.get());
            return ResponseEntity.ok(review.get());
        } else {
            System.out.println("Review not found for ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }


    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;


    @PostMapping
    public String createReview(@ModelAttribute ReviewModel review, @RequestParam Long user_id, @RequestParam Long hotel_id) {
        review.setCreatedAt(LocalDateTime.now());

        UserModel user = userService.findById(user_id).orElseGet(() -> userService.findFirstUser());
        HotelModel hotel = hotelService.findById(hotel_id).orElseGet(() -> hotelService.findFirstHotel());

        review.setUser(user);
        review.setHotel(hotel);

        reviewService.save(review);
        return "redirect:/api/reviews";
    }




    @PutMapping("/{id}")
    public ResponseEntity<ReviewModel> updateReview(@PathVariable Long id, @RequestBody ReviewModel review) {
        if (!reviewService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        review.setReviewId(id);
        return ResponseEntity.ok(reviewService.save(review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        if (!reviewService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
