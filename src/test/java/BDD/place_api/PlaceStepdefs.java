package BDD.place_api;

import BDD.utils.PlaceTestData;
import BDD.utils.Specs;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import java.io.IOException;

import static io.restassured.RestAssured.*;

public class PlaceStepdefs extends Specs {
    RequestSpecification req;
    Response resp;
    String stringResp;
    PlaceTestData payload = new PlaceTestData();

    public PlaceStepdefs() throws IOException {
    }

    @Given("add place payLoad")
    public void addPlacePayLoad() throws IOException {
        req = given().spec(addPlaceReqSpec()).body(payload.addPlacePayLoad());
    }

    @When("call addPlaceAPI with post http request")
    public void callAddPlaceAPIWithPostHttpRequest() {
        resp = req.when().post("maps/api/place/add/json")
                .then().extract().response();
    }

    @Then("called addPlaceAPI is success with status code")
    public void calledAddPlaceAPIIsSuccessWithStatusCode() {
        Assert.assertEquals(resp.getStatusCode(),200);
    }

    @Then("status in response body is OK")
    public void statusInResponseBodyIsOK() {
        stringResp = resp.asString();
        JsonPath js = new JsonPath(stringResp);
        Assert.assertEquals(js.getString("status"),"OK");
        System.out.println( stringResp);
    }
}
