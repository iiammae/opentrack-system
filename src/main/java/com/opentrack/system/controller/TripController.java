package com.opentrack.system.controller;

import com.opentrack.system.dto.ActiveTripResponse;
import com.opentrack.system.dto.LocationUpdate;
import com.opentrack.system.model.ActiveTrip;
import com.opentrack.system.model.TripLog;
import com.opentrack.system.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trip")
@CrossOrigin(origins = "*")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping("/start")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<ActiveTrip> startTrip(
            @RequestParam Long driverId,
            @RequestParam Long busId,
            @RequestParam Long routeId,
            @RequestParam Double lat,
            @RequestParam Double lng) {
        ActiveTrip trip = tripService.startTrip(driverId, busId, routeId, lat, lng);
        return ResponseEntity.ok(trip);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<ActiveTrip> updateTripLocation(@RequestBody LocationUpdate update) {
        ActiveTrip trip = tripService.updateTripLocation(update);
        return ResponseEntity.ok(trip);
    }

    @PutMapping("/stop/{tripId}")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<TripLog> stopTrip(@PathVariable Long tripId) {
        TripLog log = tripService.stopTrip(tripId);
        return ResponseEntity.ok(log);
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<ActiveTripResponse> getTripDetails(@PathVariable Long tripId) {
        return ResponseEntity.ok(new ActiveTripResponse());
    }
}
