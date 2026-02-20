package com.globo.assinatura.domain.repository;

import com.globo.assinatura.domain.model.Assinatura;

import java.util.List;
import java.util.Optional;

public interface AssinaturaRepository {

    Optional<Assinatura> buscarAtivaPorUsuario(String id);

    Assinatura salvar(Assinatura assinatura);

    List<Assinatura> buscarAssinaturasVencidas();
}
