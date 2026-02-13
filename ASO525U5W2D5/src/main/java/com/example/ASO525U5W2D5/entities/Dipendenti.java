package com.example.ASO525U5W2D5.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
public class Dipendenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String username;
    private String name;
    private String surname;
    private String email;

    public Dipendenti() {
    }

    public Dipendenti(String username, String name, String surname, String email) {
        this.username = username;
        this.name = name;
        this.surname = username;
        this.email = email;
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
