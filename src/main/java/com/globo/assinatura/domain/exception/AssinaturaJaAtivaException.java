package com.globo.assinatura.domain.exception;

public class AssinaturaJaAtivaException extends NegocioException {
    public AssinaturaJaAtivaException() {
        super("Assinatura já está ativa.");
    }
}
