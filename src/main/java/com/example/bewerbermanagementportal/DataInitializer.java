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

            Stelle stelle = stelleRepository.findByTitel("Java Entwickler")
                    .orElse(null);

            if(stelle != null) {
                stelle.setArbeitsbereich("IT");
                stelle.setKarrierestatus(Karrierestatus.EINSTEIGER);
                stelleRepository.save(stelle);
            }


            Stelle stelle2 = stelleRepository.findByTitel("DevOps Engineer (m/w/d)")
                    .orElse(null);

            if(stelle2 != null) {
                stelle2.setArbeitsbereich("IT");
                stelle2.setKarrierestatus(Karrierestatus.EXPERTE);
                stelleRepository.save(stelle2);
            }

            return;
        }

        System.out.println("Testdaten werden geladen");

        Nutzer recruiter = new Nutzer();
        recruiter.setName("Max Musterman");
        recruiter.setEmail("recruiter@text.de");
        recruiter.setPasswort(passwordEncoder.encode("1234"));
        recruiter.setRolle(Rolle.RECRUITER);

        nutzerRepository.save(recruiter);

        // Feste Anzeige
        Stelle stelle = new Stelle();
        stelle.setTitel("Java Entwickler");
        stelle.setTyp("Vollzeit");
        stelle.setBeschreibung("Entwicklung von Spring Boot Anwendung");
        stelle.setStandort("München");

        stelle.setArbeitsbereich("IT");
        stelle.setKarrierestatus(Karrierestatus.EINSTEIGER);

        stelle.setRecruiter(recruiter);

        stelleRepository.save(stelle);

        // Feste Anzeige
        Stelle stelle2 = new Stelle();
        stelle2.setTitel("DevOps Engineer (m/w/d)");
        stelle2.setTyp("Vollzeit");
        stelle2.setBeschreibung("Über die Stelle\n" +
                "Wir suchen einen erfahrenen DevOps Engineer, der unser Entwicklungsteam \n" +
                "bei der Automatisierung und Optimierung unserer CI/CD-Prozesse unterstützt.\n" +
                "\n" +
                "Deine Aufgaben\n" +
                "Verwaltung und Weiterentwicklung unserer Cloud-Infrastruktur\n" +
                "Implementierung von CI/CD-Pipelines\n" +
                "Monitoring und Fehleranalyse der Systeme\n" +
                "\n" +
                "Das bringst Du mit\n" +
                "Erfahrung mit Docker, Kubernetes oder ähnlichen Tools\n" +
                "Kenntnisse in Linux-Administration\n" +
                "Teamfähigkeit und strukturierte Arbeitsweise\n" +
                "\n" +
                "Das bieten wir\n" +
                "Flexible Arbeitszeiten & Remote-Option\n" +
                "Modernes Arbeitsumfeld\n" +
                "30 Tage Urlaub");
        stelle2.setStandort("Kaiserslautern");

        stelle2.setArbeitsbereich("IT");
        stelle2.setKarrierestatus(Karrierestatus.EXPERTE);

        stelle2.setRecruiter(recruiter);

        stelleRepository.save(stelle2);

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
