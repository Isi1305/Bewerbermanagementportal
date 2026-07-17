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

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwort;

    @Enumerated(EnumType.STRING) // Speichert den Wert als Text
    @Column(nullable = false)
    private Rolle rolle; // Typ Rolle: selbstgebautes Enum

    // Getter-Setter für id
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // Getter-Setter für name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Getter-Setter für email
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    // Getter-Setter für email
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getPasswort() {
        return passwort;
    }

    // Getter-Setter für rolle
    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }

    public Rolle getRolle() {
        return rolle;
    }
}