package com.globo.assinatura.application.usecase.assinatura;

import com.globo.assinatura.application.port.in.CriarAssinaturaInputPort;
import com.globo.assinatura.domain.repository.AssinaturaRepository;
import com.globo.assinatura.domain.enums.Plano;
import com.globo.assinatura.domain.exception.AssinaturaJaAtivaException;
import com.globo.assinatura.domain.model.Assinatura;
import com.globo.assinatura.domain.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class CriarAssinaturaUseCase implements CriarAssinaturaInputPort {

    private final AssinaturaRepository repositoryPort;

    public CriarAssinaturaUseCase(AssinaturaRepository repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Assinatura execute(Usuario usuario, Plano plano) {

        Assinatura assinatura = Assinatura.criar(usuario, plano);

        repositoryPort
                .buscarAtivaPorUsuario(assinatura.getUsuarioId().toString())
                .ifPresent(a -> {
                    throw new AssinaturaJaAtivaException();
                });

        return repositoryPort.salvar(assinatura);
    }
}
