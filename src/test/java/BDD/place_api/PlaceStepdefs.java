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
    public static String place_Id;

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
        {
            if(resource.equalsIgnoreCase("addPlaceAPI"))
            resp = req.when().post(resourceAPI.getResource());
            else if (resource.equalsIgnoreCase("updatePlaceAPI"))
                resp = req.when().post(resourceAPI.getResource());
        }
        else if (method.equalsIgnoreCase("GET"))
            resp = req.when().get(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("DELETE"))
            resp = req.when().delete(resourceAPI.getResource());
    }

    @Then("called resource is with {string}")
    public void calledResourceIsWith(String statusCode) {
        Assert.assertEquals(String.valueOf(resp.getStatusCode()),statusCode);
    }

    @Then("{string} in response body is {string}")
    public void inResponseBodyIs(String property,String propValue) {
        Assert.assertEquals(getJsonPath(resp,property),propValue);
    }

    @Then("validate place_Id created maps to {string} using {string}")
    public void validatePlace_IdCreatedMapsToUsing(String name, String resource) throws IOException {
        place_Id=getJsonPath(resp,"place_id");
        req = given().spec(addPlaceReqSpec()).queryParam("place_id",place_Id);
        callWithHttpRequest(resource,"GET");
        Assert.assertEquals(getJsonPath(resp,"name"),name);
    }

    @Given("delete place payLoad")
    public void deletePlacePayLoad() throws IOException {
        req = given().spec(addPlaceReqSpec())
                .body(payload.deletePlacePayload(place_Id));
    }
}
