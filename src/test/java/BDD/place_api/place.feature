Feature: Place APIs validation

  Scenario: Validate Add Place API
    Given add place payLoad
    When  call addPlaceAPI with post http request
    Then  called addPlaceAPI is success with status code
    And   status in response body is OK