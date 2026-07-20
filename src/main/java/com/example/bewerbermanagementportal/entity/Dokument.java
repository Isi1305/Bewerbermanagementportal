package com.example.bewerbermanagementportal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Dokument {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatische Hochzählung
    @Id // makiert das Feld als Primärschlüssel
    private Long id;

    public Dokument() {
    }

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private LocalDate uploadDatum;

    @Column(nullable = false, length = 250)
    private String dateipfad;

    public enum DokumentTyp {
        PDF, DOCX, JPG, TXT
    }

    // Nutzung von Dokumenttyp Enum
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DokumentTyp dokumenttyp;

    // Getter-Setter für id
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // Getter_Setter für name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Getter-Setter für uploadDatum
    public void setUploadDatum(LocalDate uploadDatum) {
        this.uploadDatum = uploadDatum;
    }

    public LocalDate getUploadDatum() {
        return uploadDatum;
    }

    // Getter-Setter für dateipfad
    public void setDateipfad(String dateipfad) {
        this.dateipfad = dateipfad;
    }

    public String getDateipfad() {
        return dateipfad;
    }

    // Getter-Setter füt dokumenttyp
    public void setDokumentTyp(DokumentTyp dokumenttyp) {
        this.dokumenttyp = dokumenttyp;
    }

    public DokumentTyp getDokumentTyp() {
        return dokumenttyp;
    }

    // Beziehung zu Bewerbung
    @ManyToOne
    @JoinColumn(name ="bewerbung_id", nullable = false)
    private Bewerbung bewerbung;

    // Getter-Setter für bewerbung
    public void setBewerbung(Bewerbung bewerbung) {
        this.bewerbung = bewerbung;
    }

    public Bewerbung getBewerbung() {
        return bewerbung;
    }
}