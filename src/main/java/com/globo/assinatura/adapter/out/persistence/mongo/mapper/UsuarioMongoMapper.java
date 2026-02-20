package com.globo.assinatura.adapter.out.persistence.mongo.mapper;

import com.globo.assinatura.adapter.out.persistence.mongo.document.UsuarioDocument;
import com.globo.assinatura.domain.model.Usuario;

import java.util.UUID;

public class UsuarioMongoMapper {

    private UsuarioMongoMapper() {}

    public static UsuarioDocument toDocument(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioDocument document = new UsuarioDocument();
        document.setId(usuario.getUuid() != null ? usuario.getUuid().toString() : null);
        document.setEmail(usuario.getEmail());
        document.setNome(usuario.getUsuario());

        return document;
    }

    public static Usuario toDomain(UsuarioDocument document) {
        if (document == null) {
            return null;
        }

        UUID uuid = document.getId() != null ? UUID.fromString(document.getId()) : null;

        return new Usuario(
                uuid,
                document.getEmail(),
                document.getNome()
        );
    }

}
