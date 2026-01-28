package com.opentrack.system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trip_logs", indexes = {
    @Index(name = "idx_driver_id", columnList = "driver_id"),
    @Index(name = "idx_bus_id", columnList = "bus_id"),
    @Index(name = "idx_created_at", columnList = "created_at")
})
public class TripLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private Double distanceTraveled;

    @Column(nullable = false)
    private Double averageSpeed;

    @Column(nullable = false)
    private Integer totalStops = 0;

    @Column(nullable = false)
    private Double fuelConsumed;

    @Column(nullable = false)
    private Double revenue;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(Double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public Double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(Double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public Integer getTotalStops() {
        return totalStops;
    }

    public void setTotalStops(Integer totalStops) {
        this.totalStops = totalStops;
    }

    public Double getFuelConsumed() {
        return fuelConsumed;
    }

    public void setFuelConsumed(Double fuelConsumed) {
        this.fuelConsumed = fuelConsumed;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Constructor
    public TripLog() {}

    public TripLog(User driver, Bus bus, Route route, LocalDateTime startTime, LocalDateTime endTime, Double distanceTraveled, Double averageSpeed, Double fuelConsumed, Double revenue) {
        this.driver = driver;
        this.bus = bus;
        this.route = route;
        this.startTime = startTime;
        this.endTime = endTime;
        this.distanceTraveled = distanceTraveled;
        this.averageSpeed = averageSpeed;
        this.totalStops = 0;
        this.fuelConsumed = fuelConsumed;
        this.revenue = revenue;
    }
}
