package com.example.demo.containers;

import java.util.Map;
import org.testcontainers.containers.GenericContainer;

/**
 * Docker container description.
 */
public interface ContainerDescription {

    /**
     * Unique container name (to store in map, not a docker container name).
     */
    String getName();

    /**
     * Create a container.
     */
    GenericContainer getContainer();

    /**
     * Returns a map with Spring application properties for running container.
     */
    Map<String, Object> getContextProperties();
}
