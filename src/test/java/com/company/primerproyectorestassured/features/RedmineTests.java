package com.company.primerproyectorestassured.features;

import com.company.primerproyectorestassured.config.RedmineConfig;
import com.company.primerproyectorestassured.config.RedmineEndpoints;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;

public class RedmineTests extends RedmineConfig {

    @Test
    public void createNewIssueByJson() {
        String issueBody = "{\n" +
                "  \"issue\": {\n" +
                "\t  \"project_id\": 1  ,\n" +
                "\t  \"subject\": \"Mi segundo issue creado desde RestAssured\",\n" +
                "\t  \"description\": \"As an admin user, I cannot create an user when...\",\n" +
                "\t  \"status_id\": 1,\n" +
                "\t  \"priority_id\": 1\n" +
                "\t}\n" +
                "}";

        given()
                .body(issueBody)
                .when()
                .post(RedmineEndpoints.ALL_REDMINE_ISSUES_JSON)
                .then() //el .log().all() se indica en la clase RedmineConfig
                .statusCode(201);
    }

    @Test
    public void createNewIssueByXML() {
        String issueBodyXml = "<issue>\n" +
                "    <subject>Mi issue creado desde RestAssured con XML.</subject>\n" +
                "    <description>As an admin user, I cannot create an user when xml...</description>\n" +
                "    <project_id>1</project_id>\n" +
                "    <status_id>1</status_id>\n" +
                "    <priority_id>1</priority_id>\n" +
                "</issue>";

        given()
                .body(issueBodyXml)
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml")
                .when()
                .post(RedmineEndpoints.ALL_REDMINE_ISSUES_XML)
                .then()
                .statusCode(201);
    }

    @Test
    public void updateIssueJSON() {

        String issueBodyJson = "{\n" +
                "    \"issue\": {\n" +
                "        \"subject\": \"Mi issue JSON actualizado desde RestAssured.\",\n" +
                "        \"status_id\": 2\n" +
                "    }\n" +
                "}";

        given()
                .body(issueBodyJson)
                .when()
                .put("issues/3.json")
                .then()
                .statusCode(204);
    }

    @Test
    public void updateIssueXML() {

        String issueBodyXML = "<?xml version=\"1.0\"?>\n" +
                "<issue>\n" +
                "  <subject>Mi issue XML actualizado desde RestAssured</subject>\n" +
                "  <notes>The subject was changed</notes>\n" +
                "</issue>";

        given()
                .body(issueBodyXML)
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml")
                .when()
                .put("issues/1.xml")
                .then()
                .statusCode(204);
    }

    @Test
    public void deleteIssueJSON(){
        given()
                .when()
                .delete("issues/1.json")
                .then()
                .statusCode(204);
    }

    @Test
    public void deleteProjectJSON(){
        given()
                .when()
                .delete("projects/4.json")
                .then()
                .statusCode(204);
    }

    @Test
    public void deleteIssueXML(){
        given()
                .when()
                .delete("issues/1.json")
                .then()
                .statusCode(204);
    }

    @Test
    public void deleteProjectXML(){
        given()
                .when()
                .delete("projects/3.xml")
                .then()
                .statusCode(204);
    }

    @Test
    public void getSingleIssueJSON(){  // es lo mismo que hacer desde postman GET http://192.168.0.5:8081/issues/4.json
        given()
                .pathParam("id", 4)
                .when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUE_JSON)
                .then()
                .statusCode(200);
    }

    @Test
    public void getSingleIssueXML(){  // es lo mismo que hacer desde postman GET http://192.168.0.5:8081/issues/4.xml
        given()
                .pathParam("id", 4)
                .when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUE_XML)
                .then()
                .statusCode(200);
    }

    /*
    @Test
    public void getProjectWhitCodeFromPostman() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.0.7:8081/projects/1.json")
                .method("GET", null)
                .addHeader("Cookie", "_redmine_session=N2kreU10d2pBQlZZcnp5TjEzNVlJWTNDcTVuSXg5dFpKMUhpeFM0aUNZUnU3Nyt4bjhBZDlrcE94QmlTcSszME11bURqbXNvR0F5OGczSFh1RUVMVno4Z0hqU0ltenBMMUl3NEsyY3RYVHBnSGhDS0MybzN5bDREamhCeUxFYm03ZE53N2JSZ3JvTlA1cEJiRWlwS24wdnVybzhGblR2dmx2NTNMSTNXQjVWYStOczl3cGZEUVNUcEp0RDZJdmFqZXZDYTgzeVF3Wmdkdm9IaEdWblVkZz09LS0vRTBGTGdnRUczanB6VFlIVGNWeWt3PT0%3D--bba895f7ef5b69aa3f96342e91beec59af55ab54")
                .build();
        Response response = client.newCall(request).execute();
    }*/
}
