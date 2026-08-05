package com.example.bewerbermanagementportal.controller;

import com.example.bewerbermanagementportal.entity.*;
import com.example.bewerbermanagementportal.repository.BewerbungRepository;
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
import java.util.List;

@Controller
public class BewerbungController {
    private final BewerbungService bewerbungService;
    private final NutzerRepository nutzerRepository;
    private final StelleRepository stelleRepository;
    private final DokumentService dokumentService;
    private final BewerbungRepository bewerbungRepository;

    public BewerbungController(BewerbungService bewerbungService,
                               NutzerRepository nutzerRepository,
                               StelleRepository stelleRepository,
                               DokumentService dokumentService,
                               BewerbungRepository bewerbungRepository) {
        this.bewerbungService = bewerbungService;
        this.nutzerRepository = nutzerRepository;
        this.stelleRepository = stelleRepository;
        this.dokumentService = dokumentService;
        this.bewerbungRepository = bewerbungRepository;
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
            HttpSession session,
            Model model
    ) throws IOException {

        // Validierungen für das Bewerbungsformular
        if(lebenslauf.isEmpty() || anschreiben.isEmpty()) {
            model.addAttribute("fehler", "Lebenslauf und Anschreiben müssen hochgeladen werden");
            return "fehler";
        }

        if(lebenslauf.getSize() > 5_000_000 || anschreiben.getSize() > 5_000_000) {
            model.addAttribute("fehler", "Dateien dürfen maximal 5 MB groß sein");
            return "fehler";
        }

        if(vorname == null || vorname.trim().isEmpty()) {
            model.addAttribute("fehler", "Vorname darf nicht leer sein");
            return "fehler";
        }

        if(nachname == null || nachname.trim().isEmpty()) {
            model.addAttribute("fehler", "Nachname darf nicht leer sein");
            return "fehler";
        }

        if(email == null || !email.contains("@")) {
            model.addAttribute("fehler", "Ungültige E-Mail-Adresse");
            return "fehler";
        }

        if(telefon == null || telefon.length() < 5) {
            model.addAttribute("fehler", "Telefonnummer ungültig");
            return "fehler";
        }

        if(!datenschutzAkzeptiert) {
            model.addAttribute("fehler", "Datenschutz muss akzeptiert werden");
            return "fehler";
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
        return "redirect:/recruiter/bewerbungen";
    }

    @GetMapping("/bewerbung")
    public String bewerbungFormular(@RequestParam Long stelleId, Model model) {
        model.addAttribute("stelleId", stelleId);
        return "bewerbung-formular";
    }

    // Stelle wird gesucht, geladen und Thymeleaf bekommt die Liste
    @GetMapping("/stellen/{id}/bewerbungen")
    public String bewerbungenAnzeigen(@PathVariable Long id, Model model) {
        Stelle stelle = stelleRepository.findById(id).get();

        model.addAttribute("stelle", stelle);
        model.addAttribute("bewerbungen", bewerbungService.bewerbungenProStelle(stelle));

        return "bewerbungen-liste";
    }

    // nicht eingeloggt zum Login, Bewerber zurück zur Startseite und Recruiter kann Bewerbungen sehen
    @GetMapping("/recruiter/bewerbungen")
    public String alleBewerbungen(HttpSession session, Model model) {

        Long nutzerId = (Long) session.getAttribute("nutzerId");

        if(nutzerId == null) {
            return "redirect:/login";
        }

        Nutzer nutzer = nutzerRepository.findById(nutzerId).get();

        if(nutzer.getRolle() != Rolle.RECRUITER) {
            return "redirect:/startseite";
        }

        List<Bewerbung> bewerbungen = bewerbungRepository.findAll();

        for(Bewerbung bewerbung : bewerbungen) {
            bewerbung.setDokumente(
                    dokumentService.dokumenteProBewerbung(bewerbung)
            );
        }

        model.addAttribute("bewerbungen", bewerbungen);

        return "bewerbungen";
    }

    @PostMapping("/bewerbungen/{id}/notiz")
    public String notizSpeichern(
            @PathVariable Long id,
            @RequestParam String notiz
    ) {

        Bewerbung bewerbung = bewerbungRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bewerbung nicht gefunden"));

        bewerbung.setNotiz(notiz);

        bewerbungRepository.save(bewerbung);

        return "redirect:/recruiter/bewerbungen";
    }
}