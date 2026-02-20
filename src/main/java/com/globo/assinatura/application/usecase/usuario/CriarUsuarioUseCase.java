package com.globo.assinatura.application.usecase.usuario;

import com.globo.assinatura.application.port.in.CriarUsuarioInputPort;
import com.globo.assinatura.domain.exception.EmailJaCadastradoException;
import com.globo.assinatura.domain.model.Usuario;
import com.globo.assinatura.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioUseCase implements CriarUsuarioInputPort {

    private final UsuarioRepository repository;

    public CriarUsuarioUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario execute(String email, String nome) {
        repository.buscarPorEmail(email)
                .ifPresent(u -> {
                    throw new EmailJaCadastradoException();
                });

        Usuario usuario = new Usuario(email, nome);
        return repository.salvar(usuario);
    }
}
