package br.com.bgc.controleobras.interfaces.api;

import br.com.bgc.controleobras.application.command.AlterarPessoaCommand;
import br.com.bgc.controleobras.application.command.CriarPessoaCommand;
import br.com.bgc.controleobras.application.services.PessoaApplicationService;
import br.com.bgc.controleobras.domain.Pessoa;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaApplicationService pessoaService;

    public  PessoaController(PessoaApplicationService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping()
    public Flux<Pessoa> listar() {
        return pessoaService.listar();
    }

    @GetMapping("/{id}")
    public Mono<Pessoa> pesquisar(@PathVariable Long id) {
        return pessoaService.pesquisar(id);
    }

    @PostMapping()
    public Mono<Pessoa> criar(@RequestBody CriarPessoaCommand command) {
        return pessoaService.criar(command);
    }

    @PutMapping("/{id}")
    public Mono<Pessoa> alterar(@PathVariable Long id, @RequestBody AlterarPessoaCommand command) {
        return pessoaService.alterar(id, command);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletar(@PathVariable Long id) {
        return pessoaService.deletar(id);
    }

}
