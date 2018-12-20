package com.example.demo;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import com.example.demo.data.Cat;
import com.example.demo.service.request.CatCreateRequest;
import com.google.common.primitives.Ints;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

public class CatControllerIT extends BaseIT {

    @Test
    public void createCatMustWork() {
        CatCreateRequest request = new CatCreateRequest();
        request.setAge(5);
        request.setName("Barsik");

        given()
            .contentType(ContentType.JSON)
            .body(request)
        .when()
            .post("cats")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(
                "id", is(not(empty())),
                "name", is("Barsik"),
                "age", is(5)
            );

    }

    @Test
    public void getCatMustWork() {
        Cat cat = catRepository.save(new Cat("Vaska", 50));

        when()
            .get("cats/{id}", cat.getId())
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(
                "id", is(Ints.checkedCast(cat.getId())),
                "name", is("Vaska"),
                "age", is(50)
            );
    }

    @Test
    public void getAllCatsMustWork() {
        Cat cat1 = catRepository.save(new Cat("Marusya", 13));
        Cat cat2 = catRepository.save(new Cat("Vaska", 50));

        when()
            .get("cats")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(
                "size()", is(2),
                "find { it.id == " + cat1.getId() + "}.name", is("Marusya"),
                "find { it.id == " + cat1.getId() + "}.age", is(13),

                "find { it.id == " + cat2.getId() + "}.name", is("Vaska"),
                "find { it.id == " + cat2.getId() + "}.age", is(50)
            );
    }

    @Test
    public void updateCatMustWork() {
        Cat cat = catRepository.save(new Cat("Marusya", 13));

        CatCreateRequest request = new CatCreateRequest();
        request.setAge(16);
        request.setName("Maruska");

        given()
            .contentType(ContentType.JSON)
            .body(request)
        .when()
            .put("cats/{id}", cat.getId())
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(
                "id", is(Ints.checkedCast(cat.getId())),
                "name", is("Maruska"),
                "age", is(16)
            );

        when()
            .get("cats/{id}", cat.getId())
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(
                "id", is(Ints.checkedCast(cat.getId())),
                "name", is("Maruska"),
                "age", is(16)
            );
    }

}
