package com.globo.assinatura.adapter.out.pageamento;

import com.globo.assinatura.application.port.out.ProcessarPagamentoPort;
import com.globo.assinatura.domain.exception.ErroPagamentoException;
import com.globo.assinatura.domain.model.Assinatura;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ProcessarPagamentoAdapter implements ProcessarPagamentoPort {

    private final Random random = new Random();

    @Override
    public void processarPagamento(Assinatura assinatura) throws ErroPagamentoException {
        if (random.nextInt(100) < 10) {
            throw new ErroPagamentoException();
        }
        
    }
}
