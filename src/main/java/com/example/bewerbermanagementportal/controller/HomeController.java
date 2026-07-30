package com.example.bewerbermanagementportal.controller;

import com.example.bewerbermanagementportal.service.StelleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final StelleService stelleService;

    public HomeController(StelleService stelleService) {
        this.stelleService = stelleService;
    }

    @GetMapping("/startseite")
    public String startseite(Model model) {
        model.addAttribute("stellen", stelleService.alleStellenAnzeigen());
        return "startseite";
    }
}
