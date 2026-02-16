package com.example.ASO525U5W2D5.controllers;

import com.example.ASO525U5W2D5.entities.Dipendenti;
import com.example.ASO525U5W2D5.exceptions.ValidationException;
import com.example.ASO525U5W2D5.payloads.DipendenteDTO;
import com.example.ASO525U5W2D5.payloads.LoginDTO;
import com.example.ASO525U5W2D5.payloads.LoginResponseDTO;
import com.example.ASO525U5W2D5.services.AuthService;
import com.example.ASO525U5W2D5.services.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final DipendentiService dipendentiService;

    @Autowired
    public AuthController(AuthService authService, DipendentiService dipendentiService) {
        this.authService = authService;
        this.dipendentiService = dipendentiService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body) {
        return new LoginResponseDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendenti creaDipendente(@RequestBody @Validated DipendenteDTO payload, BindingResult validationResult) {
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
}