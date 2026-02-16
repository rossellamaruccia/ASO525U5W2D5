package com.example.ASO525U5W2D5.services;

import com.example.ASO525U5W2D5.entities.Dipendenti;
import com.example.ASO525U5W2D5.exceptions.UnauthorizedException;
import com.example.ASO525U5W2D5.payloads.LoginDTO;
import com.example.ASO525U5W2D5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JWTTools jwtTools;
    private DipendentiService dipendentiService;

    @Autowired
    public AuthService(DipendentiService dipendentiService, JWTTools jwtTools) {
        this.dipendentiService = dipendentiService;
        this.jwtTools = jwtTools;
    }

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        Dipendenti found = this.dipendentiService.findByEmail(body.email());
        if (found.getPassword().equals(body.password())) {
            String accessToken = jwtTools.generateToken(found);
            return accessToken;

        } else {
            throw new UnauthorizedException("Credenziali errate.");
        }


    }
}
