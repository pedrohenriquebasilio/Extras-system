package com.ourominas.freelancers.domain;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "Users")
public class Users {

    public Users(UUID id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String email;

    private String password;

    private String department;

    private String role;

}
