# OpenTrack Bus Fleet Management System - Setup Guide

## Prerequisites
- Java 17 or higher
- MySQL 8.0+
- Maven 3.8+
- Git
- Eclipse IDE (or any Spring Boot IDE)

## Development Setup

### 1. Database Setup
```bash
# Create database
mysql -u root < create the opentrack_db database
CREATE DATABASE opentrack_db;
USE opentrack_db;
```

### 2. Clone and Configure
```bash
# Clone project
git clone <your-repo-url>
cd opentrack-system

# Update application.properties if needed
# Default: username=root, password=(empty)
```

### 3. Run the Application
```bash
# Using Maven
mvn spring-boot:run

# Or import into Eclipse:
# 1. File > Import > Existing Maven Projects
# 2. Select project folder
# 3. Right-click > Run As > Spring Boot App
```

### 4. Access the Application
- **Frontend**: http://localhost:8080/index.html
- **API**: http://localhost:8080/api
- **API Documentation**: See controller files for endpoints

## Test Credentials (After Running init-data.sql)

**Driver Login:**
- Username: `driver1`
- Password: `password123`

**Dispatcher Login:**
- Username: `dispatcher1`
- Password: `password123`

**Admin Login:**
- Username: `admin1`
- Password: `password123`

## Database Initialization

### Option 1: Manual SQL Execution
```bash
# Run in MySQL
mysql -u root opentrack_db < src/main/resources/db/init-data.sql
```

### Option 2: Automatic (Hibernate)
- Set `spring.jpa.hibernate.ddl-auto=update` (default)
- Tables auto-create on first run
- Run init-data.sql manually after tables created

## Project Structure
```
src/main/
├── java/com/opentrack/system/
│   ├── controller/     # REST API endpoints
│   ├── service/        # Business logic
│   ├── repository/     # JPA data access
│   ├── model/          # JPA entities
│   ├── dto/            # Data transfer objects
│   └── security/       # JWT & authentication
└── resources/
    ├── templates/      # HTML pages
    └── db/             # SQL initialization
```

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login

### Trip Management
- `GET /api/trips` - List all trips
- `POST /api/trips/start` - Start new trip
- `PUT /api/trips/{id}/end` - End trip
- `POST /api/trips/{id}/location` - Update GPS location

### Bus Management
- `GET /api/buses` - List buses
- `POST /api/buses` - Create bus (Admin only)
- `PUT /api/buses/{id}` - Update bus (Admin only)

### Route Management
- `GET /api/routes` - List routes
- `POST /api/routes` - Create route (Admin only)

### Dispatcher
- `GET /api/dispatcher/active-trips` - Live trip data

### Reports
- `GET /api/reports/trip-summary` - Trip statistics
- `GET /api/reports/bus-performance` - Bus performance metrics

## JWT Token Usage

Include token in request headers:
```
Authorization: Bearer <token>
```

## Troubleshooting

### "Unknown column 'password' in 'field list'"
- Your database lacks password column in users table
- Run init-data.sql to create all tables properly
- Or manually add: `ALTER TABLE users ADD COLUMN password VARCHAR(255);`

### Port Already in Use
```bash
# Change port in application.properties
server.port=8081
```

### CORS Errors
- Update `cors.allowed-origins` in application.properties
- Current allowed: localhost:8080, localhost:3000, localhost:5173

## Production Deployment (Render)

1. Create render.yaml in project root
2. Set environment variables:
   - `DATABASE_URL`: MySQL connection string
   - `JWT_SECRET`: Strong secret key (min 32 chars)
3. Push to GitHub
4. Connect repository to Render
5. Render auto-deploys on push

## Common Fixes

### All tables missing
```sql
-- Manually create tables if needed
SET FOREIGN_KEY_CHECKS=0;
-- Then run init-data.sql
SET FOREIGN_KEY_CHECKS=1;
```

### JWT errors
- Ensure JWT secret is at least 256 bits
- Update in application.properties: `jwt.secret=your_long_secret_here`

### GPS tracking not working
- Check browser geolocation permissions
- Ensure HTTPS on production (browsers require HTTPS for geolocation)

## Support
For issues, check:
1. Spring Boot logs in console
2. Browser developer console (F12)
3. MySQL error logs
```

```java file="" isHidden
