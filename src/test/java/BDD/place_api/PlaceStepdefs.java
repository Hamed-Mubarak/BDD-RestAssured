package BDD.place_api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import BDD.resources.PlaceTestData;
import BDD.resources.Utils;
import static io.restassured.RestAssured.*;

public class PlaceStepdefs extends Utils {
    RequestSpecification req;
    Response resp;
    String stringResp;
    PlaceTestData data = new PlaceTestData();

    @Given("add place payLoad")
    public void addPlacePayLoad() {
        req = given().spec(addPlaceReqSpec()).body(data.addPlacePayLoad());
    }

    @When("call addPlaceAPI with post http request")
    public void callAddPlaceAPIWithPostHttpRequest() {
        resp = req.when().post("maps/api/place/add/json")
                .then().spec(addPlaceRespSpec()).extract().response();
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
