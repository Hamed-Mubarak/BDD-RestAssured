package BDD.place_api;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/BDD/place_api/place.feature",
        glue = "BDD"
)
public class PlaceRunner extends AbstractTestNGCucumberTests{

}
