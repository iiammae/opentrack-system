# Troubleshooting Guide

## Compilation Errors

### Error: "parserBuilder() is undefined"
**Solution**: Update JwtUtil.java with new JJWT 0.12.3 syntax
- File: `src/main/java/com/opentrack/system/security/JwtUtil.java`
- Change: Use `Jwts.parser()` instead of `Jwts.parserBuilder()`
- Status: ALREADY FIXED in provided code

### Error: "setId() is undefined" or "getToken() is undefined"
**Solution**: Ensure Lombok is working
```
1. Project → Right-click → Properties
2. Search for "Lombok"
3. If not found, install from Eclipse Marketplace
4. Project → Clean
```

---

## Runtime Errors

### "Unknown column 'password' in 'field list'"
**Cause**: User table missing password column
**Solution**: 
```sql
-- Option 1: Run init-data.sql (recommended)
mysql -u root opentrack_db < src/main/resources/db/init-data.sql

-- Option 2: Manual table creation
mysql -u root opentrack_db
ALTER TABLE users ADD COLUMN password VARCHAR(255) NOT NULL DEFAULT '';
```

### "Access denied for user 'root'@'localhost'"
**Cause**: Wrong MySQL password in application.properties
**Solution**:
```
1. Check MySQL root password
2. Update application.properties:
   spring.datasource.username=root
   spring.datasource.password=YOUR_PASSWORD_HERE
```

### "Communications link failure"
**Cause**: MySQL not running or wrong database name
**Solution**:
```bash
# Check MySQL is running
mysql -u root -p

# Create database if missing
CREATE DATABASE opentrack_db;
```

---

## API Errors

### 401 Unauthorized on all endpoints except /api/auth/login
**Cause**: Missing JWT token in Authorization header
**Solution**: Send token in request:
```
Headers: Authorization: Bearer <your_jwt_token>
```

### 403 Forbidden on admin endpoints
**Cause**: User doesn't have ADMIN role
**Solution**: Login with admin1 user:
```
Username: admin1
Password: password123
Role: ADMIN
```

---

## Frontend Issues

### "Cannot read property 'getElementById' on null"
**Cause**: Trying to access DOM elements before page load
**Solution**: Add event listeners after DOM ready:
```javascript
document.addEventListener('DOMContentLoaded', function() {
    // Your code here
});
```

### Geolocation not working
**Cause**: HTTPS required on production, permissions denied
**Solution**:
```javascript
// Add permission check
if (navigator.geolocation) {
    navigator.geolocation.watchPosition(success, error);
} else {
    console.log('Geolocation not supported');
}
```

### Map not showing in dispatcher dashboard
**Cause**: Leaflet.js CDN not loaded or wrong coordinates
**Solution**: Check browser console for errors:
```
1. Open DevTools (F12)
2. Check Console tab for error messages
3. Verify map div exists: <div id="map"></div>
```

---

## Database Issues

### "Table doesn't exist"
**Cause**: Hibernate hasn't created tables yet
**Solution**:
```properties
# application.properties
spring.jpa.hibernate.ddl-auto=update  # Auto-create on startup
