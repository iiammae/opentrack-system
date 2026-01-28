package com.opentrack.system.controller;

import com.opentrack.system.model.Bus;
import com.opentrack.system.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/buses")
@CrossOrigin(origins = "*")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        return ResponseEntity.ok(busService.getAllBuses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable Long id) {
        Bus bus = busService.getBusById(id);
        return bus != null ? ResponseEntity.ok(bus) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Bus> createBus(@RequestBody Bus bus) {
        return ResponseEntity.ok(busService.saveBus(bus));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Bus> updateBus(@PathVariable Long id, @RequestBody Bus bus) {
        bus.setId(id);
        return ResponseEntity.ok(busService.saveBus(bus));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
        return ResponseEntity.noContent().build();
    }
}
