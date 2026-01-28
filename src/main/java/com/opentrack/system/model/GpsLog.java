package com.opentrack.system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "gps_logs", indexes = {
    @Index(name = "idx_active_trip_id", columnList = "active_trip_id"),
    @Index(name = "idx_timestamp", columnList = "timestamp")
})
public class GpsLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "active_trip_id", nullable = false)
    private ActiveTrip activeTrip;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double speed;

    @Column(nullable = false)
    private Double altitude;

    @Column(nullable = false)
    private Double accuracy;

    @Column(nullable = false)
    private LocalDateTime timestamp;

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

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Constructor
    public GpsLog() {}

    public GpsLog(ActiveTrip activeTrip, Double latitude, Double longitude, Double speed, Double altitude, Double accuracy, LocalDateTime timestamp) {
        this.activeTrip = activeTrip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.altitude = altitude;
        this.accuracy = accuracy;
        this.timestamp = timestamp;
    }
}
