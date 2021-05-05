import io.restassured.response.Response;
import org.junit.Test;

public class Lab5 {

    @Test
    public void getConcept() {
        Response r = Methods.getConceptById("lxg:350231317898");
        r.then().statusCode(200);
    }

    @Test
    public void getConceptNotFound() {
        Response r = Methods.getConceptById("1");
        r.then().statusCode(404);
    }

    @Test
    public void getAspirinData() {
        Response r = Methods.getDataByLabel("aspirin");
        r.then().statusCode(200);
    }

    @Test
    public void extractEntities() {
        Response r = Methods.postEntity();
        r.then().statusCode(200);
    }

    @Test
    public void highlight() {
        Response r = Methods.postEntity2();
        r.then().statusCode(200);
    }

}
