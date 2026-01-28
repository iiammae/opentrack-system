# Final Setup Checklist

## Before Running Application

- [ ] MySQL installed and running
- [ ] Database `opentrack_db` created (run Step 2 from QUICK_START.md)
- [ ] Lombok Eclipse configuration done (run Step 1 from QUICK_START.md)
- [ ] No red X errors in Eclipse (clean build if needed)
- [ ] pom.xml has all dependencies (check Maven Dependencies in project)

## Running Application

- [ ] **Project** → **Clean** (Eclipse)
- [ ] Right-click project → **Run As** → **Spring Boot App**
- [ ] Wait for console: "Started OpenTrackSystemApplication in X.XXX seconds"
- [ ] Check MySQL tables were created: `SHOW TABLES;` in MySQL

## Testing Backend

- [ ] Test login: POST `/api/auth/login` with driver1/password123
- [ ] Receive JWT token in response
- [ ] Token contains userId, username, role

## Testing Frontend

- [ ] Open `http://localhost:8080/index.html`
- [ ] Login with driver1/password123
- [ ] Redirected to driver.html (mobile interface)
- [ ] GPS tracking shows current location
- [ ] Can start/stop trip

## Database Content

- [ ] Users table has 4 test users
- [ ] Buses table has 3 buses
- [ ] Routes table has 3 routes
- [ ] ActiveTrips table has running trips (if init-data.sql ran)
- [ ] All queries work in MySQL

## Common Issues & Fixes

| Issue | Fix |
|-------|-----|
| "method undefined" errors | Do QUICK_START.md Step 1 |
| "Unknown database" | Do QUICK_START.md Step 2 |
| Red X won't go away | Restart Eclipse |
| Port 8080 in use | Change server.port in properties |
| API returns 401 Unauthorized | Check JWT_SECRET matches |

---

**All set? Congratulations! Your OpenTrack system is ready to use!**
