package com.example.bewerbermanagementportal.entity;

import jakarta.persistence.*;

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
    private Date datum;

    @Column(nullable = false, length = 250)
    private String dateipfad;

    public enum DokumentTyp {
        PDF, DOCX, JPG, TXT
    }
}
