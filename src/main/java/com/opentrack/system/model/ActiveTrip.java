package com.opentrack.system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "active_trips", indexes = {
    @Index(name = "idx_driver_id", columnList = "driver_id"),
    @Index(name = "idx_bus_id", columnList = "bus_id"),
    @Index(name = "idx_status", columnList = "status")
})
public class ActiveTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private Double currentLatitude;

    @Column(nullable = false)
    private Double currentLongitude;

    @Column(nullable = false)
    private Double speed = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TripStatus status = TripStatus.MOVING;

    @Column(nullable = false)
    private LocalDateTime lastUpdated;

    @PrePersist
    protected void onCreate() {
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
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

    public Double getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(Double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public Double getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(Double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // Constructor
    public ActiveTrip() {}

    public ActiveTrip(User driver, Bus bus, Route route, LocalDateTime startTime, Double currentLatitude, Double currentLongitude) {
        this.driver = driver;
        this.bus = bus;
        this.route = route;
        this.startTime = startTime;
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.speed = 0.0;
        this.status = TripStatus.MOVING;
        this.lastUpdated = LocalDateTime.now();
    }

    public enum TripStatus {
        MOVING, STOPPED, BREAK, EMERGENCY
    }
}
