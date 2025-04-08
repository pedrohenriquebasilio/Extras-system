package com.ourominas.freelancers.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Assignment")
@Entity
public class Assignment {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event_id;

    @ManyToOne
    @JoinColumn(name = "extra_id")
    private Extra extra_id;

    private String role;

    private LocalDateTime createdAt = LocalDateTime.now();

}
