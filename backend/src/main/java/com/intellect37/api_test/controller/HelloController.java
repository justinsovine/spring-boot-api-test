package com.intellect37.api_test.controller;

import com.intellect37.api_test.entity.HelloEntity;
import com.intellect37.api_test.repository.HelloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private HelloRepository helloRepository;

    @GetMapping("/api/hello")
    public List<HelloEntity> getHelloMessages() {
        return helloRepository.findAll();
    }

    @GetMapping("/api/peter-ariosa")
    public String getLoremIpsum() {
        return "Yo what up peter ariosa boiiiiiii";
    }
}
