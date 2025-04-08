package com.ourominas.freelancers.repositories;

import com.ourominas.freelancers.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
