package com.opentrack.system.service;

import com.opentrack.system.model.Route;
import com.opentrack.system.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> getAllActiveRoutes() {
        return routeRepository.findByIsActive(true);
    }

    public Route getRouteById(Long id) {
        return routeRepository.findById(id).orElse(null);
    }

    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }
}
