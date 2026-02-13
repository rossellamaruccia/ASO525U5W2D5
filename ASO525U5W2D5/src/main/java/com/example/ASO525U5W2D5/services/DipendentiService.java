package com.example.ASO525U5W2D5.services;

import com.example.ASO525U5W2D5.repositories.DipendentiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DipendentiService {
    @Autowired
    private DipendentiRepository dipendentiRepository;
}
