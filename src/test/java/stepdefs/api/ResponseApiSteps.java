package stepdefs.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import stepdefs.World;

public class ResponseApiSteps {
    private final World world;

    public ResponseApiSteps(World world) {
        this.world = world;
    }

    @When("I will get a {int} status response")
    public void i_will_get_a_status_response(Integer statusCode) {
        Assertions.assertEquals(statusCode, world.response.statusCode(), "response status code");
    }
}
