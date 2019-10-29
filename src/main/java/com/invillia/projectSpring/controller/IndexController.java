package com.invillia.projectSpring.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}
