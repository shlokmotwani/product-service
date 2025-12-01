package com.example.product_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/say")
public class SampleController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello, folks!";
    }

    @GetMapping("/bye")
    public String bye(){
        return "Bye, everyone!";
    }
}
