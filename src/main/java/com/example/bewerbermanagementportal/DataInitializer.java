package com.example.bewerbermanagementportal;

import com.example.bewerbermanagementportal.entity.Karrierestatus;
import com.example.bewerbermanagementportal.entity.Nutzer;
import com.example.bewerbermanagementportal.entity.Rolle;
import com.example.bewerbermanagementportal.entity.Stelle;
import com.example.bewerbermanagementportal.repository.NutzerRepository;
import com.example.bewerbermanagementportal.repository.StelleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {


    private final StelleRepository stelleRepository;
    private final NutzerRepository nutzerRepository;

    public DataInitializer(StelleRepository stelleRepository, NutzerRepository nutzerRepository) {
        this.stelleRepository = stelleRepository;
        this.nutzerRepository = nutzerRepository;
    }

    @Override
    public void run(String... args) {

        System.out.println("Testdaten werden geladen");

        Nutzer recruiter = new Nutzer();
        recruiter.setName("Max Musterman");
        recruiter.setEmail("recruiter@text.de");
        recruiter.setPasswort("1234");
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
    }
}
