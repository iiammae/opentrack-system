package com.opentrack.system.repository;

import com.opentrack.system.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByPlateNumber(String plateNumber);
    List<Bus> findByStatus(Bus.BusStatus status);
}
