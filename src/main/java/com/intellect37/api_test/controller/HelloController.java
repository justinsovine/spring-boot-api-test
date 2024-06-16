package com.intellect37.api_test.controller;

import com.intellect37.api_test.model.HelloModel;
import com.intellect37.api_test.model.ComplexHelloModel;
import com.intellect37.api_test.model.ComplexHelloModel.InnerModel;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // @GetMapping("/api/hello")
    // public HelloModel getHello() {
    //     return new HelloModel("Hello, world!");
    // }

    @GetMapping(value = "/api/hello", produces = "application/json")
    public HelloModel getHello() {
        //InnerModel innerModel = new InnerModel("Inner Name", 42);
        //return new ComplexHelloModel("Hello, complex world!", Arrays.asList("Detail1", "Detail2"), innerModel);
        return new HelloModel("Hello, world!");
    }
}
