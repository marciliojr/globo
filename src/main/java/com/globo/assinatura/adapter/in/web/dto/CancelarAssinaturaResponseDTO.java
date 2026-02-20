package com.globo.assinatura.adapter.in.web.dto;

import com.globo.assinatura.domain.enums.StatusAssinatura;

import java.time.LocalDate;

public record CancelarAssinaturaResponseDTO(String idAssinatura,
                                            StatusAssinatura statusAssinatura,
                                            LocalDate dataCriacao,
                                            LocalDate dataAtualizacao) {
}
