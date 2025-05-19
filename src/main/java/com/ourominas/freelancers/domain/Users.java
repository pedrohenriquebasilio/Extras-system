package com.ourominas.freelancers.domain;


import com.ourominas.freelancers.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
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

    @Enumerated(EnumType.STRING)
    private Role role;


}
