package com.example.bewerbermanagementportal.controller;

import com.example.bewerbermanagementportal.entity.Nutzer;
import com.example.bewerbermanagementportal.service.NutzerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NutzerController {
    private final NutzerService nutzerService;

    public NutzerController(NutzerService nutzerService) {
        this.nutzerService = nutzerService;
    }

    // leerers Formular, deswegen müssen keine Daten hier übergeben werden
    @GetMapping("/registrieren")
    public String registrierenFormular() {
        return "registrieren";
    }

    // Formulardaten atomatisch in ein Nutzer-Objekt
    @PostMapping("/registrieren")
    public String registrieren(@ModelAttribute Nutzer nutzer) {
        nutzerService.registrieren(nutzer);
        return "redirect:/login";
    }

    // leeres Formular bei login
    @GetMapping("/login")
    public String loginFormular() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Nutzer nutzer) {
        nutzerService.login(nutzer);
        return "redirect:/startseite";
    }
}