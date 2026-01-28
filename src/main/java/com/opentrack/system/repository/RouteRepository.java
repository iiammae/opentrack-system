package com.opentrack.system.repository;

import com.opentrack.system.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findByRouteCode(String routeCode);
    List<Route> findByIsActive(Boolean isActive);
}
