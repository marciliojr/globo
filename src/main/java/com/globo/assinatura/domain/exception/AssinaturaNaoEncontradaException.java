package com.globo.assinatura.domain.exception;

public class AssinaturaNaoEncontradaException extends NegocioException {
    public AssinaturaNaoEncontradaException() {
        super("Assinatura não encontrada exception");
    }
}
