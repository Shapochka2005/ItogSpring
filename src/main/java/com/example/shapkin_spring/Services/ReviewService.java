package com.example.shapkin_spring.Services;

import com.example.shapkin_spring.Models.ReviewModel;
import com.example.shapkin_spring.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewModel> findAll() {
        return reviewRepository.findAll();
    }

    public Optional<ReviewModel> findById(Long id) {
        return reviewRepository.findById(id);
    }

    public ReviewModel save(ReviewModel review) {
        return reviewRepository.save(review);
    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}
