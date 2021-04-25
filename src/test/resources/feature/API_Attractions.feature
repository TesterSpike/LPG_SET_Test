@CityAttraction
Feature: City Attraction function tests

  Scenario: Get no attractions for a city with no attractions
    Given I have a city with no attractions
    When I Get the attractions for the city
    Then I will get a 200 status response
    And I will get a list of attractions with no items

  Scenario: Get the attractions for a city with attractions
    Given I have a city with attractions
    When I Get the attractions for the city
    Then I will get a 200 status response
    And I will get a list of attractions with items

  Scenario: Get the attractions for a city by type and are sorted in descending order
    Given I have a city with attractions
    And I use the "type" parameter with value "MUSEUM"
    And I use the "_sort" parameter with value "rating"
    And I use the "_order" parameter with value "desc"
    When I Get the attractions for the city
    Then I will get a 200 status response
    And the attractions will only be "MUSEUM"
    And the attractions will be sorted by rating in "desc" order

  Scenario: Get the attractions for a city by type and are sorted in ascending order
    Given I have a city with attractions
    And I use the "type" parameter with value "MUSEUM"
    And I use the "_sort" parameter with value "rating"
    And I use the "_order" parameter with value "asc"
    When I Get the attractions for the city
    Then I will get a 200 status response
    And the attractions will only be "MUSEUM"
    And the attractions will be sorted by rating in "asc" order