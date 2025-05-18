package com.ourominas.freelancers.domain.dto.response;

import java.util.Date;
import java.util.UUID;

public record ExtraResponseDTO(UUID id, String name, String cpf, String rg, String pis, Date date_birth, String email, String telefone, String esocial, String Sefip,
                               String sindicate, boolean isAvaliable) {
}
