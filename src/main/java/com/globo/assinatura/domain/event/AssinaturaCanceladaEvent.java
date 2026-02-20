package com.globo.assinatura.domain.event;

import com.globo.assinatura.domain.enums.Plano;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class AssinaturaCanceladaEvent {
    private UUID id;
    private UUID usuarioId;
    private UUID assinaturaId;
    private LocalDate dataExpiracao;

    public AssinaturaCanceladaEvent() {
    }

    public AssinaturaCanceladaEvent(UUID id, UUID usuarioId, UUID assinaturaId, LocalDate dataExpiracao) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.assinaturaId = assinaturaId;
        this.dataExpiracao = dataExpiracao;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public UUID getAssinaturaId() {
        return assinaturaId;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AssinaturaCanceladaEvent that = (AssinaturaCanceladaEvent) o;
        return Objects.equals(id, that.id) && Objects.equals(usuarioId, that.usuarioId) && Objects.equals(assinaturaId, that.assinaturaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuarioId, assinaturaId);
    }
}
