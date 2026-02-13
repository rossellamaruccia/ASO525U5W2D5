package com.example.ASO525U5W2D5.services;

import com.example.ASO525U5W2D5.entities.Viaggi;
import com.example.ASO525U5W2D5.exceptions.NotFoundException;
import com.example.ASO525U5W2D5.payloads.ViaggioDTO;
import com.example.ASO525U5W2D5.repositories.ViaggiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
}
