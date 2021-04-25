package stepdefs.api;

import io.cucumber.java.en.And;
import stepdefs.World;

public class RequestApiSteps {
    private final World world;

    public RequestApiSteps(World world) {
        this.world = world;
    }

    @And("I use the {string} parameter with value {string}")
    public void i_use_the_parameter_with_value(String parameterName, String parameterValue) {
        world.request = world.request.param(parameterName, parameterValue);
    }
}
