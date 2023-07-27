package com.gachon.healthdiary.API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FirstAPIController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
