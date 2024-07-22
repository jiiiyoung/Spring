package com.example.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api")
public class ShopController {
    // 홈화면
    @GetMapping("/shop")
    public ModelAndView shop() {
        return new ModelAndView("index");
    }
}
