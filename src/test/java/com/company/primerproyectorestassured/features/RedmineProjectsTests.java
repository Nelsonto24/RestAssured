package com.company.primerproyectorestassured.features;

import com.company.primerproyectorestassured.config.RedmineConfig;
import com.company.primerproyectorestassured.config.RedmineEndpoints;
import com.company.projectrestassured.dataentities.Entity;
import com.company.projectrestassured.dataentities.Project;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;

import static io.restassured.RestAssured.given;

public class RedmineProjectsTests extends RedmineConfig {

    @Test
    public void testProjectValidateSchemaJSON(){  //si no muestra error, es porque pasó exitosamente la validación del esquema
        given()
                .pathParam("id", 1)
                .when()
                .get(RedmineEndpoints.SINGLE_REDMINE_PROJECT_JSON)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("project_schema.json"));
    }  //si se desea comprobar la validación con resultado incorrecto, se debe cambiar un tipo de variable(properties->id->type="String") en el archivo project_shema.json

    @Test
    public void testIssueValidateSchemaJSON(){  //mismo caso que el método anterior pero golpeando al issue
        given()
                .pathParam("id", 4)
                .when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUE_JSON)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("project_schemaIssue.json"));
    }

    @Test
    public void testProjectValidateSchemaXML(){
        given()
                .pathParam("id", 1)
                .header("Content-Type","application/xml")
                .header("Accept","application/xml")
                .when()
                .get(RedmineEndpoints.SINGLE_REDMINE_PROJECT_XML)
                .then()
                .statusCode(200)
                .body(matchesXsdInClasspath("project_schema.xsd"));
    }

    @Test
    public void testIssueValidateSchemaXML(){  //mismo caso que el método anterior pero golpeando al issue
        given()
                .pathParam("id", 1)
                .header("Content-Type","application/xml")
                .header("Accept","application/xml")
                .when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUE_XML)
                .then()
                .statusCode(200)
                .body(matchesXsdInClasspath("project_schemaIssue.xsd"));
    }

    @Test
    public void testProjectSerializationJSON(){  //llevar el json a una clase java y enviarlo como un objeto java en el request
        /**Ejemplo
        {
            "project": {
                    "name": "Redmine Project Sw Testing Dragon",
                    "identifier": "redmineprojegon",
                    "description": "Redmine Project for api testing",
                    "is_public": true
        }*/
        Project project = new Project("Proyecto creado desde RestAssured con JSON", //crear objeto
                "serializacion",
                "Proyecto prueba con serialización", true);

        Entity entity = new Entity(project);  //posibilita considerar el nivel project de la estructura de la petición (request)

        given()
                .body(entity)
                .when()
                .post(RedmineEndpoints.ALL_REDMINE_PROJECTS_JSON)
                .then()
                .statusCode(201);
        }

     @Test
     public void testProjectSerializationXML(){

         Project proyecto = new Project("Proyecto con RestAssured XML", //crear objeto
                 "serializar",
                 "Proyecto con serialización", true);

         Entity entity = new Entity(proyecto);

         given()
                 .body(entity)
                 .when()
                 .post(RedmineEndpoints.ALL_REDMINE_PROJECTS_XML)
                 .then()
                 .statusCode(201);
     }

    @Test
    public void testProjectDeSerializationJSON(){  //se obtiene la respuesta del response en un json y se transforma en objeto java

        Response response =
                given()
                        .pathParam("id", 11)
                        .when()
                        .get(RedmineEndpoints.SINGLE_REDMINE_PROJECT_JSON);

        response.then().statusCode(200);
        Entity entity = response.getBody().as(Entity.class);
        Project project = entity.getProject();
        System.out.println(project.toString());  //implementar el método toString en la clase Project para mejorar la impresión
    }

    //Pendiente codificar método deserialización con xml
    @Test
    public void testProjectDeSerializationXML(){  //se obtiene la respuesta del response en un json y se transforma en objeto java

        Response response =
                given()
                        .pathParam("id", 11)
                        .when()
                        .get(RedmineEndpoints.SINGLE_REDMINE_PROJECT_XML);

        response.then().statusCode(200);
        Entity entidad = response.getBody().as(Entity.class);
        Project proyecto = entidad.getProject();
        System.out.println(proyecto.toString());  //implementar el método toString en la clase Project para mejorar la impresión
    }
}

