package com.globo.assinatura.adapter.in.web.dto;

import com.globo.assinatura.domain.enums.Plano;

public record CriarAssinaturaRequestDTO(
        String idUsuario,
        Plano plano
) {
}
