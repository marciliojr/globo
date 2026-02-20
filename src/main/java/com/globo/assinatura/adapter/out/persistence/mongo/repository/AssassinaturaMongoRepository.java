package com.globo.assinatura.adapter.out.persistence.mongo.repository;

import com.globo.assinatura.adapter.out.persistence.mongo.document.AssinaturaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AssassinaturaMongoRepository extends MongoRepository<AssinaturaDocument, String> {

    @Query("{ 'usuarioId' : ?0, 'status' : 'ATIVA' }")
    Optional<AssinaturaDocument> findAtivaByUsuarioId(String usuarioId);

    @Query("{ 'status' : 'ATIVA', 'dataExpiracao' : { $lte: ?0 } }")
    List<AssinaturaDocument> findAssinaturasVencidas(LocalDate dataAtual);
}



