package com.ourominas.freelancers.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "extras")
@Entity
public class Extra {

    public Extra(UUID id) {
        this.id = id;
    }

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
