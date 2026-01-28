package com.opentrack.system.dto;

public class LocationUpdate {
    private Long tripId;
    private Double latitude;
    private Double longitude;
    private Double speed;
    private String status;

    public LocationUpdate() {}

    public LocationUpdate(Long tripId, Double latitude, Double longitude, Double speed, String status) {
        this.tripId = tripId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.status = status;
    }

    // Getters and Setters
    public Long getTripId() { return tripId; }
    public void setTripId(Long tripId) { this.tripId = tripId; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Double getSpeed() { return speed; }
    public void setSpeed(Double speed) { this.speed = speed; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
