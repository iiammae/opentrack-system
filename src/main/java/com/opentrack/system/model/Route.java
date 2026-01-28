package com.opentrack.system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "routes", indexes = {
    @Index(name = "idx_route_code", columnList = "route_code", unique = true),
    @Index(name = "idx_is_active", columnList = "is_active")
})
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String routeCode;

    @Column(nullable = false, length = 100)
    private String routeName;

    @Column(nullable = false, length = 100)
    private String origin;

    @Column(nullable = false, length = 100)
    private String destination;

    @Column(nullable = false)
    private Double distanceKm;

    @Column(nullable = false)
    private Integer estimatedDurationMinutes;

    @Column(nullable = false)
    private Double fare;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<ActiveTrip> activeTrips;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<TripLog> tripLogs;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Integer getEstimatedDurationMinutes() {
        return estimatedDurationMinutes;
    }

    public void setEstimatedDurationMinutes(Integer estimatedDurationMinutes) {
        this.estimatedDurationMinutes = estimatedDurationMinutes;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ActiveTrip> getActiveTrips() {
        return activeTrips;
    }

    public void setActiveTrips(List<ActiveTrip> activeTrips) {
        this.activeTrips = activeTrips;
    }

    public List<TripLog> getTripLogs() {
        return tripLogs;
    }

    public void setTripLogs(List<TripLog> tripLogs) {
        this.tripLogs = tripLogs;
    }

    // Constructor
    public Route() {}

    public Route(String routeCode, String routeName, String origin, String destination, Double distanceKm, Integer estimatedDurationMinutes, Double fare) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.origin = origin;
        this.destination = destination;
        this.distanceKm = distanceKm;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
        this.fare = fare;
        this.isActive = true;
    }
}
