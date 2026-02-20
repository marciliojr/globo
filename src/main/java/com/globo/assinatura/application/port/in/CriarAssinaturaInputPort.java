package com.globo.assinatura.application.port.in;

import com.globo.assinatura.domain.enums.Plano;
import com.globo.assinatura.domain.model.Assinatura;
import com.globo.assinatura.domain.model.Usuario;

public interface CriarAssinaturaInputPort {

    Assinatura execute(Usuario usuario, Plano plano);

}
