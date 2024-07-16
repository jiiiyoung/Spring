package com.example.myselectshopbeta.myselectshopbeta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShopController {
    // 홈화면
    @GetMapping("/shop")
    public ModelAndView shop() {
        return new ModelAndView("index");
    }
}
