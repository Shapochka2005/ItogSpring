package com.example.shapkin_spring.Services;

import com.example.shapkin_spring.Models.PaymentModel;
import com.example.shapkin_spring.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<PaymentModel> findAll() {
        return paymentRepository.findAll();
    }

    public Optional<PaymentModel> findById(Long id) {
        return paymentRepository.findById(id);
    }

    public PaymentModel save(PaymentModel payment) {
        return paymentRepository.save(payment);
    }

    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }
}
