package com.example.shapkin_spring.Repositories;

import com.example.shapkin_spring.Models.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Long> {
}
