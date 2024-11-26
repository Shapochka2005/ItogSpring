package com.example.shapkin_spring.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class FlightModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    private String airline;

    @ManyToOne
    @JoinColumn(name = "from_destination_id", nullable = false)
    private DestinationModel fromDestination;

    @ManyToOne
    @JoinColumn(name = "to_destination_id", nullable = false)
    private DestinationModel toDestination;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Double price;

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public DestinationModel getFromDestination() {
        return fromDestination;
    }
    public DestinationModel getToDestination() {
        return toDestination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setFromDestination(DestinationModel fromDestination) {
        if (fromDestination == null) {
            throw new IllegalArgumentException("From destination cannot be null");
        }
        this.fromDestination = fromDestination;
    }

    public void setToDestination(DestinationModel toDestination) {
        if (toDestination == null) {
            throw new IllegalArgumentException("To destination cannot be null");
        }
        this.toDestination = toDestination;
    }
}
