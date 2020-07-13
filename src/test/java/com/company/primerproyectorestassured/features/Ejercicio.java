package com.company.primerproyectorestassured.features;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Ejercicio {
    private static RequestSpecification request; //se declara objeto tipo request de la liberia restassured
    private static Response response; //se declara objeto tipo response de la liberia restassured

    @Test
    public void requestGetProjects_checkResponseCode_expect200_and_TotalCount10() {
        //Primero se debe probar en Postman y luego se debe validar en la IDE:
        //1. Código http respuesta (200 ok)
        //2. código del servicio satisfactorio
        //3. esquema del servicio ->consiste en validar que el servicio devuelva el mismo tipo de variable para un campo
        //4. contenido de la respuesta del servicio (ok)

        // Es lo mismo que realizar en postman GET: http://192.168.0.3:8081/projects.json
        request = given()  //crear objeto
                .contentType("application/json");

        response = request.when()
                .get("http://192.168.0.5:8081/projects.json"); //realiza la llamada del servicio
        response.then()  //compara el código. Todo lo que viene después del then, es la respuesta del servicio
                .log()
                .all()
                .assertThat()
                .statusCode(200);

        JsonPath jsonPath = new JsonPath(response.getBody().asString());//recibe un string
        Assertions.assertEquals(4, jsonPath.getInt("total_count"));  //compara el id 4 con el contador
    }

    @Test
    public void requestGetIssues_checkResponseCode_expect200(){

        request = given().contentType("application/json");

        response = request.when().get("http://192.168.0.5:8081/issues.json");

        response.then().log().all().assertThat().statusCode(200);

        JsonPath jsonPath = new JsonPath(response.getBody().asString());

        Assertions.assertEquals(6, jsonPath.getInt("total_count"));
    }

    @Test
    public void requestPostCreateIssueRedmine_checkResponseCode_expect201(){

        request = given()  //crear objeto
                .contentType("application/json");

        response = request
                .when()
                .header("X-Redmine-API-Key", "475338c5e8fe95ef9f9c1cae8cb69610e2d413ac")
                .body("{\n" +
                        " \"issue\":{\n" +
                        " \t\"project_id\": 3,\n" +
                        " \t\"subject\": \"Mi primer issue creado desde RestAssured con JSON\",\n" +
                        " \t\"description\": \"As an admin user\",\n" +
                        " \t\"tracker_id\": 1,\n" +
                        " \t\"status_id\": 1,\n" +
                        " \t\"priority_id\": 1\n" +
                        " }\n" +
                        "}\n")
                .post("http://192.168.0.5:8081/issues.json");

        response.then()
                .log()
                .all()
                .assertThat()
                .statusCode(201);

    }
}

