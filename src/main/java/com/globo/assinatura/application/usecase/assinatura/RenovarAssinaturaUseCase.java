package com.globo.assinatura.application.usecase.assinatura;

import com.globo.assinatura.application.port.in.RenovarAssinaturaInputPort;
import com.globo.assinatura.domain.repository.AssinaturaRepository;
import com.globo.assinatura.application.port.out.ProcessarPagamentoPort;
import com.globo.assinatura.domain.exception.ErroPagamentoException;
import com.globo.assinatura.domain.model.Assinatura;
import org.springframework.stereotype.Service;

@Service
public class RenovarAssinaturaUseCase implements RenovarAssinaturaInputPort {

    private final AssinaturaRepository repositoryPort;
    private final ProcessarPagamentoPort processarPagamentoPort;

    public RenovarAssinaturaUseCase(
            AssinaturaRepository repositoryPort,
            ProcessarPagamentoPort processarPagamentoPort) {
        this.repositoryPort = repositoryPort;
        this.processarPagamentoPort = processarPagamentoPort;
    }

    @Override
    public void renovarAssinatura(Assinatura assinatura) {
        if (!assinatura.podeSerRenovada()) {
            return;
        }

        try {
            processarPagamentoPort.processarPagamento(assinatura);
            assinatura.renovar();
            repositoryPort.salvar(assinatura);
        } catch (ErroPagamentoException e) {
            assinatura.incrementarTentativaRenovacao();
            repositoryPort.salvar(assinatura);
            throw e;
        }
    }
}
