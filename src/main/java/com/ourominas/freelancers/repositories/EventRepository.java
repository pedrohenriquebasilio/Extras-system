package com.ourominas.freelancers.repositories;


import com.ourominas.freelancers.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface EventRepository extends JpaRepository<Event, Long> {
    long countByExtraIdAndDataInicioBetween(Long aLong, LocalDateTime inicioSemana, LocalDateTime fimSemana);
}