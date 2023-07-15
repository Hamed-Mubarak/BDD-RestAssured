Feature: Place APIs validation

   Scenario Outline: Validate Add Place API
    Given add place payLoad with "<name>" "<language>" "<address>"
    When  call "addPlaceAPI" with "POST" http request
    Then  called addPlaceAPI is success with status code
    And   "status" in response body is "OK"
    And   validate place_Id created maps to "<name>" using "getPlaceAPI"
     Examples:
     | name    | language | address |
     | Udemy   | English  | Home    |
 #    | Mahmoud | French   | Work    |