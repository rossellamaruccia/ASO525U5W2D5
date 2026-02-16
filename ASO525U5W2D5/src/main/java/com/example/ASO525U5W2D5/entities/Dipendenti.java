package com.example.ASO525U5W2D5.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
public class Dipendenti {
    @JsonManagedReference
    @OneToMany(mappedBy = "dipendente")
    private List<Prenotazioni> listaPrenotazioni;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    //aggiungo password per l'autenticazione
    private String avatar_url;

    public Dipendenti() {
    }

    public Dipendenti(String username, String name, String surname, String email, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.surname = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Dipendenti{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
