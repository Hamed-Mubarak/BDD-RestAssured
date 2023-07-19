package BDD;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/BDD/place_api/place.feature",
        glue = "BDD",
        //here i told him add a plugin with json format and gave it a name and location
        plugin = "json: target/jsonReports/cucumber-report.json"
)
public class TestRunner extends AbstractTestNGCucumberTests{

}
