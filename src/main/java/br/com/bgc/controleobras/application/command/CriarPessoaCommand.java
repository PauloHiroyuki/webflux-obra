package br.com.bgc.controleobras.application.command;

import lombok.Data;

@Data
public class CriarPessoaCommand {
    private String nome;
    private String cpf;
    private String email;
}
