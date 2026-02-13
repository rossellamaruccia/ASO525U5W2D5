package com.example.ASO525U5W2D5.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
public class Prenotazioni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    @OneToMany
    private Dipendenti dipendente;
    @OneToMany
    private Viaggi viaggio;
    private String preferenze;

    public Prenotazioni() {
    }

    public Prenotazioni(Dipendenti dipendente, Viaggi viaggio, String preferenze) {
        this.dipendente = dipendente;
        this.viaggio = viaggio;
        this.preferenze = preferenze;
    }

    @Override
    public String toString() {
        return "Prenotazioni{" +
                "id=" + id +
                ", dipendente=" + dipendente +
                ", viaggio=" + viaggio +
                ", preferenze='" + preferenze + '\'' +
                '}';
    }
}
