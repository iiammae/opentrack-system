# OpenTrack Bus Fleet Management - QUICK START GUIDE

## Step 1: Fix Eclipse Lombok Issues (Most Important!)

Your Eclipse IDE is not recognizing Lombok annotations even though they're in your code. Follow **ONE** of these:

### Option A: Clean Build (Fastest - Try This First!)
1. In Eclipse, go to **Project** → **Clean...**
2. Select "opentrack-system" project
3. Check "Start a build immediately"
4. Click **Clean**
5. Wait 30 seconds for build to complete
6. Red X errors should disappear!

### Option B: Enable Annotation Processing
1. **Window** → **Preferences**
2. Go to **Java** → **Compiler** → **Annotation Processing**
3. Check ✓ **Enable project specific settings**
4. Check ✓ **Enable annotation processing**
5. Check ✓ **Enable processing in editor**
6. Click **Apply and Close**
7. Project → Clean (again)

### Option C: Restart Eclipse
Sometimes Eclipse just needs a restart:
1. **File** → **Exit** (close Eclipse completely)
2. Open Eclipse again
3. The errors should be gone!

**The errors like "method getToken() is undefined" will disappear after one of these steps.**

---

## Step 2: Create the Database

**IMPORTANT:** The database `opentrack_db` does NOT exist yet!

### Using MySQL Command Line:

Open Command Prompt/Terminal and run:

```bash
mysql -u root -p
```

Then paste this (no password needed since you have no password):

```sql
CREATE DATABASE IF NOT EXISTS opentrack_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE opentrack_db;
SHOW TABLES;
```

You should see: "Empty set" - that's correct!

### Using MySQL Workbench:

1. Open MySQL Workbench
2. Double-click your connection
3. File menu → New Query Tab
4. Copy-paste the SQL above
5. Click the lightning bolt icon ⚡ to execute
6. Done!

---

## Step 3: Run the Spring Boot Application

**In Eclipse:**
1. Right-click your project "opentrack-system"
2. **Run As** → **Spring Boot App**
3. Wait 30-45 seconds for startup
4. Check the console at the bottom

**Expected Output (success):**
```
... Tomcat initialized with port 8080
... Root WebApplicationContext: initialization completed
... Hibernate: create table ...
... Started OpenTrackSystemApplication in X.XXX seconds
```

If you see this, Hibernate is creating all 9 tables automatically! ✓

**If you see errors:**
- Check Step 1 & 2 were completed
- Look for "Unknown database" error → Do Step 2
- Look for "method undefined" → Do Step 1

---

## Step 4: Test the API

### Login Test:

Open browser or use Postman:

**URL:** `http://localhost:8080/api/auth/login`
**Method:** POST
**Content-Type:** application/json

**Body:**
```json
{
  "username": "driver1",
  "password": "password123"
}
```

**Expected Response (Success):**
```json
{
  "userId": 1,
  "username": "driver1",
  "role": "DRIVER",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "message": "Login successful"
}
```

If you get this, your backend is working! ✓

---

## Step 5: Access the Frontend

Open browser: `http://localhost:8080/index.html`

**Test Credentials:**

| Username | Password | Role |
|----------|----------|------|
| driver1 | password123 | Driver (Mobile) |
| dispatcher1 | password123 | Dispatcher (Live Map) |
| admin1 | password123 | Admin (CRUD) |

---

## Troubleshooting

### Problem: "Unknown database 'opentrack_db'"
**Solution:** Do Step 2 above - create the database in MySQL

### Problem: "The method getToken() is undefined"
**Solution:** Do Step 1 above - clean build or enable annotation processing in Eclipse

### Problem: "Port 8080 already in use"
**Solution:** Change in `application.properties`:
```properties
server.port=8081
