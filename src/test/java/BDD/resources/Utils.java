package BDD.resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
    RequestSpecification baseReq;
    ResponseSpecification baseResp;
    public RequestSpecification addPlaceReqSpec(){
        baseReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON)
                .build();
        return baseReq;
    }

    public ResponseSpecification addPlaceRespSpec(){
        baseResp = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
        return baseResp;
    }
}
