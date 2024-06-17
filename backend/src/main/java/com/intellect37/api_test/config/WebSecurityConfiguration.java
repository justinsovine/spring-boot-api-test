package com.intellect37.api_test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// Indicates that this class contains configuration settings
@Configuration
// Enables Spring Security’s web security support and provides the Spring MVC integration
@EnableWebSecurity
public class WebSecurityConfiguration {

    // Declares that this method returns a Spring-managed bean
    // This bean defines the security filter chain for the application
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Disable CSRF (Cross-Site Request Forgery) protection
        // CSRF is typically disabled in stateless REST APIs where each request 
        // is independent and does not rely on cookies
        http.csrf(csrf -> csrf.disable())
            // Require authentication for any request to the application
            // Ensures that all endpoints are secure and accessible only to authenticated users
            .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
            // Enable HTTP Basic Authentication, a simple authentication scheme built into the HTTP protocol
            // Clients send HTTP requests with the Authorization header that contains the word Basic followed by a 
            // space and a base64-encoded string username:password
            .httpBasic(withDefaults())
            // Configure session management to be stateless
            // The server does not create or use an HTTP session; each request must contain all 
            // the necessary information for authentication
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Build and return the SecurityFilterChain instance
        return http.build();
    }
}
