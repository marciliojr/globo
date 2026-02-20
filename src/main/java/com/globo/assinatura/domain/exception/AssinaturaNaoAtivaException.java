package com.globo.assinatura.domain.exception;

public class AssinaturaNaoAtivaException extends NegocioException{
    public AssinaturaNaoAtivaException() {
        super("Apenas assinaturas ativas podem ser renovadas.");
    }
}
