package com.ourominas.freelancers.domain.dto.request;


import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public record EventRequestDTO(
        UUID usuarioId,
        String title,
        Date date,
        LocalDateTime StartDate,
        LocalDateTime endDate,
        String description,
        List<UUID> extraId
) {}