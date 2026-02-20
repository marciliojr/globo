package com.globo.assinatura.adapter.in.web.mapper;

import com.globo.assinatura.adapter.in.web.dto.CancelarAssinaturaResponseDTO;
import com.globo.assinatura.adapter.in.web.dto.CriarAssinaturaRequestDTO;
import com.globo.assinatura.adapter.in.web.dto.CriarAssinaturaResponseDTO;
import com.globo.assinatura.domain.model.Assinatura;
import com.globo.assinatura.domain.model.Usuario;

import java.time.LocalDate;
import java.util.UUID;

public class AssinaturaWebMapper {

    private AssinaturaWebMapper() {}

    public static Usuario toUsuario(CriarAssinaturaRequestDTO request) {
        if (request == null || request.idUsuario() == null) {
            return null;
        }

        return new Usuario(UUID.fromString(request.idUsuario()), null, null);
    }


    public static CriarAssinaturaResponseDTO toResponseDTO(Assinatura assinatura) {
        if (assinatura == null) {
            return null;
        }

        return new CriarAssinaturaResponseDTO(
                assinatura.getId() != null ? assinatura.getId().toString() : null,
                assinatura.getStatus(),
                assinatura.getPlano(),
                assinatura.getDataInicio()
        );
    }

    public static CancelarAssinaturaResponseDTO toCancelarResponseDTO(Assinatura assinatura) {
        if (assinatura == null) {
            return null;
        }
        return new CancelarAssinaturaResponseDTO(
                assinatura.getId() != null ? assinatura.getId().toString() : null,
                assinatura.getStatus(),
                assinatura.getDataInicio(),
                LocalDate.now()
        );
    }
}
