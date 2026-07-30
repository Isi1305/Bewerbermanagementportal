package com.example.bewerbermanagementportal.controller;

import com.example.bewerbermanagementportal.entity.Stelle;
import com.example.bewerbermanagementportal.repository.NutzerRepository;
import com.example.bewerbermanagementportal.service.StelleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StelleController {
    private final StelleService stelleService;
    private final NutzerRepository nutzerRepository;

    public StelleController(StelleService stelleService, NutzerRepository nutzerRepository) {
        this.stelleService = stelleService;
        this.nutzerRepository = nutzerRepository;
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

    // gespeicherte ID in NutzerController wird hier ausgelesen
    @PostMapping("/stellen/neu")
    public String neueStelle(@ModelAttribute Stelle stelle, HttpSession session) {
        Long nutzerId = (Long) session.getAttribute("nutzerId");
        stelle.setRecruiter(nutzerRepository.findById(nutzerId).get());
        stelleService.stelleAnlegen(stelle);
        return "redirect:/stellen";
    }
}
