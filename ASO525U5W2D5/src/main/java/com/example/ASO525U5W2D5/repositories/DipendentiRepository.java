package com.example.ASO525U5W2D5.repositories;

import com.example.ASO525U5W2D5.entities.Dipendenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DipendentiRepository extends JpaRepository<Dipendenti, Long> {
    Optional<Dipendenti> findByEmail(String email);

    List<Long> findPrenotazioniById(long id);
}
