package com.opentrack.system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_records", indexes = {
    @Index(name = "idx_bus_id", columnList = "bus_id"),
    @Index(name = "idx_maintenance_date", columnList = "maintenance_date")
})
public class MaintenanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @Column(nullable = false, length = 100)
    private String maintenanceType;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private Double cost;

    @Column(nullable = false, length = 100)
    private String performedBy;

    @Column(nullable = false)
    private LocalDateTime maintenanceDate;

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

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public LocalDateTime getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDateTime maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Constructor
    public MaintenanceRecord() {}

    public MaintenanceRecord(Bus bus, String maintenanceType, String description, Double cost, String performedBy, LocalDateTime maintenanceDate) {
        this.bus = bus;
        this.maintenanceType = maintenanceType;
        this.description = description;
        this.cost = cost;
        this.performedBy = performedBy;
        this.maintenanceDate = maintenanceDate;
    }
}
