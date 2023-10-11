package com.yosfl;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Collections;
import java.util.Map;

public class DatabaseResource implements QuarkusTestResourceLifecycleManager {
    private static final PostgreSQLContainer DATABASE = new PostgreSQLContainer<>("postgres:13.1")
            .withDatabaseName("conversations")
            .withUsername("conversations")
            .withPassword("password")
            .withExposedPorts(5432);/*
            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
            new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(5432), new ExposedPort(5433)))
            ));*/

    @Override
    public Map<String, String> start() {
        DATABASE.start();
        return Collections.singletonMap("quarkus.datasource.jdbc.url", DATABASE.getJdbcUrl());
    }

    @Override
    public void stop() {
        DATABASE.stop();
    }
}
