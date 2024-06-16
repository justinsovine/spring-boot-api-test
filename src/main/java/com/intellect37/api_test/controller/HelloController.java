package com.intellect37.api_test.controller;

import com.intellect37.api_test.model.Hello;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indicates that this class is a RESTful web controller
public class HelloController {

    @GetMapping("/api/hello")   // Maps HTTP GET requests to the hello method
    public Hello hello() {
        return new Hello("Hello, world!");
    }
}
