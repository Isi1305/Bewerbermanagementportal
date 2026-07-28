package com.example.bewerbermanagementportal.repository;

import com.example.bewerbermanagementportal.entity.Bewerbung;
import com.example.bewerbermanagementportal.entity.Stelle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BewerbungRepository extends JpaRepository<Bewerbung, Long> {
    List<Bewerbung> findByStelle(Stelle stelle);
}
