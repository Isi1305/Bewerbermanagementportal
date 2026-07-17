package com.example.bewerbermanagementportal.entity;

import jakarta.persistence.*;

@Entity
public class Stelle {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatische Hochzählung
    @Id // makiert das Feld als Primärschlüssel
    private Long id;

    public Stelle() {
    }

    @Column(nullable = false, length = 100)
    private String titel;

    @Column(nullable = false, length = 20)
    private String typ;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String beschreibung;

    @Column(nullable = false, length = 100)
    private String standort;
}
