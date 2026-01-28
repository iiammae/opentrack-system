package com.opentrack.system.repository;

import com.opentrack.system.model.MaintenanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecord, Long> {
    List<MaintenanceRecord> findByBusIdOrderByMaintenanceDateDesc(Long busId);
}
