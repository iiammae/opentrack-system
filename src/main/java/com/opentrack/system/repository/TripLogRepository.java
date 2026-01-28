package com.opentrack.system.repository;

import com.opentrack.system.model.TripLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripLogRepository extends JpaRepository<TripLog, Long> {
    List<TripLog> findByDriverIdOrderByStartTimeDesc(Long driverId);
    List<TripLog> findByBusIdOrderByStartTimeDesc(Long busId);
    List<TripLog> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
