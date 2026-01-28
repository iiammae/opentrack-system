package com.opentrack.system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidents", indexes = {
    @Index(name = "idx_active_trip_id", columnList = "active_trip_id"),
    @Index(name = "idx_driver_id", columnList = "driver_id"),
    @Index(name = "idx_status", columnList = "status")
})
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "active_trip_id")
    private ActiveTrip activeTrip;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncidentType incidentType;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncidentStatus status = IncidentStatus.REPORTED;

    @Column(nullable = false)
    private LocalDateTime reportedAt;

    @Column(nullable = true)
    private LocalDateTime resolvedAt;

    @PrePersist
    protected void onCreate() {
        reportedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActiveTrip getActiveTrip() {
        return activeTrip;
    }

    public void setActiveTrip(ActiveTrip activeTrip) {
        this.activeTrip = activeTrip;
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

    public IncidentType getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(IncidentType incidentType) {
        this.incidentType = incidentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public IncidentStatus getStatus() {
        return status;
    }

    public void setStatus(IncidentStatus status) {
        this.status = status;
    }

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(LocalDateTime reportedAt) {
        this.reportedAt = reportedAt;
    }

    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    // Constructor
    public Incident() {}

    public Incident(User driver, Bus bus, IncidentType incidentType, String description, Double latitude, Double longitude) {
        this.driver = driver;
        this.bus = bus;
        this.incidentType = incidentType;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = IncidentStatus.REPORTED;
        this.reportedAt = LocalDateTime.now();
    }

    public enum IncidentType {
        BREAKDOWN, ACCIDENT, TRAFFIC, EMERGENCY, OTHER
    }

    public enum IncidentStatus {
        REPORTED, IN_PROGRESS, RESOLVED
    }
}
