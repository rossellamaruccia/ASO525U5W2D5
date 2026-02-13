package com.example.ASO525U5W2D5.payloads;

import com.example.ASO525U5W2D5.entities.StatoViaggio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ViaggioDTO(
        @NotBlank
        String destinazione,
        @NotNull
        StatoViaggio statoViaggio
) {
}
