Feature: Place APIs validation

  @AddPlace
  Scenario Outline: Validate Add Place API functionality
    Given add place payLoad with "<name>" "<language>" "<address>"
    When  call "addPlaceAPI" with "POST" http request
    Then  called resource is with "<statusCode>"
    And   "<property>" in response body is "<propValue>"
    And   validate place_Id created maps to "<name>" using "getPlaceAPI"
    Examples:
      | name  | language | address | statusCode | property | propValue |
      | Udemy | English  | Home    | 200        | status   | OK        |

  @DeletePlace
  Scenario Outline: Validate Delete Place API functionality
    Given delete place payLoad
    When  call "deletePlaceAPI" with "DELETE" http request
    Then  called resource is with "<statusCode>"
    And   "<property>" in response body is "<propValue>"
    Examples:
      | statusCode | property | propValue |
      | 200        | status   | OK        |

  @E2EPlace
  Scenario Outline: Validate All Place API functionality
    Given add place payLoad with "<name>" "<language>" "<address>"
    When  call "addPlaceAPI" with "POST" http request
    Then  called resource is with "<statusCode>"
    And   "<property>" in response body is "<propValue>"
    And   validate place_Id created maps to "<name>" using "getPlaceAPI"
    Given delete place payLoad
    When  call "deletePlaceAPI" with "DELETE" http request
    Then  called resource is with "<statusCode>"
    And   "<property>" in response body is "<propValue>"
    Examples:
      | name  | language | address | statusCode | property | propValue |
      | Udemy | English  | Home    | 200        | status   | OK        |
