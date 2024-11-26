package com.example.shapkin_spring.Services;

import com.example.shapkin_spring.Models.PackageModel;
import com.example.shapkin_spring.Repositories.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {

    private final PackageRepository packageRepository;

    @Autowired
    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public List<PackageModel> findAll() {
        return packageRepository.findAll();
    }

    public Optional<PackageModel> findById(Long id) {
        return packageRepository.findById(id);
    }

    public PackageModel save(PackageModel packageModel) {
        return packageRepository.save(packageModel);
    }

    public void deleteById(Long id) {
        packageRepository.deleteById(id);
    }
}
