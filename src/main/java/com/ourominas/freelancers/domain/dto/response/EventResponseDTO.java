package com.ourominas.freelancers.domain.dto.response;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Date;

public record EventResponseDTO(
        String nome,
        Date date,
        String description,
        LocalDateTime criadoEm
) {}