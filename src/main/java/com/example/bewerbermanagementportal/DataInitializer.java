package com.example.bewerbermanagementportal;

import com.example.bewerbermanagementportal.entity.*;
import com.example.bewerbermanagementportal.repository.BewerbungRepository;
import com.example.bewerbermanagementportal.repository.NutzerRepository;
import com.example.bewerbermanagementportal.repository.StelleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {


    private final StelleRepository stelleRepository;
    private final NutzerRepository nutzerRepository;
    private final PasswordEncoder passwordEncoder;
    private final BewerbungRepository bewerbungRepository;

    public DataInitializer(
            StelleRepository stelleRepository,
            NutzerRepository nutzerRepository,
            PasswordEncoder passwordEncoder,
            BewerbungRepository bewerbungRepository
    ) {
        this.stelleRepository = stelleRepository;
        this.nutzerRepository = nutzerRepository;
        this.passwordEncoder = passwordEncoder;
        this.bewerbungRepository = bewerbungRepository;
    }

    @Override
    public void run(String... args) {

        if (stelleRepository.count() > 0) {
            return;
        }

        System.out.println("Testdaten werden geladen");

        Nutzer recruiter = new Nutzer();
        recruiter.setName("Max Musterman");
        recruiter.setEmail("recruiter@text.de");
        recruiter.setPasswort(passwordEncoder.encode("1234"));
        recruiter.setRolle(Rolle.RECRUITER);

        nutzerRepository.save(recruiter);

        Stelle stelle = new Stelle();
        stelle.setTitel("Java Entwickler");
        stelle.setTyp("Vollzeit");
        stelle.setBeschreibung("Entwicklung von Spring Boot Anwendung");
        stelle.setStandort("München");

        stelle.setArbeitsbereich("IT");
        stelle.setKarrierestatus(Karrierestatus.EINSTEIGER);

        stelle.setRecruiter(recruiter);

        stelleRepository.save(stelle);

        Nutzer bewerber = new Nutzer();

        bewerber.setName("Anna Beispiel");
        bewerber.setEmail("anna@test.de");
        bewerber.setPasswort("1234");
        bewerber.setRolle(Rolle.BEWERBER);

        nutzerRepository.save(bewerber);


        Bewerbung bewerbung = new Bewerbung();

        bewerbung.setBewerber(bewerber);
        bewerbung.setStelle(stelle);

        bewerbung.setVorname("Anna");
        bewerbung.setNachname("Beispiel");
        bewerbung.setEmail("anna@test.de");
        bewerbung.setTelefon("0123456789");

        bewerbung.setDatenschutzAkzeptiert(true);
        bewerbung.setStatus(BewerbungStatus.EINGANG);

        bewerbungRepository.save(bewerbung);
    }
}
