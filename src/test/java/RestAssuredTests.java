import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Simple REST Assured tests using the public JSONPlaceholder API.
 * Dependency (Maven):
 *   <dependency>
 *     <groupId>io.rest-assured</groupId>
 *     <artifactId>rest-assured</artifactId>
 *     <version>5.4.0</version>
 *     <scope>test</scope>
 *   </dependency>
 */
public class RestAssuredTests {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    // ---------------------------------------------------------------
    // Test 1: GET – Verify a single post returns 200 and correct data
    // ---------------------------------------------------------------
    @Test
    void getPostById_shouldReturn200WithCorrectId() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/posts/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id",     equalTo(1))
                .body("userId", equalTo(1))
                .body("title",  not(emptyOrNullString()));
    }

    // ---------------------------------------------------------------
    // Test 2: GET – Verify list endpoint returns a non-empty array
    // ---------------------------------------------------------------
    @Test
    void getAllPosts_shouldReturn200AndNonEmptyList() {
        given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$",    hasSize(greaterThan(0)))   // array is not empty
                .body("[0].id", notNullValue());          // first item has an id
    }

    // ---------------------------------------------------------------
    // Test 3: POST – Create a new resource and verify the response
    // ---------------------------------------------------------------
    @Test
    void createPost_shouldReturn201WithGeneratedId() {
        String requestBody = """
                {
                  "title":  "REST Assured Test Post",
                  "body":   "This post was created by a test.",
                  "userId": 1
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)                              // 201 Created
                .body("title",  equalTo("REST Assured Test Post"))
                .body("userId", equalTo(1))
                .body("id",     notNullValue());              // server assigned an id
    }

    // ---------------------------------------------------------------
    // Test 4: DELETE – Delete a resource and verify 200 OK
    // ---------------------------------------------------------------
    @Test
    void deletePost_shouldReturn200() {
        given()
                .pathParam("id", 1)
                .when()
                .delete("/posts/{id}")
                .then()
                .statusCode(200);   // JSONPlaceholder returns 200 (not 204) on DELETE
    }
}