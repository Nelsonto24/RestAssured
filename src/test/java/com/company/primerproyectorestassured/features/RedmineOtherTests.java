package com.company.primerproyectorestassured.features;

import com.company.primerproyectorestassured.config.RedmineConfig;
import com.company.primerproyectorestassured.config.RedmineEndpoints;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RedmineOtherTests extends RedmineConfig {
    @Test
    public void getIssueStarDate() {  //compara el valor del campo star_date del issue
        given()
                .pathParam("id", 4).
                when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUE_JSON).
                then()
                .statusCode(200)
                .body("issue.star_date", equalTo("2020-04-25"));
    }

    @Test
    public void getFirstIssueSubject() { //realiza comparación del subject
        given().
                when()
                .get(RedmineEndpoints.ALL_REDMINE_ISSUES_JSON).
                then()
                .statusCode(200)  //código http de respuesta, primero se debe de realizar ésto antes de la comparación
                .body("issues[3].subject", equalTo("Mi issue actualizado desde RestAssured."));  //contenido de la respuesta del servicio
    }

    @Test
    public void getAllIssueData() {   //1era forma de obtener la data del servicio
        String responseBody = get("issues/4.json").asString();
        System.out.println(responseBody);
    }

    @Test
    public void getAllIssueDataCheck() {  //2da forma de obtener la data del servicio

        Response response =
                given()
                        .when()
                        .get("issues/4.json").
                        then()
                        .statusCode(200)
                        .extract().response();

        String jsonResponseAsString = response.asString();
        System.out.println(jsonResponseAsString);
    }

    @Test
    public void extraerHeader() {   //1era forma de extraer la cabecera del servicio con getHeader
        Response response =
                given()
                        .when()
                        .get("issues/4.json")
                        .then()
                        .statusCode(200)
                        .extract().response();
        Headers header = response.getHeaders();
        String contentType = response.getHeader("Content-Type");
        System.out.println(contentType);
    }

    @Test
    public void extractFirstIssueSubject() {   //2da forma de extraer con jsonPath
        String firstIssueSubject = get(RedmineEndpoints.ALL_REDMINE_ISSUES_JSON).jsonPath().getString("issues[3].subject");
        System.out.println(firstIssueSubject);
    }

    @Test
    public void extractAllIssueSubjects() {  //devuelve una lista de todos los subjects

        Response response =
                given()
                        .when()
                        .get(RedmineEndpoints.ALL_REDMINE_ISSUES_JSON)
                        .then()
                        .extract().response();

        List<String> subjects = response.path("issues.subject"); //.path devuelve los subjects de los issues

        for (String subject : subjects) {
            System.out.println(subject);
        }
    }
}
