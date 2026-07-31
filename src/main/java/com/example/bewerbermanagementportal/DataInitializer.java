package com.example.bewerbermanagementportal;

import com.example.bewerbermanagementportal.entity.Stelle;
import com.example.bewerbermanagementportal.repository.StelleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {


    private final StelleRepository stelleRepository;

    public DataInitializer(StelleRepository stelleRepository) {
        this.stelleRepository = stelleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Testdaten werden geladen");

        Stelle stelle = new Stelle();
        stelle.setTitel("Java Entwickler");
        stelle.setTyp("Vollzeit");
        stelle.setBeschreibung("Entwicklung von Spring Boot Anwendung");
        stelle.setStandort("München");

        stelleRepository.save(stelle);
    }
}
