package com.example.ASO525U5W2D5.services;

import com.example.ASO525U5W2D5.entities.Dipendenti;
import com.example.ASO525U5W2D5.entities.Prenotazioni;
import com.example.ASO525U5W2D5.entities.Viaggi;
import com.example.ASO525U5W2D5.exceptions.BadRequestException;
import com.example.ASO525U5W2D5.exceptions.NotFoundException;
import com.example.ASO525U5W2D5.payloads.PrenotazioneDTO;
import com.example.ASO525U5W2D5.repositories.PrenotazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PrenotazioniService {

    private PrenotazioniRepository prenotazioniRepository;
    private ViaggiService viaggiService;
    private DipendentiService dipendentiService;

    @Autowired
    public PrenotazioniService(PrenotazioniRepository prenotazioniRepository, ViaggiService viaggiService, DipendentiService dipendentiService) {
        this.prenotazioniRepository = prenotazioniRepository;
        this.viaggiService = viaggiService;
        this.dipendentiService = dipendentiService;
    }

    public List<Prenotazioni> getPrenotazioniPerDipendente(long dipendenteId) {
        List<Prenotazioni> listaPrenotazioni = dipendentiService.findById(dipendenteId).getListaPrenotazioni();
        return listaPrenotazioni;
    }

    public Prenotazioni save(PrenotazioneDTO payload) {
        Dipendenti dipendente = dipendentiService.findById(payload.dipendente_id());
        Viaggi viaggio = viaggiService.findById(payload.viaggio_id());
        List<Prenotazioni> prenotazioniDipendente = this.getPrenotazioniPerDipendente(payload.dipendente_id());
        boolean control = prenotazioniDipendente.stream().noneMatch((prenotazione -> prenotazione.getViaggio().getData().isEqual(viaggio.getData())));
        if (control) {
            Prenotazioni newPrenotazione = new Prenotazioni(dipendente, viaggio, payload.preferenze());
            prenotazioniRepository.save(newPrenotazione);
            log.info("Nuova Prenotazione creata");
            return newPrenotazione;
        } else throw new BadRequestException("Il dipendente ha già una prenotazione con questa data");
    }

    public Prenotazioni findById(long id) {
        Optional optional = prenotazioniRepository.findById(id);
        if (optional.isPresent()) {
            return (Prenotazioni) optional.get();
        } else throw new NotFoundException(id);
    }
}
