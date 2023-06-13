package com.codeup.codeupspringblog.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

    record Person(String name){}


    @GetMapping("/json")
    public Person test() {
        return new Person("Charlie Romeo");
    }
}

