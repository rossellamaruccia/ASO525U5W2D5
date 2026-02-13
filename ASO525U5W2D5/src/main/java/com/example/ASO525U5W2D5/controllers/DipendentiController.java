package com.example.ASO525U5W2D5.controllers;

import com.example.ASO525U5W2D5.entities.Dipendenti;
import com.example.ASO525U5W2D5.exceptions.ValidationException;
import com.example.ASO525U5W2D5.payloads.DipendenteDTO;
import com.example.ASO525U5W2D5.services.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    private final DipendentiService dipendentiService;

    @Autowired
    public DipendentiController(DipendentiService dipendentiService) {
        this.dipendentiService = dipendentiService;
    }

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

    @GetMapping
    public Page<Dipendenti> getAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "data") String orderBy,
                                   @RequestParam(defaultValue = "asc") String sortCriteria) {
        return this.dipendentiService.findAll(page, size, orderBy, sortCriteria);
    }

    @PatchMapping("/{dipendenteId}/avatar")
    public void uploadImage(@RequestParam("profile_pic") MultipartFile file, @PathVariable long dipendenteId) {
        this.dipendentiService.uploadAvatar(file, dipendenteId);
    }

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long dipendenteId) {
        this.dipendentiService.findByIdAndDelete(dipendenteId);
    }
}
