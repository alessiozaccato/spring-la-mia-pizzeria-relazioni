package com.java.lessons.crud1.spring_la_mia_pizzeria_crud.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String getHome() {
        return "home";
    }
}


