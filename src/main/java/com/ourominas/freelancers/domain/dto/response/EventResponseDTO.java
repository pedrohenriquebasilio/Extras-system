package com.ourominas.freelancers.domain.dto.response;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public record EventResponseDTO(
        UUID id,
        String nome,
        Date date,
        LocalDateTime StartDate,
        LocalDateTime endDate,
        String description,
        LocalDateTime criadoEm
) {}