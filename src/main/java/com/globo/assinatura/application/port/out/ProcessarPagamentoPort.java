package com.globo.assinatura.application.port.out;

import com.globo.assinatura.domain.exception.ErroPagamentoException;
import com.globo.assinatura.domain.model.Assinatura;

public interface ProcessarPagamentoPort {
    void processarPagamento(Assinatura assinatura) throws ErroPagamentoException;
}
