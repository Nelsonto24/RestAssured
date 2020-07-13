package com.company.primerproyectorestassured.features;

import com.company.primerproyectorestassured.config.RedmineConfig;
import com.company.primerproyectorestassured.config.RedmineEndpoints;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class MyfirstTest extends RedmineConfig {

    @Test
    public void MyFirstTestWithEndPoint(){
        //get("issues.json") para que reconozca el get, se debe importar el static io.restassured.RestAssured.*
        get(RedmineEndpoints.ALL_REDMINE_ISSUES_JSON)  //viene a ser lo mismo que la línea anterior, ya que en la interfase se declara la igualdad
        .then().log().all();
    }
}
