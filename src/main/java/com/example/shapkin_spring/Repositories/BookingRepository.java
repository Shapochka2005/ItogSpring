package com.example.shapkin_spring.Repositories;

import com.example.shapkin_spring.Models.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingModel, Long> {
}
