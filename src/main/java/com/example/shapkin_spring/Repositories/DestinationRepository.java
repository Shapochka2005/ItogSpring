package com.example.shapkin_spring.Repositories;

import com.example.shapkin_spring.Models.DestinationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<DestinationModel, Long> {
}
