package com.example.bewerbermanagementportal.repository;

import com.example.bewerbermanagementportal.entity.Bewerbung;
import com.example.bewerbermanagementportal.entity.Dokument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DokumentRepository extends JpaRepository<Dokument, Long> {
    List<Dokument> findByBewerbung(Bewerbung bewerbung);
}