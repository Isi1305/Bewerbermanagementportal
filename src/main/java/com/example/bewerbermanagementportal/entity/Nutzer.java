package com.example.bewerbermanagementportal.entity;

import jakarta.persistence.*;
import org.hibernate.persister.entity.UniqueKeyEntry;

@Entity
public class Nutzer {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatische Hochzählung
    @Id // makiert das Feld als Primärschlüssel
    private Long id;

    public Nutzer() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwort;

    @Enumerated(EnumType.STRING) // Speichert den Wert als Text
    @Column(nullable = false)
    private Rolle rolle; // Typ Rolle: selbstgebautes Enum
}