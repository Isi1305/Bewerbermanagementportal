package com.example.bewerbermanagementportal.controller;

import com.example.bewerbermanagementportal.entity.Stelle;
import com.example.bewerbermanagementportal.service.StelleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "stellen-liste"; // Name der Thymeleaf-Datei
    }

    // einzelne Stelle anzeigen
    @GetMapping("/stellen/{id}")
    public String stelleDetail(@PathVariable Long id, Model model) {
        model.addAttribute("stelle", stelleService.stelleFindenPerId(id));
        return "stelle-detail";
    }

    // Formular anzeigen und verarbeiten
    @GetMapping("/stellen/neu")
    public String neueStelle() {
        return "stelle-neu";
    }

    @PostMapping("/stellen/neu")
    public String neueStelle(@ModelAttribute Stelle stelle) {
        stelleService.stelleAnlegen(stelle);
        return "redirect:/stellen";
    }
}
