package com.example.bewerbermanagementportal.controller;

import com.example.bewerbermanagementportal.service.StelleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StelleController {
    private final StelleService stelleService;

    public StelleController(StelleService stelleService) {
        this.stelleService = stelleService;
    }

    // Liste holen und ans Model übergeben
    @GetMapping("/stellen")
    public String alleStellen(Model model) {
        model.addAttribute("stellen", stelleService.alleStellenAnzeigen());
        return "stellen-liste"; // Name der Thymeleaf-Datei Platzhalter
    }
}
