package com.opentrack.system.service;

import com.opentrack.system.model.*;
import com.opentrack.system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private ActiveTripRepository activeTripRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public Incident reportIncident(Long activeTripId, Incident.IncidentType type, String description, Double lat, Double lng) {
        ActiveTrip trip = activeTripRepository.findById(activeTripId).orElseThrow();

        Incident incident = new Incident();
        incident.setActiveTrip(trip);
        incident.setDriver(trip.getDriver());
        incident.setBus(trip.getBus());
        incident.setIncidentType(type);
        incident.setDescription(description);
        incident.setLatitude(lat);
        incident.setLongitude(lng);
        incident.setStatus(Incident.IncidentStatus.REPORTED);
        incident.setReportedAt(LocalDateTime.now());

        Incident saved = incidentRepository.save(incident);

        // Create notification for dispatchers
        List<User> dispatchers = userRepository.findByRole(User.UserRole.DISPATCHER);
        dispatchers.forEach(dispatcher -> {
            Notification notif = new Notification();
            notif.setUser(dispatcher);
            notif.setTitle("Incident Reported");
            notif.setMessage("Bus " + trip.getBus().getBusNumber() + ": " + type);
            notif.setType(Notification.NotificationType.EMERGENCY);
            notif.setIsRead(false);
            notif.setCreatedAt(LocalDateTime.now());
            notificationRepository.save(notif);
        });

        return saved;
    }

    public Incident updateIncidentStatus(Long incidentId, Incident.IncidentStatus status) {
        Incident incident = incidentRepository.findById(incidentId).orElseThrow();
        incident.setStatus(status);
        if (status == Incident.IncidentStatus.RESOLVED) {
            incident.setResolvedAt(LocalDateTime.now());
        }
        return incidentRepository.save(incident);
    }

    public List<Incident> getActiveIncidents() {
        return incidentRepository.findByStatus(Incident.IncidentStatus.REPORTED);
    }

    public List<Incident> getIncidentsByTrip(Long tripId) {
        return incidentRepository.findByActiveTripId(tripId);
    }
}
