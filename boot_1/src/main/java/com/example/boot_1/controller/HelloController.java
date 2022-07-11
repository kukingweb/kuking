package com.example.boot_1.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class HelloController {

   
    @RequestMapping("/list")
    public String list(Model model) {
        return "hello,world";
    }
}