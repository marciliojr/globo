package com.globo.assinatura.adapter.out.persistence.mongo.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "usuario")
public class UsuarioDocument {
    @Id
    private String id;
    private String nome;
    @Indexed(unique = true)
    private String email;
}
