package com.example.bewerbermanagementportal.service;

import com.example.bewerbermanagementportal.entity.Bewerbung;
import com.example.bewerbermanagementportal.entity.Dokument;
import com.example.bewerbermanagementportal.repository.DokumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
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

    // uploads/ angelegt, Datei wird dort gespeichert, Pfad kommt in die Datenbank
    public Dokument dokumentSpeichern(Dokument dokument, MultipartFile datei) throws IOException {
        String ordner = "upload/";

        Path pfad = Paths.get(ordner);

        if(!Files.exists(pfad)) {
            Files.createDirectories(pfad);
        }

        String dateiname = datei.getOriginalFilename();

        String endung = "";
        if(dateiname.contains(".")) {
            endung = dateiname.substring(dateiname.lastIndexOf(".") + 1).toUpperCase();
        }

        if(endung.equals("PDF")) {
            dokument.setDokumentTyp(Dokument.DokumentTyp.PDF);
        }
        else if(endung.equals("DOCX")) {
            dokument.setDokumentTyp(Dokument.DokumentTyp.DOCX);
        }
        else if(endung.equals("JPG")) {
            dokument.setDokumentTyp(Dokument.DokumentTyp.JPG);
        }
        else if(endung.equals("TXT")) {
            dokument.setDokumentTyp(Dokument.DokumentTyp.TXT);
        }

        Path dateiPfad = pfad.resolve(dateiname);

        Files.write(dateiPfad, datei.getBytes());

        dokument.setName(dateiname);
        dokument.setDateipfad(dateiPfad.toString());
        dokument.setUploadDatum(LocalDate.now());

        return dokumentRepository.save(dokument);
    }

    // alle Dokumente zu einer Bewerbung anzeigen
    public List<Dokument> dokumenteProBewerbung(Bewerbung bewerbung) {
        return dokumentRepository.findByBewerbung(bewerbung);
    }
}
