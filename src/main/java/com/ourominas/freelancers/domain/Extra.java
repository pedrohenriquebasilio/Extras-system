package com.ourominas.freelancers.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "extras")
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

    private String eSocial;

    private String Sefip;

    private String Sindicate;

    private boolean isAvaliable;



}
