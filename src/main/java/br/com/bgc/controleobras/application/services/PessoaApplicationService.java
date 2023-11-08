package br.com.bgc.controleobras.application.services;

import br.com.bgc.controleobras.application.command.AlterarPessoaCommand;
import br.com.bgc.controleobras.application.command.CriarPessoaCommand;
import br.com.bgc.controleobras.application.repository.PessoaRepository;
import br.com.bgc.controleobras.domain.Pessoa;
import br.com.bgc.controleobras.domain.excetions.ExcecaoDeNegocio;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PessoaApplicationService {

    private final PessoaRepository repository;

    public PessoaApplicationService(PessoaRepository repository) {
        this.repository = repository;
    }

    public Flux<Pessoa> listar() {
        return this.repository.findAll();
    }

    public Mono<Pessoa> pesquisar(Long id) {
        return this.repository.findById(id);
    }

    public Mono<Pessoa> criar(CriarPessoaCommand command) {
        Pessoa pessoa = Pessoa.builder()
                .nome(command.getNome())
                .cpf(command.getCpf())
                .email(command.getEmail())
                .build();
        return this.repository.save(pessoa);
    }

    public Mono<Pessoa> alterar(Long id, AlterarPessoaCommand command) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new ExcecaoDeNegocio("Pessoa com id %s não existe no banco de dados", id)))
                .flatMap(pessoa -> tratarAlteracaoPessoa(command, pessoa))
                .flatMap(this.repository::save);
    }

    private static Mono<Pessoa> tratarAlteracaoPessoa(AlterarPessoaCommand command, Pessoa pessoa) {
        pessoa.setNome(command.getNome());
        pessoa.setCpf(command.getCpf());
        pessoa.setEmail(command.getEmail());
        return Mono.just(pessoa);
    }


    public Mono<Void> deletar(Long id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new ExcecaoDeNegocio("Pessoa com id %s não existe no banco de dados", id)))
                .flatMap(this.repository::delete);
    }
}
