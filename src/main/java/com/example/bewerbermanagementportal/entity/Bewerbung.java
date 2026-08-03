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

    @Column(nullable = false, length = 50)
    private String vorname;

    @Column(nullable = false, length = 50)
    private String nachname;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 30)
    private String telefon;

    @Column(nullable = false)
    private boolean datenschutzAkzeptiert;

    @Column(columnDefinition = "TEXT")
    private String notiz;

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

    // Getter-Setter für vorname
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getVorname() {
        return vorname;
    }

    // Getter-Setter für nachname
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getNachname() {
        return nachname;
    }

    // Getter-Setter für email
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    // Getter-Setter für telefon
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTelefon() {
        return telefon;
    }

    // Getter-Setter für DatenschutzAkzeptiert
    public void setDatenschutzAkzeptiert(boolean datenschutzAkzeptiert) {
        this.datenschutzAkzeptiert = datenschutzAkzeptiert;
    }

    public boolean isDatenschutzAkzeptiert() {
        return datenschutzAkzeptiert;
    }

    // Getter-Setter für notiz
    public void setNotiz(String notiz) {
        this.notiz = notiz;
    }

    public String getNotiz() {
        return notiz;
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
