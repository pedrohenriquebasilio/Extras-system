package com.ourominas.freelancers.domain.dto.request;

import java.util.UUID;

public record UserRequestDTO(UUID id, String name, String email, String password, String department, String role) {
}
