package com.example.bewerbermanagementportal.repository;

import com.example.bewerbermanagementportal.entity.Karrierestatus;
import com.example.bewerbermanagementportal.entity.Stelle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StelleRepository extends JpaRepository<Stelle, Long> {
    List<Stelle> findByArbeitsbereichAndKarrierstatus(
            String arbeitsbereich,
            Karrierestatus karrierestatus
    );
}
