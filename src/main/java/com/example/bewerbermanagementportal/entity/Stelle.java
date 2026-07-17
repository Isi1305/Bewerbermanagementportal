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

    // Getter-Setter für titel
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // Getter-Setter für titel
    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getTitel() {
        return titel;
    }

    // Getter-Setter für typ
    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getTyp() {
        return typ;
    }

    // Getter-Setter für beschreibung
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    // Getter-Setter für standort
    public void setStandort(String standort) {
        this.standort = standort;
    }

    public String getStandort() {
        return standort;
    }
}
