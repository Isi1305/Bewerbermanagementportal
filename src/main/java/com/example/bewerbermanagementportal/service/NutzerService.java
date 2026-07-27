package com.example.bewerbermanagementportal.service;

import com.example.bewerbermanagementportal.entity.Nutzer;
import com.example.bewerbermanagementportal.repository.NutzerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NutzerService {
    private final NutzerRepository nutzerRepository; // speichert das Repository und ändert sich nicht mehr.
    private final PasswordEncoder passwordEncoder;
    public NutzerService(NutzerRepository nutzerRepository, PasswordEncoder passwordEncoder) {
        this.nutzerRepository = nutzerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Nutzer registrieren(Nutzer nutzer) { // nimmt Wert entgegen und gibt ihn am Ende gespeichert zurück.
        if (nutzerRepository.existsByEmail(nutzer.getEmail())) {
            throw new IllegalArgumentException("E-Mail bereits registriert"); // bricht die Methode sofort ab und meldet einen Fehler, falls die E-Mail schon existiert.
        }
        nutzer.setPasswort(passwordEncoder.encode(nutzer.getPasswort())); // von innen nach außen
        return nutzerRepository.save(nutzer);
    }
}
