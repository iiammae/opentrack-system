package com.opentrack.system.controller;

import com.opentrack.system.model.Incident;
import com.opentrack.system.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/incident")
@CrossOrigin(origins = "*")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @PostMapping("/report")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<Incident> reportIncident(
            @RequestParam Long activeTripId,
            @RequestParam String type,
            @RequestParam String description,
            @RequestParam Double lat,
            @RequestParam Double lng) {
        Incident incident = incidentService.reportIncident(
            activeTripId,
            Incident.IncidentType.valueOf(type),
            description,
            lat,
            lng
        );
        return ResponseEntity.ok(incident);
    }

    @GetMapping("/active")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<List<Incident>> getActiveIncidents() {
        return ResponseEntity.ok(incidentService.getActiveIncidents());
    }

    @PutMapping("/{id}/resolve")
    @PreAuthorize("hasRole('DISPATCHER')")
    public ResponseEntity<Incident> resolveIncident(@PathVariable Long id) {
        Incident incident = incidentService.updateIncidentStatus(id, Incident.IncidentStatus.RESOLVED);
        return ResponseEntity.ok(incident);
    }
}
