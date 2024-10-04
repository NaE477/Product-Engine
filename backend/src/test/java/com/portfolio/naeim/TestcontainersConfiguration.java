package com.portfolio.naeim;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    private PostgreSQLContainer<?> postgresqlContainer;

    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgresqlContainer() {
        if (postgresqlContainer == null) {
            postgresqlContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.2"));
        }
        return postgresqlContainer;
    }

    @BeforeEach
    void setUp() {
        if (!postgresqlContainer.isRunning()) {
            postgresqlContainer.start();
        }
    }

    @AfterEach
    void tearDown() {
        if (postgresqlContainer.isRunning()) {
            postgresqlContainer.stop();
        }
    }
}
