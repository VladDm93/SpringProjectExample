package com.example.demo.containers;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainerDescription implements ContainerDescription {

    private final PostgreSQLContainer postgresContainer = new PostgreSQLContainer();

    @Override
    public String getName() {
        return "postgres";
    }

    @Override
    public PostgreSQLContainer getContainer() {
        return postgresContainer;
    }

    @Override
    public Map<String, Object> getContextProperties() {
        return ImmutableMap.of(
            "spring.datasource.url", postgresContainer.getJdbcUrl(),
            "spring.datasource.username", postgresContainer.getUsername(),
            "spring.datasource.password", postgresContainer.getPassword()
        );
    }
}
