import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MyStepdefs {

    static String API_KEY = "";
    static String ID = "";

    private static RequestSpecification given() {
        return RestAssured.given()
                .header("Authorization",Config.API_KEY)
                .log().uri()
                .log().body()
                .baseUri(Config.BASE_URL)
                .contentType(ContentType.JSON);
    }

    public static Response getConceptById(String id) {
        return given()
                .pathParam("concept_id", id)
                .when()
                .get(Config.CONCEPT_ID);
    }

    @Given("Connection to API with {string}")
    public void connectionToAPIWithAPI_KEY(String API_KEY) {
        MyStepdefs.API_KEY = API_KEY;
    }

    @When("I get constraint by {string}")
    public void iGetConstraintByGet_request(String id) {
        MyStepdefs.ID = id;
    }

    @Then("I receive status code {int}")
    public void iReceiveStatusCode(int arg0) {
        Response r = getConceptById("lxg:350231317898");
        r.then().statusCode(arg0);
    }

}
