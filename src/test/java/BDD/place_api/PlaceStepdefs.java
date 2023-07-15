package BDD.place_api;

import BDD.utils.APIResources;
import BDD.utils.PlaceTestData;
import BDD.utils.Specs;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import java.io.IOException;

import static io.restassured.RestAssured.*;

public class PlaceStepdefs extends Specs {
    RequestSpecification req;
    Response resp;
    String stringResp;
    String place_Id;

    PlaceTestData payload = new PlaceTestData();

    public PlaceStepdefs() throws IOException {
    }

    @Given("add place payLoad with {string} {string} {string}")
    public void addPlacePayLoadWith(String name, String language, String address) throws IOException {
        req = given().spec(addPlaceReqSpec())
                .body(payload.addPlacePayLoad(name,language,address));
    }

    @When("call {string} with {string} http request")
    public void callWithHttpRequest(String resource, String method) {
        APIResources resourceAPI = APIResources.valueOf(resource);
        if(method.equalsIgnoreCase("POST"))
        resp = req.when().post(resourceAPI.getResource())
                .then().extract().response();
        else if (method.equalsIgnoreCase("GET"))
            resp = req.when().post(resourceAPI.getResource())
                    .then().extract().response();
        else if (method.equalsIgnoreCase("DELETE"))
            resp = req.when().delete(resourceAPI.getResource())
                    .then().extract().response();

    }

    @Then("called addPlaceAPI is success with status code")
    public void calledAddPlaceAPIIsSuccessWithStatusCode() {
        Assert.assertEquals(resp.getStatusCode(),200);
    }

    @Then("{string} in response body is {string}")
    public void inResponseBodyIs(String keyValue,String expectedValue) {
        Assert.assertEquals(getJsonPath(resp,keyValue),expectedValue);
    }

    @Then("validate place_Id created maps to {string} using {string}")
    public void validatePlace_IdCreatedMapsToUsing(String name, String resource) throws IOException {
        place_Id=getJsonPath(resp,"place_id");
        req = given().spec(addPlaceReqSpec()).queryParam("place_id",place_Id);
        callWithHttpRequest(resource,"GET");
        String respName= place_Id=getJsonPath(resp,"name");
        Assert.assertEquals(name,respName);
    }
}
