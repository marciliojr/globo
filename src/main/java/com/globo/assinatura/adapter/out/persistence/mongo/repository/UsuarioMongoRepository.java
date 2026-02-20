package com.globo.assinatura.adapter.out.persistence.mongo.repository;

import com.globo.assinatura.adapter.out.persistence.mongo.document.UsuarioDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioMongoRepository extends MongoRepository<UsuarioDocument, String> {

    Optional<UsuarioDocument> findByEmail(String email);
}
