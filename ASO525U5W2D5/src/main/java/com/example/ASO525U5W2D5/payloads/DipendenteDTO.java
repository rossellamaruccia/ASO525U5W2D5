package com.example.ASO525U5W2D5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @NotBlank(message = "username is a required field")
        String username,
        @NotBlank(message = "name is a required field")
        @Size(min = 2, max = 30, message = "name length has to be > 2 chars and <30 chars.")
        String name,
        @NotBlank(message = "surname is a required field")
        String surname,
        @NotBlank(message = "email is a required field")
        @Email(message = "wrong format")
        String email,
        @NotBlank
        //TODO: aggiungere validazione regex x pw
        String password,
        String avatar_url) {
}

//DTO per la registrazione del dipendente, deve includere ora una PW
