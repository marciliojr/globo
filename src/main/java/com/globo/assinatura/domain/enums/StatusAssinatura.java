package com.globo.assinatura.domain.enums;

public enum StatusAssinatura {
    ATIVA("Ativa"),
    CANCELADO("Cancelado"),
    SUSPENSO("Suspenso");

    private final String descricao;

    StatusAssinatura(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {

        return descricao;
    }

    public boolean isAtiva() {
        return this == StatusAssinatura.ATIVA;
    }
}
