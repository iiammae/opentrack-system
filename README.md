# OpenTrack System

A web-based tracking and management system built with Java and deployed on Render.

## 🚀 Live Demo

**Application URL**: [[Your Render App URL]](https://navigation-microservice.onrender.com/login)

The application is deployed and running on Render cloud platform.

## ✨ Features

- Real-time tracking and monitoring
- User-friendly dashboard interface
- Secure authentication and authorization
- Responsive design for mobile and desktop
- RESTful API endpoints
- Database integration for persistent storage

## 🛠️ Tech Stack

### Backend
- **Java** - Core programming language
- **Spring Boot** - Application framework
- **Spring MVC** - Web framework
- **Spring Data JPA** - Data persistence layer
- **Hibernate** - ORM framework
- **Maven** - Build and dependency management

### Database
- **PostgreSQL** - Production database (Render)
- **H2** - Development/testing database

### Deployment
- **Render** - Cloud hosting platform
- **Docker** - Containerization (if applicable)

### Frontend
- **HTML5/CSS3** - Markup and styling
- **JavaScript** - Client-side functionality
- **Bootstrap** - CSS framework (if used)
- **Thymeleaf/JSP** - Template engine

## 📋 Prerequisites

- Java JDK 11 or higher
- Maven 3.6+
- PostgreSQL 12+ (for local development)
- Git

## 🔧 Local Development Setup

### 1. Clone the repository
```bash
git clone https://github.com/yourusername/opentrack-system.git
cd opentrack-system
```

### 2. Configure Database
Update `src/main/resources/application.properties`:

**For Local Development:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/opentrack_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**For Production (Render):**
```properties
spring.datasource.url=${DATABASE_URL}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
```

### 3. Build the project
```bash
mvn clean install
```

### 4. Run locally
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 🌐 Deployment on Render

### Initial Setup

1. **Create a Render account** at [render.com](https://render.com)

2. **Create a new Web Service**
   - Connect your GitHub repository
   - Select the branch to deploy (usually `main`)

3. **Configure Build Settings**
   - **Build Command**: `mvn clean install`
   - **Start Command**: `java -jar target/opentrack-system-0.0.1-SNAPSHOT.jar`
   - **Environment**: Java

4. **Add Environment Variables**
   ```
   DATABASE_URL=<your-postgres-connection-string>
   SPRING_PROFILES_ACTIVE=production
   PORT=10000
   ```

5. **Create PostgreSQL Database**
   - Add a new PostgreSQL database in Render
   - Link it to your web service
   - The DATABASE_URL will be automatically configured

### Continuous Deployment

Render automatically deploys your application when you push to the connected branch:

```bash
git add .
git commit -m "Your commit message"
git push origin main
```

## 📁 Project Structure

```
opentrack-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/opentrack/
│   │   │       ├── controller/      # REST controllers
│   │   │       ├── model/           # Entity classes
│   │   │       ├── repository/      # Data repositories
│   │   │       ├── service/         # Business logic
│   │   │       └── config/          # Configuration
│   │   └── resources/
│   │       ├── static/              # CSS, JS, images
│   │       ├── templates/           # HTML templates
│   │       └── application.properties
│   └── test/                        # Test classes
├── target/                          # Build output
├── .classpath
├── .project
├── pom.xml                          # Maven configuration
├── package.json                     # Node packages (if any)
├── render.yaml                      # Render config (if used)
├── ECLIPSE_LOMBOK_FIX.md
├── FINAL_CHECKLIST.md
├── FIX_LOMBOK_ECLIPSE.md
├── FIXES_APPLIED.md
├── QUICK_START.md
├── SETUP_GUIDE.md
├── TROUBLESHOOTING.md
└── README.md
```

## 📚 API Documentation

### Base URL
- **Local**: `http://localhost:8080/api`
- **Production**: `https://your-app.onrender.com/api`

### Endpoints

#### Authentication
```
POST   /api/auth/register    - Register new user
POST   /api/auth/login       - User login
POST   /api/auth/logout      - User logout
```

#### Tracking
```
GET    /api/tracks           - Get all tracks
GET    /api/tracks/{id}      - Get track by ID
POST   /api/tracks           - Create new track
PUT    /api/tracks/{id}      - Update track
DELETE /api/tracks/{id}      - Delete track
```

#### User Management
```
GET    /api/users            - Get all users
GET    /api/users/{id}       - Get user by ID
PUT    /api/users/{id}       - Update user
DELETE /api/users/{id}       - Delete user
```

## 🔒 Environment Variables

Required environment variables for deployment:

| Variable | Description | Example |
|----------|-------------|---------|
| `DATABASE_URL` | PostgreSQL connection string | `postgresql://user:pass@host:5432/db` |
| `SPRING_PROFILES_ACTIVE` | Active Spring profile | `production` |
| `PORT` | Application port | `10000` |
| `JWT_SECRET` | JWT signing secret | `your-secret-key` |

## 🧪 Testing

Run tests with Maven:
```bash
mvn test
```

Run specific test class:
```bash
mvn test -Dtest=YourTestClass
```

## 📝 Documentation Files

The project includes several helpful documentation files:

- **SETUP_GUIDE.md** - Detailed setup instructions
- **QUICK_START.md** - Quick start guide
- **TROUBLESHOOTING.md** - Common issues and solutions
- **FIXES_APPLIED.md** - Applied fixes and patches
- **ECLIPSE_LOMBOK_FIX.md** - Lombok configuration for Eclipse
- **FINAL_CHECKLIST.md** - Pre-deployment checklist

## 🐛 Troubleshooting

### Common Issues

**Issue: Application won't start on Render**
- Check build logs in Render dashboard
- Verify environment variables are set correctly
- Ensure PORT is set to 10000

**Issue: Database connection failed**
- Verify DATABASE_URL is correct
- Check if PostgreSQL service is running
- Ensure database credentials are valid

**Issue: Build fails**
- Check Maven dependencies in `pom.xml`
- Verify Java version compatibility
- Clear Maven cache: `mvn clean`

For more detailed troubleshooting, see `TROUBLESHOOTING.md`

## 🚀 Performance Optimization

- Enable caching for frequently accessed data
- Use connection pooling for database
- Implement pagination for large datasets
- Compress static assets
- Use CDN for static files

## 🔐 Security Best Practices

- Never commit sensitive data (passwords, API keys)
- Use environment variables for configuration
- Implement rate limiting
- Enable HTTPS in production
- Regular security updates
- Input validation and sanitization

## 📈 Monitoring

Monitor your application on Render:
- View logs in real-time
- Check metrics and performance
- Set up alerts for errors
- Monitor resource usage

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Authors

- **Your Name** - [GitHub Profile](https://github.com/yourusername)

## 🙏 Acknowledgments

- Spring Boot documentation
- Render documentation
- Maven Central Repository
- Open source community

## 📞 Support

For support and questions:
- Open an issue on GitHub
- Check existing documentation files
- Review Render logs for deployment issues

---

**Note**: Replace placeholder URLs and credentials with your actual project information.

## 🔗 Useful Links

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Render Documentation](https://render.com/docs)
- [Maven Documentation](https://maven.apache.org/guides/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
