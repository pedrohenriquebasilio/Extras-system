package com.ourominas.freelancers.domain.dto.request;

public record UserRequestDTO(Long id, String name, String email, String password, String department, String role) {
}
