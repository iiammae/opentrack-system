package com.opentrack.system.repository;

import com.opentrack.system.model.ActiveTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface ActiveTripRepository extends JpaRepository<ActiveTrip, Long> {
    Optional<ActiveTrip> findByDriverIdAndStatusNot(Long driverId, ActiveTrip.TripStatus status);
    List<ActiveTrip> findByStatus(ActiveTrip.TripStatus status);
    Optional<ActiveTrip> findByDriverId(Long driverId);
}
