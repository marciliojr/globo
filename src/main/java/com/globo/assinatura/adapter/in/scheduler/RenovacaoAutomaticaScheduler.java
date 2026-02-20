package com.globo.assinatura.adapter.in.scheduler;

import com.globo.assinatura.application.port.in.RenovarAssinaturaInputPort;
import com.globo.assinatura.domain.repository.AssinaturaRepository;
import com.globo.assinatura.domain.model.Assinatura;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RenovacaoAutomaticaScheduler {

    private static final Logger logger = LoggerFactory.getLogger(RenovacaoAutomaticaScheduler.class);

    private final AssinaturaRepository repositoryPort;
    private final RenovarAssinaturaInputPort renovarAssinaturaUseCase;

    public RenovacaoAutomaticaScheduler(
            AssinaturaRepository repositoryPort,
            RenovarAssinaturaInputPort renovarAssinaturaUseCase) {
        this.repositoryPort = repositoryPort;
        this.renovarAssinaturaUseCase = renovarAssinaturaUseCase;
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void renovarAssinaturasVencidas() {
        logger.info("Iniciando processo de renovação automática de assinaturas");

        try {
            List<Assinatura> assinaturasVencidas = repositoryPort.buscarAssinaturasVencidas();
            
            logger.info("Encontradas {} assinaturas vencidas para renovação", assinaturasVencidas.size());

            for (Assinatura assinatura : assinaturasVencidas) {
                try {
                    renovarAssinaturaUseCase.renovarAssinatura(assinatura);
                    logger.info("Assinatura {} renovada com sucesso", assinatura.getId());
                } catch (Exception e) {
                    logger.error("Erro ao renovar assinatura {}: {}", assinatura.getId(), e.getMessage());
                }
            }

            logger.info("Processo de renovação automática concluído");
        } catch (Exception e) {
            logger.error("Erro crítico no processo de renovação automática", e);
        }
    }
}
