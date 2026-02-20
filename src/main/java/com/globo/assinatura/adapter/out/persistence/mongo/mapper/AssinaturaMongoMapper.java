package com.globo.assinatura.adapter.out.persistence.mongo.mapper;

import com.globo.assinatura.adapter.out.persistence.mongo.document.AssinaturaDocument;
import com.globo.assinatura.domain.model.Assinatura;

import java.util.UUID;

public class AssinaturaMongoMapper {

    private AssinaturaMongoMapper() {}

    public static AssinaturaDocument toDocument(Assinatura assinatura) {
        if (assinatura == null) {
            return null;
        }

        AssinaturaDocument document = new AssinaturaDocument();
        document.setId(assinatura.getId() != null ? assinatura.getId().toString() : null);
        document.setUsuarioId(assinatura.getUsuarioId() != null ? assinatura.getUsuarioId().toString() : null);
        document.setPlano(assinatura.getPlano());
        document.setDataInicio(assinatura.getDataInicio());
        document.setDataExpiracao(assinatura.getDataExpiracao());
        document.setStatus(assinatura.getStatus());
        document.setTentativasRenovacao(assinatura.getTentativasRenovacao());

        return document;
    }

    public static Assinatura toDomain(AssinaturaDocument document) {
        if (document == null) {
            return null;
        }

        UUID id = document.getId() != null ? UUID.fromString(document.getId()) : null;
        UUID usuarioId = document.getUsuarioId() != null ? UUID.fromString(document.getUsuarioId()) : null;

        return new Assinatura(
                id,
                usuarioId,
                document.getPlano(),
                document.getDataInicio(),
                document.getDataExpiracao(),
                document.getStatus(),
                document.getTentativasRenovacao()
        );
    }

}
