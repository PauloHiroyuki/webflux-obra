package br.com.bgc.controleobras.infra.config;

import br.com.bgc.controleobras.application.repository.PessoaRepository;
import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.boot.autoconfigure.sql.init.SqlR2dbcScriptDatabaseInitializer;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Profiles;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.dialect.H2Dialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.util.StringUtils;

import java.sql.SQLException;

@Configuration
@EnableR2dbcRepositories
public class R2DatabaseConfiguration {

    @Value("${spring.data.url:tcp://localhost:8740/mem:controle-obras}")
    String url;

    @Value("${spring.data.username:sa}")
    String username;

    @Value("${spring.data.password:}")
    String password;

    @Bean
    public H2ConnectionFactory connectionFactory() {
        H2ConnectionConfiguration config = H2ConnectionConfiguration.builder()
                .username(username)
                .url(url)
                .password(password)
                .build();
        return new H2ConnectionFactory(config);
    }

    @Profile("development")
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2WebServer() throws SQLException {
        return Server.createWebServer("-webAllowOthers", "-webPort", "8140", "-ifNotExists");
    }

    @Profile("development")
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer("-tcpAllowOthers", "-tcpPort", "8740", "-ifNotExists");
    }

    @Profile("development")
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2PgServer() throws SQLException {
        return Server.createPgServer("-pgAllowOthers", "-pgPort", "8540");
    }

    /*@Bean
    public ConnectionFactoryInitializer initializer(H2ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        //populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
        initializer.setDatabasePopulator(populator);
        return initializer;
    }*/
}
