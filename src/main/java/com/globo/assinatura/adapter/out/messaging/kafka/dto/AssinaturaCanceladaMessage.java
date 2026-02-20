package com.globo.assinatura.adapter.out.messaging.kafka.dto;

import java.time.LocalDate;

public record AssinaturaCanceladaMessage(
        String id,
        String usuarioId,
        String assinaturaId,
        String dataExpiracao
) {
    public static AssinaturaCanceladaMessage from(
            String id,
            String usuarioId,
            String assinaturaId,
            LocalDate dataExpiracao) {
        return new AssinaturaCanceladaMessage(
                id,
                usuarioId,
                assinaturaId,
                dataExpiracao != null ? dataExpiracao.toString() : null
        );
    }
}
