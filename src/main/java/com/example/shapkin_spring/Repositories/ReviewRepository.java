package com.example.shapkin_spring.Repositories;

import com.example.shapkin_spring.Models.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewModel, Long> {
}
