package com.globo.assinatura.adapter.in.web.dto;

import com.globo.assinatura.domain.enums.Plano;
import com.globo.assinatura.domain.enums.StatusAssinatura;

import java.time.LocalDate;

public record CriarAssinaturaResponseDTO(
        String idAssinatura,
        StatusAssinatura status,
        Plano plano,
        LocalDate dataCriacao
) {
}
