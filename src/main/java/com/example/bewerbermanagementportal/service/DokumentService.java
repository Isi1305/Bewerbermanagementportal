package com.example.bewerbermanagementportal.service;

import com.example.bewerbermanagementportal.entity.Bewerbung;
import com.example.bewerbermanagementportal.entity.Dokument;
import com.example.bewerbermanagementportal.repository.DokumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DokumentService {
    private final DokumentRepository dokumentRepository;

    public DokumentService(DokumentRepository dokumentRepository) {
        this.dokumentRepository = dokumentRepository;
    }

    // Dokument entgegennehmen, speichern und zurückgeben
    public Dokument dokumentSpeichern(Dokument dokument) {
        return dokumentRepository.save(dokument);
    }

    // alle Dokumente zu einer Bewerbung anzeigen
    public List<Dokument> dokumenteProBewerbung(Bewerbung bewerbung) {
        return dokumentRepository.findByBewerbung(bewerbung);
    }
}
