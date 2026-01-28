package com.opentrack.system.service;

import com.opentrack.system.model.TripLog;
import com.opentrack.system.repository.TripLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private TripLogRepository tripLogRepository;

    public List<TripLog> getTripHistory(Long driverId, Long busId, LocalDateTime dateFrom, LocalDateTime dateTo) {
        if (driverId != null) {
            return tripLogRepository.findByDriverIdOrderByStartTimeDesc(driverId);
        } else if (busId != null) {
            return tripLogRepository.findByBusIdOrderByStartTimeDesc(busId);
        }
        return tripLogRepository.findByStartTimeBetween(dateFrom, dateTo);
    }

    public Map<String, Object> getDriverPerformance(Long driverId) {
        List<TripLog> trips = tripLogRepository.findByDriverIdOrderByStartTimeDesc(driverId);

        Map<String, Object> performance = new HashMap<>();
        performance.put("totalTrips", trips.size());
        performance.put("totalDistance", trips.stream().mapToDouble(t -> t.getDistanceTraveled()).sum());
        performance.put("averageSpeed", trips.stream().mapToDouble(t -> t.getAverageSpeed()).average().orElse(0.0));
        performance.put("totalRevenue", trips.stream().mapToDouble(t -> t.getRevenue()).sum());

        return performance;
    }

    public Map<String, Object> getBusUtilization(Long busId) {
        List<TripLog> trips = tripLogRepository.findByBusIdOrderByStartTimeDesc(busId);

        Map<String, Object> utilization = new HashMap<>();
        utilization.put("totalTrips", trips.size());
        utilization.put("totalDistance", trips.stream().mapToDouble(t -> t.getDistanceTraveled()).sum());
        utilization.put("averageTripsPerDay", trips.size() / 30.0);

        return utilization;
    }
}
