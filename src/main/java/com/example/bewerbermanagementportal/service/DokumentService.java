package com.example.bewerbermanagementportal.service;

import com.example.bewerbermanagementportal.repository.DokumentRepository;
import org.springframework.stereotype.Service;

@Service
public class DokumentService {
    private final DokumentRepository dokumentRepository;

    public DokumentService(DokumentRepository dokumentRepository) {
        this.dokumentRepository = dokumentRepository;
    }
}
