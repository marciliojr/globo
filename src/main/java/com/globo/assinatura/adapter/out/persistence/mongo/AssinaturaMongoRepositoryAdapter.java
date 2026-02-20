package com.globo.assinatura.adapter.out.persistence.mongo;

import com.globo.assinatura.adapter.out.persistence.mongo.mapper.AssinaturaMongoMapper;
import com.globo.assinatura.adapter.out.persistence.mongo.repository.AssassinaturaMongoRepository;
import com.globo.assinatura.domain.repository.AssinaturaRepository;
import com.globo.assinatura.domain.model.Assinatura;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AssinaturaMongoRepositoryAdapter implements AssinaturaRepository {

    private final AssassinaturaMongoRepository repository;

    public AssinaturaMongoRepositoryAdapter(AssassinaturaMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Assinatura> buscarAtivaPorUsuario(String id) {
        return repository.findAtivaByUsuarioId(id)
                .map(AssinaturaMongoMapper::toDomain);
    }

    @Override
    public Assinatura salvar(Assinatura assinatura) {
        var document = AssinaturaMongoMapper.toDocument(assinatura);
        var saved = repository.save(document);
        return AssinaturaMongoMapper.toDomain(saved);
    }

    @Override
    public List<Assinatura> buscarAssinaturasVencidas() {
        return repository.findAssinaturasVencidas(LocalDate.now())
                .stream()
                .map(AssinaturaMongoMapper::toDomain)
                .collect(Collectors.toList());
    }
}
