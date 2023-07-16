package BDD;

import BDD.place_api.PlaceStepdefs;
import io.cucumber.java.Before;

import java.io.IOException;

public class HooksHandler {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        PlaceStepdefs steps = new PlaceStepdefs();
        if(PlaceStepdefs.place_Id==null)
        {
            steps.addPlacePayLoadWith("Mubarak","Arabic","Cairo");
            steps.callWithHttpRequest("addPlaceAPI","POST");
            steps.validatePlace_IdCreatedMapsToUsing("Mubarak","getPlaceAPI");
        }

    }
}
