package com.company.primerproyectorestassured.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static org.hamcrest.Matchers.lessThan;

public class RedmineConfig {

    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;

    @BeforeAll //se configura para que se ejecute la ésta configuración antes de la ejecución de los @test
    public static void setup(){
        requestSpecification =new RequestSpecBuilder() //crear objeto requestSpecification
                .setBaseUri("http://192.168.0.7")
                .setBasePath("/")
                .setPort(8081)
                .addHeader("Content-Type", "application/json")
                .addFilter(new RequestLoggingFilter())  //muestra el log del request .log().all()
                .addFilter(new ResponseLoggingFilter()) //muestra el log del response .log().all()
                .addHeader("Accept", "application/json")
                .addHeader("X-Redmine-API-Key", "475338c5e8fe95ef9f9c1cae8cb69610e2d413ac")
                .build();

        responseSpecification=new ResponseSpecBuilder() //crear objeto responseSpecification
               //.expectStatusCode(200)
               .expectResponseTime(lessThan(3000L))
               .build();

        RestAssured.requestSpecification=requestSpecification; //asignar a las variables de restAssured
        RestAssured.responseSpecification=responseSpecification;
    }

    @AfterAll
    public static void cleanUp(){  //setea en blanco al final de una llamada
        RestAssured.reset();
    }

}
