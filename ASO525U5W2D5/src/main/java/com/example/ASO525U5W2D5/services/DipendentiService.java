package com.example.ASO525U5W2D5.services;

import com.example.ASO525U5W2D5.entities.Dipendenti;
import com.example.ASO525U5W2D5.exceptions.BadRequestException;
import com.example.ASO525U5W2D5.exceptions.NotFoundException;
import com.example.ASO525U5W2D5.payloads.DipendenteDTO;
import com.example.ASO525U5W2D5.repositories.DipendentiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DipendentiService {

    private DipendentiRepository dipendentiRepository;

    @Autowired
    public DipendentiService(DipendentiRepository dipendentiRepository) {
        this.dipendentiRepository = dipendentiRepository;
    }

    public Dipendenti save(DipendenteDTO payload) {

        this.dipendentiRepository.findByEmail(payload.email()).ifPresent(dipendente -> {
            throw new BadRequestException("L'email " + payload.email() + " è già in uso!");
        });

        Dipendenti newDipendente = new Dipendenti(payload.username(), payload.name(), payload.surname(), payload.email());
        dipendentiRepository.save(newDipendente);

        log.info("Nuovo Dipendente creato");

        return newDipendente;
    }

    public void findByIdAndDelete(long dipendenteId) {
        Optional optional = dipendentiRepository.findById(dipendenteId);
        if (optional.isPresent()) {
            Dipendenti found = (Dipendenti) optional.get();
            dipendentiRepository.delete(found);
            log.info("Dipendente cancellato");
        } else throw new NotFoundException(dipendenteId);
    }
}
