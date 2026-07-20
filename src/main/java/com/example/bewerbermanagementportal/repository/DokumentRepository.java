package com.example.bewerbermanagementportal.repository;

import com.example.bewerbermanagementportal.entity.Dokument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DokumentRepository extends JpaRepository<Dokument, Long> {
}
