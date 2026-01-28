package com.opentrack.system.dto;

import java.time.LocalDateTime;

public class ActiveTripResponse {
    private Long tripId;
    private Long driverId;
    private String driverName;
    private Long busId;
    private String busNumber;
    private String plateNumber;
    private Long routeId;
    private String routeName;
    private Double latitude;
    private Double longitude;
    private Double speed;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime lastUpdated;

    public ActiveTripResponse() {}

    public ActiveTripResponse(Long tripId, Long driverId, String driverName, Long busId, String busNumber, 
                             String plateNumber, Long routeId, String routeName, Double latitude, Double longitude, 
                             Double speed, String status, LocalDateTime startTime, LocalDateTime lastUpdated) {
        this.tripId = tripId;
        this.driverId = driverId;
        this.driverName = driverName;
        this.busId = busId;
        this.busNumber = busNumber;
        this.plateNumber = plateNumber;
        this.routeId = routeId;
        this.routeName = routeName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.status = status;
        this.startTime = startTime;
        this.lastUpdated = lastUpdated;
    }

    // Getters and Setters
    public Long getTripId() { return tripId; }
    public void setTripId(Long tripId) { this.tripId = tripId; }

    public Long getDriverId() { return driverId; }
    public void setDriverId(Long driverId) { this.driverId = driverId; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public Long getBusId() { return busId; }
    public void setBusId(Long busId) { this.busId = busId; }

    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public Long getRouteId() { return routeId; }
    public void setRouteId(Long routeId) { this.routeId = routeId; }

    public String getRouteName() { return routeName; }
    public void setRouteName(String routeName) { this.routeName = routeName; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Double getSpeed() { return speed; }
    public void setSpeed(Double speed) { this.speed = speed; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
