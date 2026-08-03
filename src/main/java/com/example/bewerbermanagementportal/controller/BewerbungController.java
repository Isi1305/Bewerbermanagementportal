package com.example.bewerbermanagementportal.controller;

import com.example.bewerbermanagementportal.entity.*;
import com.example.bewerbermanagementportal.repository.NutzerRepository;
import com.example.bewerbermanagementportal.repository.StelleRepository;
import com.example.bewerbermanagementportal.service.BewerbungService;
import com.example.bewerbermanagementportal.service.DokumentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class BewerbungController {
    private final BewerbungService bewerbungService;
    private final NutzerRepository nutzerRepository;
    private final StelleRepository stelleRepository;
    private final DokumentService dokumentService;

    public BewerbungController(BewerbungService bewerbungService,
                               NutzerRepository nutzerRepository,
                               StelleRepository stelleRepository,
                               DokumentService dokumentService) {
        this.bewerbungService = bewerbungService;
        this.nutzerRepository = nutzerRepository;
        this.stelleRepository = stelleRepository;
        this.dokumentService = dokumentService;
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
            @RequestParam("lebenslauf") MultipartFile lebenslauf,
            @RequestParam("anschreiben") MultipartFile anschreiben,
            HttpSession session
    ) throws IOException {

        if(!datenschutzAkzeptiert) {
            throw new IllegalArgumentException("Datenschutz muss akzeptiert werden");
        }

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
        bewerbung.setEmail(email);
        bewerbung.setTelefon(telefon);
        bewerbung.setDatenschutzAkzeptiert(datenschutzAkzeptiert);

        Bewerbung gespeicherteBewerbung = bewerbungService.bewerbungEinreichen(bewerbung);

        Dokument lebenslaufDokument = new Dokument();
        lebenslaufDokument.setBewerbung(gespeicherteBewerbung);
        dokumentService.dokumentSpeichern(lebenslaufDokument, lebenslauf);

        Dokument anschreibenDokument = new Dokument();
        anschreibenDokument.setBewerbung(gespeicherteBewerbung);
        dokumentService.dokumentSpeichern(anschreibenDokument, anschreiben);

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