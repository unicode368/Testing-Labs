import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Methods {

    public static Response getDataByLabel(String label) {
        return given()
                .pathParam("label", label)
                .when()
                .get(Config.SEARCH);
    }

    public static Response getConceptById(String id) {
        return given()
                .pathParam("concept_id", id)
                .when()
                .get(Config.CONCEPT_ID);
    }

    public static Response postEntity() {
        return given()
                .body(Config.JSON_BODY)
                .when()
                .post(Config.ENTITY);
    }

    public static Response postEntity2() {
        return given()
                .body(Config.JSON_BODY)
                .when()
                .post(Config.HIGHLIGHT);
    }

    private static RequestSpecification given() {
        return RestAssured.given()
                .header("Authorization",Config.API_KEY)
                .log().uri()
                .log().body()
                .baseUri(Config.BASE_URL)
                .contentType(ContentType.JSON);
    }
}
