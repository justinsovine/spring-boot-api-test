package com.intellect37.api_test;

// Imports the GetMapping annotation to map HTTP GET requests onto specific handler methods
import org.springframework.web.bind.annotation.GetMapping;
// Imports the RestController annotation to indicate that this class is a RESTful web controller
import org.springframework.web.bind.annotation.RestController;

@RestController // Indicates that this class is a RESTful web controller
public class HelloController {
    
    @GetMapping("/api/hello")   // Maps HTTP GET requests to the hello method
    public String hello() {
        return "Hello, world!";
    }
}
