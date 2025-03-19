
# Spring Boot REST API with Frontend

## **Table of Contents**
- [Project Overview](#project-overview)
- [Backend](#backend)
- [Frontend](#frontend)
- [Deployment Workflow (GitHub Actions)](#deployment-workflow-github-actions)
- [Systemd Service: api-test.service](#systemd-service-api-testservice)
  - [Service Definition](#service-definition-etc-systemd-system-api-testservice)
  - [Installation & Management](#installation--management)
  - [Managing the Service](#managing-the-service)

---

## Project Overview
This project consists of a **Spring Boot 3.3.0** REST API backend and a static HTML/JS frontend UI. The API serves stored messages from a MariaDB database and is secured with HTTP Basic Authentication.

- Backend: A Spring Boot REST API that provides endpoints to retrieve stored messages.
- Frontend: A static HTML/JS app that consumes the API and displays responses.
- Deployment: Managed via GitHub Actions, with the backend running as a systemd service.

Visit the frontend at [https://api-test.intellect37.com/](https://api-test.intellect37.com/).

---

## Backend
The backend is a Spring Boot application that exposes API endpoints and interacts with a MariaDB database.

### Endpoints
| Method | Endpoint           | Description                           | Authentication |
|--------|-------------------|---------------------------------------|----------------|
| GET    | `/api/hello`      | Returns all messages from the database. | Required |

### **Persistence Layer**
- **`HelloEntity.java`**: Defines the database table schema.
- **`HelloRepository.java`**: Manages data access using **Spring Data JPA**.
- **Database Configuration** (`application.properties`):
  ```properties
  spring.datasource.url=jdbc:mariadb://localhost:3306/spring_boot_api_test
  spring.datasource.username=[username]
  spring.datasource.password=[password]
  ```

### Security
- Authentication: HTTP Basic Authentication.
- Configured in `WebSecurityConfiguration.java`:
  - Requires authentication for all endpoints.
  - Uses **stateless session management**.
- Default Credentials (for local testing):
  ```properties
  spring.security.user.name=admin
  spring.security.user.password=password
  ```

## Frontend
The frontend is a static HTML/JavaScript app that interacts with the backend API.

### Components
- `index.html`:
  - Displays a **button** to fetch data from the API.
  - Shows API responses inside a `<pre>` tag.
- `app.js`:
  - Fetches data from `https://api-test.intellect37.com/api/hello`.
  - Uses **Basic Authentication** (`admin:password`).
  - Displays the response as formatted JSON.
  ```javascript
  fetch('https://api-test.intellect37.com/api/hello', {
      method: 'GET',
      headers: {
          'Authorization': 'Basic ' + btoa('admin:password')
      }
  })
  .then(response => response.json())
  .then(data => console.log(data));
  ```
- `app.css`:
  - Styles the displayed JSON output

## **Deployment Workflow (GitHub Actions)**
The **GitHub Actions workflow** automates deployment when changes are pushed to the `main` branch.

### Workflow Steps
1. Build Backend:
   - Uses **Maven** to package the Java application.
2. Deploy Backend:
   - Transfers the JAR file to `/srv/api-test/api-test.jar` on the server.
   - Restarts the `api-test.service`.
3. Deploy Frontend:
   - Syncs frontend files to `/var/www/intellect37.com/api-test/`.

### Deployment Commands
- Backend JAR Deployment:
  ```bash
  scp api-test.jar user@server:/srv/api-test/api-test.jar
  ssh user@server "sudo systemctl restart api-test.service"
  ```
- Frontend Deployment
  ```bash
  rsync -avz frontend/ user@server:/var/www/intellect37.com/api-test/
  ```

## **Systemd Service: api-test.service**
The backend runs as a systemd service, ensuring it starts on boot and automatically restarts if it crashes.

### **Service Definition (`/etc/systemd/system/api-test.service`)**
```ini
[Unit]
Description=API Test Spring Boot Application
After=network.target

[Service]
User=[username]
ExecStart=/usr/bin/java -jar /srv/api-test/api-test-0.0.1-SNAPSHOT.jar
SuccessExitStatus=143
Restart=on-failure
RestartSec=10

[Install]
WantedBy=multi-user.target
```
### Managing the Service
| Command                           | Description                                    |
|-----------------------------------|------------------------------------------------|
| `sudo systemctl start api-test`   | Start the service manually.                    |
| `sudo systemctl stop api-test`    | Stop the service.                              |
| `sudo systemctl restart api-test` | Restart the service.                           |
| `sudo systemctl enable api-test`  | Enable the service to start on boot.           |
| `sudo systemctl disable api-test` | Disable automatic start on boot.               |

### How It Works
- **Runs the backend API as a background process** using `java -jar`.
- **Restarts automatically if it fails**, waiting **10 seconds** before retrying.
- **Loads on system boot**, ensuring the API is always available.

### Final Notes
This setup ensures full automation of deployment and service reliability on the production server. The backend API will always restart in case of failure and will automatically start on server reboot.


