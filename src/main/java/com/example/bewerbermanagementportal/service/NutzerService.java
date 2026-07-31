package com.example.bewerbermanagementportal.service;

import com.example.bewerbermanagementportal.entity.Nutzer;
import com.example.bewerbermanagementportal.entity.Rolle;
import com.example.bewerbermanagementportal.repository.NutzerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NutzerService {
    private final NutzerRepository nutzerRepository; // speichert das Repository und ändert sich nicht mehr.
    private final PasswordEncoder passwordEncoder;

    public NutzerService(NutzerRepository nutzerRepository, PasswordEncoder passwordEncoder) {
        this.nutzerRepository = nutzerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Passwort-Hashing und prüft ob E-Mail schon existiert
    public Nutzer registrieren(Nutzer nutzer) { // nimmt Wert entgegen und gibt ihn am Ende gespeichert zurück.
        if (nutzerRepository.existsByEmail(nutzer.getEmail())) {
            throw new IllegalArgumentException("E-Mail bereits registriert"); // bricht die Methode sofort ab und meldet einen Fehler, falls die E-Mail schon existiert.
        }
        nutzer.setPasswort(passwordEncoder.encode(nutzer.getPasswort())); // von innen nach außen
        nutzer.setRolle(Rolle.BEWERBER);

        return nutzerRepository.save(nutzer);
    }

    // prüft ob der Nutzer existiert und ob das Passwort richtig ist
    public Nutzer login(Nutzer nutzer) {
        Optional<Nutzer> gefundenerNutzer = nutzerRepository.findByEmail(nutzer.getEmail());

        if (gefundenerNutzer.isEmpty()) {
            throw new IllegalArgumentException("Nutzer nicht gefunden");
        }
        if (!passwordEncoder.matches(nutzer.getPasswort(), gefundenerNutzer.get().getPasswort())) {
            throw new IllegalArgumentException("Passwort falsch");
        }
        return gefundenerNutzer.get();
    }
}