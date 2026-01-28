package com.opentrack.system.controller;

import com.opentrack.system.model.Route;
import com.opentrack.system.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "*")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllActiveRoutes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
        Route route = routeService.getRouteById(id);
        return route != null ? ResponseEntity.ok(route) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        return ResponseEntity.ok(routeService.saveRoute(route));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody Route route) {
        route.setId(id);
        return ResponseEntity.ok(routeService.saveRoute(route));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}
