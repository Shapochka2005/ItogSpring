package com.example.shapkin_spring.Controllers;

import com.example.shapkin_spring.Models.BookingModel;
import com.example.shapkin_spring.Models.PaymentModel;
import com.example.shapkin_spring.Models.ReviewModel;
import com.example.shapkin_spring.Services.BookingService;
import com.example.shapkin_spring.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final BookingService bookingService;

    @Autowired
    public PaymentController(PaymentService paymentService, BookingService bookingService) {
        this.paymentService = paymentService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public String getAllPayment(Model model) {
        model.addAttribute("payments", paymentService.findAll());
        model.addAttribute("newReview", new PaymentModel());
        return "paymentPage";
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentModel> getPaymentById(@PathVariable Long id) {
        return paymentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PaymentModel> createPayment(@RequestParam Long booking_id, @RequestParam Double amount, @RequestParam String method) {
        BookingModel booking = bookingService.findById(booking_id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        PaymentModel payment = new PaymentModel();
        payment.setBooking(booking);
        payment.setAmount(amount);
        payment.setPaymentMethod(method);
        payment.setPaymentDate(LocalDateTime.now());

        PaymentModel savedPayment = paymentService.save(payment);
        return ResponseEntity.ok(savedPayment);
    }



    @PutMapping("/{id}")
    public ResponseEntity<PaymentModel> updatePayment(@PathVariable Long id, @RequestBody PaymentModel payment) {
        if (!paymentService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        payment.setPaymentId(id);
        return ResponseEntity.ok(paymentService.save(payment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        if (!paymentService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        paymentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
