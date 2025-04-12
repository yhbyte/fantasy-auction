package com.bytes.and.dragons.fantasyauction.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresTestContainerConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("auction")
            .withUsername("postgres")
            .withPassword("postgres");

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        POSTGRES_CONTAINER.start();

        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(context,
                "spring.datasource.url=" + POSTGRES_CONTAINER.getJdbcUrl(),
                "spring.datasource.username=" + POSTGRES_CONTAINER.getUsername(),
                "spring.datasource.password=" + POSTGRES_CONTAINER.getPassword(),
                "spring.datasource.driver-class-name=" + POSTGRES_CONTAINER.getDriverClassName()
        );
    }
}
