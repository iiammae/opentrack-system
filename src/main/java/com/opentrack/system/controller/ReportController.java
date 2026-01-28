package com.opentrack.system.controller;

import com.opentrack.system.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/trip-history")
    @PreAuthorize("hasRole('DISPATCHER') or hasRole('ADMIN')")
    public ResponseEntity<?> getTripHistory(
            @RequestParam(required = false) Long driverId,
            @RequestParam(required = false) Long busId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo) {
        return ResponseEntity.ok(reportService.getTripHistory(driverId, busId, dateFrom, dateTo));
    }

    @GetMapping("/driver-performance/{driverId}")
    @PreAuthorize("hasRole('DISPATCHER') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getDriverPerformance(@PathVariable Long driverId) {
        return ResponseEntity.ok(reportService.getDriverPerformance(driverId));
    }

    @GetMapping("/bus-utilization/{busId}")
    @PreAuthorize("hasRole('DISPATCHER') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getBusUtilization(@PathVariable Long busId) {
        return ResponseEntity.ok(reportService.getBusUtilization(busId));
    }
}
