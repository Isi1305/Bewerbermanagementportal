package com.example.bewerbermanagementportal.controller;

import com.example.bewerbermanagementportal.entity.Bewerbung;
import com.example.bewerbermanagementportal.entity.BewerbungStatus;
import com.example.bewerbermanagementportal.entity.Nutzer;
import com.example.bewerbermanagementportal.entity.Stelle;
import com.example.bewerbermanagementportal.repository.NutzerRepository;
import com.example.bewerbermanagementportal.repository.StelleRepository;
import com.example.bewerbermanagementportal.service.BewerbungService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BewerbungController {
    private final BewerbungService bewerbungService;
    private final NutzerRepository nutzerRepository;
    private final StelleRepository stelleRepository;

    public BewerbungController(BewerbungService bewerbungService, NutzerRepository nutzerRepository, StelleRepository stelleRepository) {
        this.bewerbungService = bewerbungService;
        this.nutzerRepository = nutzerRepository;
        this.stelleRepository = stelleRepository;
    }

    // Einreichen der Bewerbung und Weiterleitung zurück zur Stellendetailseite
    @PostMapping("/bewerbung")
    public String bewerbungEinreichen(
            @RequestParam Long stelleId,
            @RequestParam String vorname,
            @RequestParam String nachname,
            @RequestParam String email,
            @RequestParam String telefon,
            @RequestParam boolean datenschutzAkzeptiert,
            HttpSession session
    ) {


        Long nutzerId = (Long) session.getAttribute("nutzerId");

        if(nutzerId == null){
            return "redirect:/login";
        }


        Nutzer bewerber = nutzerRepository.findById(nutzerId).get();
        Stelle stelle = stelleRepository.findById(stelleId).get();


        Bewerbung bewerbung = new Bewerbung();

        bewerbung.setBewerber(bewerber);
        bewerbung.setStelle(stelle);
        bewerbung.setVorname(vorname);
        bewerbung.setNachname(nachname);

        bewerbungService.bewerbungEinreichen(bewerbung);


        return "redirect:/stellen/" + stelleId;
    }

    // Statusänderung vom Recruiter
    @PostMapping("/bewerbungen/{id}/status")
    public String statusAendern(@PathVariable Long id, @RequestParam BewerbungStatus neuerStatus) {
        bewerbungService.statusAendern(id, neuerStatus);
        return "redirect:/stellen";
    }

    @GetMapping("/bewerbung")
    public String bewerbungFormular(@RequestParam Long stelleId, Model model) {
        model.addAttribute("stelleId", stelleId);
        return "bewerbung-formular";
    }
}