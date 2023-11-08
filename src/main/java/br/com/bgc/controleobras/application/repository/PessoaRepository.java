package br.com.bgc.controleobras.application.repository;

import br.com.bgc.controleobras.domain.Pessoa;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PessoaRepository extends ReactiveCrudRepository<Pessoa, Long> {
}
