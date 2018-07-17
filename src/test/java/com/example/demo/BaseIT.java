package com.example.demo;


import com.example.demo.containers.ContainerInitializer;
import com.example.demo.containers.DatabaseConfigurer;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
@ContextConfiguration(classes = DemoApplication.class, initializers = ContainerInitializer.class)
public abstract class BaseIT {

    @LocalServerPort
    int serverPort;

    @Autowired CatRepository catRepository;

    static {
        DatabaseConfigurer.start();
    }

    @Before
    public void initRestAssured() {
        RestAssured.port = serverPort;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @After
    public void cleanUp() {
        catRepository.deleteAll();
    }
}
