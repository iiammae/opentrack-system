package com.opentrack.system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "buses", indexes = {
    @Index(name = "idx_plate_number", columnList = "plate_number", unique = true),
    @Index(name = "idx_status", columnList = "status")
})
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String plateNumber;

    @Column(nullable = false, length = 20)
    private String busNumber;

    @Column(nullable = false, length = 50)
    private String model;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer yearManufactured;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusStatus status = BusStatus.ACTIVE;

    @Column(name = "last_maintenance_date")
    private LocalDateTime lastMaintenanceDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<ActiveTrip> activeTrips;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<TripLog> tripLogs;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Incident> incidents;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<MaintenanceRecord> maintenanceRecords;

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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getYearManufactured() {
        return yearManufactured;
    }

    public void setYearManufactured(Integer yearManufactured) {
        this.yearManufactured = yearManufactured;
    }

    public BusStatus getStatus() {
        return status;
    }

    public void setStatus(BusStatus status) {
        this.status = status;
    }

    public LocalDateTime getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(LocalDateTime lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
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

    public List<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }

    public List<MaintenanceRecord> getMaintenanceRecords() {
        return maintenanceRecords;
    }

    public void setMaintenanceRecords(List<MaintenanceRecord> maintenanceRecords) {
        this.maintenanceRecords = maintenanceRecords;
    }

    // Constructor
    public Bus() {}

    public Bus(String plateNumber, String busNumber, String model, Integer capacity, Integer yearManufactured) {
        this.plateNumber = plateNumber;
        this.busNumber = busNumber;
        this.model = model;
        this.capacity = capacity;
        this.yearManufactured = yearManufactured;
        this.status = BusStatus.ACTIVE;
    }

    public enum BusStatus {
        ACTIVE, MAINTENANCE, RETIRED
    }
}
