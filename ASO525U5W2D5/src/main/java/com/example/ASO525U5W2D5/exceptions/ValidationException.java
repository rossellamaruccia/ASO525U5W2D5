package com.example.ASO525U5W2D5.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private List<String> listaErrori;

    public ValidationException(List<String> listaErrori) {
        super("Something went wrong :(");
        this.listaErrori = listaErrori;
    }
}
