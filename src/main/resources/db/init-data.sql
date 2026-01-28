-- OpenTrack Bus Fleet Management System - Initial Data
-- This script initializes sample users and routes for testing
-- FIXED: All column names match Java entity definitions
-- FIXED: Correct BCrypt hash for password123

USE opentrackss_db;

-- Clear existing data (optional - remove if you want to keep data)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE gps_logs;
TRUNCATE TABLE incidents;
TRUNCATE TABLE notifications;
TRUNCATE TABLE maintenance_records;
TRUNCATE TABLE trip_logs;
TRUNCATE TABLE active_trips;
TRUNCATE TABLE routes;
TRUNCATE TABLE buses;
TRUNCATE TABLE users;
SET FOREIGN_KEY_CHECKS = 1;

-- ====================================
-- INSERT USERS
-- ====================================
-- All users: password=password123
-- BCrypt hash: $2a$10$q55qAyTm61r3EuzRjcbKje09ioHC.CjREcN3/ghn2Yiz58wv03nIe
-- Column mapping: full_name, phone_number (NOT email, phone)
INSERT INTO users (id, username, password, full_name, phone_number, role, is_active, created_at, updated_at) VALUES 
(1, 'driver1', '$2a$10$q55qAyTm61r3EuzRjcbKje09ioHC.CjREcN3/ghn2Yiz58wv03nIe', 'Driver One', '09123456789', 'DRIVER', true, NOW(), NOW()),
(2, 'driver2', '$2a$10$q55qAyTm61r3EuzRjcbKje09ioHC.CjREcN3/ghn2Yiz58wv03nIe', 'Driver Two', '09123456790', 'DRIVER', true, NOW(), NOW()),
(3, 'dispatcher1', '$2a$10$q55qAyTm61r3EuzRjcbKje09ioHC.CjREcN3/ghn2Yiz58wv03nIe', 'Dispatcher One', '09123456791', 'DISPATCHER', true, NOW(), NOW()),
(4, 'admin1', '$2a$10$q55qAyTm61r3EuzRjcbKje09ioHC.CjREcN3/ghn2Yiz58wv03nIe', 'Admin User', '09123456792', 'ADMIN', true, NOW(), NOW());

-- ====================================
-- INSERT BUSES
-- ====================================
INSERT INTO buses (id, plate_number, bus_number, model, capacity, year_manufactured, status, created_at, updated_at) VALUES 
(1, 'TVN-001', 'BUS-001', 'Yutong ZK6128HE', 50, 2022, 'ACTIVE', NOW(), NOW()),
(2, 'TVN-002', 'BUS-002', 'King Long XMQ6129Y', 55, 2021, 'ACTIVE', NOW(), NOW()),
(3, 'TVN-003', 'BUS-003', 'Higer KLQ6101', 45, 2020, 'ACTIVE', NOW(), NOW());

-- ====================================
-- INSERT ROUTES
-- ====================================
INSERT INTO routes (id, route_code, route_name, origin, destination, distance_km, estimated_duration_minutes, fare, is_active, created_at, updated_at) VALUES 
(1, 'RT-001', 'Cubao to Ayala', 'Cubao, Quezon City', 'Ayala Avenue, Makati', 8.5, 35, 12.00, true, NOW(), NOW()),
(2, 'RT-002', 'Pasay to Luneta', 'Pasay City', 'Rizal Park, Manila', 6.2, 25, 10.00, true, NOW(), NOW()),
(3, 'RT-003', 'Malabon to Quiapo', 'Malabon', 'Quiapo Church, Manila', 12.3, 45, 15.00, true, NOW(), NOW());

-- ====================================
-- INSERT ACTIVE TRIPS
-- ====================================
-- Column mapping: start_time, last_updated (NOT started_at, updated_at)
-- Status: MOVING (NOT IN_PROGRESS)
-- Added: speed column (required, default 0.0)
INSERT INTO active_trips (id, bus_id, route_id, driver_id, status, current_latitude, current_longitude, speed, start_time, last_updated) VALUES 
(1, 1, 1, 1, 'MOVING', 14.5994, 121.0425, 25.0, NOW(), NOW()),
(2, 2, 2, 2, 'MOVING', 14.5547, 120.9871, 30.0, NOW(), NOW()),
(3, 3, 3, 1, 'STOPPED', 14.6504, 120.9995, 0.0, NOW(), NOW());

-- ====================================
-- INSERT TRIP LOGS
-- ====================================
-- Column mapping: distance_traveled, average_speed, total_stops, fuel_consumed (NOT total_distance, passengers_*, notes)
INSERT INTO trip_logs (id, bus_id, route_id, driver_id, start_time, end_time, distance_traveled, average_speed, total_stops, fuel_consumed, revenue, created_at) VALUES 
(1, 1, 1, 1, DATE_SUB(NOW(), INTERVAL 2 DAY), DATE_SUB(NOW(), INTERVAL 2 DAY) + INTERVAL 1 HOUR, 8.5, 35.0, 5, 2.1, 500.00, NOW()),
(2, 2, 2, 2, DATE_SUB(NOW(), INTERVAL 1 DAY), DATE_SUB(NOW(), INTERVAL 1 DAY) + INTERVAL 45 MINUTE, 6.2, 28.0, 4, 1.5, 480.00, NOW()),
(3, 3, 3, 1, DATE_SUB(NOW(), INTERVAL 3 DAY), DATE_SUB(NOW(), INTERVAL 3 DAY) + INTERVAL 50 MINUTE, 12.3, 32.0, 7, 3.0, 550.00, NOW());

-- ====================================
-- INSERT GPS LOGS
-- ====================================
-- Column mapping: timestamp, speed, altitude, accuracy (NOT logged_at, created_at, speed_kmh, direction)
INSERT INTO gps_logs (id, active_trip_id, latitude, longitude, speed, altitude, accuracy, timestamp) VALUES 
(1, 1, 14.5994, 121.0425, 25.0, 50.0, 10.0, DATE_SUB(NOW(), INTERVAL 5 MINUTE)),
(2, 1, 14.6050, 121.0480, 28.0, 55.0, 8.0, NOW()),
(3, 2, 14.5547, 120.9871, 32.0, 45.0, 12.0, DATE_SUB(NOW(), INTERVAL 3 MINUTE)),
(4, 2, 14.5590, 120.9910, 30.0, 48.0, 9.0, NOW()),
(5, 3, 14.6504, 120.9995, 0.0, 60.0, 15.0, DATE_SUB(NOW(), INTERVAL 7 MINUTE)),
(6, 3, 14.6460, 120.9960, 0.0, 58.0, 11.0, NOW());

-- ====================================
-- INSERT INCIDENTS
-- ====================================
-- Column mapping: active_trip_id, bus_id, incident_type, status, reported_at, resolved_at (NOT trip_id, severity, location_*, created_at, updated_at)
-- Note: active_trip_id can be NULL, so using bus_id as primary reference
INSERT INTO incidents (id, active_trip_id, driver_id, bus_id, incident_type, description, latitude, longitude, status, reported_at, resolved_at) VALUES 
(1, 1, 1, 1, 'OTHER', 'Engine overheating warning', 14.5994, 121.0425, 'RESOLVED', DATE_SUB(NOW(), INTERVAL 2 DAY), DATE_SUB(NOW(), INTERVAL 2 DAY) + INTERVAL 30 MINUTE),
(2, 2, 2, 2, 'ACCIDENT', 'Minor collision at intersection', 14.5547, 120.9871, 'RESOLVED', DATE_SUB(NOW(), INTERVAL 1 DAY), DATE_SUB(NOW(), INTERVAL 1 DAY) + INTERVAL 2 HOUR),
(3, 3, 1, 3, 'BREAKDOWN', 'Tire puncture on route', 14.6504, 120.9995, 'REPORTED', NOW(), NULL);

-- ====================================
-- INSERT NOTIFICATIONS
-- ====================================
-- Column mapping: type (using enum: INFO, WARNING, EMERGENCY)
INSERT INTO notifications (id, user_id, type, title, message, is_read, created_at) VALUES 
(1, 1, 'EMERGENCY', 'High Priority Incident', 'Tire puncture reported on Route 3', false, NOW()),
(2, 3, 'INFO', 'Trip Started', 'Driver driver1 started trip on Route 1', true, DATE_SUB(NOW(), INTERVAL 1 DAY)),
(3, 4, 'WARNING', 'Maintenance Due', 'Bus BUS-001 scheduled maintenance due in 5 days', true, DATE_SUB(NOW(), INTERVAL 3 DAY));

-- ====================================
-- INSERT MAINTENANCE RECORDS
-- ====================================
-- Column mapping: maintenance_type, performed_by (NOT parts_replaced, next_maintenance_date, notes, updated_at)
INSERT INTO maintenance_records (id, bus_id, maintenance_type, description, cost, performed_by, maintenance_date, created_at) VALUES 
(1, 1, 'Oil Change', 'Regular oil change and filter replacement', 500.00, 'Mechanic Shop A', DATE_SUB(NOW(), INTERVAL 10 DAY), NOW()),
(2, 2, 'Brake Repair', 'Brake pad replacement', 800.00, 'Mechanic Shop B', DATE_SUB(NOW(), INTERVAL 5 DAY), NOW()),
(3, 3, 'Tire Service', 'Tire rotation and alignment', 600.00, 'Tire Center C', DATE_SUB(NOW(), INTERVAL 15 DAY), NOW());