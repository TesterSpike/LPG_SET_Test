@Cities
Feature: City API functional tests

  Scenario: As a user I am able to view all cities
    When I GET all cities
    Then I will get a 200 status response
    And I will view a list of cities