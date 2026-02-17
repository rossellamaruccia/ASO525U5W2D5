package com.example.ASO525U5W2D5.services;

import com.example.ASO525U5W2D5.entities.Dipendenti;
import com.example.ASO525U5W2D5.exceptions.UnauthorizedException;
import com.example.ASO525U5W2D5.payloads.LoginDTO;
import com.example.ASO525U5W2D5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JWTTools jwtTools;
    private DipendentiService dipendentiService;
    private PasswordEncoder bcrypt;

    @Autowired
    public AuthService(DipendentiService dipendentiService, JWTTools jwtTools, PasswordEncoder bcrypt) {
        this.dipendentiService = dipendentiService;
        this.jwtTools = jwtTools;
        this.bcrypt = bcrypt;
    }

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        Dipendenti found = this.dipendentiService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            String accessToken = jwtTools.generateToken(found);
            return accessToken;

        } else {
            throw new UnauthorizedException("Credenziali errate.");
        }


    }
}
