
# Java Spring Boot REST API

## Overview
A Java 17 Spring Boot 3.3.0-based REST API for retrieving and displaying data via a web interface. Secured with Spring Security, it connects to a MariaDB database for data storage and retrieval.

## Technical Components

### Spring Boot Application
- **Main Application**: `ApiTestApplication.java`
- **Controller**: Handles API requests (`HelloController.java`)
- **Model**: Defines data structure (`HelloEntity.java`)
- **Repository**: Interface for CRUD operations (`HelloRepository.java`)

### Security
- **Configuration**: Secures API endpoints with basic authentication (`WebSecurityConfiguration.java`)
- **Authentication**: Credentials set in `application.properties`

### Database
- **MariaDB**: JDBC connection via `spring.datasource` properties
- **JPA**: Automatic table creation and data persistence

### Frontend
- **HTML/CSS**: TailwindCSS for styling
- **JavaScript**: Fetches and displays JSON data (`app.js`)
- **UI Components**: Form for data retrieval, display area for formatted JSON

### Deployment
- **Systemd Service**: Manages the application (`api-test.service`)
- **Logs**: Configured for troubleshooting and monitoring

## Workflow
1. **Data Retrieval**: Frontend sends GET request to `/hello` endpoint. Backend fetches data from MariaDB using JPA.
2. **Security**: Basic authentication enforced for API access.
3. **Data Display**: JSON response formatted and displayed in web interface.

## Deployment Steps
1. **Build**: 
   ```bash
   mvn clean package -DskipTests
   ```
2. **Deploy**: 
   - Transfer jar to server.
   - Configure as systemd service.
   - Ensure MariaDB is running and accessible.
3. **Run**: 
   ```bash
   sudo systemctl start api-test.service
   sudo journalctl -u api-test.service -f
   ```

## CI/CD Setup
To automate the build and deployment, you can use a CI/CD tool like GitHub Actions.

### Example Workflow for GitHub Actions
Create a `.github/workflows/deploy.yml` file in your repository:

```yaml
name: Deploy

on:
  push:
    branches:
      - main

jobs:
  build-and-deploy:
    runs-on: ubuntu-latest

    env:
      BACKEND_DIR: backend
      FRONTEND_DIR: frontend
      SERVER_USER: youruser
      SERVER_IP: your.server.ip
      BACKEND_DEPLOY_PATH: /srv/api-test
      FRONTEND_DEPLOY_PATH: /var/www/html

    steps:
    - name: Checkout code
      uses: actions/checkout@v2

    - name: Set up JDK 17
      uses: actions/setup-java@v2
      with:
        java-version: '17'

    - name: Build backend
      run: |
        cd ${{ env.BACKEND_DIR }}
        mvn clean package -DskipTests

    - name: Set up Node.js
      uses: actions/setup-node@v2
      with:
        node-version: '14'

    - name: Build frontend
      run: |
        cd ${{ env.FRONTEND_DIR }}
        npm install
        npm run build

    - name: Deploy backend
      run: |
        scp ${{ env.BACKEND_DIR }}/target/*.jar ${{ env.SERVER_USER }}@${{ env.SERVER_IP }}:${{ env.BACKEND_DEPLOY_PATH }}/api-test.jar
        ssh ${{ env.SERVER_USER }}@${{ env.SERVER_IP }} "sudo systemctl restart api-test.service"

    - name: Deploy frontend
      run: |
        rsync -avz ${{ env.FRONTEND_DIR }}/dist/ ${{ env.SERVER_USER }}@${{ env.SERVER_IP }}:${{ env.FRONTEND_DEPLOY_PATH }}
```

### Server Setup
Ensure your server is ready to receive deployments:

1. **Backend Setup**:
   - Ensure `api-test.service` is set up to use the jar file from `/srv/api-test/api-test.jar`.
   - Example `api-test.service` file:
     ```ini
     [Unit]
     Description=API Test Spring Boot Application
     After=network.target

     [Service]
     User=youruser
     Group=yourgroup
     ExecStart=/usr/bin/java -jar /srv/api-test/api-test.jar
     Restart=always

     [Install]
     WantedBy=multi-user.target
     ```

2. **Frontend Setup**:
   - Ensure your web server (e.g., Nginx, Apache) is configured to serve files from `/var/www/html`.

## Summary
By integrating this CI/CD process, you can streamline the deployment of both your frontend and backend, ensuring they are always in sync and reducing the risk of manual errors.
