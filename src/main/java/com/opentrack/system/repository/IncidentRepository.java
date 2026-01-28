package com.opentrack.system.repository;

import com.opentrack.system.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findByStatus(Incident.IncidentStatus status);
    List<Incident> findByActiveTripId(Long activeTripId);
}
