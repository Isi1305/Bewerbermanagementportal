package com.example.bewerbermanagementportal.repository;

import com.example.bewerbermanagementportal.entity.Nutzer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutzerRepository extends JpaRepository<Nutzer, Long> {
}
