package stepdefs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class World {
    public Response response;
    public RequestSpecification request = RestAssured.with().log().uri();

}
