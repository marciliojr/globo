package com.globo.assinatura.application.port.in;

import com.globo.assinatura.domain.model.Assinatura;

import java.util.UUID;

public interface CancelarAssinaturaInputPort {
    Assinatura executar(UUID UsuarioId);
}
