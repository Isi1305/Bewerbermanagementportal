package com.example.bewerbermanagementportal.service;

import com.example.bewerbermanagementportal.repository.NutzerRepository;
import org.springframework.stereotype.Service;

@Service
public class NutzerService {
    private final NutzerRepository nutzerRepository; // speichert das Repository und ändert sich nicht mehr.
    public NutzerService(NutzerRepository nutzerRepository) {
        this.nutzerRepository = nutzerRepository;
    }
}
