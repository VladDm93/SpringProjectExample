package com.example.demo.containers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;

/**
 * Starts containers and holds it's instances to provide Spring context configuration.
 */
public class ContainerInitializer
    implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Logger log = LoggerFactory.getLogger(ContainerInitializer.class);
    private static final Map<String, Container> RUNNING_CONTAINERS = new HashMap<>();

    /**
     * Start docker container by it's description.
     *
     * @param containerDescription container description instance
     */
    public static void start(ContainerDescription containerDescription) {
        GenericContainer container = containerDescription.getContainer();
        container.start();
        RUNNING_CONTAINERS.put(containerDescription.getName(), new Container(containerDescription, container));
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        String[] propertiesStrings = buildAllContainersPropertiesStrings();
        log.info("Added properties: {}", Arrays.toString(propertiesStrings));
        TestPropertyValues.of(Stream.of(propertiesStrings)).applyTo(applicationContext);
    }

    private String[] buildAllContainersPropertiesStrings() {
        return RUNNING_CONTAINERS.values().stream()
            .flatMap(container -> container.getDescription().getContextProperties()
                .entrySet().stream())
            .map(entry -> entry.getKey() + "=" + entry.getValue())
            .toArray(String[]::new);
    }

    private static class Container {
        private final ContainerDescription description;
        private final GenericContainer instance;

        private Container(ContainerDescription description, GenericContainer instance) {
            this.description = description;
            this.instance = instance;
        }

        ContainerDescription getDescription() {
            return description;
        }

        GenericContainer getInstance() {
            return instance;
        }
    }
}
