package com.example.ASO525U5W2D5.payloads;

import jakarta.validation.constraints.Size;

public record ModificaDipendenteDTO(
        String username,
        @Size(min = 2, max = 30, message = "name length has to be > 2 chars and <30 chars.")
        String name,
        String surname,
        String email,
        String password
) {
}
