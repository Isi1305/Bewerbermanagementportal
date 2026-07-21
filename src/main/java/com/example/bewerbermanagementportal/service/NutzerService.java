package com.example.bewerbermanagementportal.service;

import com.example.bewerbermanagementportal.entity.Nutzer;
import com.example.bewerbermanagementportal.repository.NutzerRepository;
import org.springframework.stereotype.Service;

@Service
public class NutzerService {
    private final NutzerRepository nutzerRepository; // speichert das Repository und ändert sich nicht mehr.
    public NutzerService(NutzerRepository nutzerRepository) {
        this.nutzerRepository = nutzerRepository;
    }

    public Nutzer registrieren(Nutzer nutzer) { // nimmt Wert entgegen und gibt ihn am Ende gespeichert zurück.
        if (nutzerRepository.existsByEmail(nutzer.getEmail())) {
            throw new IllegalArgumentException("E-Mail bereits registriert"); // bricht die Methode sofort ab und meldet einen Fehler, falls die E-Mail schon existiert.
        }
        return nutzerRepository.save(nutzer);
    }
}
