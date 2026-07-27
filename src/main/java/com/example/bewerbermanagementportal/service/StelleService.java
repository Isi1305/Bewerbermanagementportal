package com.example.bewerbermanagementportal.service;

import com.example.bewerbermanagementportal.entity.Stelle;
import com.example.bewerbermanagementportal.repository.StelleRepository;
import org.springframework.stereotype.Service;

@Service
public class StelleService {
    private final StelleRepository stelleRepository;

    public StelleService(StelleRepository stelleRepository) {
        this.stelleRepository = stelleRepository;
    }

    // Stelle entgegennehmen, speichern und zurückgeben
    public Stelle stelleAnlegen(Stelle stelle) {
        return stelleRepository.save(stelle);
    }
}
