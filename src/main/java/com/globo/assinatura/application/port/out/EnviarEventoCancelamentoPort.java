package com.globo.assinatura.application.port.out;

import com.globo.assinatura.domain.event.AssinaturaCanceladaEvent;

public interface EnviarEventoCancelamentoPort {
    void enviar(AssinaturaCanceladaEvent evento);
}
