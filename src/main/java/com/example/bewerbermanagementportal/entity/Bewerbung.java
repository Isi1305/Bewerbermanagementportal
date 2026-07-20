package com.example.bewerbermanagementportal.entity;

import jakarta.persistence.*;

@Entity
public class Bewerbung {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatische Hochzählung
    @Id // makiert das Feld als Primärschlüssel
    private Long id;

    public Bewerbung() {
    }

    // Enum von BewerbungStatus
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BewerbungStatus status;

    // Getter-Setter für id
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // Getter-Setter für Status
    public void setStatus(BewerbungStatus status) {
        this.status = status;
    }

    public BewerbungStatus getStatus() {
        return status;
    }

    // Nutzer mit Rolle BEWERBER (technisch Typ Nutzer, Rollenprüfung erfolgt im Service)
    @ManyToOne
    @JoinColumn(name = "nutzer_id", nullable = false)
    private Nutzer bewerber;

    public void setBewerber(Nutzer bewerber) {
        this.bewerber = bewerber;
    }

    public Nutzer getBewerber() {
        return bewerber;
    }

    // Fremdschlüssel stelle_id in Bewerbung
    @ManyToOne
    @JoinColumn(name = "stelle_id", nullable = false)
    private Stelle stelle;

    public void setStelle(Stelle stelle) {
        this.stelle = stelle;
    }

    public Stelle getStelle() {
        return stelle;
    }
}
