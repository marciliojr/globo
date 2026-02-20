package com.globo.assinatura.adapter.out.persistence.mongo;

import com.globo.assinatura.adapter.out.persistence.mongo.mapper.UsuarioMongoMapper;
import com.globo.assinatura.adapter.out.persistence.mongo.repository.UsuarioMongoRepository;
import com.globo.assinatura.domain.model.Usuario;
import com.globo.assinatura.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioMongoRepositoryAdapter implements UsuarioRepository {

    private final UsuarioMongoRepository repository;

    public UsuarioMongoRepositoryAdapter(UsuarioMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        var document = UsuarioMongoMapper.toDocument(usuario);
        var saved = repository.save(document);
        return UsuarioMongoMapper.toDomain(saved);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .map(UsuarioMongoMapper::toDomain);
    }
}
