package com.ourominas.freelancers.domain.dto.request;


import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.UUID;

public record EventRequestDTO(
        UUID extraId,
        UUID usuarioId,
        String nome,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        String local,
        String descricao,
        String funcao,
        @NotNull BigDecimal preco
) {}