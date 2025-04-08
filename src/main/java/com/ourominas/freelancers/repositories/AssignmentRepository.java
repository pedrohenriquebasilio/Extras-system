package com.ourominas.freelancers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssignmentRepository extends JpaRepository<AssignmentRepository, UUID> {
}
