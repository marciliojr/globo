package com.globo.assinatura.domain.exception;

public class EmailJaCadastradoException extends NegocioException {

    public EmailJaCadastradoException() {
        super("Já existe um usuário cadastrado com este e-mail");
    }
}
