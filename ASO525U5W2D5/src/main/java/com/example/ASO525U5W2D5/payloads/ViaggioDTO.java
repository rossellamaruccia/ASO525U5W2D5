package com.example.ASO525U5W2D5.payloads;

import com.example.ASO525U5W2D5.entities.StatoViaggio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ViaggioDTO(
        @NotBlank
        String destinazione,
        @NotNull
        LocalDate data,
        @NotNull
        StatoViaggio statoViaggio
) {
}
