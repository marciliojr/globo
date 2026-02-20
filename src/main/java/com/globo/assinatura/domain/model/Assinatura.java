package com.globo.assinatura.domain.model;

import com.globo.assinatura.domain.enums.Plano;
import com.globo.assinatura.domain.enums.StatusAssinatura;
import com.globo.assinatura.domain.exception.AssinaturaNaoAtivaException;
import com.globo.assinatura.domain.exception.PlanoNaoInformadoException;
import com.globo.assinatura.domain.exception.UsuarioNaoInformadoException;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static com.globo.assinatura.domain.enums.StatusAssinatura.ATIVA;

public class Assinatura {
    private UUID id;
    private UUID usuarioId;
    private Plano plano;
    private LocalDate dataInicio;
    private LocalDate dataExpiracao;
    private StatusAssinatura status;
    private int tentativasRenovacao;

    public Assinatura(UUID usuarioId, Plano plano) {
        this.id = UUID.randomUUID();
        this.usuarioId = usuarioId;
        this.plano = plano;
        this.dataInicio = LocalDate.now();
        this.dataExpiracao = dataInicio.plusMonths(1);
        this.status = ATIVA;
        this.tentativasRenovacao = 0;
    }

    public Assinatura(UUID id, UUID usuarioId, Plano plano, LocalDate dataInicio, LocalDate dataExpiracao, StatusAssinatura status, int tentativasRenovacao) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.plano = plano;
        this.dataInicio = dataInicio;
        this.dataExpiracao = dataExpiracao;
        this.status = status;
        this.tentativasRenovacao = tentativasRenovacao;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public Plano getPlano() {
        return plano;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public StatusAssinatura getStatus() {
        return status;
    }

    public int getTentativasRenovacao() {
        return tentativasRenovacao;
    }

    public boolean isAssinaturaAtiva(){
        return this.status.isAtiva();
    }

    public void cancelaAssinatura(){
        this.status = StatusAssinatura.CANCELADO;
    }

    public void renovar() {
        if (!this.status.isAtiva()) {
            throw new AssinaturaNaoAtivaException();
        }
        this.dataInicio = LocalDate.now();
        this.dataExpiracao = dataInicio.plusMonths(1);
        this.tentativasRenovacao = 0;
    }

    public void incrementarTentativaRenovacao() {
        this.tentativasRenovacao++;
        if (this.tentativasRenovacao >= 3) {
            this.status = StatusAssinatura.SUSPENSO;
        }
    }

    public boolean estaVencida() {
        return LocalDate.now().isAfter(this.dataExpiracao) || LocalDate.now().isEqual(this.dataExpiracao);
    }

    public boolean podeSerRenovada() {
        return this.status.isAtiva() && estaVencida();
    }

    public static Assinatura criar(Usuario usuario, Plano plano) {

        if(usuario == null || usuario.getUuid() == null){
            throw new UsuarioNaoInformadoException();
        }

        if(plano == null){
            throw new PlanoNaoInformadoException();
        }
        return new Assinatura(usuario.getUuid(), plano);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Assinatura that = (Assinatura) o;
        return tentativasRenovacao == that.tentativasRenovacao && Objects.equals(id, that.id) && Objects.equals(usuarioId, that.usuarioId) && plano == that.plano && Objects.equals(dataInicio, that.dataInicio) && Objects.equals(dataExpiracao, that.dataExpiracao) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuarioId, plano, dataInicio, dataExpiracao, status, tentativasRenovacao);
    }
}
