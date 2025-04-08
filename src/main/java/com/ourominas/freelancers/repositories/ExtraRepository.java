package com.ourominas.freelancers.repositories;

import com.ourominas.freelancers.domain.Extra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExtraRepository extends JpaRepository<Extra, UUID> {
}
