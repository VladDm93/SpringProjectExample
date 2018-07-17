package com.example.demo.containers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConfigurer {
    private static final Logger log = LoggerFactory.getLogger(DatabaseConfigurer.class);

    public static void start() {
        if (!dataSourceParamsProvided()) {
            ContainerInitializer.start(new PostgresContainerDescription());
        }
    }

    private static boolean dataSourceParamsProvided() {
        String url = System.getProperty("spring.datasource.url");
        String username = System.getProperty("spring.datasource.username");
        String password = System.getProperty("spring.datasource.password");

        return StringUtils.isNoneBlank(url, username, password);
    }
}
