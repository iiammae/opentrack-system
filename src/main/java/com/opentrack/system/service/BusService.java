package com.opentrack.system.service;

import com.opentrack.system.model.Bus;
import com.opentrack.system.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus getBusById(Long id) {
        return busRepository.findById(id).orElse(null);
    }

    public List<Bus> getBusesByStatus(Bus.BusStatus status) {
        return busRepository.findByStatus(status);
    }

    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }
}
