package com.example.ASO525U5W2D5.controllers;

import com.example.ASO525U5W2D5.entities.Dipendenti;
import com.example.ASO525U5W2D5.exceptions.ValidationException;
import com.example.ASO525U5W2D5.payloads.DipendenteDTO;
import com.example.ASO525U5W2D5.services.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    private final DipendentiService dipendentiService;

    @Autowired
    public DipendentiController(DipendentiService dipendentiService) {
        this.dipendentiService = dipendentiService;
    }

    // 1. POST http://localhost:8080/dipendenti (+ Payload)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendenti createDipendente(@RequestBody @Validated DipendenteDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationException(errorsList);
        } else {
            return this.dipendentiService.save(payload);
        }

    }

    // 5. DELETE http://localhost:8080/dipendenti/{userId}
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long userId) {
        this.dipendentiService.findByIdAndDelete(userId);
    }
}
