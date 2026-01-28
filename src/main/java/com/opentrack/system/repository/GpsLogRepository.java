package com.opentrack.system.repository;

import com.opentrack.system.model.GpsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GpsLogRepository extends JpaRepository<GpsLog, Long> {
    List<GpsLog> findByActiveTripIdOrderByTimestampDesc(Long activeTripId);
}
