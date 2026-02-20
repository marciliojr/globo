package com.globo.assinatura.adapter.in.web.mapper;

import com.globo.assinatura.adapter.in.web.dto.CriarUsuarioRequestDTO;
import com.globo.assinatura.adapter.in.web.dto.CriarUsuarioResponseDTO;
import com.globo.assinatura.domain.model.Usuario;

public class UsuarioWebMapper {

    private UsuarioWebMapper() {}

    public static CriarUsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new CriarUsuarioResponseDTO(
                usuario.getUuid() != null ? usuario.getUuid().toString() : null,
                usuario.getEmail(),
                usuario.getUsuario()
        );
    }
}
