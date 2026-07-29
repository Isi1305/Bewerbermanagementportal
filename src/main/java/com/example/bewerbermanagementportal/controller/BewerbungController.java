package com.example.bewerbermanagementportal.controller;

import com.example.bewerbermanagementportal.entity.Bewerbung;
import com.example.bewerbermanagementportal.entity.BewerbungStatus;
import com.example.bewerbermanagementportal.service.BewerbungService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BewerbungController {
    private final BewerbungService bewerbungService;

    public BewerbungController(BewerbungService bewerbungService) {
        this.bewerbungService = bewerbungService;
    }

    // Einreichen der Bewerbung und Weiterleitung zurück zur Stellendetailseite
    @PostMapping("/bewerbung")
    public String bewerbungEinreichen(@ModelAttribute Bewerbung bewerbung) {
        bewerbungService.bewerbungEinreichen(bewerbung);
        return "redirect:/stelle-detail";
    }

    // Statusänderung vom Recruiter
    @PostMapping("/bewerbungen/{id}/status")
    public String statusAendern(@PathVariable Long id, @RequestParam BewerbungStatus neuerStatus) {
        bewerbungService.statusAendern(id, neuerStatus);
        return "redirect:/stellen";
    }
}