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
@Table(name = "event")
@Entity
public class Event {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    private Date date;

}
