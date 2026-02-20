package com.globo.assinatura.application.usecase.assinatura;

import com.globo.assinatura.application.port.in.CancelarAssinaturaInputPort;
import com.globo.assinatura.application.port.out.EnviarEventoCancelamentoPort;
import com.globo.assinatura.domain.event.AssinaturaCanceladaEvent;
import com.globo.assinatura.domain.exception.AssinaturaNaoEncontradaException;
import com.globo.assinatura.domain.model.Assinatura;
import com.globo.assinatura.domain.repository.AssinaturaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CancelarAssinaturaUseCase implements CancelarAssinaturaInputPort {

    private static final Logger logger = LoggerFactory.getLogger(CancelarAssinaturaUseCase.class);

    private final AssinaturaRepository repository;
    private final EnviarEventoCancelamentoPort enviarEventoCancelamentoPort;

    public CancelarAssinaturaUseCase(
            AssinaturaRepository repository,
            EnviarEventoCancelamentoPort enviarEventoCancelamentoPort) {
        this.repository = repository;
        this.enviarEventoCancelamentoPort = enviarEventoCancelamentoPort;
    }

    @Override
    public Assinatura executar(UUID usuarioId) {
        Assinatura assinatura = executarCancelamento(usuarioId);

        try {
            publicarEventoCancelamento(assinatura);
        } catch (Exception e) {
            logger.error("Falha ao publicar evento de cancelamento da assinatura {}. Compensação: apenas log.", assinatura.getId(), e);
            compensar(assinatura);
            throw e;
        }

        return assinatura;
    }

    private Assinatura executarCancelamento(UUID usuarioId) {
        Assinatura assinatura = repository
                .buscarAtivaPorUsuario(usuarioId.toString())
                .orElseThrow(AssinaturaNaoEncontradaException::new);

        assinatura.cancelaAssinatura();
        return repository.salvar(assinatura);
    }

    private void publicarEventoCancelamento(Assinatura assinatura) {
        AssinaturaCanceladaEvent evento = new AssinaturaCanceladaEvent(
                UUID.randomUUID(),
                assinatura.getUsuarioId(),
                assinatura.getId(),
                assinatura.getDataExpiracao()
        );
        enviarEventoCancelamentoPort.enviar(evento);
    }

    private void compensar(Assinatura assinatura) {
        logger.warn("Compensação: evento de cancelamento não publicado para assinatura {}. " +
                "Assinatura permanece cancelada.", assinatura.getId());
    }
}
