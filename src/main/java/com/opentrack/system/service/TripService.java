package com.opentrack.system.service;

import com.opentrack.system.dto.ActiveTripResponse;
import com.opentrack.system.dto.LocationUpdate;
import com.opentrack.system.model.*;
import com.opentrack.system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TripService {

    @Autowired
    private ActiveTripRepository activeTripRepository;

    @Autowired
    private TripLogRepository tripLogRepository;

    @Autowired
    private GpsLogRepository gpsLogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;

    // Start a new trip
    public ActiveTrip startTrip(Long driverId, Long busId, Long routeId, Double lat, Double lng) {
        User driver = userRepository.findById(driverId).orElseThrow();
        Bus bus = busRepository.findById(busId).orElseThrow();
        Route route = routeRepository.findById(routeId).orElseThrow();

        ActiveTrip trip = new ActiveTrip();
        trip.setDriver(driver);
        trip.setBus(bus);
        trip.setRoute(route);
        trip.setStartTime(LocalDateTime.now());
        trip.setCurrentLatitude(lat);
        trip.setCurrentLongitude(lng);
        trip.setSpeed(0.0);
        trip.setStatus(ActiveTrip.TripStatus.MOVING);
        trip.setLastUpdated(LocalDateTime.now());

        return activeTripRepository.save(trip);
    }

    // Update trip location (GPS tracking)
    public ActiveTrip updateTripLocation(LocationUpdate update) {
        ActiveTrip trip = activeTripRepository.findById(update.getTripId()).orElseThrow();

        // Calculate distance and update
        double distance = calculateDistance(
            trip.getCurrentLatitude(), trip.getCurrentLongitude(),
            update.getLatitude(), update.getLongitude()
        );

        trip.setCurrentLatitude(update.getLatitude());
        trip.setCurrentLongitude(update.getLongitude());
        trip.setSpeed(update.getSpeed());
        trip.setStatus(ActiveTrip.TripStatus.valueOf(update.getStatus()));
        trip.setLastUpdated(LocalDateTime.now());

        ActiveTrip savedTrip = activeTripRepository.save(trip);

        // Log GPS coordinates
        GpsLog gpsLog = new GpsLog();
        gpsLog.setActiveTrip(trip);
        gpsLog.setLatitude(update.getLatitude());
        gpsLog.setLongitude(update.getLongitude());
        gpsLog.setSpeed(update.getSpeed());
        gpsLog.setAccuracy(5.0); // Default accuracy
        gpsLog.setTimestamp(LocalDateTime.now());

        gpsLogRepository.save(gpsLog);

        return savedTrip;
    }

    // Stop trip and create trip log
    public TripLog stopTrip(Long tripId) {
        ActiveTrip activeTrip = activeTripRepository.findById(tripId).orElseThrow();

        // Calculate total distance
        List<GpsLog> gpsLogs = gpsLogRepository.findByActiveTripIdOrderByTimestampDesc(tripId);
        double totalDistance = 0.0;
        double avgSpeed = 0.0;

        if (!gpsLogs.isEmpty()) {
            for (int i = 0; i < gpsLogs.size() - 1; i++) {
                GpsLog current = gpsLogs.get(i);
                GpsLog next = gpsLogs.get(i + 1);
                totalDistance += calculateDistance(
                    current.getLatitude(), current.getLongitude(),
                    next.getLatitude(), next.getLongitude()
                );
            }
            avgSpeed = gpsLogs.stream().mapToDouble(GpsLog::getSpeed).average().orElse(0.0);
        }

        LocalDateTime endTime = LocalDateTime.now();
        TripLog tripLog = new TripLog();
        tripLog.setDriver(activeTrip.getDriver());
        tripLog.setBus(activeTrip.getBus());
        tripLog.setRoute(activeTrip.getRoute());
        tripLog.setStartTime(activeTrip.getStartTime());
        tripLog.setEndTime(endTime);
        tripLog.setDistanceTraveled(totalDistance);
        tripLog.setAverageSpeed(avgSpeed);
        tripLog.setTotalStops(0);
        tripLog.setFuelConsumed(0.0);
        tripLog.setRevenue(activeTrip.getRoute().getFare());
        tripLog.setCreatedAt(LocalDateTime.now());

        tripLogRepository.save(tripLog);
        activeTripRepository.delete(activeTrip);

        return tripLog;
    }

    // Get all active trips
    public List<ActiveTripResponse> getAllActiveTrips() {
        return activeTripRepository.findByStatus(ActiveTrip.TripStatus.MOVING)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Get driver's active trip
    public ActiveTripResponse getDriverActiveTrip(Long driverId) {
        return activeTripRepository.findByDriverId(driverId)
                .map(this::convertToResponse)
                .orElse(null);
    }

    private ActiveTripResponse convertToResponse(ActiveTrip trip) {
        return new ActiveTripResponse(
            trip.getId(),
            trip.getDriver().getId(),
            trip.getDriver().getFullName(),
            trip.getBus().getId(),
            trip.getBus().getBusNumber(),
            trip.getBus().getPlateNumber(),
            trip.getRoute().getId(),
            trip.getRoute().getRouteName(),
            trip.getCurrentLatitude(),
            trip.getCurrentLongitude(),
            trip.getSpeed(),
            trip.getStatus().toString(),
            trip.getStartTime(),
            trip.getLastUpdated()
        );
    }

    // Haversine formula to calculate distance
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth radius in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
