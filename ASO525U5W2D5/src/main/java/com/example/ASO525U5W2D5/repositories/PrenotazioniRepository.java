package com.example.ASO525U5W2D5.repositories;

import com.example.ASO525U5W2D5.entities.Prenotazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazioni, Long> {
}
