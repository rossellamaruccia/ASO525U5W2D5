package com.example.ASO525U5W2D5.services;

import com.example.ASO525U5W2D5.entities.Viaggi;
import com.example.ASO525U5W2D5.exceptions.NotFoundException;
import com.example.ASO525U5W2D5.payloads.ViaggioDTO;
import com.example.ASO525U5W2D5.repositories.ViaggiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ViaggiService {
    @Autowired
    private ViaggiRepository viaggiRepository;

    public Viaggi save(ViaggioDTO payload) {
        //TODO:controlli su viaggi?

        Viaggi newViaggio = new Viaggi(payload.destinazione(), payload.statoViaggio());
        viaggiRepository.save(newViaggio);
        log.info("Nuovo Viaggio creato");

        return newViaggio;
    }

    public Viaggi findById(long viaggioId) {
        Optional optional = viaggiRepository.findById(viaggioId);
        if (optional.isPresent()) {
            return (Viaggi) optional.get();
        } else throw new NotFoundException(viaggioId);
    }

    public Viaggi findByIdAndUpdate(long viaggioId, ViaggioDTO payload) {
        if (viaggiRepository.findById(viaggioId).isPresent()) {
            Viaggi viaggio = (Viaggi) viaggiRepository.findById(viaggioId).get();
            viaggio.setStatoViaggio(payload.statoViaggio());
            viaggiRepository.save(viaggio);
            return viaggio;
        } else throw new NotFoundException(viaggioId);
    }

    public Page<Viaggi> findAll(int page, int size, String orderBy, String sortCriteria) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.viaggiRepository.findAll(pageable);
    }

    public void findByIdAndDelete(long viaggioId) {
        Optional optional = viaggiRepository.findById(viaggioId);
        if (optional.isPresent()) {
            Viaggi found = (Viaggi) optional.get();
            viaggiRepository.delete(found);
            log.info("Viaggio cancellato");
        } else throw new NotFoundException(viaggioId);
    }
}
