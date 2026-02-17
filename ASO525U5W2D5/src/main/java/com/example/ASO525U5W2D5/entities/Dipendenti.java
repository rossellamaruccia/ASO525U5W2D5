package com.example.ASO525U5W2D5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
public class Dipendenti implements UserDetails {
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
    @JsonIgnore
    private String password;
    //aggiungo password per l'autenticazione
    private String avatar_url;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Dipendenti() {
    }

    public Dipendenti(String username, String name, String surname, String email, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.surname = username;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }
}
