package com.globo.assinatura.application.port.in;

import com.globo.assinatura.domain.model.Usuario;

public interface CriarUsuarioInputPort {

    Usuario execute(String email, String nome);

}
