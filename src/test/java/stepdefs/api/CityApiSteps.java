package stepdefs.api;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import stepdefs.World;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class CityApiSteps {
    private final World world;
    private final String citysUrl = "https://my-json-server.typicode.com/leisurepassgroup/SDET-interview/citys";

    public CityApiSteps(World world) {
        this.world = world;
    }

    @When("I GET all cities")
    public void i_get_all_cities() {
        world.response = RestAssured.with().log().uri().accept(ContentType.JSON).get(citysUrl);
    }

    @When("I will view a list of cities")
    public void i_will_view_a_list_of_cities() {
        JsonPath json = world.response.getBody().jsonPath();
        List<HashMap<String, Object>> cities = json.getList("");
        Optional<HashMap<String, Object>> city = cities.stream().filter(temp -> temp.get("id").equals(1)).findFirst();
        city.ifPresent(testCity -> Assertions.assertEquals("New York", testCity.get("title"), "City title"));
    }
}
