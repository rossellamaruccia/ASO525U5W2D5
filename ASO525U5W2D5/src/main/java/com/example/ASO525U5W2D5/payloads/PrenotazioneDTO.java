package com.example.ASO525U5W2D5.payloads;

import jakarta.validation.constraints.NotNull;

public record PrenotazioneDTO(
        @NotNull
        long dipendente_id,
        @NotNull
        long viaggio_id,
        @NotNull
        String preferenze
) {
}

//nel payload attribuisco l'id degli oggetti, nel service devo fare i get
