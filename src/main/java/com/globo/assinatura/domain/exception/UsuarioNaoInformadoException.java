package com.globo.assinatura.domain.exception;

public class UsuarioNaoInformadoException extends NegocioException {

    public UsuarioNaoInformadoException() {
        super("Usuário deve ser informado.");
    }
}
