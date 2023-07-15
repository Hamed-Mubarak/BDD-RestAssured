package BDD.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Specs {

    RequestSpecification baseReq;
    ConfigProperties config = new ConfigProperties("/Applications/1-Rabbit/API Course/API_RestAssured/BDD_RestAssured/BDD_RestAssured/src/test/java/BDD/utils/baseUrl.properties");

    public Specs() throws IOException {
    }

    public RequestSpecification addPlaceReqSpec() throws IOException {
       PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
        baseReq = new RequestSpecBuilder()
                .setBaseUri(config.getConfigProp("URL"))
                .addQueryParam("key","qaclick123")
               .addFilter(RequestLoggingFilter.logRequestTo(log))
               .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON)
                .build();
        return baseReq;
    }
}

