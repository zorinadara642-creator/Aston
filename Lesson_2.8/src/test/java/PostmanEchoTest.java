import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PostmanEchoTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    void testGetRequest() {
        Response response = given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get");

        assertEquals(200, response.getStatusCode());

        JsonPath json = response.jsonPath();
        assertEquals("bar1", json.getString("args.foo1"));
        assertEquals("bar2", json.getString("args.foo2"));
        assertEquals("https://postman-echo.com/get?foo1=bar1&foo2=bar2", json.getString("url"));
    }

    @Test
    void testPostRawText() {
        String rawText = "This is expected to be sent back as part of response body.";

        Response response = given()
                .contentType("text/plain")
                .body(rawText)
                .when()
                .post("/post");

        assertEquals(200, response.getStatusCode());

        JsonPath json = response.jsonPath();
        assertEquals(rawText, json.getString("data"));
        assertEquals("https://postman-echo.com/post", json.getString("url"));
    }

    @Test
    void testPostFromData() {
        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("key1", "value1")
                .formParam("key2", "value2")
                .when()
                .post("/post");

        assertEquals(200, response.getStatusCode());

        JsonPath json = response.jsonPath();
        assertEquals("value1", json.getString("form.key1"));
        assertEquals("value2", json.getString("form.key2"));
    }

    @Test
    void testPutRequest() {
        String requestBody = "{\"updated\": true, \"id\": 1}";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("/put");

        assertEquals(200, response.getStatusCode());

        JsonPath json = response.jsonPath();
        assertEquals(true, json.getBoolean("json.updated"));
        assertEquals(1, json.getInt("json.id"));
        assertEquals("https://postman-echo.com/put", json.getString("url"));
    }

    @Test
    void testPatchRequest() {
        String requestBody = "{\"status\": \"active\"}";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch("/patch");

        assertEquals(200, response.getStatusCode());

        JsonPath json = response.jsonPath();
        assertEquals("active", json.getString("json.status"));
        assertEquals("https://postman-echo.com/patch", json.getString("url"));
    }

    @Test
    void testDeleteRequest() {
        String requestBody = "{\"id\": 42}";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .delete("/delete");

        assertEquals(200, response.getStatusCode());

        JsonPath json = response.jsonPath();
        assertEquals(42, json.getInt("json.id"));
        assertEquals("https://postman-echo.com/delete", json.getString("url"));
    }

}
