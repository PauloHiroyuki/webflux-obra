package br.com.bgc.controleobras.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Objects;
import org.springframework.data.relational.core.mapping.Table;


@NoArgsConstructor
@Getter

@Table(name = "CONTROLE_OBRAS.PESSOAS")
public class Pessoa {
    @Builder
    public Pessoa(Long id, String nome, String cpf, String email) {
        this.id = id;
        this.nome = Objects.requireNonNull(nome);
        this.cpf = Objects.requireNonNull(cpf);
        this.email = email;
        this.ativo = true;
    }

    @Id
    private Long id;
    @Setter
    private String nome;
    @Setter
    private String cpf;
    @Setter
    private String email;
    private boolean ativo;
}
