-- Insert test data after tables are created by Hibernate
-- Run this AFTER Spring Boot creates all tables automatically

USE opentrackss_db;

-- Insert Users (Drivers, Dispatchers, Admins)
INSERT INTO users (username, password, full_name, phone_number, role, is_active, created_at, updated_at) VALUES
('driver1', '$2a$10$slYQmyNdGzin7aYM2N1rHeSXm1H2Q3t3A.1H2Q3t3AAAA1H2Q3t', 'Juan Dela Cruz', '09123456789', 'DRIVER', true, NOW(), NOW()),
('driver2', '$2a$10$slYQmyNdGzin7aYM2N1rHeSXm1H2Q3t3A.1H2Q3t3AAAA1H2Q3t', 'Maria Santos', '09234567890', 'DRIVER', true, NOW(), NOW()),
('dispatcher1', '$2a$10$slYQmyNdGzin7aYM2N1rHeSXm1H2Q3t3A.1H2Q3t3AAAA1H2Q3t', 'Pedro Garcia', '09345678901', 'DISPATCHER', true, NOW(), NOW()),
('admin1', '$2a$10$slYQmyNdGzin7aYM2N1rHeSXm1H2Q3t3A.1H2Q3t3AAAA1H2Q3t', 'Admin User', '09456789012', 'ADMIN', true, NOW(), NOW());

-- Insert Buses
INSERT INTO buses (plate_number, bus_number, model, capacity, year_manufactured, status, created_at, updated_at) VALUES
('TVN-001', 'BUS-001', 'Yutong', 50, 2022, 'ACTIVE', NOW(), NOW()),
('TVN-002', 'BUS-002', 'King Long', 45, 2021, 'ACTIVE', NOW(), NOW()),
('TVN-003', 'BUS-003', 'Higer', 52, 2023, 'ACTIVE', NOW(), NOW());

-- Insert Routes
INSERT INTO routes (route_code, route_name, origin, destination, distance_km, estimated_duration_minutes, fare, is_active, created_at, updated_at) VALUES
('R001', 'Cubao to Ayala', 'Cubao', 'Ayala', 12.5, 45, 25.00, true, NOW(), NOW()),
('R002', 'Pasay to Luneta', 'Pasay', 'Luneta', 8.3, 35, 18.00, true, NOW(), NOW()),
('R003', 'Malabon to Quiapo', 'Malabon', 'Quiapo', 15.2, 55, 32.00, true, NOW(), NOW());

-- Insert Active Trips (currently running)
INSERT INTO active_trips (driver_id, bus_id, route_id, start_time, current_latitude, current_longitude, speed, status, last_updated) VALUES
(1, 1, 1, NOW() - INTERVAL 1 HOUR, 14.5821, 121.0275, 40.5, 'MOVING', NOW()),
(2, 2, 2, NOW() - INTERVAL 2 HOUR, 14.5549, 120.9846, 35.0, 'MOVING', NOW()),
(1, 3, 3, NOW() - INTERVAL 30 MINUTE, 14.6091, 121.0245, 45.0, 'MOVING', NOW());

-- Insert GPS Logs
INSERT INTO gps_logs (active_trip_id, latitude, longitude, speed, altitude, accuracy, timestamp) VALUES
(1, 14.5821, 121.0275, 40.5, 10.0, 5.0, NOW()),
(1, 14.5822, 121.0276, 41.0, 10.1, 5.0, NOW() - INTERVAL 5 MINUTE),
(2, 14.5549, 120.9846, 35.0, 5.0, 5.0, NOW()),
(3, 14.6091, 121.0245, 45.0, 15.0, 5.0, NOW());

-- Insert Incidents
INSERT INTO incidents (active_trip_id, driver_id, bus_id, incident_type, description, latitude, longitude, status, reported_at) VALUES
(1, 1, 1, 'BREAKDOWN', 'Engine warning light', 14.5821, 121.0275, 'REPORTED', NOW()),
(2, 2, 2, 'ACCIDENT', 'Minor fender bender', 14.5549, 120.9846, 'IN_PROGRESS', NOW() - INTERVAL 30 MINUTE);

-- Insert Notifications
INSERT INTO notifications (user_id, title, message, type, is_read, created_at) VALUES
(3, 'New Incident', 'Bus TVN-001: BREAKDOWN reported', 'EMERGENCY', false, NOW()),
(3, 'Active Trips', '3 buses currently in service', 'INFO', true, NOW() - INTERVAL 1 HOUR);

-- Insert Maintenance Records
INSERT INTO maintenance_records (bus_id, maintenance_type, description, cost, performed_by, maintenance_date, created_at) VALUES
(1, 'Oil Change', 'Regular 10000km service', 1500.00, 'John Mechanic', NOW() - INTERVAL 30 DAY, NOW()),
(2, 'Tire Replacement', 'All 6 tires replaced', 3200.00, 'Mike Mechanic', NOW() - INTERVAL 15 DAY, NOW());
