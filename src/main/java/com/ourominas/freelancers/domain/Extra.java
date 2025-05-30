package com.ourominas.freelancers.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ourominas.freelancers.domain.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "extra")
@Entity

public class Extra {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String cpf;
    private String rg;
    private String pis;
    private Date dateBirth;
    private String email;
    private String Telefone;
    @Column(name = "esocial")
    private String eSocial;
    private String sefip;
    private String Sindicate;
    private boolean isAvailable;
    @ManyToMany(mappedBy = "extras")
    @JsonBackReference
    private Set<Event> events = new HashSet<>();
}