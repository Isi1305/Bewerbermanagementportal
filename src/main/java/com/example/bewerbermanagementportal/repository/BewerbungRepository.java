package com.example.bewerbermanagementportal.repository;

import com.example.bewerbermanagementportal.entity.Bewerbung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BewerbungRepository extends JpaRepository<Bewerbung, Long> {
}
