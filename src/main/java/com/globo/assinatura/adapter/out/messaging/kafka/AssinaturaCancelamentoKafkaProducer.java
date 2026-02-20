package com.globo.assinatura.adapter.out.messaging.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globo.assinatura.adapter.out.messaging.kafka.dto.AssinaturaCanceladaMessage;
import com.globo.assinatura.application.port.out.EnviarEventoCancelamentoPort;
import com.globo.assinatura.domain.event.AssinaturaCanceladaEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AssinaturaCancelamentoKafkaProducer implements EnviarEventoCancelamentoPort {

    private static final Logger logger = LoggerFactory.getLogger(AssinaturaCancelamentoKafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${app.kafka.topic.assinatura-cancelada:assinatura.cancelada}")
    private String topic;

    public AssinaturaCancelamentoKafkaProducer(
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void enviar(AssinaturaCanceladaEvent evento) {
        AssinaturaCanceladaMessage message = AssinaturaCanceladaMessage.from(
                evento.getId() != null ? evento.getId().toString() : null,
                evento.getUsuarioId() != null ? evento.getUsuarioId().toString() : null,
                evento.getAssinaturaId() != null ? evento.getAssinaturaId().toString() : null,
                evento.getDataExpiracao()
        );

        String key = evento.getAssinaturaId() != null ? evento.getAssinaturaId().toString() : null;
        String payload;
        try {
            payload = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            logger.error("Falha ao serializar evento de cancelamento", e);
            throw new IllegalStateException("Erro ao serializar evento de cancelamento", e);
        }

        kafkaTemplate.send(topic, key, payload)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        logger.error("Falha ao enviar evento de cancelamento para o tópico {}: {}",
                                topic, ex.getMessage());
                    }
                });
    }
}
