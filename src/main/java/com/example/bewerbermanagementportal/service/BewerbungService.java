package com.example.bewerbermanagementportal.service;

import com.example.bewerbermanagementportal.repository.BewerbungRepository;
import org.springframework.stereotype.Service;

@Service
public class BewerbungService {
    private final BewerbungRepository bewerbungRepository;

    public BewerbungService(BewerbungRepository bewerbungRepository) {
        this.bewerbungRepository = bewerbungRepository;
    }
}
