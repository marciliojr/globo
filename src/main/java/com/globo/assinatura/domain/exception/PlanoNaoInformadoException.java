package com.globo.assinatura.domain.exception;

public class PlanoNaoInformadoException extends NegocioException {
    public PlanoNaoInformadoException() {
        super("Plano de Assinatura deve ser informado.");
    }
}
