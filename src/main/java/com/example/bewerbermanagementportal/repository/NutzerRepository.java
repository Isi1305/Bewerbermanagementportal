package com.example.bewerbermanagementportal.repository;

import com.example.bewerbermanagementportal.entity.Nutzer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NutzerRepository extends JpaRepository<Nutzer, Long> {
    boolean existsByEmail(String email); // prüft, ob ein Datensatz mit einem email-Wert existiert

    Optional<Nutzer> findByEmail(String email);
}
