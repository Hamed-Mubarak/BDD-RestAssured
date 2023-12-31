package BDD.utils;

import pojo.AddPlaceBody;
import pojo.LocationReq;

import java.util.ArrayList;
import java.util.List;

public class PlaceTestData {

    public AddPlaceBody addPlacePayLoad(String name, String language, String address){

            AddPlaceBody a = new AddPlaceBody();
            a.setAccuracy(50);
            a.setAddress(address);
            a.setLanguage(language);
            a.setName(name);
            a.setPhone_number("01090074666");
            a.setWebsite("https://rahulshettyacademy.com");
            List<String> myTypesList = new ArrayList<>();
            myTypesList.add("Shoe Park");
            myTypesList.add("shop");
            a.setTypes(myTypesList);
            // as we expect Location class, so we have to take an object from location class first and pass it
            LocationReq l = new LocationReq();
            l.setLat(29.9891544);
            l.setLng(31.3063091);
            a.setLocation(l);
            return a;
    }
    public String deletePlacePayload(String place_Id){
            return "{\r\n    \"place_id\":\""+ place_Id +"\"\r\n}";
    }
}
