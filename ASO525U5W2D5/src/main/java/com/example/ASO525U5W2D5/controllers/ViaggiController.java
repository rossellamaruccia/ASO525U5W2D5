package com.example.ASO525U5W2D5.controllers;

import com.example.ASO525U5W2D5.entities.Viaggi;
import com.example.ASO525U5W2D5.exceptions.ValidationException;
import com.example.ASO525U5W2D5.payloads.ViaggioDTO;
import com.example.ASO525U5W2D5.services.ViaggiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggiController {
    private final ViaggiService viaggiService;

    @Autowired
    public ViaggiController(ViaggiService viaggiService) {
        this.viaggiService = viaggiService;
    }

    // 1. POST http://localhost:3001/viaggi (+ Payload)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggi createViaggi(@RequestBody @Validated ViaggioDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();

            throw new ValidationException(errorsList);
        } else {
            return this.viaggiService.save(payload);
        }
    }
}
