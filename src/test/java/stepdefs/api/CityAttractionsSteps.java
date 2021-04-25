package stepdefs.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import stepdefs.World;

import java.util.HashMap;
import java.util.List;

public class CityAttractionsSteps {
    private final World world;
    private final String attractionUrl = "https://my-json-server.typicode.com/leisurepassgroup/SDET-interview/attractions";
    private int cityId;

    public CityAttractionsSteps(World world) {
        this.world = world;
    }

    @Given("I have a city with attractions")
    public void i_have_a_city_with_attractions() {
        cityId = 1;
    }

    @Given("I have a city with no attractions")
    public void i_have_a_city_with_no_attractions() {
        cityId = 2;
    }

    @When("I Get the attractions for the city")
    public void i_get_the_attractions_for_the_city() {
        world.response = world.request
                .accept(ContentType.JSON)
                .param("cityId", cityId)
                .get(attractionUrl);
    }

    @And("I will get a list of attractions with no items")
    public void i_will_get_a_list_of_attractions_with_no_items() {
        JsonPath json = world.response.getBody().jsonPath();
        Assertions.assertEquals(0, json.getList("").size(), "Count of attractions");
    }

    @And("I will get a list of attractions with items")
    public void i_will_get_a_list_of_attractions_with_items() {
        JsonPath json = world.response.getBody().jsonPath();
        Assertions.assertNotEquals(0, json.getList("").size(), "Count of attractions");
    }

    @And("the attractions will only be {string}")
    public void the_attractions_will_only_be_type(String type) {
        List<HashMap<String, Object>> attractions = world.response.getBody().jsonPath().getList("");
        for (HashMap<String, Object> attraction : attractions) {
            Assertions.assertEquals(attraction.get("type"), type, "Attraction type");
        }
    }

    @And("the attractions will be sorted by rating in {string} order")
    public void the_attractions_will_be_sorted_by_rating_in_specific_order(String sortOrder) {
        List<HashMap<String, Object>> attractions = world.response.getBody().jsonPath().getList("");
        float lastRating;
        if (sortOrder.equals("desc")) {
            lastRating = (float) 99.99;
        } else {
            lastRating = (float) -1.00;
        }

        for (HashMap<String, Object> attraction : attractions) {
            float currentRating = Float.parseFloat(attraction.get("rating").toString());
            String attractionName = (String) attraction.get("title");
            String errorMessage = "Rating of " + currentRating + " for " + attractionName;
            if(sortOrder.equals("desc")) {
                Assertions.assertTrue(currentRating <= lastRating, errorMessage + " should be lower than " + lastRating);
            } else {
                Assertions.assertTrue(currentRating >= lastRating, errorMessage + " should be higher than " + lastRating);
            }
            lastRating = currentRating;

        }
    }
}

