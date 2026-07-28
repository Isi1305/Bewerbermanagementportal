package com.example.bewerbermanagementportal.service;

import com.example.bewerbermanagementportal.entity.Bewerbung;
import com.example.bewerbermanagementportal.entity.BewerbungStatus;
import com.example.bewerbermanagementportal.entity.Stelle;
import com.example.bewerbermanagementportal.repository.BewerbungRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BewerbungService {
    private final BewerbungRepository bewerbungRepository;

    public BewerbungService(BewerbungRepository bewerbungRepository) {
        this.bewerbungRepository = bewerbungRepository;
    }

    // Status kommt rein als EINGANG
    public Bewerbung bewerbungEinreichen(Bewerbung bewerbung) {
        bewerbung.setStatus(BewerbungStatus.EINGANG);
        return bewerbungRepository.save(bewerbung);
    }

    // Status Umwandlung und suche nach ID
    public Bewerbung statusAendern(Long id, BewerbungStatus neuerStatus) {
        Optional<Bewerbung> gefundeneBewerbung = bewerbungRepository.findById(id);

        if (gefundeneBewerbung.isEmpty()) {
            throw new IllegalArgumentException("Bewerbung existiert nicht");
        }

        gefundeneBewerbung.get().setStatus(neuerStatus);

        return bewerbungRepository.save(gefundeneBewerbung.get());
    }


}
