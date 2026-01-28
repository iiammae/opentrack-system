package com.opentrack.system.controller;

import com.opentrack.system.dto.ActiveTripResponse;
import com.opentrack.system.model.Bus;
import com.opentrack.system.service.TripService;
import com.opentrack.system.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dispatcher")
@CrossOrigin(origins = "*")
public class DispatcherController {

    @Autowired
    private TripService tripService;

    @Autowired
    private BusService busService;

    @GetMapping("/active-trips")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<List<ActiveTripResponse>> getActiveTrips() {
        List<ActiveTripResponse> trips = tripService.getAllActiveTrips();
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/buses/status")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<List<Bus>> getBusesStatus() {
        List<Bus> buses = busService.getAllBuses();
        return ResponseEntity.ok(buses);
    }

    @GetMapping("/fleet-summary")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<Map<String, Object>> getFleetSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("activeTrips", tripService.getAllActiveTrips().size());
        summary.put("idleBuses", busService.getBusesByStatus(Bus.BusStatus.ACTIVE).size());
        summary.put("maintenanceBuses", busService.getBusesByStatus(Bus.BusStatus.MAINTENANCE).size());
        return ResponseEntity.ok(summary);
    }
}
