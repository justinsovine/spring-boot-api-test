package com.intellect37.api_test.controller;

import com.intellect37.api_test.model.HelloModel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/api/hello", produces = "application/json")
    public HelloModel getHello() {
        return new HelloModel("Hello, world!");
    }
}
