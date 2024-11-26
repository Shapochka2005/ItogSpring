package com.example.shapkin_spring.Repositories;

import com.example.shapkin_spring.Models.PackageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<PackageModel, Long> {
}
