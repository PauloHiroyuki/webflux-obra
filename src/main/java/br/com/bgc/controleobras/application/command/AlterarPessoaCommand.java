package br.com.bgc.controleobras.application.command;

import lombok.Data;

@Data
public class AlterarPessoaCommand {
    private String nome;
    private String cpf;
    private String email;
}
