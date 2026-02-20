package com.globo.assinatura.adapter.out.persistence.mongo.document;

import com.globo.assinatura.domain.enums.Plano;
import com.globo.assinatura.domain.enums.StatusAssinatura;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Document(collection = "assinatura")
public class AssinaturaDocument {
    @Id
    private String id;
    @Indexed
    private String usuarioId;
    private Plano plano;
    private LocalDate dataInicio;
    private LocalDate dataExpiracao;
    private StatusAssinatura status;
    private int tentativasRenovacao;
    @Version
    private Long version;
}
