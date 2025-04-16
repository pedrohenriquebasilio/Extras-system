package com.ourominas.freelancers.domain.dto.request;

import java.util.Date;

public record ExtraRequestDTO( String name, String cpf, String rg, String pis, Date date_birth, String email, String telefone, String esocial, String Sefip,
                              String sindicate, boolean isAvaliable) {
}
