package com.globo.assinatura.domain.model;

import java.util.Objects;
import java.util.UUID;

public class Usuario {
    private UUID uuid;
    private String email;
    private String usuario;

    public Usuario(UUID uuid, String email, String usuario) {
        this.uuid = uuid;
        this.email = email;
        this.usuario = usuario;
    }

    public Usuario(String email, String usuario) {
        this.uuid = UUID.randomUUID();
        this.email = email;
        this.usuario = usuario;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(uuid, usuario1.uuid) && Objects.equals(email, usuario1.email) && Objects.equals(usuario, usuario1.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, email, usuario);
    }
}
