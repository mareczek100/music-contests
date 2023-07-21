package mareczek100.musiccontests.test_configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
@Testcontainers
@TestConfiguration(proxyBeanMethods = false)
public class PersistenceTestConfig {

    private static final String USERNAME = "test";
    private static final String PASSWORD = "test";
    private static final String POSTGRESQL = "postgres";
    private static final String POSTGRESQL_CONTAINER = "postgres:latest";

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(POSTGRESQL_CONTAINER);
    }
//    @Bean
//    @Qualifier(POSTGRESQL)
//    public PostgreSQLContainer<?> postgreSQLContainer(){
//        return new PostgreSQLContainer<>(POSTGRESQL_CONTAINER)
//                .withUsername(USERNAME)
//                .withPassword(PASSWORD);
//    }

    @Bean
    DataSource dataSource (PostgreSQLContainer<?> postgreSQLContainer){
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName(postgreSQLContainer.getDriverClassName())
                .url(postgreSQLContainer.getJdbcUrl())
                .username(postgreSQLContainer.getUsername())
                .password(postgreSQLContainer.getPassword())
                .build();
    }
}