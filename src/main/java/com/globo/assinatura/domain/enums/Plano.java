package com.globo.assinatura.domain.enums;

import java.math.BigDecimal;

public enum Plano {

    BASICO(BigDecimal.valueOf(19.90)),
    PREMIUM(BigDecimal.valueOf(39.90)),
    FAMILIA(BigDecimal.valueOf(59.90));

    private final BigDecimal valor;

    Plano(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

}
