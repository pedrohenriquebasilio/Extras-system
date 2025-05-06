package com.ourominas.freelancers.domain.dto.response;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public record EventResponseDTO(
        Long id,
        java.util.UUID extraId,
        java.util.UUID usuarioId,
        String nome,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        String local,
        String descricao,
        String funcao,
        BigDecimal preco,
        Boolean ativo,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {}