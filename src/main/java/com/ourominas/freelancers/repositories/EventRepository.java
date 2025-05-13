package com.ourominas.freelancers.repositories;


import com.ourominas.freelancers.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findByDateBetween(Date start, Date end);


}