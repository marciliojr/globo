package com.globo.assinatura.domain.exception;

public class ErroPagamentoException extends NegocioException {
    public ErroPagamentoException() {
        super( "Falha ao processar pagamento da assinatura");
    }
}
