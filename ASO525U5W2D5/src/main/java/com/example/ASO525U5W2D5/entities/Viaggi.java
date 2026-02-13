package com.example.ASO525U5W2D5.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
public class Viaggi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String destinazione;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;

    public Viaggi() {
    }

    public Viaggi(String destinazione, StatoViaggio statoViaggio) {
        this.destinazione = destinazione;
        this.data = LocalDate.now();
        this.statoViaggio = statoViaggio;
    }

    @Override
    public String toString() {
        return "Viaggi{" +
                "id=" + id +
                ", destinazione='" + destinazione + '\'' +
                ", data=" + data +
                ", statoViaggio=" + statoViaggio +
                '}';
    }
}
